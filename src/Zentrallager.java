import java.util.ArrayList;
//diese Klasse initialisiert die lagerbestände in de,Ö_n standorten!!! das ist nicht zum nachschicken GEDACHT!!!!
public class Zentrallager {
    private String name;
    private ArrayList<Lagerbestand> lagerbestaende;

    public Zentrallager(String name) {
        this.name = name;
        this.lagerbestaende = new ArrayList<>();
    }

    public void verschicke(Standort standort, Getraenke getraenk, int anzahlKaesten) {
        // Erstellt einen neuen Lagerbestand für das übergebene Getränk und Standort
        Lagerbestand neuerLagerbestand = new Lagerbestand(getraenk, anzahlKaesten * getraenk.getFlaschenProKasten());
        standort.lagerbestand.add(neuerLagerbestand);

        // Sucht den entsprechenden Lagerbestand im Zentrallager
        for (Lagerbestand lager : lagerbestaende) {
            if (lager.getGetraenk() == getraenk) {
                // Aktualisiert den gefundenen Lagerbestand im Zentrallager mit der neuen Anzahl an Einzelflaschen
                lager.setAnzahlEinzelflaschen(lager.getAnzahlEinzelflaschen() + neuerLagerbestand.getAnzahlEinzelflaschen());
                lager.updateLagerstand();
                break;
            }
        }
    }
}