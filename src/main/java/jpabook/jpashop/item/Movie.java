package jpabook.jpashop.item;

import javax.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("M")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie extends Item {

    private String director;

    private String actor;

}
