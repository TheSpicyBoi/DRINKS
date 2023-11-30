import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
public class Main {
    private static void verschiebe(Standort ursprungsort,Standort zielort,Lagerbestand lagerbestand, int anzahl){
        Getraenke getraenk = lagerbestand.getGetraenk();
        int flaschenProKasten = getraenk.getFlaschenProKasten();
        int verschickteFlaschen = anzahl * flaschenProKasten;
        lagerbestand.setAnzahlEinzelflaschen(lagerbestand.getAnzahlEinzelflaschen()-verschickteFlaschen);
        lagerbestand.updateLagerstand();
        boolean found = false;
        for (Lagerbestand zielLager: zielort.lagerbestand){
            if(zielLager.getGetraenk().getName().equals(getraenk.getName())&& anzahl <zielort.getLagerkapazitaetInKaesten()){
                zielLager.setAnzahlEinzelflaschen(zielLager.getAnzahlEinzelflaschen()+verschickteFlaschen);
                zielLager.updateLagerstand();
                found = true;
                break;
            }
        }
        if (!found){
            Lagerbestand neuerLagerbestand = new Lagerbestand(getraenk, verschickteFlaschen);
            zielort.lagerbestand.add(neuerLagerbestand);
        }
    }
    public static void main(String[] args) {
        // Erstellung von Getränken
        List<Standort> standorte = new ArrayList<>();
        // Erstellung von Standorten
        Standort standort1 = new Standort("Lager1", 100);
        Standort standort2 = new Standort("Lager2", 150);
        standorte.add(standort1);
        standorte.add(standort2);

        // Erstellung des Zentrallagers
        Zentrallager zentrallager = new Zentrallager("Zentrale");
        zentrallager.initialeLagerbestaende();

        // Verschicken von Getränken vom Zentrallager zu Standorten
        //System.out.println("versucht sachen zu verschicken");
        //zentrallager.verschicke(standort1, "cola", 5);
        //zentrallager.verschicke(standort2, "limonade", 3);
        //System.out.println("hat getränke verschickt");

        // Überprüfen der Lagerbestände in den Standorten
        //standort1.lagerbestandChecken();
        //standort2.lagerbestandChecken();

        // Verkauf von Getränken von Standorten
        //standort1.sachenVerkaufen("cola",15);

        // Überprüfen der aktualisierten Lagerbestände nach dem Verkauf
        //System.out.println("\nLagerbestände in Standort 1 nach Verkauf:");
        //standort1.lagerbestandChecken();

        // Weitere Aktionen im Zentrallager möglich, z.B. Zugriff auf andere Funktionen
        // zentralLager.weitereFunktionImZentralLager();
        Scanner scanner = new Scanner(System.in);
        int option = 0;

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
                        // Verkaufen
                        System.out.println("Verkaufsaktion:");
                        // Was soll verkauft werden
                        System.out.println("wo soll verkauft werden");
                        for (Standort standort:standorte){
                            System.out.println(standort.getStandortName());
                        }
                        standortname = scanner.next();
                        for (Standort standort:standorte){
                            if (standort.getStandortName().equals(standortname));
                            System.out.println("was soll verkauft werden");
                            standort.lagerbestandChecken();
                            String verkauftesache = scanner.next();
                            for (Lagerbestand lager :standort.getLagerbestandListe()){
                                if (lager.getGetraenk().getName().equals(verkauftesache)){
                                    System.out.println("Wie viele willst du verkaufen?");
                                    anzahl = scanner.nextInt();
                                    standort.sachenVerkaufen(verkauftesache,anzahl);
                                }else break;
                            }
                        }
                        break;
                    case 2:
                        // Verschicken
                        System.out.println("Versandaktion:");
                        //was soll verschickt werden
                        zentrallager.zentrallagerbestandChecken();
                        System.out.println("was soll verschickt werden?");
                        sortenname = scanner.next();
                        for (Lagerbestand lager: zentrallager.getZentrallagerbestand()){
                            if (lager.getGetraenk().getName().equals(sortenname)){
                                System.out.println("wohin soll es verschickt werden");
                                for (Standort standort:standorte){
                                    System.out.println(standort.getStandortName());
                                }
                                standortname = scanner.next();
                                for(Standort standort:standorte){
                                        if (standort.getStandortName().equals(standortname)){
                                     System.out.println("Wie viele kästen sollen verschickt werden?");
                                     System.out.println(lager.getAnzahlKaesten()+" kästen sind hier gelagert2");
                                     System.out.println(standort.getLagerkapazitaetInKaesten() +" kästen können gelagert werden");
                                     anzahl = scanner.nextInt();
                                     if(anzahl<standort.getLagerkapazitaetInKaesten()){
                                         zentrallager.verschicke(standort,sortenname,anzahl);
                                     } else System.out.println("so viele sachen können nicht verschickt werden");
                                    }
                                }
                                break;
                            }

                        }
                        break;
                    case 3:
                        // Lagerbestände checken
                        System.out.println("Lagerbestände:");
                        for (Standort standort:standorte){
                            System.out.println(standort.getStandortName());
                        }
                        standortname = scanner.next();
                        for (Standort standort:standorte){
                            if (standort.getStandortName().equals(standortname)){
                                standort.lagerbestandChecken();
                            }
                        }
                        break;
                    case 4:
                        for (Standort standort:standorte){
                            System.out.println(standort.getStandortName());
                        }
                        standortname = scanner.next();
                        for (Standort standort:standorte){
                            if (standort.getStandortName().equals(standortname)){
                                standort.nachbestellen(zentrallager);
                            }else break;
                        }
                        break;
                    case 5:
                        System.out.println("von wo soll verschickt werden");
                        for (Standort standort:standorte){
                            System.out.println(standort.getStandortName());
                        }
                        urpsrungsort =scanner.next();
                        for (Standort ursprungsort:standorte){
                            if(ursprungsort.getStandortName().equals(urpsrungsort)){
                                System.out.println("was soll verschickt werden");
                                ursprungsort.lagerbestandChecken();
                                sortenname =scanner.next();
                                for (Lagerbestand lager:ursprungsort.getLagerbestandListe()){
                                    if(lager.getGetraenk().getName().equals(sortenname)){
                                        System.out.println("wohin soll es verschickt werden?");
                                        for (Standort zielstandort:standorte){
                                            if(zielstandort.getStandortName().equals(urpsrungsort)){
                                                continue;
                                            } else System.out.println(zielstandort.getStandortName());
                                        }
                                        zielort = scanner.next();
                                        for (Standort zielstandort:standorte){
                                            if (zielstandort.getStandortName().equals(zielort)){
                                                System.out.println("wie viel soll verschickt werden?");
                                                System.out.println(lager.getAnzahlKaesten()+" können verschickt werden");
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
                        System.out.println("Ungültige Option. Bitte wähle eine Zahl von 1 bis 5.");
                        break;

                }
            } else {
                System.out.println("Bitte gebe eine Zahl ein.");
                scanner.next();
            }
        }
    }
}
