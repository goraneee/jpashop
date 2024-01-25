package jpabook.jpashop.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateMemberResponse {
    private Long id;
    private String name;
}
