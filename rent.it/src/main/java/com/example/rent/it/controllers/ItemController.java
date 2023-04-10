package com.example.rent.it.controllers;

import com.example.rent.it.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itens")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;
}
