package rest.cxf.serwisy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rest.cxf.cxfdao.KomputerDao;
import rest.cxf.model.KomputerDaneOgolne;
import rest.cxf.model.interfejsy.Komputer;
import rest.cxf.serwisy.interfejsy.KomputerService;

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


}
