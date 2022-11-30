package com.lciresh.cartservice.controller;

import com.lciresh.cartservice.model.Item;
import com.lciresh.cartservice.service.ItemService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

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

    @PostMapping("/create-item")
    public ResponseEntity<Item> createNewItem(
            @RequestBody Item item,
            HttpServletRequest request
    ) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String token = authorizationHeader.substring("Bearer ".length());
        return new ResponseEntity<>(itemService.createNewItem(item, token), HttpStatus.CREATED);
    }
}
