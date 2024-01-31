package com.encore.orederSystem.item.service;

import com.encore.orederSystem.item.domain.Item;
import com.encore.orederSystem.item.dto.ItemListResDto;
import com.encore.orederSystem.item.dto.ItemUpdateReqDto;
import com.encore.orederSystem.item.repository.ItemRepository;
import com.encore.orederSystem.orderitem.repository.OrderItemRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final OrderItemRepository orderItemRepository;

    public ItemService(ItemRepository itemRepository, OrderItemRepository orderItemRepository) {
        this.itemRepository = itemRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public List<ItemListResDto> findAll(){
        List<Item> items = itemRepository.findAll();
        List<ItemListResDto> ItemListResDtos = new ArrayList<>();
        for(Item item : items){
            ItemListResDto itemListResDto = new ItemListResDto();
            itemListResDto.setId(item.getId());
            itemListResDto.setName(item.getName());
            itemListResDto.setStockQuantity(item.getStockQuantity());
            ItemListResDtos.add(itemListResDto);
        }
        return ItemListResDtos;
    }

    public Item findById(Long id) throws EntityNotFoundException {
        Item item = itemRepository.findById(id).orElseThrow(()->new EntityNotFoundException("검색하신 아이템이 없습니다."));
        return item;
    }

//    public void updateItem(Long id, ItemUpdateReqDto itemUpdateReqDto){
//        Item item = this.findById(id);
//        .updateItem(itemUpdateReqDto.getQuantity(),postUpDateReqDto.getContents());
//        postRepository.save(post);
//    }
}
