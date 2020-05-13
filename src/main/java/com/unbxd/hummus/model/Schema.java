package com.unbxd.hummus.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.lang.NonNull;


import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Schema {
    private String siteKey;
    private List<Field> fields;
}
