public class Defender extends Player {
    public Defender() {
        super(Position.DEFENSE);
    }

    @Override
    public void play() {
        System.out.println("Player " + this.showName() + " is a defender and defends things!");
    }
}
