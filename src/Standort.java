import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse Standort repräsentiert einen Lagerstandort für Getränke.
 */
public class Standort {
    public String name;
    private int lagerkapazitaetInKaesten;
    public List<Lagerbestand> lagerbestand;

    /**
     * Konstruktor für die Klasse Standort.
     * @param name Der Name des Lagerstandorts.
     * @param lagerkapazitaetInKaesten Die Lagerkapazität in Kästen.
     */
    public Standort(String name, int lagerkapazitaetInKaesten) {
        this.name = name;
        this.lagerkapazitaetInKaesten = lagerkapazitaetInKaesten;
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
     * Gibt die Lagerkapazität in Kästen zurück.
     * @return Die Lagerkapazität in Kästen.
     */
    public int getLagerkapazitaetInKaesten() {
        return lagerkapazitaetInKaesten;
    }

    /**
     * Setzt die Lagerkapazität in Kästen.
     * @param lagerkapazitaetInKaesten Die zu setzende Lagerkapazität in Kästen.
     */
    public void setLagerkapazitaetInKaesten(int lagerkapazitaetInKaesten) {
        this.lagerkapazitaetInKaesten = lagerkapazitaetInKaesten;
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

    /**
     * Bestellt Getränke beim Zentrallager, wenn der Bestand unter den Sollwert fällt.
     * @param zentrallager Das Zentrallager, bei dem die Bestellung aufgegeben wird.
     */
    public void nachbestellen(Zentrallager zentrallager) {
        for (Lagerbestand lager : lagerbestand) {
            Getraenke getraenk = lager.getGetraenk();
            int aktuellerBestand = lager.getAnzahlEinzelflaschen();

            if (aktuellerBestand < getraenk.getSollLagerbestand()) {
                int zuBestellen = getraenk.getSollLagerbestand() - aktuellerBestand;
                int anzahlKaestenZuBestellen = (zuBestellen + getraenk.getFlaschenProKasten() - 1) / getraenk.getFlaschenProKasten();

                zentrallager.nachschicken(getraenk.getName(), this, anzahlKaestenZuBestellen);
            }
        }
    }
}
