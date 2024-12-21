import java.util.List;

public class Main {
    public static void main(String[] args) {
        IntialPersons initializer = new IntialPersons();
        Checker checker = new Checker();

        List<Person> persons = initializer.intitalizePersons();
        checker.doesPersonHaveCar(persons.get(15));
    }
}
