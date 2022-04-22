package fr.upshift.demo.resource;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.quarkus.logging.Log;
import io.smallrye.common.annotation.NonBlocking;

import org.jboss.resteasy.reactive.RestQuery;

import fr.upshift.demo.entity.Person;
import fr.upshift.demo.service.PersonService;

@Path("/person")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    @Inject
    PersonService personService;

    @GET
    @Path("/init")
    @NonBlocking
    public void init() {
        Log.info("Init");
        personService.init();
    }

    @GET
    @NonBlocking
    public List<Person> list() {
        Log.info("List persons");
        return personService.list();
    }

    @GET
    @Path("/find")
    @NonBlocking
    public Person get(@RestQuery String name) {
        Log.info("Find person " + name);
        return personService.get(name);
    }

    @POST
    @NonBlocking
    public Response create(Person person) {
        Log.info("Create person " + person.name);
        personService.create(person);
        return Response.created(URI.create("/person/" + person.id)).build();
    }
}
