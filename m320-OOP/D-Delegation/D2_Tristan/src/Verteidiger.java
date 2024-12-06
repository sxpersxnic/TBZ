public class Verteidiger extends Spieler {
    private double zweikampfQuote;

    public Verteidiger(String name, double zweikampfQuote) {
        super(name);
        this.zweikampfQuote = zweikampfQuote;
    }

    @Override
    public void spielen() {
        System.out.println(name + " verteidigt das Tor! Seine Zweikampf Quote ist: " + zweikampfQuote + "%!");
    }
}