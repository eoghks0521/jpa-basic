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
// 기본값은 Entity 명이지만 변경 가능하다.
@DiscriminatorValue("A")
public class BasicAlbum extends BasicItem {

    private String artist;
}
