public class Player {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public Player() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void showName() {
        System.out.println(name);
    }

    public void play() {
        System.out.println(name + " is playing!");
    }
}
