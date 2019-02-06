package wzorce.abstractFactory.fabryki;

import wzorce.abstractFactory.Komputer;
import wzorce.abstractFactory.Serwer;

public class FabrykaSerwerow implements FabrykaKomputer {
    public Komputer getKomputer() {
        return new Serwer();
    }
}
