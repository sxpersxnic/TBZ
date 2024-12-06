public class Main {
    public static void main(String[] args) {
        // Erstellung der Spieler
        Spieler goalie = new Goalie("Manuel Neuer", 1.93);
        Spieler angreifer = new Angreifer("Lionel Messi", 120);
        Spieler verteidiger = new Verteidiger("Sergio Ramos", 83.6);

        // Spieler in Aktion
        goalie.spielen();
        angreifer.spielen();
        verteidiger.spielen();
    }
}