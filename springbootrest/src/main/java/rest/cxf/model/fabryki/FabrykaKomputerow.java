package rest.cxf.model.fabryki;

import rest.cxf.model.interfejsy.Komputer;

public class FabrykaKomputerow {
    public static Komputer stworzKomputer(FabrykaKomputer fabrykaKomputer){
        return fabrykaKomputer.stworzNowy();
    }
}