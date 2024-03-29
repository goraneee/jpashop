package jpabook.jpashop.item;
import jakarta.persistence.*;

import lombok.*;

@Entity
@DiscriminatorValue("B")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book  extends Item {

    private String author;

    private String isbn;

}
