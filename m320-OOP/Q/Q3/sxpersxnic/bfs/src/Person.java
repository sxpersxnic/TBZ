import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {
    private int key;
    private String name;
    private boolean hasCar;
    private List<Person> friends;

    public Person(int key, String name, boolean hasCar) {
        this.key = key;
        this.name = name;
        this.hasCar = hasCar;
        this.friends = new ArrayList<>();
    }

    public List<Person> getFriends() {
        return friends;
    }

    public void setFriends(List<Person> friends) {
        this.friends = friends;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHasCar() {
        return hasCar;
    }

    public void setHasCar(boolean hasCar) {
        this.hasCar = hasCar;
    }

    public void addFriend(Person friend) {
        this.friends.add(friend);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return key == person.key && hasCar == person.hasCar;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, hasCar);
    }

}
