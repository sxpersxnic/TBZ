public class Spieler {
    protected String name;

    public Spieler(String name) {
        this.name = name;
    }

    public void spielen() {
        System.out.println(name + " spielt Fußball.");
    }

    public void zeigeName() {
        System.out.println("Spieler: " + name);
    }
}