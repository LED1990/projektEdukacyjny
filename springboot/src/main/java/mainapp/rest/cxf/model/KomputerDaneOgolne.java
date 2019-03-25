package mainapp.rest.cxf.model;

import org.springframework.stereotype.Component;

@Component
public class KomputerDaneOgolne {

    private String ram;

    private String dysk;

    private int rozmiar;

    public KomputerDaneOgolne() {
    }

    public KomputerDaneOgolne(String ram, String dysk, int rozmiar) {
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
}
