import java.util.*;

public class Menu {
    private final Team team;

    public Menu(Team team) {
        this.team = team;
    }

    public void changeGoalie(Goalie goalie) {
        team.setGoalie(goalie);
    }

    public Integer getTeamSize() {
        int count = 0;
        if (team.getGoalie() != null) {
            count++;
        }
        int defenderCount = team.getDefenders().size();
        int strikerCount = team.getStrikers().size();
        return count + defenderCount + strikerCount;
    }

    public void addStriker(Striker striker) {
        Set<Striker> strikers = team.getStrikers();

        if (strikers.contains(striker)) {
            System.out.println("Striker " + striker.showName() + " already exists");
        }
        if (strikers.size() <= 16) {
            strikers.add(striker);
            team.setStrikers(strikers);
        } else {
            System.out.println("Max amount of Strikers reached, remove a striker to add a new one");
        }
    }

    public void addDefender(Defender defender) {
        Set<Defender> defenders = team.getDefenders();

        if (defenders.contains(defender)) {
            System.out.println("Defender " + defender.showName() + " already exists");
        }
        if (defenders.size() <= 4) {
            defenders.add(defender);
            team.setDefenders(defenders);
        } else {
            System.out.println("Max amount of Defenders reached, remove a striker to add a new one");
        }
    }

    public void removeStriker(int index) {

        if (team.getStrikers().size() <= index || team.getDefenders().isEmpty()) {
            System.out.println("Striker " + index + " does not exist");
        }

        Set<Striker> strikers = team.getStrikers();
        List<Striker> strikerList = new ArrayList<>(strikers);
        strikerList.remove(index);
        strikers.addAll(strikerList);
        team.setStrikers(strikers);
    }

    public void removeDefender(int index) {

        if (team.getDefenders().size() < index || team.getDefenders().isEmpty()) {
            System.out.println("Defender " + index + " does not exist");
        }

        Set<Defender> defenders = team.getDefenders();
        List<Defender> defenderList = new ArrayList<>(defenders);
        defenderList.remove(index);
        defenders.addAll(defenderList);
        team.setDefenders(defenders);
    }

    public Team getTeam() {
        return team;
    }

    public void play() {
        Goalie goalie = 
        System.out.println("Your team won ☺!");
    }
}
