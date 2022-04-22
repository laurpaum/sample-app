package fr.upshift.demo.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import fr.upshift.demo.entity.Person;
import fr.upshift.demo.entity.Status;

@ApplicationScoped
public class PersonService {

    public void init() {
        Person person = new Person();
        person.name = "Laurent";
        person.birth = LocalDate.of(1971, Month.NOVEMBER, 4);
        person.status = Status.LIVING;
        person.persist();
    }

    public List<Person> list() {
        return Person.listAll();
    }

    public Person get(String name) {
        return Person.find("name", name).firstResult();
    }

    public void create(Person person) {
        person.persist();
    }

}
