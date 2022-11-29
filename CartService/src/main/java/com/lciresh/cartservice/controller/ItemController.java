package com.lciresh.cartservice.controller;

import com.lciresh.cartservice.model.Item;
import com.lciresh.cartservice.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/create")
    public ResponseEntity<Item> createNewItem(
            @RequestBody Item item
    ) {
        return new ResponseEntity<>(itemService.createNewItem(item), HttpStatus.CREATED);
    }
}
