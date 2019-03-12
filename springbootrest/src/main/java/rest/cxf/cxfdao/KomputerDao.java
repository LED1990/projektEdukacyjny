package rest.cxf.cxfdao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rest.cxf.model.fabryki.FabrykaKomputer;
import rest.cxf.model.fabryki.FabrykaKomputerow;
import rest.cxf.model.fabryki.FabrykaPc;
import rest.cxf.model.interfejsy.Komputer;
import rest.cxf.repo.KomputerRepo;

import java.util.ArrayList;
import java.util.List;

@Component
public class KomputerDao {

    @Autowired
    private FabrykaPc fabrykaPc;

    @Autowired
    private KomputerRepo komputerRepo;

    public void dodajNowyKomputer(String typ){
        if (typ.equals("PC")){
            komputerRepo.dodajDolisty(FabrykaKomputerow.stworzKomputer(fabrykaPc));
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
