package com.codecool.dadsinventory.controller;

import com.codecool.dadsinventory.model.Item;
import com.codecool.dadsinventory.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class ItemController {

    ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/search")
    public String getAllBySeachTerm(@RequestParam(name = "searchterm") String searchTerm, Model model) {
        List<Item> items = itemService.getAllBySearchTerm(searchTerm);
        String title = "Dad's inventory";
        model.addAttribute("title", title);
        model.addAttribute("items", items);
        return "lists";
    }

    @GetMapping("/item/details/{id}")
    public String getItemById(@PathVariable("id") Long id, Model model) {
        Item item = itemService.getById(id);
        model.addAttribute("item", item);
        return "details";
    }
}
