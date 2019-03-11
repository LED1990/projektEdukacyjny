package rest.cxf.cxfdao;

import org.springframework.stereotype.Component;
import rest.cxf.model.KomputerPc;

import java.util.List;

@Component
public class KomputerDao {
//todo dorobic implementacje metod + zastosować interfesy i fabryke!!!
    private List<KomputerPc> komputers;

    public void dodajNowyKomputer(KomputerPc komputer){
//        komputers.add(komputer);
    }

    public List<KomputerPc> getAllKomputer(){
        return komputers;
    }

    public KomputerPc getKomputer(){
        KomputerPc komputer = new KomputerPc("AAA","VVV",11);
        return komputer;
    }
}
