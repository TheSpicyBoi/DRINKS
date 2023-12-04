import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
public class Main {
    private static void verschiebe(Standort ursprungsort, Standort zielort, Lagerbestand lagerbestand, int anzahl){
        Getraenke getraenk = lagerbestand.getGetraenk();
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
    private static void initialeLagerbestaende(List<Getraenke> sortiment, Zentrallager zentrallager, List<Standort> standorte) {
        Getraenke mineralwasserstill = new Getraenke("Mineralwasser, still", 6, 200, "glas",200,100,50);
        Getraenke mineralwasserkohlensäure = new Getraenke("Mineralwasser, mit Kohlensäure", 12, 400, "glas",400,200,100);
        Getraenke apfelsaft = new Getraenke("Apfelsaft", 6, 200, "glas",200,100,50);
        Getraenke orangensaft = new Getraenke("Orangensaft", 6, 400, "glas",400,200,200);
        Getraenke limo = new Getraenke("Limonade", 12, 300, " fruchtgehalt",300,150,100);
        Getraenke bier = new Getraenke("Bier", 24, 200, "5% Alkoholgehalt",200,150,150);
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
        List<VerkaufsStandorte> verkaufsStandorte = new ArrayList<>();
        VerkaufsStandorte verkaufsStandorte1 = new VerkaufsStandorte("Lager1");
        VerkaufsStandorte verkaufsStandorte2 = new VerkaufsStandorte("Lager2");
        standorte.add(verkaufsStandorte1);
        standorte.add(verkaufsStandorte2);
        verkaufsStandorte.add(verkaufsStandorte1);
        verkaufsStandorte.add(verkaufsStandorte2);
        Zentrallager zentrallager = new Zentrallager("Zentrale");
        standorte.add(zentrallager);
        List<Getraenke> sortiment = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int option = 0;

        initialeLagerbestaende(sortiment,zentrallager,standorte);


        while (option != 6) {
            System.out.println("\n--- Menü ---");
            System.out.println("1. Sachen verkaufen");
            System.out.println("2. Sachen verschicken");
            System.out.println("3. Lagerbestände checken");
            System.out.println("4. Nachschicken aus Zentrallager");
            System.out.println("5. verschicken zwischen standorten");
            System.out.println("6. Beenden");
            System.out.print("Wähle eine Option: ");

            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                String standortname = null;
                String sortenname = null;
                String urpsrungsort = null;
                String zielort = null;
                int anzahl;
                switch (option) {
                    case 1:
                        System.out.println("Verkaufsaktion:");
                        System.out.println("Wo soll verkauft werden");
                        for (VerkaufsStandorte verkaufsStandort :verkaufsStandorte){
                            System.out.println(verkaufsStandort.getStandortName());
                        }
                        standortname = scanner.next();
                        for (VerkaufsStandorte verkaufsStandort :verkaufsStandorte){
                            if (verkaufsStandort.getStandortName().equals(standortname)){
                                System.out.println("Welches Getränk soll verkauft werden?");
                                verkaufsStandort.lagerbestandChecken();
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
                        System.out.println("Versandaktion:");
                        zentrallager.lagerbestandChecken();
                        System.out.println("Welches Getränk soll verschickt werden?");
                        sortenname = scanner.next();
                        for (Lagerbestand lager: zentrallager.getLagerbestandListe()){
                            if (lager.getGetraenk().getName().equals(sortenname)){
                                System.out.println("Wohin soll es verschickt werden");
                                for (Standort standort :standorte){
                                    if(standort.getStandortName() != "Zentrale")
                                    System.out.println(standort.getStandortName());
                                }
                                standortname = scanner.next();
                                for(Standort standort :standorte){
                                        if (standort.getStandortName().equals(standortname)){
                                     System.out.println("Wie viele Kästen sollen verschickt werden?");
                                     System.out.println(lager.getAnzahlKaesten()+" Kästen sind hier gelagert");
                                     System.out.println(lager.getGetraenk().getstandortmax(standort) +" Kästen können gelagert werden");
                                     anzahl = scanner.nextInt();
                                         zentrallager.verschicke(standort,sortenname,anzahl);
                                    }
                                }
                                break;
                            }

                        }
                        break;
                    case 3:
                        System.out.println("Lagerbestände:");
                        for (Standort standort :standorte){
                            System.out.println(standort.getStandortName());
                        }
                        standortname = scanner.next();
                        for (Standort standort :standorte){
                            if (standort.getStandortName().equals(standortname)){
                                standort.lagerbestandChecken();
                            }
                        }
                        break;
                    case 4:
                        System.out.println("Welches Lager soll nachgefüllt werden?");
                        for (VerkaufsStandorte standort :verkaufsStandorte){
                            System.out.println(standort.getStandortName());
                        }
                        standortname = scanner.next();
                        for (VerkaufsStandorte standort :verkaufsStandorte){
                            if (standort.getStandortName().equals(standortname)){
                                zentrallager.nachschicken(standort);
                            }else break;
                        }
                        break;
                    case 5:
                        System.out.println("Von wo soll verschoben werden");
                        for (Standort standort :standorte){
                            System.out.println(standort.getStandortName());
                        }
                        urpsrungsort =scanner.next();
                        for (Standort ursprungsort:standorte){
                            if(ursprungsort.getStandortName().equals(urpsrungsort)){
                                System.out.println("Welches Getränk sollen verschoben werden?");
                                ursprungsort.lagerbestandChecken();
                                sortenname =scanner.next();
                                for (Lagerbestand lager:ursprungsort.getLagerbestandListe()){
                                    if(lager.getGetraenk().getName().equals(sortenname)){
                                        System.out.println("Wohin soll es verschoben werden?");
                                        for (Standort zielstandort:standorte){
                                            if(zielstandort.getStandortName().equals(urpsrungsort)){
                                                continue;
                                            } else System.out.println(zielstandort.getStandortName());
                                        }
                                        zielort = scanner.next();
                                        for (Standort zielstandort:standorte){
                                            if (zielstandort.getStandortName().equals(zielort)){
                                                System.out.println("Wie viele Kästen sollen verschoben werden?");
                                                System.out.println(lager.getGetraenk().getstandortmax(zielstandort)+" Kästen können verschickt werden");
                                                anzahl =scanner.nextInt();
                                                if (anzahl< lager.getAnzahlKaesten()){
                                                    verschiebe(ursprungsort,zielstandort,lager,anzahl);
                                                }
                                            }
                                        }

                                    }
                                }
                            }
                        }
                        break;
                    case 6:
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

