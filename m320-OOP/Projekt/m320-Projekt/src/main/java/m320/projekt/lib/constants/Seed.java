package m320.projekt.lib.constants;

import m320.projekt.model.Item;
import m320.projekt.model.Role;
import m320.projekt.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Seed {

    public static List<Role> getRoleSeed() {
        List<Role> seeds = new ArrayList<>();

        int roleId = 1;

        for (String roleName : List.of("USER", "MODERATOR", "ADMIN")) {
            Role role = new Role();
            role.setName(roleName);
            role.setId(roleId++);
            seeds.add(role);
        }
        return seeds;
    }

    public static List<Item> getItemSeed() {
        List<Item> seeds = new ArrayList<>();
        List<User> users = getUserSeed();

        for (int i = 1; i <= 15; i++) {
            Item item = new Item();
            item.setId(i);

            if (i <= 5) {
                item.setAuthor(users.get(0));
                item.setTitle("A Users item nr. " + i);
            } else if (i <= 10) {
                item.setAuthor(users.get(1));
                item.setTitle("A Moderators item nr. " + (i - 5));
            } else {
                item.setAuthor(users.get(2));
                item.setTitle("An Admins item nr. " + (i - 10));
            }

            seeds.add(item);
        }

        return seeds;
    }

    public static List<User> getUserSeed() {

        List<User> seeds = new ArrayList<>();
        List<Role> roles = getRoleSeed();

        int userId = 1;
        for (String user : List.of("user", "moderator", "admin")) {
            User userSeed = new User();

            userSeed.setId(userId);
            userSeed.setUsername(user);
            userSeed.setEmail(user + "@localhost.com");
            userSeed.setPassword("P@ssw0rd!14");
            userSeed.setRole(roles.get(userId-1));
            userSeed.setItems(new HashSet<>());

            seeds.add(userSeed);

            userId++;
        }

        return seeds;
    }

}
