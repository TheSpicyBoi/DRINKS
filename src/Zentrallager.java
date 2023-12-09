/**
 * Die Klasse Zentrallager repräsentiert ein Zentrallager für Getränke.
 */
public class Zentrallager extends Standort{

    public Zentrallager(String name) {
        super(name);
    }

    /**
     *
     * @param sorte
     * @param anzahl
     * @return FehlerCode - Wenn dieser 0 ist, ist die Methode erfolgreich gewesen, ansonsten ist ein Fehler aufgetreten
     */
    public int nachbestellen(GetraenkeSorte sorte,int anzahl){
        if(anzahl <= 0){
            System.out.println("Ungülltige Kastenanzahl");
            return -1;
        }

        Lagerbestand lagerbestand = null;
        for (Lagerbestand lager: this.lagerbestaende){
            if(lager.getGetraenk().getName().equals(sorte.getName())){
                lagerbestand = lager;
                break;
            }
        }
        if(lagerbestand == null){
            //wenn die sorte vorhanden ist wird die variable durch die oder-verschränkung true
            boolean sorteVorhanden = false;
            for (GetraenkeSorte s: DrinksManagement.getrankeSortiment){
                sorteVorhanden =  sorteVorhanden || sorte.getName().equals(s.getName());
            }

            if(!sorteVorhanden){
                System.out.println("Getränkesorte konnte nicht im sortiment identifiziert werden");
                return -1;
            }
            lagerbestand = new Lagerbestand(sorte,anzahl);
            this.getLagerbestandListe().add(lagerbestand);
        }

        if(lagerbestand.getAnzahlKaesten() + anzahl > lagerbestand.getGetraenk().getstandortmax(this)){
            System.out.println("Maximale Kapazität des Zentrallagers überschritten");
            return -1;
        }

        lagerbestand.setAnzahlEinzelflaschen(lagerbestand.getAnzahlEinzelflaschen()+anzahl*lagerbestand.getGetraenk().getFlaschenProKasten());
        return 0;

    }
}