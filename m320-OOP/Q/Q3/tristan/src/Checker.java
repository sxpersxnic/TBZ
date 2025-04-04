import java.util.*;

public class Checker {

    public Optional<Person> doesPersonHaveCar(Person startPerson)
    {
        Queue<Person> queue = new LinkedList<>();
        Set<Person> seen = new HashSet<>();
        queue.add(startPerson);
        while(!queue.isEmpty())
        {
            Person current = queue.poll();

            if(!current.hasCar())
            {
                System.out.println(current.name + " does not have a car");
                seen.add(current);
                queue.addAll(current.getFriends());
                queue.removeAll(seen);
            }
            else
            {
                System.out.println(current.name + " has a car!");
                return Optional.of(current);
            }
        }
        System.out.println("Nobody has a car!");
        return Optional.empty();
    }
}
