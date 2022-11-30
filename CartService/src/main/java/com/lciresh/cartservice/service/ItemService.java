package com.lciresh.cartservice.service;

import com.lciresh.cartservice.dto.UserAuthDTO;
import com.lciresh.cartservice.exceptions.IllegalPermissionException;
import com.lciresh.cartservice.model.Item;
import com.lciresh.cartservice.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Transactional
public class ItemService implements ItemServiceInterface{

    private final ItemRepository itemRepository;
    private final RestTemplate restTemplate;

    public ItemService(ItemRepository itemRepository, RestTemplate restTemplate) {
        this.itemRepository = itemRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public Item createNewItem(Item item, String token) {
        UserAuthDTO userAuth = restTemplate.getForObject("http://AUTH-SERVICE/api/app-users/user/"+token+"/get-username-and-roles", UserAuthDTO.class);
        String roles = userAuth.getRoles();

        if (roles.contains("ROLE_ADMIN")) {
            itemRepository.save(item);
            return item;
        } else {
            throw new IllegalPermissionException("User has no permission to add items!!");
        }

    }

    @Override
    public List<Item> getItems() {
        return itemRepository.findAll();
    }
}
