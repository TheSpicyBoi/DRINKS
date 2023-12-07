import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class KonsolenManagement {

    static Scanner scanner;

    /**
     * Die Methode kann gestartet werden um die Konsolenschleife zu starten, welche wiederholt die aktionsmöglichkeiten für den Nutzer aus gibt
     */
    public static void starteEingabeSchleife(){
        scanner = new Scanner(System.in);

        int option = 0;

        while (option != 6) {
            System.out.println("\n--- Menü ---");
            System.out.println("1. Getränke verkaufen");
            System.out.println("2. Getränke verschieben");
            System.out.println("3. Lagerbestände checken");
            System.out.println("4. Lagerbestände aus Zentrallager auffüllen");
            System.out.println("5. Beenden");
            System.out.print("Wähle eine Option: ");

            boolean programmShouldClose = false;
            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                String standortname = null;

                int anzahl;
                switch (option) {
                    case 1:
                        optionVerkaufen();
                        break;
                    case 2:
                        optionVerschieben();
                        break;
                    case 3:
                        optionLagerbestaendeChecken();
                        break;
                    case 4:
                        System.out.println("Welches Lager soll nachgefüllt werden?");
                        for (VerkaufsStandort standort :DrinksManagement.verkaufsStandorte){
                            System.out.println(standort.getName());
                        }
                        standortname = scanner.next();
                        for (VerkaufsStandort standort :DrinksManagement.verkaufsStandorte){
                            if (standort.getName().equals(standortname)){
                                //DrinksManagement.zentrallager.nachschicken(standort);
                                standort.lagerbestandAuffuellen(DrinksManagement.zentrallager);
                            }else break;
                        }
                        break;
                    case 5:
                        System.out.println("Programm wird beendet.");
                        programmShouldClose = true;
                        break;
                    default:
                        System.out.println("Ungültige Option. Bitte wähle eine Zahl von 1 bis 6.");
                        break;

                }

            } else {
                System.out.println("Bitte gebe eine Zahl ein.");
                scanner.next();
            }

            if(programmShouldClose)
                break;
        }

        scanner.close();
    }

    private static void optionVerkaufen(){
        System.out.println("Verkaufsaktion:");
        System.out.println("Wo soll verkauft werden");
        for (VerkaufsStandort verkaufsStandort : DrinksManagement.verkaufsStandorte){
            System.out.println(verkaufsStandort.getName());
        }
        String standortname = scanner.next();
        VerkaufsStandort standort = null;
        for (VerkaufsStandort verkaufsStandort : DrinksManagement.verkaufsStandorte) {
            if (verkaufsStandort.getName().equals(standortname)) {
                standort = verkaufsStandort;
            }
        }
        if(standort == null){
            System.out.println("Standort konnte nicht identifiziert werden");
            return;
        }

        System.out.println("Welches Getränk soll verkauft werden?");
        standort.lagerbestandAusgeben();
        String verkauftesache = scanner.next();

        System.out.println("Wie viele Flaschen sollen verkauft werden?");
        int anzahl = scanner.nextInt();
        standort.getraenkeVerkaufen(verkauftesache, anzahl);

    }
    private static void optionVerschieben(){
        System.out.println("Von Wo soll es verschoben werden");
        for (Standort standort :DrinksManagement.standorte) {
            System.out.println(standort.getName());
        }
        String startStandortName = scanner.next();
        System.out.println("Wohin soll es verschoben werden");
        for (Standort standort :DrinksManagement.standorte){
            System.out.println(standort.getName());
        }
        String zielStandortName = scanner.next();
        Standort startStandort = null;
        Standort zielStandort = null;
        for(Standort standort :DrinksManagement.standorte){
            if(startStandortName.equals(standort.getName()))
                startStandort = standort;
            if(zielStandortName.equals(standort.getName()))
                zielStandort = standort;
        }
        if(startStandort == null || zielStandort == null){
            System.out.println("Die Standortnamen konnten nicht identifiziert werden");
            return;
        }

        System.out.println("Welches Getränk soll verschickt werden?");
        startStandort.lagerbestandAusgeben();
        String sortenname = scanner.next();

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
            return;
        }

        System.out.println("Wie viele Kästen sollen verschickt werden?");
        System.out.println(startLagerbestand.getAnzahlKaesten()+" Kästen sind im Start Standort");
        System.out.println((zielLagerbestand.getGetraenk().getstandortmax(zielStandort) - zielLagerbestand.getAnzahlKaesten())+" Kästen können noch in den Ziel Standort gelagert werden");
        int anzahl = 0;
        if(scanner.hasNextInt())
            anzahl = scanner.nextInt();
        else return;
        startStandort.verschiebe(zielStandort,sortenname,anzahl);
    }

    private static void optionLagerbestaendeChecken(){
        System.out.println("Lagerbestände:");
        for (Standort standort :DrinksManagement.standorte){
            System.out.println(standort.getName());
        }
        String standortname = scanner.next();
        for (Standort standort :DrinksManagement.standorte){
            if (standort.getName().equals(standortname)){
                standort.lagerbestandAusgeben();
            }
        }
    }

}
