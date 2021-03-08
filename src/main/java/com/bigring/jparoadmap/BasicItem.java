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
@Inheritance(strategy = InheritanceType.JOINED)
// 자식 테이블명 DTYPE 추가
@DiscriminatorColumn
public class BasicItem {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;
}
