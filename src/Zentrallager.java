import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse Zentrallager repräsentiert ein Zentrallager für Getränke.
 */
public class Zentrallager {
    private String name;
    public List<Lagerbestand> zentrallagerbestand;

    /**
     * Konstruktor für die Klasse Zentrallager.
     *
     * @param name Der Name des Zentrallagers.
     */
    public Zentrallager(String name) {
        this.name = name;
        this.zentrallagerbestand = new ArrayList<>();
    }

    /**
     * Gibt den Bestand des Zentrallagers zurück.
     *
     * @return Die Liste des Zentrallagerbestands.
     */
    public List<Lagerbestand> getZentrallagerbestand() {
        return zentrallagerbestand;
    }

    /**
     * Überprüft den Lagerbestand im Zentrallager und gibt Informationen darüber aus.
     */
    public void zentrallagerbestandChecken() {
        System.out.println("Lagerbestände für Standort " + name + ":");
        for (Lagerbestand lagerbestand : zentrallagerbestand) {
            System.out.println("Getränk: " + lagerbestand.getGetraenk().getName() +
                    ", Anzahl Einzelflaschen: " + lagerbestand.getAnzahlEinzelflaschen() +
                    ", Anzahl Kästen: " + lagerbestand.getAnzahlKaesten());
        }
    }

    /**
     * Initialisiert die Anfangslagerbestände im Zentrallager für verschiedene Getränke.
     */
    public void initialeLagerbestaende() {
        Getraenke wasser = new Getraenke("Wasser", 20, 30, "glas");
        Getraenke cola = new Getraenke("Wola", 10, 30, "plastik");
        Getraenke limo = new Getraenke("Limo", 20, 30, " fruchtgehalt");
        Getraenke bier = new Getraenke("Bier", 20, 30, "5% Alkoholgehalt");
        Getraenke saft = new Getraenke("Saft", 12, 25, "");

        Lagerbestand wasserLager = new Lagerbestand(wasser, 500);
        Lagerbestand colaLager = new Lagerbestand(cola, 500);
        Lagerbestand limoLager = new Lagerbestand(limo, 500);
        Lagerbestand bierLager = new Lagerbestand(bier, 500);
        Lagerbestand saftLager = new Lagerbestand(saft, 400);

        zentrallagerbestand.add(wasserLager);
        zentrallagerbestand.add(colaLager);
        zentrallagerbestand.add(limoLager);
        zentrallagerbestand.add(bierLager);
        zentrallagerbestand.add(saftLager);
    }

    /**
     * Verschickt Getränke an einen bestimmten Standort.
     *
     * @param standort          Der Zielstandort, an den die Getränke verschickt werden.
     * @param getraenksorteName Der Name der Getränksorte, die verschickt werden sollen.
     * @param anzahlKaesten     Die Anzahl der zu verschickenden Kästen.
     */
    public void verschicke(Standort standort, String getraenksorteName, int anzahlKaesten) {
        Getraenke gesuchtesGetraenk = null;

        for (Lagerbestand lager : zentrallagerbestand) {
            if (lager.getGetraenk().getName().equals(getraenksorteName)) {
                if (lager.getAnzahlKaesten() > anzahlKaesten && anzahlKaesten < standort.getLagerkapazitaetInKaesten()) {
                    gesuchtesGetraenk = lager.getGetraenk();
                } else System.out.println("so viele kästen gibt es nicht!");

                break;
            }
        }
        if (gesuchtesGetraenk != null) {
            Lagerbestand neuerLagerbestand = new Lagerbestand(gesuchtesGetraenk, anzahlKaesten * gesuchtesGetraenk.getFlaschenProKasten());
            standort.lagerbestand.add(neuerLagerbestand);

            for (Lagerbestand lager : zentrallagerbestand) {
                if (lager.getGetraenk() == gesuchtesGetraenk) {
                    lager.setAnzahlEinzelflaschen(lager.getAnzahlEinzelflaschen() + neuerLagerbestand.getAnzahlEinzelflaschen());
                    lager.updateLagerstand();
                    break;
                }
            }
        } else {
            System.out.println("Das gesuchte Getränk ist nicht im Zentrallager verfügbar.");
        }
    }

    /**
     * Schickt Getränke an einen bestimmten Standort nach, wenn der Bestand dort zu niedrig ist.
     *
     * @param getraenksorte Der Name der Getränksorte, die nachgeschickt werden sollen.
     * @param standort      Der Zielstandort, an den die Getränke nachgeschickt werden.
     * @param anzahlKaesten Die Anzahl der nachzuschickenden Kästen.
     */
    public void nachschicken(String getraenksorte, Standort standort, int anzahlKaesten) {
        Getraenke gesuchtesGetraenk = null;

        for (Lagerbestand lager : zentrallagerbestand) {
            if (lager.getGetraenk().getName().equals(getraenksorte)) {
                gesuchtesGetraenk = lager.getGetraenk();
                break;
            }
        }
    }
}