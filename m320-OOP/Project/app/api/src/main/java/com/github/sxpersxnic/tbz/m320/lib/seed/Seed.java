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
        User adminUser = new User();
        User moderatorUser = new User();
        User userUser = new User();

        adminUser.setEmail("admin@localhost.com");
        moderatorUser.setEmail("moderator@localhost.com");
        userUser.setEmail("user@localhost.com");

        adminUser.setPassword("admin123");
        moderatorUser.setPassword("moderator123");
        userUser.setPassword("user123");

        Role admin = roleService.findByName("ADMIN");
        Role moderator = roleService.findByName("MODERATOR");

        adminUser.getAssignedRoles().add(admin);
        adminUser.getAssignedRoles().add(moderator);

        moderatorUser.getAssignedRoles().add(moderator);

        userService.create(adminUser, "admin");
        userService.create(moderatorUser, "moderator");
        userService.create(userUser, "user");
    }
}
