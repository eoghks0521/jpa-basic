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
// 이 전략은 애초에 테이블로 구분이 되기 때문에 DiscriminatorColumn 을 사용해도 적용이 안된다.
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// BasicItem 은 단독으로 사용될 일이 없기 때문에 애초에 처음부터 추상클래스로 만들었어야한다.
public abstract class BasicItem {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;
}
