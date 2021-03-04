package com.bigring.jparoadmap.relation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Setter
@Getter
@ToString(exclude = "members")
@Entity
public class Team {

    @Id @GeneratedValue
    private Long id;

    @Column(name = "teamname")
    private String name;

    @OneToMany(mappedBy = "team")
    private List<RelationMember> members = new ArrayList<>();
}
