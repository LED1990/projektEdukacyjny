package wzorce.factory;

import java.util.logging.Logger;

public class ZgloszenieB implements Zgloszenia {
    private final Logger log = Logger.getLogger(getClass().getName());

    public void dodajNoweZgloszenie() {
        log.info("dodawanie nowego zgłoszenia B");
        dodatkowaOperacja("podczas dodawania zgloszenia B");
    }

    public void usunZgloszenie() {
        log.info("usuwanie zgloszenia  B");
        dodatkowaOperacja("podczas usuwania zgloszenia B");
    }

    private void dodatkowaOperacja(String typOperacji) {
        log.info("dodatkowa  operacja: " + typOperacji);
    }
}
