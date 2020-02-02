package com.unbxd.hummus.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.lang.NonNull;


import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Schema {
    @NonNull
    String siteKey;
    List<Field> fields;
}
