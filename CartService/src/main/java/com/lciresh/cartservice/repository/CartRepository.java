package com.lciresh.cartservice.repository;

import com.lciresh.cartservice.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findCartByUsername(String username);

    Optional<Cart> getCartByUsername(String username);

}
