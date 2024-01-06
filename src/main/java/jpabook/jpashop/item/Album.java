package jpabook.jpashop.item;

import jakarta.persistence.*;
import jpabook.jpashop.item.Item;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("A")
@Getter
@Setter
public class Album extends Item {


    private String artist;

    private String etc;

}
