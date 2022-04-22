package fr.upshift.demo.mongo;

import java.time.LocalDate;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(database = "person", collection = "person")
public class Person extends PanacheMongoEntity {
    public String name;
    public LocalDate birth;

    public static Person findByName(String name){
        return find("name", name).firstResult();
    }

    public static void deleteLoics(){
        delete("name", "Loïc");
    }
}