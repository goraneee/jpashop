package jpabook.jpashop.item;

import jakarta.persistence.*;
import jpabook.jpashop.item.Item;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("B")
@Getter
@Setter
public class Book  extends Item {

    private String author;

    private String ispn;

}
