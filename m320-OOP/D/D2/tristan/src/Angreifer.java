public class Angreifer extends Spieler {
    private int schussGeschwindigkeit;

    public Angreifer(String name, int schussGeschwindigkeit) {
        super(name);
        this.schussGeschwindigkeit = schussGeschwindigkeit;
    }

    @Override
    public void spielen() {
        System.out.println(name + " schießt aufs Tor! Der Schuss war " + schussGeschwindigkeit + " km/h schnell!");
    }
}