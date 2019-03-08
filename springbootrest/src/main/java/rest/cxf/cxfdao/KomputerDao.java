package rest.cxf.cxfdao;

import org.springframework.stereotype.Component;
import rest.cxf.model.Komputer;

import java.util.List;

@Component
public class KomputerDao {
//todo dorobic implementacje metod + zastosować interfesy i fabryke!!!
    private List<Komputer> komputers;

    public void dodajNowyKomputer(Komputer komputer){
//        komputers.add(komputer);
    }

    public List<Komputer> getAllKomputer(){
        return komputers;
    }

    public Komputer getKomputer(){
        Komputer komputer = new Komputer("AAA","VVV",11);
        return komputer;
    }
}
