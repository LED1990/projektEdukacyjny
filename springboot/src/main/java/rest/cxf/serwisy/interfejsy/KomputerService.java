package rest.cxf.serwisy.interfejsy;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rest.cxf.model.KomputerDaneOgolne;
import rest.cxf.model.interfejsy.Komputer;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @Api oraz @ApiOperation to adnotacje swaggera
 * słuza do wskazania usługi raz metod ktore ona udostępnia
 */
@Path("/v1/komp")
@Api(value = "Serwis komputerowy")
public interface KomputerService {

    @POST
    @Path("/dodaj/{typ}")
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "dodawanie nowego komputera", consumes = MediaType.APPLICATION_JSON)
    void dodajNowy(@PathParam("typ") String typ, KomputerDaneOgolne komputer);

    @GET
    @Path("/komputery")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "pobieranie wszystkich", produces = MediaType.APPLICATION_JSON)
    List<Komputer> getAll();

    @GET
    @Path("/komputer/{index}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "pobieranie jednego komputera po id (ideksie)", produces = MediaType.APPLICATION_JSON)
    Komputer getKomputer(@PathParam("index") int index);

    @DELETE
    @Path("/komputer/{index}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "usuwanie pojedyńczego komputera", produces = MediaType.APPLICATION_JSON)
    void usunKomputerZListy(@PathParam("index") int index);

    @GET
    @Path("/komputery/catched")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "pobieranie wszystkich z użyciem catchecontrol", produces = MediaType.APPLICATION_JSON)
    List<Komputer> getAllCatched(@Context HttpServletResponse response);
}
