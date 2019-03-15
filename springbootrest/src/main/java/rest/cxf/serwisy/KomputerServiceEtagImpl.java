package rest.cxf.serwisy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import rest.cxf.cxfdao.KomputerDao;
import rest.cxf.model.interfejsy.Komputer;
import rest.cxf.serwisy.interfejsy.KomputerServiceEtag;

import javax.ws.rs.core.Request;

@Component
public class KomputerServiceEtagImpl implements KomputerServiceEtag {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    KomputerDao komputerDao;

    /**
     * ponieważ korzystam z filtra springowego do generowania etagów nie muszę sam ich generować i ustawiać
     * wartośc jest utomatycznie generowana i wrzuca do headera odpowiedzi
     * @param index
     * @param request
     * @return
     */
    @Override
    public ResponseEntity<Komputer> getKomputerEntity(int index, Request request) {
        /**
         * jeżeli bym nie używał filtra od springa do obsługi etagów to bym musiał sam implementować ich obsługę
         * wstawianie etaga do headera odpowiedzi
         * sprawdzanie czy etag z zapytania zgadza się z tym który wcześniej wygenerowałem i dałem do headera
         * zwracanie odpowiedzi ze statusami (304 - jeśli etagi takie same, 200 + body - jeśli etagi sa różne)
         *
         * filtr springa ShallowEtagHeaderFilter nie działa z if-match co znaczy że jesli etagi by miały działać też na POST/PUT to by trzeba zrobić swoja implementację żebe kontrolować tagi
         * UWAGA!! ten filtr springa jest cięzki  w obliczeniach więc może się opłacać samemu robić implementacje
         */
        logger.info("request: "+request);
        return ResponseEntity.ok()
                             .body(komputerDao.getKomputer(index));
    }
}
