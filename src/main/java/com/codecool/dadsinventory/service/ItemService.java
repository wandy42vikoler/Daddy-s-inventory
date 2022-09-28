package com.codecool.dadsinventory.service;

import com.codecool.dadsinventory.model.Item;
import com.codecool.dadsinventory.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {

    ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllBySearchTerm(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            searchTerm = "";
        }
        String finalSearchTerm = searchTerm;
        List<Item> items = itemRepository.findAll()
                .stream()
                .filter(item -> item.getName()
                        .contains(finalSearchTerm))
                .collect(Collectors.toList());
        return items;
    }

    public Item getById(Long id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        return optionalItem.orElseThrow(NoSuchElementException::new);
    }
}
