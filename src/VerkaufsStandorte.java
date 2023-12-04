import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse Standort repräsentiert einen Lagerstandort für Getränke.
 */
public class VerkaufsStandorte extends Standort {
    public VerkaufsStandorte(String name) {
        super(name);
    }
    /**
     * Verkauft Einzelflaschen eines bestimmten Getränks aus dem Lagerbestand.
     * @param getraenksorteName Der Name der Getränksorte, die verkauft werden soll.
     * @param verkauftEinzelflaschen Die Anzahl der zu verkaufenden Einzelflaschen.
     */
    public void sachenVerkaufen(String getraenksorteName, int verkauftEinzelflaschen) {
        boolean gefunden = false;
        for (Lagerbestand lager : lagerbestand) {
            if (lager.getGetraenk().getName().equals(getraenksorteName)) {
                int aktuelleEinzelflaschen = lager.getAnzahlEinzelflaschen();
                if (verkauftEinzelflaschen <= aktuelleEinzelflaschen) {
                    lager.setAnzahlEinzelflaschen(aktuelleEinzelflaschen - verkauftEinzelflaschen);
                    gefunden = true;
                    break;
                } else {
                    System.out.println("Nicht genügend Bestand für den Verkauf.");
                    gefunden = true;
                    break;
                }
            }
        }

        if (!gefunden) {
            System.out.println("Das gesuchte Getränk ist nicht im Lager dieses Standorts verfügbar.");
        }
    }


    /**
     * Bestellt Getränke beim Zentrallager, wenn der Bestand unter den Sollwert fällt.
     * @param zentrallager Das Zentrallager, bei dem die Bestellung aufgegeben wird.
     */
    public void nachbestellen(Zentrallager zentrallager) {
        for (Lagerbestand lager : lagerbestand) {
            Getraenke getraenk = lager.getGetraenk();
            int aktuellerBestand = lager.getAnzahlEinzelflaschen();

            if (aktuellerBestand < getraenk.getSollLagerbestand()) {
                int zuBestellen = getraenk.getSollLagerbestand() - aktuellerBestand;
                int anzahlKaestenZuBestellen = (zuBestellen + getraenk.getFlaschenProKasten() - 1) / getraenk.getFlaschenProKasten();

                zentrallager.verschicke(this, getraenk.getName(), anzahlKaestenZuBestellen);
            }
        }
    }
}
