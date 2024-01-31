package com.encore.orederSystem.ordering.dto;

import com.encore.orederSystem.item.domain.Item;
import com.encore.orederSystem.ordering.domain.Ordering;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderingSaveReqDto {
    private Long memberId;
    private List<OrderingItemDto> orderingItemDtos;

    @Data
    public static class OrderingItemDto{
    private Long itemId;
    private int count;
    }

}
