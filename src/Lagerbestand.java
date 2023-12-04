/**
 * Die Klasse Lagerbestand repräsentiert den Bestand eines bestimmten Getränks in einem Lager.
 */
public class Lagerbestand {
    private Getraenke getraenk;
    private int anzahlEinzelflaschen;
    private int anzahlKaesten;

    /**
     * Konstruktor für die Klasse Lagerbestand.
     * @param getraenk Das Getränk, das im Lagerbestand enthalten ist.
     * @param anzahlEinzelflaschen Die Anzahl der Einzelflaschen des Getränks im Lager.
     */
    public Lagerbestand(Getraenke getraenk, int anzahlEinzelflaschen) {
        this.getraenk = getraenk;
        this.anzahlEinzelflaschen = anzahlEinzelflaschen;
        updateLagerstand();
    }

    /**
     * Gibt das Getränk zurück, das im Lagerbestand enthalten ist.
     * @return Das Getränk im Lagerbestand.
     */
    public Getraenke getGetraenk() {
        return getraenk;
    }

    /**
     * Setzt das Getränk im Lagerbestand.
     * @param getraenk Das zu setzende Getränk.
     */
    public void setGetraenk(Getraenke getraenk) {
        this.getraenk = getraenk;
    }

    /**
     * Gibt die Anzahl der Einzelflaschen des Getränks im Lager zurück.
     * @return Die Anzahl der Einzelflaschen im Lagerbestand.
     */
    public int getAnzahlEinzelflaschen() {
        return anzahlEinzelflaschen;
    }

    /**
     * Setzt die Anzahl der Einzelflaschen des Getränks im Lager.
     * Aktualisiert den Lagerstand nach der Änderung.
     * @param anzahlEinzelflaschen Die neue Anzahl der Einzelflaschen.
     */
    public void setAnzahlEinzelflaschen(int anzahlEinzelflaschen) {
        this.anzahlEinzelflaschen = anzahlEinzelflaschen;
        updateLagerstand();
    }

    /**
     * Gibt die Anzahl der Kästen des Getränks im Lager zurück.
     * @return Die Anzahl der Kästen im Lagerbestand.
     */
    public int getAnzahlKaesten() {
        return anzahlKaesten;
    }

    /**
     * Aktualisiert den Lagerstand basierend auf der aktuellen Anzahl der Einzelflaschen.
     */
    public void updateLagerstand() {
        int flaschenProKasten = getraenk.getFlaschenProKasten();
        int restflaschen = anzahlEinzelflaschen % flaschenProKasten;
        anzahlKaesten = (anzahlEinzelflaschen - restflaschen) / flaschenProKasten;
    }
}