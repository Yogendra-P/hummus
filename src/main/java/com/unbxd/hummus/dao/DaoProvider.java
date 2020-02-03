package com.unbxd.hummus.dao;

import com.google.inject.Provider;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.pojo.PojoCodecProvider;
import com.unbxd.hummus.dao.impl.MongoDao;
import com.unbxd.hummus.dao.impl.RedisDao;
import com.unbxd.hummus.model.Schema;
import org.bson.codecs.configuration.CodecRegistry;
import redis.clients.jedis.Jedis;



import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class DaoProvider implements Provider<SchemaDao> {

    private String MONGO_COLLECTION_NAME = "schemaCollection";
    private String DATABASE_NAME = "schemaDB";
    private String DEFAULT_HOSTDB = "mongodb";

    public SchemaDao get()
    {
        String hostDB = System.getProperty("hostDB",DEFAULT_HOSTDB);
        System.out.println("hostDB = "+hostDB);
        if("mongodb".equals(hostDB)) {
            CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                    fromProviders(PojoCodecProvider.builder().automatic(true).build()));
            MongoClient mongoClient = new MongoClient("localhost");
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME)
                    .withCodecRegistry(pojoCodecRegistry);
            return new MongoDao( database.getCollection(MONGO_COLLECTION_NAME, Schema.class) );
        }
        else {
            Jedis jedis = new Jedis("localhost");
            jedis.ping();
            return new RedisDao(jedis);
        }
    }
}