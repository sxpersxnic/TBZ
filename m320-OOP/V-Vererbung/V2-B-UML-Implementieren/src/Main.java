
public class Main {
    public static void main(String[] args) {

        System.out.println("Enter Team-Name: ");
        String teamName = System.console().readLine();
        Team team = new Team(teamName);
        Menu menu = new Menu(team);
        System.out.println("[1] play [2] add player [3] remove player [4] change player");
        System.out.println("Enter action: ");
        int action = Integer.parseInt(System.console().readLine());

        switch (action) {
            case 1:
                menu.play();
            case 2:
                System.out.println("[1] Striker [2] Defender [3] Goalie");
                System.out.println("Select player:");
                int choice = Integer.parseInt(System.console().readLine());

                switch (choice) {
                    case 1:
                        Striker striker = new Striker();

                        System.out.println("Enter Name: ");
                        String strikerName = System.console().readLine();
                        striker.setName(strikerName);

                        menu.addStriker(striker);
                    case 2:
                        Defender defender = new Defender();
                        System.out.println("Enter Name: ");
                        String defenderName = System.console().readLine();
                        defender.setName(defenderName);
                        menu.addDefender(defender);
                    case 3:
                        System.out.println("Enter Name: ");
                        String goalieName = System.console().readLine();
                        double height = Double.parseDouble(System.console().readLine());
                        Goalie goalie = new Goalie(height);
                        goalie.setName(goalieName);

                        menu.changeGoalie(goalie);
                }
            case 3:

        }
    }
}
