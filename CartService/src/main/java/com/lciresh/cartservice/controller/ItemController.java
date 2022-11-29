package com.lciresh.cartservice.controller;

import com.lciresh.cartservice.model.Item;
import com.lciresh.cartservice.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/all-items")
    public ResponseEntity<List<Item>> getItems() {
        return new ResponseEntity<>(itemService.getItems(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Item> createNewItem(
            @RequestBody Item item
    ) {
        return new ResponseEntity<>(itemService.createNewItem(item), HttpStatus.CREATED);
    }
}
