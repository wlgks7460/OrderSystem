package com.encore.orederSystem.member.dto;

import com.encore.orederSystem.member.domain.Member;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;


@Data
public class MemberSaveReqDto{
    private String name;
    private String email;
    private String password;
    private String role;
    private LocalDateTime createdTime;
}
