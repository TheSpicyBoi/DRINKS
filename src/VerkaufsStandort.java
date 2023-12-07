/**
 * Die Klasse Standort repräsentiert einen Lagerstandort für Getränke.
 */
public class VerkaufsStandort extends Standort {
    public VerkaufsStandort(String name) {
        super(name);
    }
    /**
     * Verkauft Einzelflaschen eines bestimmten Getränks aus dem Lagerbestand.
     * @param getraenksorteName Der Name der Getränksorte, die verkauft werden soll.
     * @param verkauftEinzelflaschen Die Anzahl der zu verkaufenden Einzelflaschen.
     */
    public void getraenkeVerkaufen(String getraenksorteName, int verkauftEinzelflaschen) {
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

    public void lagerbestandAuffuellen(Zentrallager zentrallager){
        for (Lagerbestand lager : lagerbestand) {
            Lagerbestand zentrallagerBestand = null;
            for(Lagerbestand z_lager : zentrallager.lagerbestand){
                if(z_lager.getGetraenk().getName().equals(lager.getGetraenk().getName())) {
                    zentrallagerBestand = z_lager;
                    break;
                }
            }
            int fehlendeKaesten = lager.getGetraenk().getstandortmax(this) - lager.getAnzahlKaesten();
            //garantiert, dass nicht mehr kästen nachgefüllt werden, als da sind
            int kaesten = Math.min(zentrallagerBestand.getAnzahlKaesten(),fehlendeKaesten);

            //Flaschen von Zentrallager auf Standort übertragen
            lager.setAnzahlEinzelflaschen(lager.getAnzahlEinzelflaschen() + kaesten * lager.getGetraenk().getFlaschenProKasten());
            zentrallagerBestand.setAnzahlEinzelflaschen(zentrallagerBestand.getAnzahlEinzelflaschen() - kaesten * zentrallagerBestand.getGetraenk().getFlaschenProKasten());
        }
    }


    /**
     * Bestellt Getränke beim Zentrallager, wenn der Bestand unter den Sollwert fällt.
     * @param zentrallager Das Zentrallager, bei dem die Bestellung aufgegeben wird.
     */
    public void nachbestellen(Zentrallager zentrallager) {
        for (Lagerbestand lager : lagerbestand) {
            GetraenkeSorte getraenk = lager.getGetraenk();
            int aktuellerBestand = lager.getAnzahlEinzelflaschen();

            if (aktuellerBestand < getraenk.getSollLagerbestand()) {
                int zuBestellen = getraenk.getSollLagerbestand() - aktuellerBestand;
                int anzahlKaestenZuBestellen = (zuBestellen + getraenk.getFlaschenProKasten() - 1) / getraenk.getFlaschenProKasten();

                zentrallager.verschiebe(this, getraenk.getName(), anzahlKaestenZuBestellen);
            }
        }
    }
}
