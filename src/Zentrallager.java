import java.util.ArrayList;
import java.util.List;

//diese Klasse initialisiert die lagerbestände in de,Ö_n standorten!!! das ist nicht zum nachschicken GEDACHT!!!!
public class Zentrallager {
    private String name;

    public List<Lagerbestand> zentrallagerbestand;

    Getraenke wasser = new Getraenke("Wasser", 20, 30);
    Getraenke saft = new Getraenke("Saft", 12, 25);

    public void initialeLagerbestaende() {
        Getraenke wasser = new Getraenke("Wasser", 20, 30);
        Getraenke saft = new Getraenke("Saft", 12, 25);

        Lagerbestand wasserLager = new Lagerbestand(wasser, 50);
        Lagerbestand saftLager = new Lagerbestand(saft, 40);

        zentrallagerbestand.add(wasserLager);
        zentrallagerbestand.add(saftLager);
    }


    public Zentrallager(String name) {
        this.name = name;
        this.zentrallagerbestand = new ArrayList<>();
    }

    public void verschicke(Standort standort, Getraenke getraenk, int anzahlKaesten) {
        // Erstellt einen neuen Lagerbestand für das übergebene Getränk und Standort
        Lagerbestand neuerLagerbestand = new Lagerbestand(getraenk, anzahlKaesten * getraenk.getFlaschenProKasten());
        standort.lagerbestand.add(neuerLagerbestand);

        // Sucht den entsprechenden Lagerbestand im Zentrallager
        for (Lagerbestand lager : zentrallagerbestand) {
            if (lager.getGetraenk() == getraenk) {
                // Aktualisiert den gefundenen Lagerbestand im Zentrallager mit der neuen Anzahl an Einzelflaschen
                lager.setAnzahlEinzelflaschen(lager.getAnzahlEinzelflaschen() + neuerLagerbestand.getAnzahlEinzelflaschen());
                lager.updateLagerstand();
                break;
            }
        }
    }
}