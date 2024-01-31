package com.encore.orederSystem.ordering.controller;

import com.encore.orederSystem.member.dto.MemberListResDto;
import com.encore.orederSystem.member.dto.MemberSaveReqDto;
import com.encore.orederSystem.ordering.dto.OrderingListReqDto;
import com.encore.orederSystem.ordering.dto.OrderingSaveReqDto;
import com.encore.orederSystem.ordering.service.OrderingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrderingController {

    private OrderingService orderingService;
    @Autowired
    public OrderingController(OrderingService orderingService){
        this.orderingService = orderingService;
    }
    @GetMapping("order/new")
    @ResponseBody
    public String orderSaveScreen(){
        return "ok";
    }

    @PostMapping("order/new")
    @ResponseBody
    public String orderSave(@RequestBody OrderingSaveReqDto orderingSaveReqDto) {
        orderingService.save(orderingSaveReqDto);
        return "ok";
    }

    @GetMapping("/orders")
    @ResponseBody
    public List<OrderingListReqDto> orders(Model model){
        model.addAttribute("orders", orderingService.orders());
        return orderingService.orders();
    }

    @PostMapping("/order/{id}/cancel")
    @ResponseBody
    public String orderCancel(@PathVariable Long id){
        orderingService.cancel(id);
        return "ok";
    }


}
