public class Main {
    public static void main(String[] args) {
        // Erstellung von Getränken
        Getraenke cola = new Getraenke("Cola", 24, 10);
        Getraenke limonade = new Getraenke("Limonade", 30, 15);

        // Erstellung von Standorten
        Standort standort1 = new Standort("Lager1", 100);
        Standort standort2 = new Standort("Lager2", 150);

        // Erstellung des Zentrallagers
        Zentrallager zentrallager = new Zentrallager("Zentrale");
        zentrallager.initialeLagerbestaende();

        // Verschicken von Getränken vom Zentrallager zu Standorten
        zentrallager.verschicke(standort1, cola, 5);
        zentrallager.verschicke(standort2, limonade, 3);

        // Überprüfen der Lagerbestände in den Standorten
        System.out.println("Lagerbestände in Standort 1:");
        standort1.lagerbestandChecken();
        System.out.println("\nLagerbestände in Standort 2:");
        standort2.lagerbestandChecken();

        // Verkauf von Getränken von Standorten
        standort1.sachenVerkaufen(20);

        // Überprüfen der aktualisierten Lagerbestände nach dem Verkauf
        System.out.println("\nLagerbestände in Standort 1 nach Verkauf:");
        standort1.lagerbestandChecken();

        // Weitere Aktionen im Zentrallager möglich, z.B. Zugriff auf andere Funktionen
        // zentralLager.weitereFunktionImZentralLager();
    }
}
