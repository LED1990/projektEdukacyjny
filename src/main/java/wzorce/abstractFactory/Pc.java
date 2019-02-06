package wzorce.abstractFactory;

import java.util.logging.Logger;

public class Pc implements Komputer {
    private final Logger log = Logger.getLogger(getClass().getName());
    public void wykonajObliczenia() {
        log.info("wkonuje operacje jako koputer PC");
        dodatkoweOperacje();
    }

    private void dodatkoweOperacje(){
        log.info("wykonuje dodatkowe operacje jako koputer PC");
    }
}
