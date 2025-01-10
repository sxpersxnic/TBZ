import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public static Team start() {
        System.out.println("Welcome to the Team Menu");
        return new Team();
    }

    public static void selectOption(Team team) {
        String option = read("Enter [v] to view your team, [c] to create a player and [p] to play: ");
        switch (option) {
            case "v":
                team.printTeam();
                break;
            case "c":
                String name = read("Enter name: ");
                String positionStr = read("Enter position: ");
                Position position = Position.valueOf(positionStr);
                team.addPlayer(createPlayer(position, name));
                break;
            case "p":
                play(team);
                break;
            default:
                end();
        }
    }

    public static void play(Team team) {
        System.out.println("Match Started!");
        ArrayList<Player> players = team.getPlayers();
        if (!players.isEmpty()) {
            for (Player player : players) {
                player.play();
            }
        } else {
            System.out.println("Team is empty, you lost!");
        }

    }

    public static void end() {
        System.out.println(" ");
        System.out.println("Bye!");
        System.exit(0);
    }

    public static Player createPlayer(Position position, String name) {
        return switch (position) {
            case Keeper -> {
                double height = readDouble("Enter keepers height: ");
                yield createKeeper(height, name);
            }
            case Striker -> createStriker(name);
            case Defender -> createDefender(name);
            default -> {
                yield null;
            }
        };
    }

    public static Keeper createKeeper(double height, String name) {
        return new Keeper(name, height);
    }

    public static Defender createDefender(String name) {
        return new Defender(name);
    }

    public static Striker createStriker(String name) {
        Striker striker = new Striker(name);
        striker.jogTraining();
        return striker;
    }

    public static String read(String out) {
        System.out.println(out);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static double readDouble(String out) {
        System.out.print(out);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextDouble();
    }
}
