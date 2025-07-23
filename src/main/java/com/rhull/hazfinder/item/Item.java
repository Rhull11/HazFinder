package com.rhull.hazfinder.item;

import org.springframework.data.annotation.Id;

public record Item(
        @Id
        Integer id,
        String thumbnail,
        String asin,
        String name,
        String ingredients
)
{
}
