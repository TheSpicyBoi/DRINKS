/**
 * Die Klasse Zentrallager repräsentiert ein Zentrallager für Getränke.
 */
public class Zentrallager extends Standort{

    public Zentrallager(String name) {
        super(name);
    }

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