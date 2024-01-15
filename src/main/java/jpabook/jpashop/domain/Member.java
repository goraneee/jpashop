package jpabook.jpashop.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @NotEmpty
    @Column(name = "NAME")
    private String name;

    @Embedded
    @Column(name = "ADDRESS")
    private Address address;

    @OneToMany(mappedBy = "member") // id 뺴고??
    private List<Order> orders = new ArrayList<>();

    @Column(name = "CREATEDBY")
    private String createdBy;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "CREATEDDATE")
    private String createdDate;

    @Column(name = "LASTMODIFIEDBY")
    private String lastModifiedBy;

    @Column(name = "LASTMODIFIEDDATE")
    private String lastModifiedDate;



}
