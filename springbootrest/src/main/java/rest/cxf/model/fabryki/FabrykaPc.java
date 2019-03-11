package rest.cxf.model.fabryki;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rest.cxf.model.KomputerPc;
import rest.cxf.model.interfejsy.Komputer;

@Component
public class FabrykaPc implements FabrykaKomputer{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private KomputerPc komputer;

    @Override
    public Komputer stworzNowy() {
        komputer.setDysk("HSDF");
        komputer.setRam("rammm");
        komputer.setRozmiar(123);
        logger.info("adres nowego komputera: " + komputer);
        return komputer;
    }
}
