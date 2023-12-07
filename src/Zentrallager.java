/**
 * Die Klasse Zentrallager repräsentiert ein Zentrallager für Getränke.
 */
public class Zentrallager extends Standort{

    public Zentrallager(String name) {
        super(name);
    }


    /*public void verschicke(Standort standort, String getraenksorteName, int anzahlKaesten) {
        GetraenkeSorte gesuchtesGetraenk = null;
        boolean found = false;

        for (Lagerbestand lager : lagerbestand) {
            if (lager.getGetraenk().getName().equals(getraenksorteName)) {
                if (lager.getAnzahlKaesten() > anzahlKaesten){
                    gesuchtesGetraenk = lager.getGetraenk();
                } else System.out.println("so viele kästen gibt es nicht, bitte zuerst Nachbestellen!!!");

                break;
            }
        }

        if (gesuchtesGetraenk != null && !standort.name.equals("zentrallager")) {
            Lagerbestand neuerLagerbestand = new Lagerbestand(gesuchtesGetraenk, anzahlKaesten * gesuchtesGetraenk.getFlaschenProKasten());
            for(Lagerbestand standortlager : standort.lagerbestand){
                if (standortlager.getGetraenk().getName().equals(neuerLagerbestand.getGetraenk().getName())){
                    found = true;
                    standortlager.setAnzahlEinzelflaschen(anzahlKaesten*standortlager.getGetraenk().getFlaschenProKasten());
                }
            }

            for (Lagerbestand lager : lagerbestand) {
                if (lager.getGetraenk() == gesuchtesGetraenk) {
                    lager.setAnzahlEinzelflaschen(lager.getAnzahlEinzelflaschen() + neuerLagerbestand.getAnzahlEinzelflaschen());
                    lager.updateLagerstand();
                    break;
                }
            }
        } else System.out.println("Das gesuchte Getränk ist nicht im Zentrallager verfügbar.");
    }*/

    /**
     * Schickt Getränke an einen bestimmten Standort nach, wenn der Bestand dort zu niedrig ist.
     * @param standort      Der Zielstandort, an den die Getränke nachgeschickt werden.
     */
    public void nachschicken(Standort standort) {
        GetraenkeSorte gesuchtesGetraenk = null;

        for (Lagerbestand lager : standort.lagerbestand) {
            if (lager.getAnzahlKaesten()<lager.getGetraenk().getstandortmax(standort)) {
                int anzahl = lager.getGetraenk().getstandortmax(standort)- lager.getAnzahlKaesten();
                verschiebe(standort,lager.getGetraenk().getName(),anzahl);
            }
        }
    }

    public void nachbestellen(){
        for (Lagerbestand lagerbestand: this.lagerbestand){
            lagerbestand.setAnzahlEinzelflaschen(lagerbestand.getGetraenk().getstandortmax(this)*lagerbestand.getGetraenk().getFlaschenProKasten());
            lagerbestand.updateLagerstand();
        }
    }
}