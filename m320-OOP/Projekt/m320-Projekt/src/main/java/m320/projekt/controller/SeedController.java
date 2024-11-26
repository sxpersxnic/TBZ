package m320.projekt.controller;

import m320.projekt.lib.constants.Seed;
import m320.projekt.model.Item;
import m320.projekt.model.Role;
import m320.projekt.model.User;
import m320.projekt.service.ItemService;
import m320.projekt.service.RoleService;
import m320.projekt.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static m320.projekt.lib.constants.Controller.*;

@RestController
@RequestMapping(SEED_PATH)
public class SeedController {
    private final UserService userService;
    private final RoleService roleService;
    private final ItemService itemService;

    public SeedController(UserService userService, RoleService roleService, ItemService itemService) {
        this.userService = userService;
        this.roleService = roleService;
        this.itemService = itemService;
    }

    @GetMapping()
    public ResponseEntity<?> seed() {
        try {
            List<Role> roles = Seed.getRoleSeed();
            List<User> users = Seed.getUserSeed();
            List<Item> items = Seed.getItemSeed();

            for (Role role : roles ) {
                roleService.create(role);
            }

            for (User user : users ) {
                userService.create(user);
            }

            for (Item item : items ) {
                itemService.create(item);
            }

            return ResponseEntity.ok().body("Seed Completed");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
