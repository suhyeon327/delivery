package com.example.delivery.payment.service;

import com.example.delivery.order.entity.Order;
import com.example.delivery.order.entity.OrderStatusEnum;
import com.example.delivery.order.repository.OrderRepository;
import com.example.delivery.payment.dto.request.PaymentRequestDto;
import com.example.delivery.payment.entity.Payment;
import com.example.delivery.payment.entity.PaymentMethodEnum;
import com.example.delivery.payment.repository.PaymentRepository;
import com.example.delivery.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public void processPayment(PaymentRequestDto requestDto, User user) {

        // 주문이 존재하는지 확인
        Order order = orderRepository.findById(requestDto.getOrderId())
                .orElseThrow(() -> new IllegalArgumentException("주문이 존재하지 않습니다."));

        // 본인 주문인지 확인
        if (!order.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("본인의 주문이 아닙니다.");
        }

        // 결제수단이 카드인지 확인
        if (requestDto.getMethod() != PaymentMethodEnum.CARD) {
            throw new IllegalArgumentException("결제는 카드만 가능합니다.");
        }

        // 주문 상태가 PENDING인지 확인
        if (order.getStatus() != OrderStatusEnum.PENDING) {
            throw new IllegalArgumentException("주문 요청 상태가 아닙니다.");
        }

        // 결제 내역 저장
        Payment payment = new Payment(order, order.getTotalAmount(), requestDto.getMethod(), OrderStatusEnum.PAID);
        paymentRepository.save(payment);

        // 주문 상태 변경
        order.changeStatus(OrderStatusEnum.PAID);

    }
}
