package kolekcje;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class MainListy {
    private final Logger log = Logger.getLogger(getClass().getName());
    public static void main(String[] args) {
        MainListy main = new MainListy();
        main.testyListyList();

    }

    void testyListyList(){
        List<String> listaArray = new ArrayList<String>();
        List<String> listaLinked = new LinkedList<String>();

        listaArray.add("ABS1");
        listaArray.add("ABS2");
        listaArray.add("ABS3");

        listaLinked.add("ABS1");
        listaLinked.add("ABS2");
        listaLinked.add("ABS3");


        wyswietlListe(listaArray);
        wyswietlListe(listaLinked);
        wyswietlListeIterator(listaArray);
        wyswietlListeIterator(listaLinked);

        wyswietlPoIndexie(listaArray, 2);
        wyswietlPoIndexie(listaLinked,2);
    }

    void wyswietlListe(List<String> lista){
        for (String el: lista
             ) {
            log.info("element listy: " + el);
        }
        log.info("-------------------------");
    }

    void wyswietlListeIterator(List<String> lista){
        Iterator<String> iterator = lista.iterator();
        while (iterator.hasNext()){
            log.info("itertator: " + iterator.next());
        }
        log.info("-------------------------");
    }

    void wyswietlPoIndexie(List<String> lista, int index){
        log.info("element: " + lista.get(index));
    }
}
