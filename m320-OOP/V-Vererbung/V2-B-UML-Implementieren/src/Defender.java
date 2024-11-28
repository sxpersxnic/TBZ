public class Defender extends Player {
    public Defender(String name) {
        super(name);
    }

    @Override
    public void play() {
        System.out.println(getName() + " is playing as a Defender!");
    }
}
