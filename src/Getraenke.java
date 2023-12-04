/**
 * Die Klasse Getraenke repräsentiert ein Getränk mit seinen Eigenschaften.
 */
public class Getraenke {
    private String name;
    private int flaschenProKasten;
    private int sollLagerbestand;
    private String attribut;

    /**
     * Konstruktor für die Klasse Getraenke.
     * @param name Der Name des Getränks.
     * @param flaschenProKasten Die Anzahl der Flaschen pro Kasten.
     * @param sollLagerbestandInKaesten Der Soll-Lagerbestand in Kästen.
     * @param attribut Das Attribut des Getränks (z. B. Material, Gehalt usw.).
     */
    public Getraenke(String name, int flaschenProKasten, int sollLagerbestandInKaesten, String attribut) {
        this.name = name;
        this.flaschenProKasten = flaschenProKasten;
        this.sollLagerbestand = sollLagerbestandInKaesten;
        this.attribut = attribut;
    }

    /**
     * Gibt den Namen des Getränks zurück.
     * @return Der Name des Getränks.
     */
    public String getName() {
        return name;
    }

    /**
     * Setzt den Namen des Getränks.
     * @param name Der zu setzende Name des Getränks.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gibt die Anzahl der Flaschen pro Kasten zurück.
     * @return Die Anzahl der Flaschen pro Kasten.
     */
    public int getFlaschenProKasten() {
        return flaschenProKasten;
    }

    /**
     * Setzt die Anzahl der Flaschen pro Kasten.
     * @param flaschenProKasten Die zu setzende Anzahl der Flaschen pro Kasten.
     */
    public void setFlaschenProKasten(int flaschenProKasten) {
        this.flaschenProKasten = flaschenProKasten;
    }

    /**
     * Gibt den Soll-Lagerbestand in Kästen zurück.
     * @return Der Soll-Lagerbestand in Kästen.
     */
    public int getSollLagerbestand() {
        return sollLagerbestand;
    }

    /**
     * Setzt den Soll-Lagerbestand in Kästen.
     * @param sollLagerbestand Der zu setzende Soll-Lagerbestand in Kästen.
     */
    public void setSollLagerbestand(int sollLagerbestand) {
        this.sollLagerbestand = sollLagerbestand;
    }

    /**
     * Gibt das Attribut des Getränks zurück.
     * @return Das Attribut des Getränks.
     */
    public String getAttribut() {
        return attribut;
    }
}
