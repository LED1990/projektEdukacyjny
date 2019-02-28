package mjersey;

import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/hello")
public class HelloWorldRestSerwis {

    private final Logger log = Logger.getLogger(getClass().getName());

    @GET
    @Path("/world")
    @Produces(MediaType.TEXT_PLAIN)
    public String getHelloWorld(){
        log.info("GET jersey example");
        return "jersey - odpowiedz z uslugi rest";
    }
}
