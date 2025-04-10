package com.rhull.hazfinder.item;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

public record Item(
        @Id
        Integer id,
        String asin,
        String name,
        String ingredients
)
{
}
