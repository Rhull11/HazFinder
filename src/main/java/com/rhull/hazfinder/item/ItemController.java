package com.rhull.hazfinder.item;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("hazfinder/api/items")
public class ItemController
{
    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    @GetMapping("")
    List<Item> findAll(){
        return itemRepository.findAll();
    }

    @GetMapping("/{id}")
    Item findById(@PathVariable Integer id){
        Optional<Item> item = itemRepository.findById(id);
        if(item.isEmpty())
        {
            throw new ItemNotFoundException();
        }
        return item.get();
    }

    // post
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@RequestBody Item item)
    {
        itemRepository.create(item);
    }

    // put
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@RequestBody Item item, @PathVariable Integer id)
    {
        itemRepository.update(item,id);
    }

    // delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id)
    {
        itemRepository.delete(id);
    }
}
