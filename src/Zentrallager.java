import java.util.ArrayList;
import java.util.List;

//diese Klasse initialisiert die lagerbestände in de,Ö_n standorten!!! das ist nicht zum nachschicken GEDACHT!!!!
public class Zentrallager {
    private String name;

    public List<Lagerbestand> zentrallagerbestand;
    public List<Lagerbestand> getZentrallagerbestand() {
        return zentrallagerbestand;
    }

    public void zentrallagerbestandChecken() {
        System.out.println("Lagerbestände für Standort " + name + ":");
        for (Lagerbestand lagerbestand : zentrallagerbestand) {
            System.out.println("Getränk: " + lagerbestand.getGetraenk().getName() +
                    ", Anzahl Einzelflaschen: " + lagerbestand.getAnzahlEinzelflaschen() +
                    ", Anzahl Kästen: " + lagerbestand.getAnzahlKaesten());
        }
    }


    public void initialeLagerbestaende() {
        Getraenke wasser = new Getraenke("wasser", 20, 30, "glas");
        Getraenke cola = new Getraenke("cola", 10, 30,"plastik");
        Getraenke limo = new Getraenke("limo", 20, 30," fruchtgehalt");
        Getraenke bier = new Getraenke("bier", 20, 30, "5% Alkoholgehalt");
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


    public Zentrallager(String name) {
        this.name = name;
        this.zentrallagerbestand = new ArrayList<>();
    }

    public void verschicke(Standort standort,String getraenksorteName, int anzahlKaesten) {
        Getraenke gesuchtesGetraenk = null;

        // Durchsuche den Lagerbestand im Zentrallager nach dem gesuchten Getränkenamen
        for (Lagerbestand lager : zentrallagerbestand) {
            if (lager.getGetraenk().getName().equals(getraenksorteName)) {
                if (lager.getAnzahlKaesten()>anzahlKaesten&&anzahlKaesten<standort.getLagerkapazitaetInKaesten()){
                    gesuchtesGetraenk = lager.getGetraenk();
                }else System.out.println("so viele kästen gibt es nicht!");

                break;
            }
        }
        //verschickt getränk
        if (gesuchtesGetraenk != null) {
            Lagerbestand neuerLagerbestand = new Lagerbestand(gesuchtesGetraenk, anzahlKaesten * gesuchtesGetraenk.getFlaschenProKasten());
            standort.lagerbestand.add(neuerLagerbestand);

            // Aktualisiert den gefundenen Lagerbestand im Zentrallager mit der neuen Anzahl an Einzelflaschen
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
    //schickt getränke nach
    public void nachschicken(String getraenksorteName, Standort standort, int anzahlKaesten) {
        Getraenke gesuchtesGetraenk = null;

        // Durchsuche den Lagerbestand im Zentrallager nach dem gesuchten Getränkenamen
        for (Lagerbestand lager : zentrallagerbestand) {
            if (lager.getGetraenk().getName().equals(getraenksorteName)) {
                gesuchtesGetraenk = lager.getGetraenk();
                break;
            }
        }

        if (gesuchtesGetraenk != null) {
            // Aktualisiere den Lagerbestand im Standort anhand des gefundenen Getränks
            boolean found = false;
            for (Lagerbestand lager : standort.lagerbestand) {
                if (lager.getGetraenk() == gesuchtesGetraenk) {
                    lager.setAnzahlEinzelflaschen(lager.getAnzahlEinzelflaschen() + anzahlKaesten * gesuchtesGetraenk.getFlaschenProKasten());
                    lager.updateLagerstand();
                    found = true;
                    break;
                }
            }

            // Falls der Lagerbestand im Standort nicht gefunden wurde, füge einen neuen hinzu
            if (!found) {
                Lagerbestand neuerLagerbestand = new Lagerbestand(gesuchtesGetraenk, anzahlKaesten * gesuchtesGetraenk.getFlaschenProKasten());
                standort.lagerbestand.add(neuerLagerbestand);
            }
        } else {
            System.out.println("Das gesuchte Getränk ist nicht im Zentrallager verfügbar.");
        }
    }
}