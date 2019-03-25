package mainapp.rest.cxf.model.fabryki;

import mainapp.rest.cxf.model.KomputerPc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mainapp.rest.cxf.model.KomputerDaneOgolne;
import mainapp.rest.cxf.model.interfejsy.Komputer;

@Component
public class FabrykaPc implements FabrykaKomputer{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * ObjectFactory - to jeden ze sposobow springa na wstrzykiwanie beanow ze scopem prototype do beanow ze scopem singleton.
     * bez tego bean ze scopem protorype i tak by byl tylko raz utworzony przez kontener springa
     * wywolujac metode getObject fabryki otrzymujemy za kazdym razem nowy bean prototype.
     */
    @Autowired
    private ObjectFactory<KomputerPc> komputerPcObjectFactory;

    @Override
    public Komputer stworzNowy(KomputerDaneOgolne komputerDane) {
        KomputerPc komputerPc = komputerPcObjectFactory.getObject();
        komputerPc.setDysk(komputerDane.getDysk());
        komputerPc.setRam(komputerDane.getRam());
        komputerPc.setRozmiar(komputerDane.getRozmiar());
        logger.info("adres nowego komputera: " + komputerPc);
        return komputerPc;
    }
}
