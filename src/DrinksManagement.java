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
        VerkaufsStandort verkaufsStandort1 = new VerkaufsStandort("Lager1");
        VerkaufsStandort verkaufsStandort2 = new VerkaufsStandort("Lager2");
        standorte = new ArrayList<>();
        standorte.add(verkaufsStandort1);
        standorte.add(verkaufsStandort2);
        verkaufsStandorte = new ArrayList<>();
        verkaufsStandorte.add(verkaufsStandort1);
        verkaufsStandorte.add(verkaufsStandort2);
        zentrallager = new Zentrallager("Zentrale");
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
        GetraenkeSorte mineralwasserstill = new GetraenkeSorte("Mineralwasser,still", 6, 200, "Material","Glas",200,100,50);
        GetraenkeSorte mineralwasserkohlensäure = new GetraenkeSorte("Mineralwasser,sprudel", 12, 400, "Material","Glas",400,200,100);
        GetraenkeSorte apfelsaft = new GetraenkeSorte("Apfelsaft", 6, 200, "Fruchtgehalt","25",200,100,50);
        GetraenkeSorte orangensaft = new GetraenkeSorte("Orangensaft", 6, 400, "Fruchtgehalt","100",400,200,200);
        GetraenkeSorte limo = new GetraenkeSorte("Limonade", 12, 300, " Fruchtgehalt","20",300,150,100);
        GetraenkeSorte bier = new GetraenkeSorte("Bier", 24, 200, "Alkoholgehalt","5",200,150,150);
        for (Standort standort:standorte){
            Lagerbestand mineralwasserstillLager = new Lagerbestand(mineralwasserstill, 0);
            Lagerbestand mineralwasserkohlensäureLager = new Lagerbestand(mineralwasserkohlensäure, 0);
            Lagerbestand apfelsaftLager = new Lagerbestand(apfelsaft, 0);
            Lagerbestand orangensaftLager = new Lagerbestand(orangensaft,0 );
            Lagerbestand limoLager = new Lagerbestand(limo,0 );
            Lagerbestand bierLager = new Lagerbestand(bier,0 );
            standort.lagerbestand.add(mineralwasserstillLager);
            standort.lagerbestand.add(mineralwasserkohlensäureLager);
            standort.lagerbestand.add(apfelsaftLager);
            standort.lagerbestand.add(orangensaftLager);
            standort.lagerbestand.add(limoLager);
            standort.lagerbestand.add(bierLager);
        }
        zentrallager.nachbestellen();

    }

}
