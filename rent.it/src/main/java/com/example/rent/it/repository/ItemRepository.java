package com.example.rent.it.repository;

import com.example.rent.it.object.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
