package com.encore.orederSystem.member.service;

import com.encore.orederSystem.member.domain.Member;
import com.encore.orederSystem.member.dto.MemberListResDto;
import com.encore.orederSystem.member.dto.MemberSaveReqDto;
import com.encore.orederSystem.member.repository.MemberRepository;
import com.encore.orederSystem.ordering.domain.Ordering;
import com.encore.orederSystem.ordering.dto.OrderingListReqDto;
import com.encore.orederSystem.ordering.repository.OrderingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final OrderingRepository orderingRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository, OrderingRepository orderingRepository){
        this.memberRepository = memberRepository;
        this.orderingRepository = orderingRepository;
    }

    public void save(MemberSaveReqDto memberSaveReqDto) throws IllegalArgumentException{
//        if(memberSaveReqDto.findByEmail(memberSaveReqDto.getEmail()).isPresent()) throw new IllegalArgumentException("중복이메일");
        Member.Role role = null;
        if(memberSaveReqDto.getRole().equals("admin")){
            role = Member.Role.ADMIN;
        }else{
            role = Member.Role.USER;
        }
        Member member = Member.builder()
                .email(memberSaveReqDto.getEmail())
                .name(memberSaveReqDto.getName())
                .password(memberSaveReqDto.getPassword())
                .role(role)
                .build();
        memberRepository.save(member);
    }

    public List<MemberListResDto> members(){
        List<Member> members = memberRepository.findAll();
        List<MemberListResDto> memberListResDtos = new ArrayList<>();
        for(Member member : members){
            MemberListResDto memberListResDto = new MemberListResDto();
            memberListResDto.setId(member.getId());
            memberListResDto.setName(member.getName());
            memberListResDto.setEmail(member.getEmail());
            memberListResDtos.add(memberListResDto);
        }
        return memberListResDtos;
    }

    public List<OrderingListReqDto> findMemberOrders(Long id) throws EntityNotFoundException {
        Member member = memberRepository.findById(id).orElseThrow(()->new EntityNotFoundException("검색하신 ID의 Member가 없습니다."));
        List<Ordering> orderings = orderingRepository.findByMemberId(member.getId());
        List<OrderingListReqDto> orderingListReqDtos2 = new ArrayList<>();
        for(Ordering ordering : orderings){
            OrderingListReqDto orderingListReqDto = new OrderingListReqDto();
            orderingListReqDto.setOrderingId(ordering.getId());
            orderingListReqDto.setOrderStatus(ordering.getOrderStatus());
            orderingListReqDto.setMemberId(ordering.getMember().getId());
            orderingListReqDtos2.add(orderingListReqDto);
        }
        return orderingListReqDtos2;
    }

}
