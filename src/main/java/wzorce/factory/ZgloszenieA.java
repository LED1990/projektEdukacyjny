package wzorce.factory;

import java.util.logging.Logger;

public class ZgloszenieA implements Zgloszenia {
    private final Logger log = Logger.getLogger(getClass().getName());

    public void dodajNoweZgloszenie() {
        log.info("dodawanie zgloszenia A");
        dodatkowaOperacja("dodatek podczas dodawania zgloszenia A");
    }

    public void usunZgloszenie() {
        log.info("usuwanie zgłoszenia A");
        dodatkowaOperacja("dodatek podczas  usuwania zgloszenia A");
    }

    private void dodatkowaOperacja(String typOperacji) {
        log.info("dodatkowa operacja: " + typOperacji);
    }
}
