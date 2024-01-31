package com.encore.orederSystem.item.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemListResDto {
    private Long id;
    private String name;
    private int stockQuantity;
}
