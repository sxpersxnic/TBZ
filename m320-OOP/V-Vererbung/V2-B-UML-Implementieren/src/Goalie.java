public class Goalie extends Player {

    private double height;

    public Goalie(double height) {
        super(Position.GOAL);
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public void play() {
        System.out.println("Im " + this.showName() + " and i play as the Goalie!");
    }
}
