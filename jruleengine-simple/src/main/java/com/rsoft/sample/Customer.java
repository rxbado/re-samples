package com.rsoft.sample;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
    private String name;
    private String location;
    private Integer age;

}
