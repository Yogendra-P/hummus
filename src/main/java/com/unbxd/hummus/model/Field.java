package com.unbxd.hummus.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Field {
    private String name;
    private String dataType;
    private String id;
}
