import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        List<Person> persons = Seed.seedPersons();
        Seed.connectPersons(persons);

        Node<Person> root = Seed.buildNetworkGraph(persons);

        Optional<Node<Person>> result = BFSAlgorithm.search(
                persons.get(38),
                root
        );

        if (result.isPresent() && result.get().getValue().isHasCar()) {
            Person foundPerson = result.get().getValue();
            System.out.println("Found person with car: " + foundPerson.getName() + " (Key: " + foundPerson.getKey() + ")");
        } else if (result.isPresent()) {
            Person foundPerson = result.get().getValue();
            System.out.println("Found person without car: " + foundPerson.getName() + " (Key: " + foundPerson.getKey() + ")");

        } else {
            System.out.println("No person with car found.");
        }
    }
}
