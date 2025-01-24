package com.github.sxpersxnic.tbz.m320.util;

import com.github.sxpersxnic.tbz.m320.model.Profile;
import com.github.sxpersxnic.tbz.m320.model.User;
import com.github.sxpersxnic.tbz.m320.model.Role;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class DataUtil {

    public static User getTestUser() {
        return getTestUsers().getFirst();
    }

    public static List<User> getTestUsers() {
        List<User> userList = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            User user = new User();
            user.setId(testUUID(i));
            user.setEmail("user" + i + "@foo.bar");
            user.setPassword("password" + i);

            Role role = new Role();
            Profile profile = new Profile("user" + i);

            role.setId(testUUID(3));
            role.setName("USER");
            user.setAssignedRoles(new HashSet<>());
            user.getAssignedRoles().add(role);
            user.getProfiles().add(profile);
            userList.add(user);
        }
        return userList;
    }

    public static Role getTestRole() {
        return getTestRoles().getFirst();
    }

    public static List<Role> getTestRoles() {
        List<Role> roles = new ArrayList<>();

        int roleId = 1;

        for (String roleName : List.of("MANAGER", "ADMIN", "USER")) {
            Role role = new Role();
            role.setName(roleName);
            role.setId(testUUID(roleId++));
            roles.add(role);
        }
        return roles;
    }

    // 1  1  1  1  1  1  1  1  -  1  1  1  1  -  4  1  1  1  -  a  1  1  1  -  1  1  1  1  1  1  1  1  1  1  1  1
    // 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35
    public static UUID testUUID(int number) {
        StringBuilder uuid = new StringBuilder();
        for (int i = 0; i <= 35; i++) {
            if (i == 8 || i == 13 || i == 18 || i == 23) {
                uuid.append("-");
            } else if (i == 14) {
                uuid.append("4");
            } else if (i == 19) {
                uuid.append("8");
            } else {
                uuid.append(number);
            }
        }
        return UUID.fromString(uuid.toString());
    }
}
