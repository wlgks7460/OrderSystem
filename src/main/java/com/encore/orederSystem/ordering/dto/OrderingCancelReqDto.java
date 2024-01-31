package com.encore.orederSystem.ordering.dto;

import com.encore.orederSystem.ordering.domain.Ordering;
import lombok.Data;

@Data
public class OrderingCancelReqDto {
    private Ordering.OrderStatus orderStatus;
}
