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

    public void sachenVerkaufen(int verkauftEinzelflaschen) {
        for (Lagerbestand lagerbestand : lagerbestand) {
            int aktuelleEinzelflaschen = lagerbestand.getAnzahlEinzelflaschen();
            if (verkauftEinzelflaschen <= aktuelleEinzelflaschen) {
                lagerbestand.setAnzahlEinzelflaschen(aktuelleEinzelflaschen - verkauftEinzelflaschen);
                break;
            } else {
                verkauftEinzelflaschen -= aktuelleEinzelflaschen;
                lagerbestand.setAnzahlEinzelflaschen(0);
            }
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
}