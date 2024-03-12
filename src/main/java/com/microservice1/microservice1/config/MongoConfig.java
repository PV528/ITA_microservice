package com.microservice1.microservice1.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;


@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Bean
    public MongoTemplate mongoTemplate() {
        MongoDatabaseFactory factory = new SimpleMongoClientDatabaseFactory(mongoUri);
        return new MongoTemplate(factory);
    }
}
/*
@Configuration
public class MongoConfig {

    @Bean
    public MongoTemplate mongoTemplate() {
        Dotenv dotenv = Dotenv.configure().load();
        String mongoUri = dotenv.get("spring.data.mongodb.uri");
        MongoDatabaseFactory factory = new SimpleMongoClientDatabaseFactory(mongoUri);
        return new MongoTemplate(factory);
    }
}*/
