package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {

    private String memberName;  // 회원 이름

    private OrderStatus orderStatus;    // 회원 이름
}
