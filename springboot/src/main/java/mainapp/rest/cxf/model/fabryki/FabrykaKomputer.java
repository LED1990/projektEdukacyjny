package mainapp.rest.cxf.model.fabryki;

import mainapp.rest.cxf.model.interfejsy.Komputer;
import mainapp.rest.cxf.model.KomputerDaneOgolne;

public interface FabrykaKomputer {
    Komputer stworzNowy(KomputerDaneOgolne komputerDane);
}
