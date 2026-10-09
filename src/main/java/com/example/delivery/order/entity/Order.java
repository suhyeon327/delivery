package com.example.delivery.order.entity;

import com.example.delivery.global.entity.BaseEntity;
import com.example.delivery.menu.entity.Menu;
import com.example.delivery.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private Long totalAmount;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    public Order(User user, Menu menu, Long quantity, Long totalAmount, String address, OrderStatusEnum status) {
        this.user = user;
        this.menu = menu;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.address = address;
        this.status = status;
    }

    public void cancel() {
        this.status = OrderStatusEnum.CANCELLED;
    }

    public void changeStatus(OrderStatusEnum status) {
        this.status = status;
    }
}
