package com.github.sxpersxnic.tbz.m320.lib.seed;

import com.github.sxpersxnic.tbz.m320.model.Role;
import com.github.sxpersxnic.tbz.m320.service.RoleService;
import com.github.sxpersxnic.tbz.m320.model.User;
import com.github.sxpersxnic.tbz.m320.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author sxpersxnic
 */
@Service
public class Seed {
    private final RoleService roleService;
    private final UserService userService;

    public Seed(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    public void seed() {
        Role admin  = new Role();
        Role moderator = new Role();
        Role user  = new Role();

        User adminUser = new User();
        User moderatorUser = new User();
        User userUser = new User();

        adminUser.setEmail("admin@localhost.com");
        moderatorUser.setEmail("moderator@localhost.com");
        userUser.setEmail("user@localhost.com");

        adminUser.setPassword("admin123");
        moderatorUser.setPassword("moderator123");
        userUser.setPassword("user123");

        admin.setName("ADMIN");
        moderator.setName("MODERATOR");
        user.setName("USER");

        admin.getAssignedUsers().add(adminUser);
        moderator.getAssignedUsers().add(moderatorUser);
        moderator.getAssignedUsers().add(adminUser);
        user.getAssignedUsers().add(adminUser);
        user.getAssignedUsers().add(userUser);

        adminUser.getAssignedRoles().add(admin);
        adminUser.getAssignedRoles().add(moderator);

        moderatorUser.getAssignedRoles().add(moderator);

        roleService.create(admin);
        roleService.create(moderator);
        roleService.create(user);

        userService.create(adminUser);
        userService.create(moderatorUser);
        userService.create(userUser);
    }
}
