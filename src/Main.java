import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;
public class Main {
    private static void verschiebe(Standort ursprungsort, Standort zielort, Lagerbestand lagerbestand, int anzahl){
        GetraenkeSorte getraenk = lagerbestand.getGetraenk();
        int flaschenProKasten = getraenk.getFlaschenProKasten();
        int verschickteFlaschen = anzahl * flaschenProKasten;
        lagerbestand.setAnzahlEinzelflaschen(lagerbestand.getAnzahlEinzelflaschen()-verschickteFlaschen);
        lagerbestand.updateLagerstand();
        //nimmt erstmal an das es bisher keinen Lagerbestand gibt und sucht dann erst ob es den doch gibt
        boolean found = false;
        for (Lagerbestand zielLager: zielort.lagerbestand){
            if(zielLager.getGetraenk().getName().equals(getraenk.getName())&& anzahl < zielLager.getGetraenk().getstandortmax(zielort)){
                zielLager.setAnzahlEinzelflaschen(zielLager.getAnzahlEinzelflaschen()+verschickteFlaschen);
                zielLager.updateLagerstand();
                found = true;
                break;
            }
        }
        //Falls es keinen Lagerbestand gibt erstellt es einen neuen
        if (!found){
            Lagerbestand neuerLagerbestand = new Lagerbestand(getraenk, verschickteFlaschen);
            zielort.lagerbestand.add(neuerLagerbestand);
        }
    }
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

    public static void main(String[] args) {





        List<Standort> standorte = new ArrayList<>();
        List<VerkaufsStandort> verkaufsStandorte = new ArrayList<>();
        VerkaufsStandort verkaufsStandort1 = new VerkaufsStandort("Lager1");
        VerkaufsStandort verkaufsStandort2 = new VerkaufsStandort("Lager2");
        standorte.add(verkaufsStandort1);
        standorte.add(verkaufsStandort2);
        verkaufsStandorte.add(verkaufsStandort1);
        verkaufsStandorte.add(verkaufsStandort2);
        Zentrallager zentrallager = new Zentrallager("Zentrale");
        standorte.add(zentrallager);
        List<GetraenkeSorte> sortiment = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int option = 0;

        initialeLagerbestaende(sortiment,zentrallager,standorte);


        while (option != 6) {
            System.out.println("\n--- Menü ---");
            System.out.println("1. Getränke verkaufen");
            System.out.println("2. Getränke verschieben");
            System.out.println("3. Lagerbestände checken");
            System.out.println("4. Nachschicken aus Zentrallager");
            System.out.println("6. Beenden");
            System.out.print("Wähle eine Option: ");

            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                String sortenname = null;
                String urpsrungsort = null;
                String zielort = null;
                String standortname = null;
                int anzahl;
                switch (option) {
                    case 1:
                        System.out.println("Verkaufsaktion:");
                        System.out.println("Wo soll verkauft werden");
                        for (VerkaufsStandort verkaufsStandort :verkaufsStandorte){
                            System.out.println(verkaufsStandort.getName());
                        }
                        standortname = scanner.next();
                        for (VerkaufsStandort verkaufsStandort :verkaufsStandorte){
                            if (verkaufsStandort.getName().equals(standortname)){
                                System.out.println("Welches Getränk soll verkauft werden?");
                                verkaufsStandort.lagerbestandAusgeben();
                                String verkauftesache = scanner.next();
                                for (Lagerbestand lager : verkaufsStandort.getLagerbestandListe()) {
                                    if (lager.getGetraenk().getName().equals(verkauftesache)) {
                                        System.out.println("Wie viele Flaschen sollen verkauft werden?");
                                        anzahl = scanner.nextInt();
                                        verkaufsStandort.sachenVerkaufen(verkauftesache, anzahl);
                                    }else break;
                                }
                            }else break;
                        }
                        break;
                    case 2:
                        System.out.println("Von Wo soll es verschoben werden");
                        for (Standort standort :standorte) {
                            System.out.println(standort.getName());
                        }
                        String startStandortName = scanner.next();
                        System.out.println("Wohin soll es verschoben werden");
                        for (Standort standort :standorte){
                            System.out.println(standort.getName());
                        }
                        String zielStandortName = scanner.next();
                        Standort startStandort = null;
                        Standort zielStandort = null;
                        for(Standort standort :standorte){
                            if(startStandortName.equals(standort.getName()))
                                startStandort = standort;
                            if(zielStandortName.equals(standort.getName()))
                                zielStandort = standort;
                        }
                        if(startStandort == null || zielStandort == null){
                            System.out.println("Die Standortnamen konnten nicht identifiziert werden");
                            break;
                        }

                        System.out.println("Welches Getränk soll verschickt werden?");
                        startStandort.lagerbestandAusgeben();
                        sortenname = scanner.next();

                        Lagerbestand startLagerbestand = null;
                        for (Lagerbestand lagerbestand: startStandort.getLagerbestandListe()){
                            if(lagerbestand.getGetraenk().getName().equals(sortenname)){
                                startLagerbestand = lagerbestand;
                            }
                        }
                        Lagerbestand zielLagerbestand = null;
                        for (Lagerbestand lagerbestand: zielStandort.getLagerbestandListe()){
                            if(lagerbestand.getGetraenk().getName().equals(sortenname)){
                                zielLagerbestand = lagerbestand;
                            }
                        }

                        if(startLagerbestand == null || zielLagerbestand == null){
                            System.out.println("Der Sortenname wurde in den Standorten nicht gefunden");
                            break;
                        }

                        System.out.println("Wie viele Kästen sollen verschickt werden?");
                        System.out.println(startLagerbestand.getAnzahlKaesten()+" Kästen sind im Start Standort");
                        System.out.println((zielLagerbestand.getGetraenk().getstandortmax(zielStandort) - zielLagerbestand.getAnzahlKaesten())+" Kästen können noch in den Ziel Standort gelagert werden");
                        try{
                            anzahl = scanner.nextInt();
                        }catch(InputMismatchException e){break;}
                        startStandort.verschiebe(zielStandort,sortenname,anzahl);

                        break;
                    case 3:
                        System.out.println("Lagerbestände:");
                        for (Standort standort :standorte){
                            System.out.println(standort.getName());
                        }
                       standortname = scanner.next();
                        for (Standort standort :standorte){
                            if (standort.getName().equals(standortname)){
                                standort.lagerbestandAusgeben();
                            }
                        }
                        break;
                    case 4:
                        System.out.println("Welches Lager soll nachgefüllt werden?");
                        for (VerkaufsStandort standort :verkaufsStandorte){
                            System.out.println(standort.getName());
                        }
                        standortname = scanner.next();
                        for (VerkaufsStandort standort :verkaufsStandorte){
                            if (standort.getName().equals(standortname)){
                                zentrallager.nachschicken(standort);
                            }else break;
                        }
                        break;
                    case 5:
                        System.out.println("Programm wird beendet.");
                        break;
                    default:
                        System.out.println("Ungültige Option. Bitte wähle eine Zahl von 1 bis 6.");
                        break;

                }
            } else {
                System.out.println("Bitte gebe eine Zahl ein.");
                scanner.next();
            }
        }
    }


}

