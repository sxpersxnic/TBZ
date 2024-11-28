public class Keeper extends Player {

    private double height;

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Keeper(String name, double height) {
        super(name);
        this.height = height;
    }

    @Override
    public void play() {
        double height = getHeight();
        String formattedDouble = String.format("%.2f", height);
        System.out.println(getName() + " is playing as Keeper with the height of " + formattedDouble + "m!");
    }
}
