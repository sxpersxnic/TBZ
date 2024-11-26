package m320.projekt.util;

import m320.projekt.model.Item;
import m320.projekt.model.Role;
import m320.projekt.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DataUtil {
    public static User getTestUser(Integer index) {
        return getTestUsers().get(index);
    }

    public static List<User> getTestUsers() {
        List<User> userList = new ArrayList<User>();

        for (int i = 1; i <= 4; i++) {
            User user = new User();
            user.setId(i);
            user.setUsername("test_user" + i);
            user.setEmail("test_user" + i + "@localhost.com");
            user.setPassword("P@ssw0rd" + i);
            if (i == 4) {
             user.setRole(getTestRole(1));
            } else {
             user.setRole(getTestRole(i));
            }
            user.setItems(new HashSet<Item>());
            userList.add(user);
        }
        return userList;
    }

    public static Item getTestItem(int index) {
        return getTestItems().get(index);
    }

    public static List<Item> getTestItems() {
        List<Item> itemList = new ArrayList<Item>();

        for (int i = 1; i <= 8; i++) {
            Item item = new Item();

            item.setId(i);
            item.setTitle("Test Item " + i);

            if (i <= 4) {
                item.setAuthor(getTestUser(i));
            } else {
                item.setAuthor(getTestUser(i-4));
            }
        }

        return itemList;
    }

    public static Role getTestRole(int index) {
        return getTestRoles().get(index);
    }

    public static List<Role> getTestRoles() {
        List<Role> roleList = new ArrayList<Role>();
        Integer roleId = 1;
        for (String name : List.of("user", "moderator", "admin")) {
            Role role = new Role();
            role.setId(roleId);
            role.setName(name);
            role.setUsers(new HashSet<User>());
            roleId++;
        }
        return roleList;
    }
}
