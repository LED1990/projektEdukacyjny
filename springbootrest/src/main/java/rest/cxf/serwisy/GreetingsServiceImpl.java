package rest.cxf.serwisy;

import org.springframework.stereotype.Component;
import rest.cxf.serwisy.interfejsy.GreetingsService;

/**
 * używając tutaj @Component moge potem wstrzyknąć serwis do konfiguracji apache cxf
 */
@Component
public class GreetingsServiceImpl implements GreetingsService {



    @Override
    public String zwrocProstrPowietanie() {
        return "witaj z apache cxf współpracującym ze springbootem!!!";
    }
}
