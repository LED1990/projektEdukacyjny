package rest.mapaowania;

import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


/**
 * takie coś nie zadziała ze springbootem!!!
 * nie da się mieszać springboota z adnotacjami javaxa!
 * trzeba by spróbowac dodac jersey albo apache cxf
 */
@Path(value = "/klasycznyRest")
@Service
public class GreetingsService {

    @GET
    @Path("/greetings")
    @Produces(MediaType.TEXT_PLAIN)
    public String zwrocKlasycznyRest(){
        return "powitanie klasycznego resta na plikacji springbootowej";
    }
}
