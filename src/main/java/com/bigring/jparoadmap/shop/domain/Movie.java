package com.bigring.jparoadmap.shop.domain;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Movie extends Item {

    private String director;
    private String actor;
}
