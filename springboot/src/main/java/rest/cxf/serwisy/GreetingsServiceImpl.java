package rest.cxf.serwisy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import rest.cxf.serwisy.interfejsy.GreetingsService;

/**
 * używając tutaj @Component moge potem wstrzyknąć serwis do konfiguracji apache cxf
 */
@Component
public class GreetingsServiceImpl implements GreetingsService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String zwrocProstrPowietanie() {
        logger.info("powitanie usługa");
        return "witaj z apache cxf współpracującym ze springbootem!!!";
    }
}
