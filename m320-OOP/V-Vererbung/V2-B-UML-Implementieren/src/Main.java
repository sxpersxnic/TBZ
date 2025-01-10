public class Main {
    public static void main(String[] args) {
        String choice = Menu.read("Enter s to start, q to quit: ");
        if (choice.equals("s")) {
            Team team = Menu.start();
            while (true) {
                Menu.selectOption(team);
            }
        } else {
            Menu.end();
        }
    }
}
