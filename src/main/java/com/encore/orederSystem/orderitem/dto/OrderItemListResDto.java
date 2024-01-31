package com.encore.orederSystem.orderitem.dto;

import com.encore.orederSystem.item.domain.Item;
import com.encore.orederSystem.ordering.domain.Ordering;
import lombok.Data;

@Data
public class OrderItemListResDto {
    private Long itemId;
    private int quantity;
    private Long orderingId;
}
