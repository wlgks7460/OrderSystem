package com.encore.orederSystem.ordering.domain;


import com.encore.orederSystem.member.domain.Member;
import com.encore.orederSystem.orderitem.domain.OrderItem;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

//id, member(Member와 ManyToOne관계, NotNull),
// OrderStatus(enum - ORDERED, CANCELED), createdTime, updatedTime
@Getter
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Ordering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "ordering", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    LocalDateTime createdTime;

    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    LocalDateTime updatedTime;

    public enum OrderStatus{
        ORDERED, CANCELED;
    }

    public void cancelOrdering(OrderStatus orderStatus){
        this.orderStatus = OrderStatus.CANCELED;
    }
}
