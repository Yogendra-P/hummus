package com.unbxd.hummus.services;

import com.google.inject.Guice;
import com.unbxd.hummus.dao.DaoModule;
import com.unbxd.hummus.services.impl.SchemaServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    SchemaService getSchemaService() {
        return Guice.createInjector(new DaoModule())
                .getInstance(SchemaServiceImpl.class);
    }
}
