package com.unbxd.hummus.services.impl;

import com.unbxd.hummus.execptions.ValidationException;
import com.unbxd.hummus.model.Field;
import com.unbxd.hummus.model.Schema;
import com.unbxd.hummus.services.SchemaService;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class SchemaServiceImplTest {

    private SchemaService schemaService = new SchemaServiceImpl(null);

    private Schema getSchema(String siteKey, List<Field> fields) {
        Schema schema = new Schema();
        schema.setSiteKey(siteKey);
        schema.setFields(fields);
        return schema;
    }

    private Field getField(String name, String dataType, String id)
    {
        Field field = new Field();
        field.setName(name);
        field.setDataType(dataType);
        field.setId(id);
        return field;
    }

    @Test
    public  void  testAddSchema()  {
        //test with siteKey and fields as null
        Throwable exception = assertThrows(ValidationException.class, () ->
            schemaService.addSchema(getSchema(null,null)));
        assertEquals(exception.getMessage(), "siteKey is missing in Schema");

        //test with fields as null
         exception = assertThrows(ValidationException.class, () ->
                 schemaService.addSchema(getSchema("site123",null)));
        assertEquals(exception.getMessage(), "fields are missing in Schema");

        //test with field which does not have name
        List<Field> fields = new ArrayList<>(1);
        fields.add(getField(null,"text","a1"));

        exception = assertThrows(ValidationException.class, () ->
                schemaService.addSchema(getSchema("site123",fields)));
        assertEquals(exception.getMessage(), "name of the Field is missing in Schema");

    }
}