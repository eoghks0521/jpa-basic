package com.bigring.jparoadmap;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
// DTYPE이 필수로 필요하기 때문에 DiscriminatorColumn 어노테이션이 없어도 DTYPE이 생성된다.
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class BasicItem {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;
}
