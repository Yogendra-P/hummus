package com.unbxd.hummus.dao.impl;

import com.mongodb.MongoClientException;
import com.mongodb.client.MongoCollection;
import com.unbxd.hummus.dao.SchemaDao;
import com.unbxd.hummus.execptions.DaoException;
import com.unbxd.hummus.execptions.SchemaNotFoundException;
import com.unbxd.hummus.model.Schema;

import static com.mongodb.client.model.Filters.eq;

public class MongoDao implements SchemaDao {

    private MongoCollection<Schema> collection;

    public MongoDao(MongoCollection<Schema> collection) {
        this.collection = collection;
    }

    public void add(Schema schema) throws DaoException{
        try {
            collection.insertOne(schema);
        }
        catch (MongoClientException e) {
            throw new DaoException(e.getMessage());
        }
    }

    public Schema get(String siteKey) throws SchemaNotFoundException , DaoException {
        try {
            Schema schema = collection.find(eq("siteKey",siteKey)).first();
            if( schema == null )
                throw new SchemaNotFoundException("schema not found for the siteKey:" + siteKey);
            return schema;
        }
        catch (MongoClientException e) {
            throw new DaoException(e.getMessage());
        }
    }

}
