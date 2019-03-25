package mainapp.rest.cxf.serwisy.interfejsy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/v1/greeting")
public interface GreetingsService {

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
    String zwrocProstrPowietanie();
}
