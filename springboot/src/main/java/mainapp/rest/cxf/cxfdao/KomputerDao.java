package mainapp.rest.cxf.cxfdao;

import mainapp.rest.cxf.model.fabryki.FabrykaKomputerow;
import mainapp.rest.cxf.model.fabryki.FabrykaPc;
import mainapp.rest.cxf.repo.KomputerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mainapp.rest.cxf.model.KomputerDaneOgolne;
import mainapp.rest.cxf.model.interfejsy.Komputer;

import java.util.List;

@Component
public class KomputerDao {

    @Autowired
    private FabrykaPc fabrykaPc;

    @Autowired
    private KomputerRepo komputerRepo;

    public void dodajNowyKomputer(String typ, KomputerDaneOgolne komputerDane){
        if (typ.equals("PC")){
            komputerRepo.dodajDolisty(FabrykaKomputerow.stworzKomputer(fabrykaPc, komputerDane));
        }
    }

    public List<Komputer> getAllKomputer(){
        return komputerRepo.zwrocWszystkie();
    }

    public Komputer getKomputer(int index){
        return komputerRepo.zwrocKomputerZListy(index);
    }

    public void usunKomputer(int index){
        komputerRepo.usunKomputerZListy(index);
    }
}
