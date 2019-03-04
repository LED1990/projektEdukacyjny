package cxfrestwebservlet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello/")
public class GreetingsCxfSerwis {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/greetings")
    public String getCxfGreetings(){
        return "witam z usługi apache cxf rest!";
    }
}
