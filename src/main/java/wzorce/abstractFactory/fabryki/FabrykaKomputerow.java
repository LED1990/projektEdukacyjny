package wzorce.abstractFactory.fabryki;

import wzorce.abstractFactory.Komputer;

public class FabrykaKomputerow {

    public static Komputer stworzKomputer(FabrykaKomputer fabrykaKomputer){
        return fabrykaKomputer.getKomputer();
    }
}
