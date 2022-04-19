package fr.upshift.demo.resource;

import java.net.URI;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.reactive.RestQuery;

import fr.upshift.demo.entity.Person;
import fr.upshift.demo.entity.Status;
import io.quarkus.logging.Log;
import io.smallrye.common.annotation.NonBlocking;

@Path("/person")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    @GET
    @Path("/init")
    @NonBlocking
    public void init() {
        Log.info("Init");
        Person person = new Person();
        person.name = "Laurent";
        person.birth = LocalDate.of(1971, Month.NOVEMBER, 4);
        person.status = Status.LIVING;
        person.persist();
    }

    @GET
    @NonBlocking
    public List<Person> list() {
        Log.info("List persons");
        return Person.listAll();
    }

    @GET
    @Path("/find")
    @NonBlocking
    public Person get(@RestQuery String name) {
        Log.info("Find person " + name);
        return Person.find("name", name).firstResult();
    }

    @POST
    @NonBlocking
    public Response create(Person person) {
        Log.info("Create person " + person.name);
        person.persist();
        return Response.created(URI.create("/person/" + person.id)).build();
    }
}
