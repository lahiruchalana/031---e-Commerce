package com.lciresh.cartservice.repository;

import com.lciresh.cartservice.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findItemByItemId(Long itemId);

}
