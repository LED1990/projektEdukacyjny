package rest.cxf.serwisy.interfejsy;

import rest.cxf.model.KomputerDaneOgolne;
import rest.cxf.model.interfejsy.Komputer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/v1/komp")
public interface KomputerService {

    @POST
    @Path("/dodaj/{typ}")
    @Consumes(MediaType.APPLICATION_JSON)
    void dodajNowy(@PathParam("typ") String typ, KomputerDaneOgolne komputer);

    @GET
    @Path("/komputery")
    @Produces(MediaType.APPLICATION_JSON)
    List<Komputer> getAll();

    @GET
    @Path("/komputer/{index}")
    @Produces(MediaType.APPLICATION_JSON)
    Komputer getKomputer(@PathParam("index") int index);

    @DELETE
    @Path("/komputer/{index}")
    @Produces(MediaType.APPLICATION_JSON)
    void usunKomputerZListy(@PathParam("index") int index);
}
