import java.util.ArrayList;
import java.util.List;

public class Person {
    private Boolean hasCar;
    String name;
    private List<Person> friends;

    public Person(String name, Boolean hasCar){
        this.hasCar = hasCar;
        this.name = name;
        this.friends = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Boolean hasCar() {
        return hasCar;
    }

    public void setFriends(List<Person> friends) {
        this.friends = friends;
    }

    public List<Person> getFriends() {
        return friends;
    }

}
