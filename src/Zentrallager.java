/**
 * Die Klasse Zentrallager repräsentiert ein Zentrallager für Getränke.
 */
public class Zentrallager extends Standort{

    public Zentrallager(String name) {
        super(name);
    }

    public void nachbestellen(GetraenkeSorte sorte,int anzahl){
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
                return;
            }
            lagerbestand = new Lagerbestand(sorte,anzahl);
            this.getLagerbestandListe().add(lagerbestand);
        }

        lagerbestand.setAnzahlEinzelflaschen(lagerbestand.getAnzahlEinzelflaschen()+anzahl*lagerbestand.getGetraenk().getFlaschenProKasten());
        System.out.println("Getränke erfolgreich bestellt!");

    }
}