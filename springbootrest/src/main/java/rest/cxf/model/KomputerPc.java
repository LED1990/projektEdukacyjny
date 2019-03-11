package rest.cxf.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement
//@XmlAccessorType(XmlAccessType.FIELD)
public class KomputerPc {

    private String ram;

    private String dysk;

    private int rozmiar;

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
}
