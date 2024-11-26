import java.util.Set;

public class Team {
    private final String teamName;
    private Goalie goalie = null;
    private Set<Defender> defenders;
    private Set<Striker> strikers;

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public Goalie getGoalie() {
        return goalie;
    }

    public void setGoalie(Goalie goalie) {
        this.goalie = goalie;
    }

    public Set<Defender> getDefenders() {
        return defenders;
    }

    public void setDefenders(Set<Defender> defenders) {
        this.defenders = defenders;
    }

    public Set<Striker> getStrikers() {
        return strikers;
    }

    public void setStrikers(Set<Striker> strikers) {
        this.strikers = strikers;
    }
}
