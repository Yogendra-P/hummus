package com.unbxd.hummus.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Field {
    String name;
    String dataType;
    String id;
}
