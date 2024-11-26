public class Striker extends Player {

    public Striker() {
        super(Position.STRIKE);
    }

    @Override
    public void play() {
        System.out.println("Im " + this.showName() + " and i strike many goals!");
    }
    public void jogTraining() {
        System.out.println(this.showName() + " is jogging...");
    }
}
