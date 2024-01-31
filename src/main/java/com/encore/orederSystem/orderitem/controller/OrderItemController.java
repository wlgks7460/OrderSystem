package com.encore.orederSystem.orderitem.controller;

import com.encore.orederSystem.member.dto.MemberListResDto;
import com.encore.orederSystem.member.service.MemberService;
import com.encore.orederSystem.ordering.service.OrderingService;
import com.encore.orederSystem.orderitem.dto.OrderItemListResDto;
import com.encore.orederSystem.orderitem.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OrderItemController {
    private OrderItemService orderItemService;
    @Autowired
    public OrderItemController(OrderItemService orderItemService){
        this.orderItemService= orderItemService;
    }

    @GetMapping("/orderItems/{id}")
    @ResponseBody
    public List<OrderItemListResDto> orderItems(@PathVariable Long id, Model model){
        model.addAttribute("orderItems", orderItemService.orderItems(id));
        return orderItemService.orderItems(id);
    }
}
