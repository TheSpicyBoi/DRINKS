/**
 * Die Klasse Getraenke repräsentiert ein Getränk mit seinen Eigenschaften.
 */
public class GetraenkeSorte {
    private String name;
    private int flaschenProKasten;
    private int sollLagerbestand;
    private String attributName;
    private String attributWert;

    private int zentrallagermax;

    private int standort1max;

    private int standort2max;



    /**
     * Konstruktor für die Klasse Getraenke.
     * @param name Der Name des Getränks.
     * @param flaschenProKasten Die Anzahl der Flaschen pro Kasten.
     * @param sollLagerbestandInKaesten Der Soll-Lagerbestand in Kästen.
     * @param attributName Der Name des Attributs (z. B. Material, Gehalt usw.).
     * @param attributWert Der Wert des Attributs (z. B. Material, Gehalt usw.).
     */
    public GetraenkeSorte(String name, int flaschenProKasten, int sollLagerbestandInKaesten, String attributName, String attributWert, int zentrallagermax, int standort1max, int standort2max) {
        this.name = name;
        this.flaschenProKasten = flaschenProKasten;
        this.sollLagerbestand = sollLagerbestandInKaesten;
        this.attributName = attributName;
        this.attributWert = attributWert;
        this.zentrallagermax=zentrallagermax;
        this.standort1max=standort1max;
        this.standort2max=standort2max;
    }

    /**
     *
     * @param standort Ist die
     * @return
     */
    public int getstandortmax(Standort standort){
        switch (standort.name){
            case "Standort1":return standort1max;
            case "Standort2":return standort2max;
            case "Zentrallager":return zentrallagermax;
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
     * Gibt den Attribut Namen des Getränks zurück.
     * @return Den Attribut Namen des Getränks.
     */
    public String getAttributName() {
        return attributName;
    }

    /**
     * Gibt den Attribut Wert des Getränks zurück.
     * @return Den Attribut Wert des Getränks.
     */
    public String getAttributWert() {
        return attributWert;
    }

    /**
     * Setzt den Attribut Wert des Getränks.
     * @param wert Der Wert des Getränks.
     */
    public void setAttributWert(String wert) {
       this.attributWert = wert;
    }

    /**
     * Setzt den maximalen Bestand für Zentrallager
     * @param zentrallagermax Der maximale Bestand für Zentrallager
     */
    public void setZentrallagermax(int zentrallagermax){
        this.zentrallagermax = zentrallagermax;
    }

    /**
     * Gibt den maximalen Bestand für Zentrallager zurück
     * @return Der maximale Bestand für Zentrallager
     */
    public int getZentrallagermax(){
        return zentrallagermax;
    }

    /**
     * Setzt den maximalen Bestand für Standort 1
     * @param standort1max Der maximale Bestand für Standort 1
     */
    public void setStandort1max(int standort1max){
        this.standort1max = standort1max;
    }

    /**
     * Gibt den maximalen Bestand für Standort 1 zurück
     * @return Der maximale Bestand für Standort 1
     */
    public int getStandort1max(){
        return standort1max;
    }

    /**
     * Setzt den maximalen Bestand für Standort 2
     * @param standort2max Der maximale Bestand für Standort 2
     */
    public void setStandort2max(int standort2max){
        this.standort2max =standort2max;
    }

    /**
     * Gibt den maximalen Bestand für Standort 2 zurück
     * @return Der maximale Bestand für Standort 2
     */
    public int getStandort2max() {
        return standort2max;
    }
}
