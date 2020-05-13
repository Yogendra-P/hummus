package com.unbxd.hummus.dao;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.pojo.PojoCodecProvider;
import com.unbxd.hummus.dao.impl.MongoDao;
import com.unbxd.hummus.dao.impl.RedisDao;
import com.unbxd.hummus.model.Schema;
import org.bson.codecs.configuration.CodecRegistry;
import redis.clients.jedis.Jedis;


import java.util.Properties;

import static com.unbxd.hummus.Constants.*;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class DaoProvider implements Provider<SchemaDao> {

    @Inject
    private Properties properties;
    public SchemaDao get()
    {
        String hostDB = properties.getProperty("hostDB",DEFAULT_HOSTDB);
        System.out.println("hostDB = "+hostDB);
        if("mongodb".equals(hostDB)) {
            CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                    fromProviders(PojoCodecProvider.builder().automatic(true).build()));
            MongoClient mongoClient = new MongoClient(LOCAL_HOST);
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME)
                    .withCodecRegistry(pojoCodecRegistry);
            return new MongoDao( database.getCollection(MONGO_COLLECTION_NAME, Schema.class) );
        }
        else {
            Jedis jedis = new Jedis(LOCAL_HOST);
            jedis.ping();
            return new RedisDao(jedis);
        }
    }
}