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
        //nimmt erstmal an das es bisher keinen Lagerbestand gibt und sucht dann erst ob es den doch gibt
        boolean found = false;
        for (Lagerbestand zielLager: zielort.lagerbestand){
            if(zielLager.getGetraenk().getName().equals(getraenk.getName())&& anzahl <zielort.getLagerkapazitaetInKaesten()){
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
    public static void main(String[] args) {
        List<Standort> standorte = new ArrayList<>();
        Standort standort1 = new Standort("Lager1", 100);
        Standort standort2 = new Standort("Lager2", 150);
        standorte.add(standort1);
        standorte.add(standort2);
        Zentrallager zentrallager = new Zentrallager("Zentrale");
        zentrallager.initialeLagerbestaende();
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
                        System.out.println("Verkaufsaktion:");
                        System.out.println("Wo soll verkauft werden");
                        for (Standort standort:standorte){
                            System.out.println(standort.getStandortName());
                        }
                        standortname = scanner.next();
                        for (Standort standort:standorte){
                            if (standort.getStandortName().equals(standortname));
                            System.out.println("Welches Getränk soll verkauft werden?");
                            standort.lagerbestandChecken();
                            String verkauftesache = scanner.next();
                            for (Lagerbestand lager :standort.getLagerbestandListe()){
                                if (lager.getGetraenk().getName().equals(verkauftesache)){
                                    System.out.println("Wie viele Flaschen sollen verkauft werden?");
                                    anzahl = scanner.nextInt();
                                    standort.sachenVerkaufen(verkauftesache,anzahl);
                                }else break;
                            }
                        }
                        break;
                    case 2:
                        System.out.println("Versandaktion:");
                        zentrallager.zentrallagerbestandChecken();
                        System.out.println("Welches Getränk soll verschickt werden?");
                        sortenname = scanner.next();
                        for (Lagerbestand lager: zentrallager.getZentrallagerbestand()){
                            if (lager.getGetraenk().getName().equals(sortenname)){
                                System.out.println("Wohin soll es verschickt werden");
                                for (Standort standort:standorte){
                                    System.out.println(standort.getStandortName());
                                }
                                standortname = scanner.next();
                                for(Standort standort:standorte){
                                        if (standort.getStandortName().equals(standortname)){
                                     System.out.println("Wie viele Kästen sollen verschickt werden?");
                                     System.out.println(lager.getAnzahlKaesten()+" Kästen sind hier gelagert2");
                                     System.out.println(standort.getLagerkapazitaetInKaesten() +" Kästen können gelagert werden");
                                     anzahl = scanner.nextInt();
                                     if(anzahl<standort.getLagerkapazitaetInKaesten()){
                                         zentrallager.verschicke(standort,sortenname,anzahl);
                                     } else System.out.println("so viele Kästen können nicht verschickt werden");
                                    }
                                }
                                break;
                            }

                        }
                        break;
                    case 3:
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
                        System.out.println("Von wo soll verschickt werden");
                        for (Standort standort:standorte){
                            System.out.println(standort.getStandortName());
                        }
                        urpsrungsort =scanner.next();
                        for (Standort ursprungsort:standorte){
                            if(ursprungsort.getStandortName().equals(urpsrungsort)){
                                System.out.println("Welches Getränk soll verschickt werden?");
                                ursprungsort.lagerbestandChecken();
                                sortenname =scanner.next();
                                for (Lagerbestand lager:ursprungsort.getLagerbestandListe()){
                                    if(lager.getGetraenk().getName().equals(sortenname)){
                                        System.out.println("Wohin soll es verschickt werden?");
                                        for (Standort zielstandort:standorte){
                                            if(zielstandort.getStandortName().equals(urpsrungsort)){
                                                continue;
                                            } else System.out.println(zielstandort.getStandortName());
                                        }
                                        zielort = scanner.next();
                                        for (Standort zielstandort:standorte){
                                            if (zielstandort.getStandortName().equals(zielort)){
                                                System.out.println("Wie viele Kästen sollen verschickt werden?");
                                                System.out.println(lager.getAnzahlKaesten()+" Kästen können verschickt werden");
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

