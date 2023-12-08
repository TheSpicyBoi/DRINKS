import java.util.ArrayList;
import java.util.List;
/**
 * Die Klasse DrinksManagement enthält alle Listen über Standorte, Getränke, die wichtig sind um diese im Kontext des Systems richtig miteinander arbeiten zu lassen
 * Es sind alle Attribute und Methoden statisch und damit nur einmal vorhanden, weshalb es nicht sinnvoll ist diese Klasse zu instanziieren und sie deshalb abstrakt ist.
 */
public abstract class DrinksManagement {
    static List<Standort> standorte;
    static List<VerkaufsStandort> verkaufsStandorte;
    static Zentrallager zentrallager;
    static List<GetraenkeSorte> getrankeSortiment;

    /**
     * Initalisiert die Listen enthalten, die die Standorte, Verkaufsstandorte und Getränkesorten beinhalten
     */
    public static void initDrinksmanagement(){
        VerkaufsStandort verkaufsStandort1 = new VerkaufsStandort("Standort1");
        VerkaufsStandort verkaufsStandort2 = new VerkaufsStandort("Standort2");
        standorte = new ArrayList<>();
        standorte.add(verkaufsStandort1);
        standorte.add(verkaufsStandort2);
        verkaufsStandorte = new ArrayList<>();
        verkaufsStandorte.add(verkaufsStandort1);
        verkaufsStandorte.add(verkaufsStandort2);
        zentrallager = new Zentrallager("Zentrallager");
        standorte.add(zentrallager);
        getrankeSortiment = new ArrayList<>();

        initialeLagerbestaende(getrankeSortiment,zentrallager,standorte);
    }


    /**
     * Es werden die gegebenen Getränkesorten und dessen Attribute erstellt, und in die Lagerbestands Objekte eingegeben, die hier auch erstellt werden
     * @param sortiment Die Liste aller im System vorhandenen Getränkesorten
     * @param zentrallager Das Zentrallager im System
     * @param standorte Die Liste aller im System vorhandenen Standorte
     */
    private static void initialeLagerbestaende(List<GetraenkeSorte> sortiment, Zentrallager zentrallager, List<Standort> standorte) {
        sortiment.add(new GetraenkeSorte("Mineralwasser,still", 6, 200, "Material","Glas",200,100,50));
        sortiment.add(new GetraenkeSorte("Mineralwasser,sprudel", 12, 400, "Material","Glas",400,200,100));
        sortiment.add(new GetraenkeSorte("Apfelsaft", 6, 200, "Fruchtgehalt","25",200,100,50));
        sortiment.add(new GetraenkeSorte("Orangensaft", 6, 400, "Fruchtgehalt","100",400,200,200));
        sortiment.add(new GetraenkeSorte("Limonade", 12, 300, " Fruchtgehalt","20",300,150,100));
        sortiment.add(new GetraenkeSorte("Bier", 24, 200, "Alkoholgehalt","5",200,150,150));

        //Getränkesorten in Kombination mit dessen Lagerbeständen in die Standorte hinzufügen
        for (Standort standort:standorte){
            for(GetraenkeSorte sorte : sortiment){
                standort.lagerbestaende.add(new Lagerbestand(sorte,0));
            }
        }

        //Das Zentrallager komplett befüllen
        for(Lagerbestand bestand : zentrallager.getLagerbestandListe()) {
            bestand.setAnzahlEinzelflaschen(bestand.getGetraenk().getstandortmax(zentrallager) * bestand.getGetraenk().getFlaschenProKasten());
        }
    }

    public static void automatischeNachbestellung(){
        int[] bestaende = new int[getrankeSortiment.size()];
        for(Standort standort : standorte){
            for(int i = 0;i < getrankeSortiment.size();i++){
                for(Lagerbestand lagerbestand : standort.getLagerbestandListe()){
                    if(lagerbestand.getGetraenk().getName().equals(getrankeSortiment.get(i).getName())){
                        bestaende[i] += lagerbestand.getAnzahlKaesten();
                    }
                }
            }
        }
        for(int i = 0;i < bestaende.length;i++){
            bestaende[i] = getrankeSortiment.get(i).getSollLagerbestand()-bestaende[i];

            if(bestaende[i] >= 0){
                int ret = zentrallager.nachbestellen(getrankeSortiment.get(i),bestaende[i]);
                if(ret != 0)
                    return;
            }
        }

        System.out.println("Nachbestellung erfolgreich");
    }

}
