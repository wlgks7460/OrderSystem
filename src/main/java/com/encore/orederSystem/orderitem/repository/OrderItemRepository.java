package com.encore.orederSystem.orderitem.repository;

import com.encore.orederSystem.item.domain.Item;
import com.encore.orederSystem.member.domain.Member;
import com.encore.orederSystem.orderitem.domain.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderingId(Long id);
}
