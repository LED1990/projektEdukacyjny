package rest.cxf.serwisy.interfejsy;

import rest.cxf.model.KomputerPc;
import rest.cxf.model.interfejsy.Komputer;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/v1/komp")
public interface KomputerService {

    @POST
    @Path("/dodaj/{typ}")
    @Consumes(MediaType.APPLICATION_JSON)
    void dodajNowy(@PathParam("typ") String typ);

    @GET
    @Path("/komputery")
    @Produces(MediaType.APPLICATION_JSON)
    List<Komputer> getAll();

    @GET
    @Path("/komputer")
    @Produces(MediaType.APPLICATION_JSON)
    KomputerPc getKomputer();

}
