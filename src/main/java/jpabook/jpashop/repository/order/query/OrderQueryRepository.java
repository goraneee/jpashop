package jpabook.jpashop.repository.order.query;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

    private final EntityManager em;

    public List<OrderQueryDto> findOrderQueryDtos() {
        List<OrderQueryDto> result = findOrders();  // query 1번  실행했는데 결과가 2개 나옴
        result.forEach(
                o -> {
                    List<OrderItemQueryDto> orderItems = findOrderItems(o.getOrderId());    // query 2번 실행됨
                    o.setOrderItems(orderItems);
                });
        return result;
    }

    private List<OrderItemQueryDto> findOrderItems(Long orderId) {

        return em.createQuery("SELECT new jpabook.jpashop.repository.order.query.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count)"
                        + " FROM OrderItem oi"
                        + " JOIN oi.item i "
                        + " WHERE oi.order.id = :orderId", OrderItemQueryDto.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }

    private List<OrderQueryDto> findOrders() {
        return em.createQuery("SELECT new jpabook.jpashop.repository.order.query.OrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address)  "
                        + " FROM Order o "
                        + " JOIN o.member m"
                        + " JOIN o.delivery d", OrderQueryDto.class)
                .getResultList();
    }

    private List<OrderItemQueryDto> findOrderItemMap(List<Long> orderIds) {
        List<OrderItemQueryDto> orderItems = em.createQuery("SELECT new jpabook.jpashop.repository.order.query.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count)"
                        + " FROM OrderItem oi"
                        + " JOIN oi.item i "
                        + " WHERE oi.order.id IN :orderIds", OrderItemQueryDto.class)
                .setParameter("orderIds", orderIds)
                .getResultList();
        return orderItems;
    }

    private static List<Long> toOrderIds(List<OrderQueryDto> result) {
        List<Long> orderIds = result.stream()
                .map(o -> o.getOrderId())
                .collect(Collectors.toList());
        return orderIds;
    }

    public List<OrderQueryDto> findAllByDto_optimization() {
        List<OrderQueryDto> result = findOrders();
        List<Long> orderIds = toOrderIds(result);
        // 쿼리를 한 번 날리고
        List<OrderItemQueryDto> orderItems = findOrderItemMap(orderIds);

        // 매모리에 내용을 map으로 가져온다.
        Map<Long, List<OrderItemQueryDto>> orderItemMap = orderItems.stream()
                .collect(Collectors.groupingBy(orderItemQueryDto -> orderItemQueryDto.getOrderId()));
        result.forEach(o -> o.setOrderItems(orderItemMap.get(o.getOrderId()))); // 루프를 돌면서 모자랐던 컬렉션 데이터를 채운다?
        orderItems.stream().collect(Collectors.groupingBy(OrderItemQueryDto::getOrderId));
        return result;
    }

    public List<OrderFlatDto> findAllByDto_flat() {
        return em.createQuery("SELECT new jpabook.jpashop.repository.order.query.OrderFlatDto(o.id, m.name, o.orderDate, " +
                        "o.status , d.address, i.name, oi.orderPrice, oi.count)"
                        + " FROM Order o"
                        + " JOIN o.member m"
                        + " JOIN o.delivery d"
                        + " JOIN o.orderItems oi"
                        + " JOIN oi.item i", OrderFlatDto.class)
                .getResultList();
    }
}