package rest.cxf.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import rest.cxf.model.interfejsy.Komputer;

import java.util.ArrayList;
import java.util.List;

@Component
public class KomputerRepo {
    private final Logger logger = LoggerFactory.getLogger(getClass());


    private List<Komputer> listaKomputerow = new ArrayList<>();

    public void dodajDolisty(Komputer komputer){
        listaKomputerow.add(komputer);
    }

    public List<Komputer> zwrocWszystkie(){
        return listaKomputerow;
    }

    public Komputer zwrocKomputerZListy(int index){
        if (index >= listaKomputerow.size()){
            logger.error("próba pobrania elementu spoza listy");
            return null;
        }
        return listaKomputerow.get(index);
    }

    public void usunKomputerZListy(int index){
        if (index >= listaKomputerow.size()){
            logger.error("próba usuniecia elementu spoza listy");
            return;
        }
        listaKomputerow.remove(index);
    }
}
