package rest.cxf.exceptions;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;


/**
 * CXF pozwala tworzyc ExceptionMappery którymi można nadpisywać komunikaty błędów zgłaszane przez aplikacje
 * ta klasa obsługuje błędy 4XX - czyli błędy których przyczyna jest klinet
 * Uwaga! pamiętać o dodaniu mappera do providersów w konfiguracji cxf
 */
@Component
public class MWebApplicationException implements ExceptionMapper<WebApplicationException> {
    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private ObjectFactory<ExceptionInfo> exceptionInfoObjectFactory;

    @Override
    public Response toResponse(WebApplicationException exception) {
        ExceptionInfo exceptionInfo = exceptionInfoObjectFactory.getObject();
        logger.debug("nowy obiekt exception info: " + exceptionInfo.hashCode());
        exceptionInfo.setKod(exception.getResponse()
                                      .getStatus());
        exceptionInfo.setTyp("bład po strone klienta");
        exceptionInfo.setWiadomosc(exception.getMessage());
        return Response.status(Response.Status.BAD_REQUEST)
                       .type(MediaType.APPLICATION_JSON_TYPE)
                       .entity(exceptionInfo)
                       .build();
    }
}
