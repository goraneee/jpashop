package jpabook.jpashop.item;
import jakarta.persistence.*;

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
