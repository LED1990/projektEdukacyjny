package wzorce.abstractFactory;

import wzorce.abstractFactory.fabryki.FabrykaKomputerow;
import wzorce.abstractFactory.fabryki.FabrykaPc;
import wzorce.abstractFactory.fabryki.FabrykaSerwerow;

public class Main {

    public static void main(String[] args) {
        Komputer pc = FabrykaKomputerow.stworzKomputer(new FabrykaPc());
        Komputer serwer = FabrykaKomputerow.stworzKomputer(new FabrykaSerwerow());

        pc.wykonajObliczenia();
        serwer.wykonajObliczenia();
    }
}
