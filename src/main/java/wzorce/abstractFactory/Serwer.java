package wzorce.abstractFactory;

import java.util.logging.Logger;

public class Serwer implements Komputer {
    private final Logger log = Logger.getLogger(getClass().getName());
    public void wykonajObliczenia() {
        log.info("wykonuje obliczenia jako Serwer!!");
        dodatkoweOperacje();
    }

    private void dodatkoweOperacje(){
        log.info("wykonuje dodatkowe operacje serwerowe");
    }
}
