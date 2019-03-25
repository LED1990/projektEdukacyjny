package rest.cxf.serwisy.interfejsy;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import rest.cxf.model.interfejsy.Komputer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;

@Path("/v1/komp/etag")
@Api(value = "Serwis komputerowy przy pomocy etagów")
public interface KomputerServiceEtag {

    @GET
    @Path("/entity/komputer/{index}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "pobieranie komputera ze zancznikiem etag", produces = MediaType.APPLICATION_JSON)
    ResponseEntity<Komputer> getKomputerEntity(@PathParam("index") int index, @Context Request request);
}
