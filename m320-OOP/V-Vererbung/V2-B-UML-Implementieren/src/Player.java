public class Player {
    private String name;
    private Position position;

    public Player(Position position) {
        this.position = position;
    }

    public String showName() {
        return this.name;
    }

    public void play() {
        System.out.println("Im a Player!");
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
