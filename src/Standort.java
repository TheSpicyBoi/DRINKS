import java.util.ArrayList;
import java.util.List;

public class Standort {
    public String name;
    public List<Lagerbestand> lagerbestand;
    /**
     * Konstruktor für die Klasse Standort.
     * @param name Der Name des Lagerstandorts.
     */
    public Standort(String name) {
        this.name = name;
        this.lagerbestand = new ArrayList<>();
    }

    /**
     * Gibt den Namen des Lagerstandorts zurück.
     * @return Der Name des Lagerstandorts.
     */
    public String getStandortName() {
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
        return lagerbestand;
    }

    /**
     * Setzt die Liste des Lagerbestands.
     * @param lagerbestandListe Die zu setzende Liste des Lagerbestands.
     */
    public void setLagerbestandListe(List<Lagerbestand> lagerbestandListe) {
        this.lagerbestand = lagerbestandListe;
    }

    /**
     * Verkauft Einzelflaschen eines bestimmten Getränks aus dem Lagerbestand.
     * @param getraenksorteName Der Name der Getränksorte, die verkauft werden soll.
     * @param verkauftEinzelflaschen Die Anzahl der zu verkaufenden Einzelflaschen.
     */
    public void sachenVerkaufen(String getraenksorteName, int verkauftEinzelflaschen) {
        boolean gefunden = false;
        for (Lagerbestand lager : lagerbestand) {
            if (lager.getGetraenk().getName().equals(getraenksorteName)) {
                int aktuelleEinzelflaschen = lager.getAnzahlEinzelflaschen();
                if (verkauftEinzelflaschen <= aktuelleEinzelflaschen) {
                    lager.setAnzahlEinzelflaschen(aktuelleEinzelflaschen - verkauftEinzelflaschen);
                    gefunden = true;
                    break;
                } else {
                    System.out.println("Nicht genügend Bestand für den Verkauf.");
                    gefunden = true;
                    break;
                }
            }
        }

        if (!gefunden) {
            System.out.println("Das gesuchte Getränk ist nicht im Lager dieses Standorts verfügbar.");
        }
    }


    /**
     * Überprüft den Lagerbestand und gibt Informationen über die Bestände aus.
     */
    public void lagerbestandChecken() {
        System.out.println("Lagerbestände für Standort " + name + ":");
        for (Lagerbestand lagerbestand : lagerbestand) {
            System.out.println("Getränk: " + lagerbestand.getGetraenk().getName() +
                    ", Anzahl Einzelflaschen: " + lagerbestand.getAnzahlEinzelflaschen() +
                    ", Anzahl Kästen: " + lagerbestand.getAnzahlKaesten()+
                    ", mit dem Attribut: " + lagerbestand.getGetraenk().getAttribut());
        }    }
}
