package com.encore.orederSystem.item.repository;

import com.encore.orederSystem.item.domain.Item;
import com.encore.orederSystem.member.domain.Member;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
