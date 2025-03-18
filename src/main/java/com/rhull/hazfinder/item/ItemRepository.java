package com.rhull.hazfinder.item;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ItemRepository
{
    private List<Item> items = new ArrayList<>();

    List<Item> findAll()
    {
        return items;
    }

    Optional<Item> findById(Integer id)
    {
        return items.stream().filter(item -> item.id() == id).findFirst();
    }

    void create(Item item){
        items.add(item);
    }

    void update(Item item, Integer id)
    {
        Optional<Item> existingItem = findById(id);
        if(existingItem.isPresent()){
            items.set(items.indexOf(existingItem.get()), item);
        }
    }

    void delete(Integer id)
    {
        items.removeIf(item -> item.id().equals(id));
    }

    @PostConstruct
    private void init()
    {
        items.add(new Item(1, "water","water"));
        items.add(new Item(2, "Food Club Honey","honey"));
        items.add(new Item(3, "Nature Made Fish Oil 2000mg","Gelatin, Glycerin, Methacryclic Acid Copolymer"));
    }
}
