package mainapp.rest.spring.wyjatkiHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HttpExHandler extends ResponseEntityExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)//500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> obslugaBledowSerwera(WebRequest req, Exception e) {
        logger.info("obsluga bledow wewnetrznycha serwera");
        KomunikatBledu komunikatBledu = new KomunikatBledu(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        return handleExceptionInternal(e, "cos poszlo nie tak jak maialo", null, HttpStatus.INTERNAL_SERVER_ERROR, req);
    }

}
