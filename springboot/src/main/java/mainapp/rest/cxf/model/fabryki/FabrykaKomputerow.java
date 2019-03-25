package mainapp.rest.cxf.model.fabryki;

import mainapp.rest.cxf.model.KomputerDaneOgolne;
import mainapp.rest.cxf.model.interfejsy.Komputer;

public class FabrykaKomputerow {
    public static Komputer stworzKomputer(FabrykaKomputer fabrykaKomputer, KomputerDaneOgolne komputerDane){
        return fabrykaKomputer.stworzNowy(komputerDane);
    }
}
