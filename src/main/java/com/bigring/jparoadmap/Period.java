package com.bigring.jparoadmap;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Period {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
