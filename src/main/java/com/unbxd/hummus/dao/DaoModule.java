package com.unbxd.hummus.dao;

import com.google.inject.AbstractModule;



public class DaoModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(SchemaDao.class).toProvider(DaoProvider.class).asEagerSingleton();
    }
}
