package com.unbxd.hummus.services;

import com.unbxd.hummus.execptions.SchemaNotFoundException;
import com.unbxd.hummus.execptions.ServiceException;
import com.unbxd.hummus.execptions.ValidationException;
import com.unbxd.hummus.model.Schema;

public interface SchemaService {
    public void addSchema(Schema schema) throws ServiceException , ValidationException;
    public Schema getSchema(String siteKey) throws ServiceException , SchemaNotFoundException;
}
