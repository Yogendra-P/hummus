package com.unbxd.hummus.services.impl;

import com.google.inject.Inject;
import com.unbxd.hummus.dao.SchemaDao;
import com.unbxd.hummus.execptions.DaoException;
import com.unbxd.hummus.execptions.SchemaNotFoundException;
import com.unbxd.hummus.execptions.ServiceException;
import com.unbxd.hummus.execptions.ValidationException;
import com.unbxd.hummus.model.Field;
import com.unbxd.hummus.model.Schema;
import com.unbxd.hummus.services.SchemaService;

import java.util.List;

public class SchemaServiceImpl implements SchemaService {

    private SchemaDao schemaDao;

    @Inject
    public SchemaServiceImpl(SchemaDao schemaDao) {
        this.schemaDao = schemaDao;
    }

    public void addSchema(Schema schema) throws ServiceException , ValidationException {
        validateSchema(schema);
        try {
            schemaDao.add(schema);
        }
        catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Schema getSchema(String siteKey) throws ServiceException ,SchemaNotFoundException {
        try {
            return schemaDao.get(siteKey);
        }
       catch (DaoException e) {
            throw new ServiceException(e.getMessage());
       }
    }

    private void validateSchema(Schema schema) throws ValidationException {
        if(schema.getSiteKey() == null)
            throw new ValidationException("siteKey is missing in Schema");
        validateFields(schema.getFields());
    }

    private void validateFields(List<Field> fields) throws ValidationException {
        if(fields == null || fields.isEmpty())
            throw new ValidationException("fields are missing in Schema");
        for (Field field : fields) {
            if (field.getName() == null)
                throw new ValidationException("name of the Field is missing in Schema");
            if (field.getDataType() == null)
                throw new ValidationException("dataType of the Field is missing in Schema");
            if (field.getId() == null){
                throw new ValidationException("id of the Field is missing in Schema");}
        }
    }
}
