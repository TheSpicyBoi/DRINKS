import java.util.ArrayList;
import java.util.List;

public abstract class Standort {
    public String name;
    public List<Lagerbestand> lagerbestaende;
    /**
     * Konstruktor für die Klasse Standort.
     * @param name Der Name des Lagerstandorts.
     */
    public Standort(String name) {
        this.name = name;
        this.lagerbestaende = new ArrayList<>();
    }

    /**
     * Gibt den Namen des Lagerstandorts zurück.
     * @return Der Name des Lagerstandorts.
     */
    public String getName() {
        return name;
    }


    /**
     * Setzt den Namen des Lagerstandorts.
     * @param name Der zu setzende Name.
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gibt die Liste des Lagerbestands zurück.
     * @return Die Liste des Lagerbestands.
     */
    public List<Lagerbestand> getLagerbestandListe() {
        return lagerbestaende;
    }

    /**
     * Setzt die Liste des Lagerbestands.
     * @param lagerbestandListe Die zu setzende Liste des Lagerbestands.
     */
    public void setLagerbestandListe(List<Lagerbestand> lagerbestandListe) {
        this.lagerbestaende = lagerbestandListe;
    }

    /**
     * Vewrschiebt Getränke an einen bestimmten Standort.
     *
     * @param zielStandort          Der Zielstandort, an den die Getränke verschickt werden.
     * @param getraenksorteName Der Name der Getränksorte, die verschickt werden sollen.
     * @param anzahlKaesten     Die Anzahl der zu verschickenden Kästen.
     */
    public void verschiebe(Standort zielStandort, String getraenksorteName, int anzahlKaesten) {
        if(anzahlKaesten <= 0){
            System.out.println("Ungülltige Kastenanzahl");
            return;
        }

        if(this == zielStandort){
            System.out.println("Start- und Zielstandort sind identisch!");
            return;
        }

        Lagerbestand startLagerbestand = null;
        for (Lagerbestand lager : lagerbestaende) {
            if (lager.getGetraenk().getName().equals(getraenksorteName)) {
                startLagerbestand = lager;
            }
        }
        if(startLagerbestand == null){
            System.out.println("Start Lagerbestand nicht gefunden");
            return;
        }

        Lagerbestand zielLagerbestand = null;
        for (Lagerbestand lager : zielStandort.getLagerbestandListe()) {
            if (lager.getGetraenk().getName().equals(getraenksorteName)) {
                zielLagerbestand = lager;
            }
        }

        if (zielLagerbestand == null) {
            zielLagerbestand = new Lagerbestand(startLagerbestand.getGetraenk(),0);
            zielStandort.getLagerbestandListe().add(zielLagerbestand);
        }
        if(startLagerbestand.getAnzahlKaesten() < anzahlKaesten) {
            System.out.println("Nicht genug Kästen vorhanden");
            return;
        }

        //Zusätzliches abchecken, ob noch restflaschen da sind. Denn wenn der bestand mit standortmax-1 Kästen befüllt ist und noch restflaschen da sind darf nicht noch ein Kasten hierher verschoben werden
        int restflaschen = zielLagerbestand.getAnzahlEinzelflaschen() % zielLagerbestand.getGetraenk().getstandortmax(zielStandort);
        if(zielLagerbestand.getGetraenk().getstandortmax(zielStandort) < (anzahlKaesten+zielLagerbestand.getAnzahlKaesten() + (restflaschen > 0 ? 1 : 0))) {
            System.out.println("Maximale Kapazität des Ziel Standortes überschritten");
            return;
        }

        zielLagerbestand.setAnzahlEinzelflaschen((zielLagerbestand.getAnzahlKaesten() + anzahlKaesten)*zielLagerbestand.getGetraenk().getFlaschenProKasten());
        startLagerbestand.setAnzahlEinzelflaschen((startLagerbestand.getAnzahlKaesten() - anzahlKaesten)*startLagerbestand.getGetraenk().getFlaschenProKasten());
        System.out.println("Verschieben erfolgreich!");
    }


    /**
     * Überprüft den Lagerbestand und gibt Informationen über die Bestände aus.
     */
    public void lagerbestandAusgeben() {
        System.out.println("Lagerbestände für Standort " + name + ":");
        for (Lagerbestand lagerbestand : lagerbestaende) {
            System.out.println("Getränk: " + lagerbestand.getGetraenk().getName() +
                    "; Anzahl Einzelflaschen: " + lagerbestand.getAnzahlEinzelflaschen() +
                    "; Anzahl Kästen: " + lagerbestand.getAnzahlKaesten()+
                    "; " + lagerbestand.getGetraenk().getAttributName() + ": "+lagerbestand.getGetraenk().getAttributWert());
        }    }
}
