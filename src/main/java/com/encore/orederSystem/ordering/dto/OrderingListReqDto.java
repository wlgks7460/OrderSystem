package com.encore.orederSystem.ordering.dto;

import com.encore.orederSystem.ordering.domain.Ordering;
import lombok.Data;

@Data
public class OrderingListReqDto {
    private Long memberId;
    private Ordering.OrderStatus orderStatus;
    private Long orderingId;
}
