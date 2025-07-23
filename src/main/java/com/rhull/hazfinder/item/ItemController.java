package com.rhull.hazfinder.item;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("hazfinder/api/items")
public class ItemController
{
    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository)
    {
        this.itemRepository = itemRepository;
    }

    @GetMapping("")
    List<Item> findAll()
    {
        return itemRepository.findAll();
    }

    @GetMapping("/{id}")
    Item findById(@PathVariable Integer id)
    {

        Optional<Item> item = itemRepository.findById(id);
        if (item.isEmpty())
        {
            throw new ItemNotFoundException();
        }
        return item.get();
    }

    @GetMapping("/asin/{asin}")
    Item findByAsin(@PathVariable String asin)
    {
        Optional<Item> item = itemRepository.findByAsin(asin);
        if (item.isEmpty())
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
        itemRepository.save(item);
    }

    // put
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@RequestBody Item item, @PathVariable Integer id)
    {
        itemRepository.save(item);
    }

    // delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id)
    {
        try
        {
            itemRepository.delete(itemRepository.findById(id).get());
        }
        catch (NoSuchElementException ex)
        {
            throw new ItemNotFoundException();
        }
    }
}
