package com.github.sxpersxnic.tbz.m320.controller;

import com.github.sxpersxnic.tbz.m320.lib.seed.Seed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.sxpersxnic.tbz.m320.lib.constants.Controller.DB_INIT;

/**
 * @author sxpersxnic
 */
@RestController
@RequestMapping(DB_INIT)
public class InitController {
    private final Seed seed;

    public InitController(Seed seed) {
        this.seed = seed;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_MODERATOR')")
    public ResponseEntity<String> seed() {
        try {
            seed.seed();
            return ResponseEntity.ok().body("Seeded");
        } catch (AccessDeniedException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not allowed to access this resource");
        }
    }
}
