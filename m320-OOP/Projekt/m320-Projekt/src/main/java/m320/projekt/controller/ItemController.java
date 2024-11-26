package m320.projekt.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import m320.projekt.model.Item;
import m320.projekt.payload.dto.request.ItemReqDTO;
import m320.projekt.payload.dto.response.ItemResDTO;
import m320.projekt.payload.mapper.ItemMapper;
import m320.projekt.service.ItemService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static m320.projekt.lib.constants.Controller.*;

@RestController
@RequestMapping(ITEM_PATH)
public class ItemController {
    private final ItemService itemService;
    private final ItemMapper itemMapper;

    public ItemController(ItemService itemService, ItemMapper itemMapper) {
        this.itemService = itemService;
        this.itemMapper = itemMapper;
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<Item> items = itemService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(items.stream().map(itemMapper::toDTO).toList());
    }

    @GetMapping(ITEM_GET_PATH)
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        try {
            Item item = itemService.findById(id);
            ItemResDTO dto = itemMapper.toDTO(item);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping(ITEM_POST_PATH)
    public ResponseEntity<?> create(@Valid @RequestBody ItemReqDTO reqDTO) {
        try {
            Item newItem = itemMapper.fromDTO(reqDTO);
            Item savedItem = itemService.create(newItem);
            ItemResDTO resDTO = itemMapper.toDTO(savedItem);
            return ResponseEntity.status(HttpStatus.CREATED).body(resDTO);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PatchMapping(ITEM_PATCH_PATH)
    public ResponseEntity<?> patch(
            @PathVariable Integer id,
            @RequestBody ItemReqDTO reqDTO
    ) {
        try {
            Item patchItem = itemMapper.fromDTO(reqDTO);
            Item savedItem = itemService.update(patchItem, id);
            ItemResDTO resDTO = itemMapper.toDTO(savedItem);
            return ResponseEntity.status(HttpStatus.OK).body(resDTO);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping(ITEM_DELETE_PATH)
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            itemService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
