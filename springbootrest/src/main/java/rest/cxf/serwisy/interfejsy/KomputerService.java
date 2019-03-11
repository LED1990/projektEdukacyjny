package rest.cxf.serwisy.interfejsy;

import rest.cxf.model.KomputerPc;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/v1/komp")
public interface KomputerService {

    @POST
    @Path("/dodaj")
    @Consumes(MediaType.APPLICATION_JSON)
    void dodajNowy(KomputerPc komputer);

    @GET
    @Path("/komputery")
    @Produces(MediaType.APPLICATION_JSON)
    List<KomputerPc> getAll();

    @GET
    @Path("/komputer")
    @Produces(MediaType.APPLICATION_JSON)
    KomputerPc getKomputer();

}
