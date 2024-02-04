package jpabook.jpashop.repository.order.simplequery;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.repository.OrderSimpleQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {

    private final EntityManager em;

    public List<OrderSimpleQueryDto> findOrderDto(){
        return em.createQuery("SELECT new jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address)  "
                + "FROM Order o"
                + " JOIN o.member m "
                + " JOIN o.delivery d", OrderSimpleQueryDto.class).getResultList();
    }
}