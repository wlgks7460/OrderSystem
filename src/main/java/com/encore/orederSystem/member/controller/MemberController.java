package com.encore.orederSystem.member.controller;

import com.encore.orederSystem.member.domain.Member;
import com.encore.orederSystem.member.dto.MemberListResDto;
import com.encore.orederSystem.member.dto.MemberSaveReqDto;
import com.encore.orederSystem.member.service.MemberService;
import com.encore.orederSystem.ordering.dto.OrderingListReqDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MemberController {

    private MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService= memberService;
    }

    @GetMapping("/member/new")
    @ResponseBody
    public String memberSaveScreen(){
        return "ok";
    }

    @PostMapping("member/new")
    @ResponseBody
    public String memberSave(MemberSaveReqDto memberSaveReqDto) {
        memberService.save(memberSaveReqDto);
        return "ok";
    }

    @GetMapping("members")
    @ResponseBody
    public List<MemberListResDto> members(Model model){
        model.addAttribute("members", memberService.members());
        return memberService.members();
    }
// 주문 상세
    @GetMapping("/member/{id}/orders")
    @ResponseBody
    public List<OrderingListReqDto> memberOrders(@PathVariable Long id, Model model){
        model.addAttribute("memberOrders", memberService.findMemberOrders(id));
        return memberService.findMemberOrders(id);
    }


}
