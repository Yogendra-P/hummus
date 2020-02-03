package com.unbxd.hummus.dao;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import java.util.Properties;


public class DaoModule extends AbstractModule {
    @Override
    protected void configure() {

        bind(SchemaDao.class).toProvider(DaoProvider.class).asEagerSingleton();
    }

    @Provides
    public Properties getProperties() {

        return System.getProperties();
    }
}
