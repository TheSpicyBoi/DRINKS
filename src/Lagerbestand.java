public class Lagerbestand {
    private Getraenke getraenk;
    private int anzahlEinzelflaschen;
    private int anzahlKaesten;

    public Lagerbestand(Getraenke getraenk, int anzahlEinzelflaschen) {
        this.getraenk = getraenk;
        this.anzahlEinzelflaschen = anzahlEinzelflaschen;
        updateLagerstand();
    }

    public Getraenke getGetraenk() {
        return getraenk;
    }

    public void setGetraenk(Getraenke getraenk) {
        this.getraenk = getraenk;
    }

    public int getAnzahlEinzelflaschen() {
        return anzahlEinzelflaschen;
    }

    public void setAnzahlEinzelflaschen(int anzahlEinzelflaschen) {
        this.anzahlEinzelflaschen = anzahlEinzelflaschen;
        updateLagerstand();
    }

    public int getAnzahlKaesten() {
        return anzahlKaesten;
    }

    // Berechnung der Anzahl der Kästen basierend auf den Einzelflaschen und Flaschen pro Kasten
    public void updateLagerstand() {
        int flaschenProKasten = getraenk.getFlaschenProKasten();
        int restflaschen = anzahlEinzelflaschen % flaschenProKasten;
        anzahlKaesten = (anzahlEinzelflaschen-restflaschen)/flaschenProKasten;
    }
}