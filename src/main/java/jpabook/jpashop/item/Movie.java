package jpabook.jpashop.item;

import jakarta.persistence.*;
import jpabook.jpashop.item.Item;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("M")
@Getter
@Setter
public class Movie extends Item {

    private String director;

    private String actor;

}
