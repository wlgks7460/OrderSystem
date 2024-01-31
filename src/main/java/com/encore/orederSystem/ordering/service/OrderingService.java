package com.encore.orederSystem.ordering.service;

import com.encore.orederSystem.item.domain.Item;
import com.encore.orederSystem.item.repository.ItemRepository;
import com.encore.orederSystem.member.domain.Member;
import com.encore.orederSystem.member.dto.MemberListResDto;
import com.encore.orederSystem.member.dto.MemberSaveReqDto;
import com.encore.orederSystem.member.repository.MemberRepository;
import com.encore.orederSystem.ordering.domain.Ordering;
import com.encore.orederSystem.ordering.dto.OrderingListReqDto;
import com.encore.orederSystem.ordering.dto.OrderingSaveReqDto;
import com.encore.orederSystem.ordering.repository.OrderingRepository;
import com.encore.orederSystem.orderitem.domain.OrderItem;
import com.encore.orederSystem.orderitem.dto.OrderItemListResDto;
import com.encore.orederSystem.orderitem.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderingService {
    private final OrderingRepository orderingRepository;
    private final MemberRepository memberRepository;
    private final OrderItemRepository orderItemRepository;

    private final ItemRepository itemRepository;

    @Autowired
    public OrderingService(OrderingRepository orderingRepository, MemberRepository memberRepository, OrderItemRepository orderItemRepository, ItemRepository itemRepository){
        this.orderingRepository = orderingRepository;
        this.memberRepository = memberRepository;
        this.orderItemRepository = orderItemRepository;
        this.itemRepository = itemRepository;

    }

    public List<OrderingListReqDto> orders(){
        List<Ordering> orderings = orderingRepository.findAll();
        List<OrderingListReqDto> orderingListReqDtos = new ArrayList<>();
        for(Ordering ordering : orderings){
            OrderingListReqDto orderingListReqDto = new OrderingListReqDto();
            orderingListReqDto.setOrderingId(ordering.getId());
            orderingListReqDto.setOrderStatus(ordering.getOrderStatus());
            orderingListReqDto.setMemberId(ordering.getMember().getId());
            orderingListReqDtos.add(orderingListReqDto);
        }
        return orderingListReqDtos;
    }

    public void save(OrderingSaveReqDto orderingSaveReqDto) throws IllegalArgumentException{

        Member member = memberRepository.findById(orderingSaveReqDto.getMemberId()).orElse(null);
        Ordering.OrderStatus orderStatus = Ordering.OrderStatus.ORDERED;
        Ordering ordering = Ordering.builder()
                .member(member)
                .orderStatus(orderStatus)
                .build();
        orderingRepository.save(ordering);

        List<OrderingSaveReqDto.OrderingItemDto> orderingItemDtos = orderingSaveReqDto.getOrderingItemDtos();
        for(OrderingSaveReqDto.OrderingItemDto orderingItemDto : orderingItemDtos){
            OrderItem orderItem = OrderItem.builder()
                    .item(itemRepository.findById(orderingItemDto.getItemId()).orElse(null))
                    .quantity(orderingItemDto.getCount())
                    .ordering(ordering)
                    .build();
            orderItemRepository.save(orderItem);
            Item item = itemRepository.findById(orderingItemDto.getItemId()).orElseThrow(()->new EntityNotFoundException("검색하신 아이템이 없습니다."));
            if(orderingItemDto.getCount()>item.getStockQuantity()){
                throw new IllegalArgumentException("수량 부족");
            }else{
                item.saveStockQuantity(orderingItemDto.getCount());
            }
        }
    }

    public void cancel(Long id){
        Ordering ordering = orderingRepository.findById(id).orElseThrow(()->new EntityNotFoundException("검색하신 주문이 없습니다."));
        ordering.cancelOrdering(ordering.getOrderStatus());

        List<OrderItem> orderItems = orderItemRepository.findByOrderingId(id);
        for(OrderItem orderItem : orderItems){
            Item item = itemRepository.findById(orderItem.getItem().getId()).orElseThrow(()->new EntityNotFoundException("해당 아이템이 없습니다."));
            item.cancelStockQuantity(orderItem.getQuantity());
        }
    }

}
