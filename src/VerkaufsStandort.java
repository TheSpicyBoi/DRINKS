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
    public void verkaufen(String getraenksorteName, int verkauftEinzelflaschen) {
        if(verkauftEinzelflaschen <= 0){
            System.out.println("Ungülltige Verkaufszahl");
            return;
        }


        boolean gefunden = false;
        for (Lagerbestand lager : lagerbestaende) {
            if (lager.getGetraenk().getName().equals(getraenksorteName)) {
                int aktuelleEinzelflaschen = lager.getAnzahlEinzelflaschen();
                if (verkauftEinzelflaschen <= aktuelleEinzelflaschen) {
                    lager.setAnzahlEinzelflaschen(aktuelleEinzelflaschen - verkauftEinzelflaschen);
                    gefunden = true;
                    break;
                } else {
                    System.out.println("Nicht genügend Bestand für den Verkauf.");
                    gefunden = true;
                    return;
                }
            }
        }

        if (!gefunden) {
            System.out.println("Das gesuchte Getränk ist nicht im Lager dieses Standorts verfügbar.");
            return;
        }

        System.out.println("Getränke erfolgreich verkauft!");
    }

    public void lagerbestandAuffuellen(Zentrallager zentrallager){
        for (Lagerbestand lager : lagerbestaende) {
            Lagerbestand zentrallagerBestand = null;
            for(Lagerbestand z_lager : zentrallager.lagerbestaende){
                if(z_lager.getGetraenk().getName().equals(lager.getGetraenk().getName())) {
                    zentrallagerBestand = z_lager;
                    break;
                }
            }
            if(zentrallagerBestand == null)
                continue;

            int lagerRestFlaschen = lager.getAnzahlEinzelflaschen() % lager.getGetraenk().getFlaschenProKasten();
            int fehlendeKaesten = lager.getGetraenk().getstandortmax(this) - lager.getAnzahlKaesten() - ((lagerRestFlaschen > 0) ? 1:0);
            //garantiert, dass nicht mehr kästen nachgefüllt werden, als da sind
            int kaesten = Math.min(zentrallagerBestand.getAnzahlKaesten(),fehlendeKaesten);

            //Flaschen von Zentrallager auf Standort übertragen
            lager.setAnzahlEinzelflaschen(lager.getAnzahlEinzelflaschen() + kaesten * lager.getGetraenk().getFlaschenProKasten());
            zentrallagerBestand.setAnzahlEinzelflaschen(zentrallagerBestand.getAnzahlEinzelflaschen() - kaesten * zentrallagerBestand.getGetraenk().getFlaschenProKasten());
        }
    }
}
