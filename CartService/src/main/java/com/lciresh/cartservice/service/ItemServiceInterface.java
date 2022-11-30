package com.lciresh.cartservice.service;

import com.lciresh.cartservice.model.Item;

import java.util.List;

public interface ItemServiceInterface {

    Item createNewItem(Item item, String token);

    List<Item> getItems();
}
