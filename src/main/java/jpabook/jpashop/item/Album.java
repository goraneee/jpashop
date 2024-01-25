package jpabook.jpashop.item;

import javax.persistence.*;
import jpabook.jpashop.item.Item;
import lombok.*;

@Entity
@DiscriminatorValue("A")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Album extends Item {


    private String artist;

    private String etc;

}
