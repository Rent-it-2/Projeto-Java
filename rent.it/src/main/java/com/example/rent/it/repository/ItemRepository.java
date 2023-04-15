package com.example.rent.it.repository;

import com.example.rent.it.object.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    boolean existsById(Long id);
    Optional<Item> findById(Long id);

}
