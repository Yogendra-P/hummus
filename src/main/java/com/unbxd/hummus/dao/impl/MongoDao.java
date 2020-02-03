package com.unbxd.hummus.dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.unbxd.hummus.dao.SchemaDao;
import com.unbxd.hummus.execptions.SchemaNotFoundException;
import com.unbxd.hummus.model.Schema;

import static com.mongodb.client.model.Filters.eq;

public class MongoDao implements SchemaDao {

    private MongoCollection<Schema> collection;

    public MongoDao(MongoCollection<Schema> collection) {
        this.collection = collection;
    }

    public void add(Schema schema) {
        collection.insertOne(schema);
    }

    public Schema get(String siteKey) throws SchemaNotFoundException {
        Schema schema = collection.find(eq("siteKey",siteKey)).first();
        if( schema == null )
            throw new SchemaNotFoundException();
        return schema;
    }

}
