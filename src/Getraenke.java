public class Getraenke {
    private String name;
    private int flaschenProKasten;
    private int getSollLagerbestand;
    private String attribut;

    // Konstruktor
    public Getraenke(String name, int flaschenProKasten, int SollLagerbestandInKaesten,String attribut) {
        this.name = name;
        this.flaschenProKasten = flaschenProKasten;
        this.getSollLagerbestand = SollLagerbestandInKaesten;
        this.attribut = attribut;
    }

    // Getter und Setter für "name"
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter und Setter für "flaschenProKasten"
    public int getFlaschenProKasten() {
        return flaschenProKasten;
    }

    public void setFlaschenProKasten(int flaschenProKasten) {
        this.flaschenProKasten = flaschenProKasten;
    }

    // Getter und Setter für "SollLagerbestand"
    public int getSollLagerbestand() {
        return getSollLagerbestand;
    }

    public void setSollLagerbestand(int getSollLagerbestand) {
        this.getSollLagerbestand = getSollLagerbestand;
    }

}