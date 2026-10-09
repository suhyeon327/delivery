package com.example.delivery.order.service;

import com.example.delivery.menu.entity.Menu;
import com.example.delivery.menu.repository.MenuRepository;
import com.example.delivery.order.dto.request.OrderRequestDto;
import com.example.delivery.order.dto.response.OrderResponseDto;
import com.example.delivery.order.entity.Order;
import com.example.delivery.order.entity.OrderStatusEnum;
import com.example.delivery.order.repository.OrderRepository;
import com.example.delivery.user.entity.User;
import com.example.delivery.user.entity.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;

    public void createOrder(OrderRequestDto requestDto, User user) {
        Long menuId = requestDto.getMenuId();
        Long quantity = requestDto.getQuantity();
        String address = requestDto.getAddress();
        OrderStatusEnum status = OrderStatusEnum.PENDING;

        // 메뉴가 존재하는 지 확인
        Menu menu = menuRepository.findByIdAndDeletedAtIsNull(menuId)
                .orElseThrow(() -> new IllegalArgumentException("메뉴가 존재하지 않습니다."));

        // 총액 계산
        Long totalAmount = menu.getPrice() * quantity;

        // 주문 생성
        Order order = new Order(user, menu, quantity, totalAmount, address, status);

        // DB 저장
        orderRepository.save(order);
    }

    @Transactional(readOnly = true)
    public List<OrderResponseDto> getOrders(User user) {
        List<Order> orders;

        if (user.getRole() == UserRoleEnum.CUSTOMER) {
            orders =  orderRepository.findByUser_Id(user.getId());
        } else if (user.getRole() == UserRoleEnum.OWNER) {
            orders = orderRepository.findByMenu_User_Id(user.getId());
        } else {
            throw new IllegalArgumentException("조회 권한이 없습니다.");
        }

        return orders.stream().map(OrderResponseDto::new).toList();
    }

    public void cancelOrder(Long orderId, User user) {
        // 주문이 DB에 있는지 확인
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문이 존재하지 않습니다."));

        // 본인 주문이 맞는지 확인
        if (!order.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("본인의 주문이 아닙니다.");
        }

        // 주문 요청 상태라면 주문 상태 주문 취소 변경 아니면 거절
        if (order.getStatus() != OrderStatusEnum.PENDING) {
            throw new IllegalArgumentException("주문 요청 상태가 아닙니다. 주문 취소가 불가능합니다.");
        }

        order.cancel();
    }
}
