package com.bigring.jparoadmap;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@DiscriminatorValue("B")
public class BasicBook extends BasicItem {

    private String author;
    private String isbn;
}
