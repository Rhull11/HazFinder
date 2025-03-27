package com.rhull.hazfinder.item;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends ListCrudRepository<Item, Integer>
{
    Optional<Item> findByAsin(String asin);
}
