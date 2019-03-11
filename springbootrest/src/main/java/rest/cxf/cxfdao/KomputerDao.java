package rest.cxf.cxfdao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rest.cxf.model.KomputerPc;
import rest.cxf.model.fabryki.FabrykaKomputerow;
import rest.cxf.model.fabryki.FabrykaPc;
import rest.cxf.model.interfejsy.Komputer;

import java.util.ArrayList;
import java.util.List;

@Component
public class KomputerDao {
    private List<Komputer> komputers = new ArrayList<>();

    @Autowired
    private FabrykaPc fabrykaPc;

    public void dodajNowyKomputer(String typ){
        if (typ.equals("PC")){
            komputers.add(FabrykaKomputerow.stworzKomputer(fabrykaPc));
        }
    }

    public List<Komputer> getAllKomputer(){
        return komputers;
    }

    public KomputerPc getKomputer(){
        KomputerPc komputer = new KomputerPc("AAA","VVV",11);
        return komputer;
    }
}
