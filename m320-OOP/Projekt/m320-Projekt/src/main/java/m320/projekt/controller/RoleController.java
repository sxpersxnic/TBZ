package m320.projekt.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import m320.projekt.model.Role;
import m320.projekt.payload.dto.request.RoleReqDTO;
import m320.projekt.payload.dto.response.RoleResDTO;
import m320.projekt.payload.mapper.RoleMapper;
import m320.projekt.service.RoleService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static m320.projekt.lib.constants.Controller.*;

@RestController
@RequestMapping(ROLE_PATH)
public class RoleController {
    public final RoleService roleService;
    public final RoleMapper roleMapper;

    public RoleController(RoleService roleService, RoleMapper roleMapper) {
        this.roleService = roleService;
        this.roleMapper = roleMapper;
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_MODERATOR')")
    public ResponseEntity<?> findAll() {
        List<Role> roles = roleService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(roles.stream().map(roleMapper::toDTO).toList());
    }

    @GetMapping(ROLE_GET_PATH)
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_MODERATOR')")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        try {
            Role role = roleService.findById(id);
            RoleResDTO dto = roleMapper.toDTO(role);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping(ROLE_POST_PATH)
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody RoleReqDTO reqDTO) {
        try {
            Role newRole = roleMapper.fromDTO(reqDTO);
            Role savedRole = roleService.create(newRole);
            RoleResDTO resDTO = roleMapper.toDTO(savedRole);
            return ResponseEntity.status(HttpStatus.CREATED).body(resDTO);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PatchMapping(ROLE_PATCH_PATH)
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    public ResponseEntity<?> patch(
            @PathVariable Integer id,
            @RequestBody RoleReqDTO reqDTO
    ) {
        try {
            Role patchRole = roleMapper.fromDTO(reqDTO);
            Role savedRole = roleService.update(patchRole, id);
            RoleResDTO resDTO = roleMapper.toDTO(savedRole);
            return ResponseEntity.status(HttpStatus.OK).body(resDTO);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping(ROLE_DELETE_PATH)
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            roleService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
