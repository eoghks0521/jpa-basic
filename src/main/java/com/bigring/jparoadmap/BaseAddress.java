package com.bigring.jparoadmap;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class BaseAddress {

    private String city;
    private String street;
    private String zipcode;

}
