package com.lciresh.cartservice.service;

import com.lciresh.cartservice.model.Item;
import com.lciresh.cartservice.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ItemService implements ItemServiceInterface{

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item createNewItem(Item item) {
        itemRepository.save(item);
        return item;
    }

    @Override
    public List<Item> getItems() {
        return itemRepository.findAll();
    }
}
