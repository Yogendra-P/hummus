package com.unbxd.hummus.dao.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unbxd.hummus.dao.SchemaDao;
import com.unbxd.hummus.execptions.DaoException;
import com.unbxd.hummus.execptions.SchemaNotFoundException;
import com.unbxd.hummus.model.Schema;
import redis.clients.jedis.Jedis;

public class RedisDao implements SchemaDao {

 private static ObjectMapper mapper = new ObjectMapper();
 private Jedis redisClient;

 public RedisDao(Jedis redisClient){
   this.redisClient = redisClient;
  }

 @Override
 public void add(Schema schema) throws DaoException {
   try {
    String schemaAsString = mapper.writeValueAsString(schema);
    redisClient.set(schema.getSiteKey(),schemaAsString);
   }
   catch (JsonProcessingException e) {
        throw new DaoException("Unable to serialize schema object");
   }
 }

 public Schema get(String siteKey) throws  DaoException , SchemaNotFoundException{
     String data = redisClient.get(siteKey);
     if(data == null)
        throw new SchemaNotFoundException("schema not found for the siteKey : " + siteKey);
     JavaType type = mapper.constructType(Schema.class);
     try {
         return mapper.readValue(data, type);
     }
     catch (JsonProcessingException e) {
         throw new DaoException("Unable to deserialize schema object");
     }
 }
}
