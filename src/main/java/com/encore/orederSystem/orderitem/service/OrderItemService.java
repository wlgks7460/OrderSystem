package com.encore.orederSystem.orderitem.service;

import com.encore.orederSystem.member.domain.Member;
import com.encore.orederSystem.member.dto.MemberListResDto;
import com.encore.orederSystem.member.repository.MemberRepository;
import com.encore.orederSystem.orderitem.domain.OrderItem;
import com.encore.orederSystem.orderitem.dto.OrderItemListResDto;
import com.encore.orederSystem.orderitem.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository){
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItemListResDto> orderItems(Long id){
        List<OrderItem> orderItems = orderItemRepository.findByOrderingId(id);
        List<OrderItemListResDto> orderItemListResDtos = new ArrayList<>();
        for(OrderItem orderItem : orderItems){
            OrderItemListResDto orderItemListResDto = new OrderItemListResDto();
            orderItemListResDto.setOrderingId(id);
            orderItemListResDto.setItemId(orderItem.getItem().getId());
            orderItemListResDto.setQuantity(orderItem.getQuantity());
            orderItemListResDtos.add(orderItemListResDto);
        }
        return orderItemListResDtos;
    }
}
