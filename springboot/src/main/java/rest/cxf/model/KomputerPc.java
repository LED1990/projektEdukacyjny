package rest.cxf.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import rest.cxf.model.interfejsy.Komputer;

import javax.annotation.PostConstruct;

@Component
@Scope("prototype")
public class KomputerPc implements Komputer {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String ram;

    private String dysk;

    private int rozmiar;

    @PostConstruct
    private void init(){
        logger.info("powstaje nowy bean KomputerPc");
    }

    /**
     * ważnie żeby był domyślny konstruktor bo apachcxf nie  ogarnie xmla!!!!
     */
    public KomputerPc() {}

    public KomputerPc(String ram, String dysk, int rozmiar) {
        this.ram = ram;
        this.dysk = dysk;
        this.rozmiar = rozmiar;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getDysk() {
        return dysk;
    }

    public void setDysk(String dysk) {
        this.dysk = dysk;
    }

    public int getRozmiar() {
        return rozmiar;
    }

    public void setRozmiar(int rozmiar) {
        this.rozmiar = rozmiar;
    }

    @Override
    public void wykonajObliczenia() {
        logger.info("wykonuje obliczenia komputer PC");
    }
}
