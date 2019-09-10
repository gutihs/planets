package com.example.planets.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "com.example.planets.model")
public class MongoConfig extends AbstractMongoConfiguration {
    @Override
    public MongoClient mongoClient() {
        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://admin:12345@cluster0-gsmvp.mongodb.net/planets?retryWrites=true&w=majority");
        return new MongoClient(uri);
    }

    @Override
    protected String getDatabaseName() {
        return "planets";
    }
}
