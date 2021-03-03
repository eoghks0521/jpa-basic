package com.bigring.jparoadmap.relation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Team {

    @Id @GeneratedValue
    private Long id;

    @Column(name = "teamname")
    private String name;
}
