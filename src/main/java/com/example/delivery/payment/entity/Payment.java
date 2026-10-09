package com.example.delivery.payment.entity;

import com.example.delivery.global.entity.BaseEntity;
import com.example.delivery.order.entity.Order;
import com.example.delivery.order.entity.OrderStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "payments")
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMethodEnum method;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    public Payment(Order order, Long amount, PaymentMethodEnum method, OrderStatusEnum status) {
        this.order = order;
        this.amount = amount;
        this.method = method;
        this.status = status;
    }
}
