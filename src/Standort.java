import java.util.ArrayList;
import java.util.List;

public class Standort {
    public String name;
    private int lagerkapazitaetInKaesten;
    public List<Lagerbestand> lagerbestand;

    public Standort(String name, int lagerkapazitaetInKaesten) {
        this.name = name;
        this.lagerkapazitaetInKaesten = lagerkapazitaetInKaesten;
        this.lagerbestand = new ArrayList<>();
    }

    public String getStandortName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLagerkapazitaetInKaesten() {
        return lagerkapazitaetInKaesten;
    }

    public void setLagerkapazitaetInKaesten(int lagerkapazitaetInKaesten) {
        this.lagerkapazitaetInKaesten = lagerkapazitaetInKaesten;
    }

    public List<Lagerbestand> getLagerbestandListe() {
        return lagerbestand;
    }

    public void setLagerbestandListe(List<Lagerbestand> lagerbestandListe) {
        this.lagerbestand = lagerbestandListe;
    }

    public void sachenVerkaufen(String getraenksorteName, int verkauftEinzelflaschen) {
        boolean gefunden = false;

        // Durchsuche den Lagerbestand des Standorts nach dem gesuchten Getränkenamen
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

    public void lagerbestandChecken() {
        System.out.println("Lagerbestände für Standort " + name + ":");
        for (Lagerbestand lagerbestand : lagerbestand) {
            System.out.println("Getränk: " + lagerbestand.getGetraenk().getName() +
                    ", Anzahl Einzelflaschen: " + lagerbestand.getAnzahlEinzelflaschen() +
                    ", Anzahl Kästen: " + lagerbestand.getAnzahlKaesten());
        }
    }
    public void nachbestellen(Zentrallager zentrallager) {
        for (Lagerbestand lager : lagerbestand) {
            Getraenke getraenk = lager.getGetraenk();
            int aktuellerBestand = lager.getAnzahlEinzelflaschen();

            // Überprüfen, ob der aktuelle Bestand kleiner ist als der Soll-Lagerbestand
            if (aktuellerBestand < getraenk.getSollLagerbestand()) {
                int zuBestellen = getraenk.getSollLagerbestand() - aktuellerBestand;
                int anzahlKaestenZuBestellen = (zuBestellen + getraenk.getFlaschenProKasten() - 1) / getraenk.getFlaschenProKasten();

                // Nachbestellung aus dem Zentrallager
                zentrallager.nachschicken(getraenk.getName(), this, anzahlKaestenZuBestellen);
            }
        }
    }
}