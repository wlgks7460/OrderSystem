package com.encore.orederSystem.ordering.repository;

import com.encore.orederSystem.ordering.domain.Ordering;
import com.encore.orederSystem.orderitem.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderingRepository extends JpaRepository<Ordering, Long> {
    List<Ordering> findByMemberId(Long id);
}
