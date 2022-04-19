package fr.upshift.demo.entity;

import java.time.LocalDate;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection="person")
public class Person extends PanacheMongoEntity {
    public String name;
    public LocalDate birth;
    public Status status;
}
