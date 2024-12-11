import java.util.*;

public class Seed {
    public static List<Person> seedPersons() {
        List<Person> persons = new ArrayList<>();

        for (int i = 1; i <= 50; i++) {
            Person person;
            if (i == 39) {
                person = new Person(39, "Target", true);
            } else {
                person = new Person(i, Util.getRandomName(), false);
            }
            persons.add(person);
        }

        return persons;
    }

    public static void connectPersons(List<Person> persons) {
        Random random = new Random();

        for (Person person : persons) {
            int numberOfFriends = random.nextInt(5) + 1;
            for (int i = 0; i < numberOfFriends; i++) {
                Person friend = persons.get(random.nextInt(persons.size()));
                if (!person.getFriends().contains(friend) && person != friend) {
                    person.addFriend(friend);
                }
            }
        }
    }

    public static Node<Person> buildNetworkGraph(List<Person> persons) {
        Map<Integer, Node<Person>> personNodeMap = new HashMap<>();

        for (Person person : persons) {
            personNodeMap.put(person.getKey(), new Node<>(person));
        }

        for (Person person : persons) {
            Node<Person> personNode = personNodeMap.get(person.getKey());
            for (Person friend : person.getFriends()) {
                Node<Person> friendNode = personNodeMap.get(friend.getKey());
                personNode.connect(friendNode);
            }
        }

        return personNodeMap.get(1);
    }
}
