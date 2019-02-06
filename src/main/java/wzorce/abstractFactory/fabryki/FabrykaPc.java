package wzorce.abstractFactory.fabryki;

import wzorce.abstractFactory.Komputer;
import wzorce.abstractFactory.Pc;

public class FabrykaPc implements FabrykaKomputer {
    public Komputer getKomputer() {
        return new Pc();
    }
}
