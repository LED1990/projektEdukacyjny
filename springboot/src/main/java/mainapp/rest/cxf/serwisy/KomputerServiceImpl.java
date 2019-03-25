package mainapp.rest.cxf.serwisy;

import mainapp.rest.cxf.serwisy.interfejsy.KomputerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mainapp.rest.cxf.cxfdao.KomputerDao;
import mainapp.rest.cxf.model.KomputerDaneOgolne;
import mainapp.rest.cxf.model.interfejsy.Komputer;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class KomputerServiceImpl implements KomputerService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    KomputerDao komputerDao;

    @Override
    public void dodajNowy(String typ, KomputerDaneOgolne komputerDane) {
        logger.info("dodawanie nowego komputera "+ komputerDane);
        komputerDao.dodajNowyKomputer(typ, komputerDane);
    }

    @Override
    public List<Komputer> getAll() {
        logger.info("pobiernaie wszystkich komputerów");
        return komputerDao.getAllKomputer();
    }

    @Override
    public Komputer getKomputer(int index) {
        logger.info("pobiernaie jednego elementu z listy");
        return komputerDao.getKomputer(index);
    }

    @Override
    public void usunKomputerZListy(int index) {
        logger.info("usuwanie komputera z listy");
        komputerDao.usunKomputer(index);
    }

    /**
     * porzykład requesta z buforowaniem odopwiedzi
     * jeśli wszystkie odpowiedzi by miały być buforowane to by trzeba było to zrobć np w filtrze servletó
     * nagłówki można edytować jak w przykładzie lub można użyć obiektu ResponseEntity
     * w tym przypadku zasób będzie przez 10 sekund trzymany w przegladarce i requesty o ten zasub nie będa wychodzić do serwera
     * informacje o tym parametrze nagłówka: https://developer.mozilla.org/pl/docs/Web/HTTP/Headers/Cache-Control
     *
     * UWAGA! -> alternatywnie mozna używać EXPIRES i określić datę kiedy zasób będzie trzeba pobrać jeszcze raz
     * UWAGA! -> jeżeli catch control istnieje i zawiera max-age albo s-maxage to expires jest ignorowane.
     * info o expires: https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Expires
     * @param response
     * @return
     */
    @Override
    public List<Komputer> getAllCatched(HttpServletResponse response) {
        logger.info("pobiernaie wszystkich komputerów + catch control");
        response.addHeader("Cache-Control","max-age=10, public");
        return komputerDao.getAllKomputer();
    }


}
