/**
 * Die Klasse Getraenke repräsentiert ein Getränk mit seinen Eigenschaften.
 */
public class Getraenke {
    private String name;
    private int flaschenProKasten;
    private int sollLagerbestand;
    private String attribut;

    private int zentrallagermax;

    private int standort1max;

    private int standort2max;



    /**
     * Konstruktor für die Klasse Getraenke.
     * @param name Der Name des Getränks.
     * @param flaschenProKasten Die Anzahl der Flaschen pro Kasten.
     * @param sollLagerbestandInKaesten Der Soll-Lagerbestand in Kästen.
     * @param attribut Das Attribut des Getränks (z. B. Material, Gehalt usw.).
     */
    public Getraenke(String name, int flaschenProKasten, int sollLagerbestandInKaesten, String attribut, int zentrallagermax, int standort1max, int standort2max) {
        this.name = name;
        this.flaschenProKasten = flaschenProKasten;
        this.sollLagerbestand = sollLagerbestandInKaesten;
        this.attribut = attribut;
        this.zentrallagermax=zentrallagermax;
        this.standort1max=standort1max;
        this.standort2max=standort2max;
    }
    public int getstandortmax(Standort standort){
        switch (standort.name){
            case "Lager1":return standort1max;
            case "Lager2":return standort2max;
            case "Zentrale":return zentrallagermax;
            default:System.out.println("kein gültiger standort");
                    return 0;
        }
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

    public void setZentrallagermax(int zentrallagermax){
        this.zentrallagermax = zentrallagermax;
    }

    public int getZentrallagermax(){
        return zentrallagermax;
    }

    public void setStandort1max(int standort1max){
        this.standort1max = standort1max;
    }

    public int getStandort1max(){
        return standort1max;
    }
    public void setStandort2max(int standort2max){
        this.standort2max =standort2max;
    }
    public int getStandort2max() {
        return standort2max;
    }
}
