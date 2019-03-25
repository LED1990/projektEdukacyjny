package rest.cxf.model.fabryki;

import rest.cxf.model.KomputerDaneOgolne;
import rest.cxf.model.interfejsy.Komputer;

public interface FabrykaKomputer {
    Komputer stworzNowy(KomputerDaneOgolne komputerDane);
}
