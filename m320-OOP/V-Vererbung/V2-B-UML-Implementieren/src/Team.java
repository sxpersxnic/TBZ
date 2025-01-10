import java.util.ArrayList;

public class Team {
    private ArrayList<Player> players;

    public Team() {
        this.players = new ArrayList<>();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void printTeam() {
        if (!players.isEmpty()) {
            for (Player player : players) {
                player.showName();
            }
        } else {
            System.out.println("Team is empty");
        }
    }
}
