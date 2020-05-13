package com.unbxd.hummus.dao;

import com.unbxd.hummus.execptions.DaoException;
import com.unbxd.hummus.execptions.SchemaNotFoundException;
import com.unbxd.hummus.model.Schema;

public interface SchemaDao {
    void add(Schema schema) throws  DaoException;
    Schema get(String siteKey) throws DaoException , SchemaNotFoundException;
}
