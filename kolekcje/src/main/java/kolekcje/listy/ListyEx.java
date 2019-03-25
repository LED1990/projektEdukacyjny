package kolekcje.listy;

import kolekcje.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class ListyEx {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ObjectFactory<Student> objectFactory;

    /**
     * główne rożnice między array list a linked  list dotycza szybkosci operacji na dużych kolekcjach
     * linked list jest lepsze na add i remove -> edytowane sa tylko sasiednie wpisy (kazdy element tej listy ma informacje o nasepcy i poprzedniku
     * array list jest lepsze na get u set
     *
     * jeśli array list podczas dodawania lub usuwania elementu wymaga zmiany rozmiaru to wtedy jest gorsze bo cała lista jest kopiowana do nowej
     * zestawienie czasochłonności dla powyższych:
     *
     * LINKED LIST
     * get(int index) is O(n) (with n/4 steps on average)
     add(E element) is O(1)
     add(int index, E element) is O(n) (with n/4 steps on average), but O(1) when index = 0 <--- main benefit of LinkedList<E>
     remove(int index) is O(n) (with n/4 steps on average)
     Iterator.remove() is O(1). <--- main benefit of LinkedList<E>
     ListIterator.add(E element) is O(1) This is one of the main benefits of LinkedList<E>

     * ARRAY LIST
     *get(int index) is O(1) <--- main benefit of ArrayList<E>
     add(E element) is O(1) amortized, but O(n) worst-case since the array must be resized and copied
     add(int index, E element) is O(n) (with n/2 steps on average)
     remove(int index) is O(n) (with n/2 steps on average)
     Iterator.remove() is O(n) (with n/2 steps on average)
     ListIterator.add(E element) is O(n) (with n/2 steps on average)
     */
    private List<Student> listaArray = new ArrayList<>();
    private List<Student> listaLinked = new LinkedList<>();

    @Autowired
    public ListyEx(ObjectFactory<Student> objectFactory) {
        this.objectFactory = objectFactory;
        dodajStudentowDoListy(listaArray);
        dodajStudentowDoListy(listaLinked);
    }

    private Student nowyStudent(int id) {
        Student student = objectFactory.getObject();
        student.setId(id);
        student.setNazwa("student nr: " + id);
        return student;
    }

    private void dodajStudentowDoListy(List<Student> list) {
        for (int i = 0; i < 1000000; i++) {
            list.add(nowyStudent(i));
        }
    }

    public void wyswietlListy() {
        for (Student student : listaArray) {
            logger.info("--------- ARRAY LIST ---------- " + student.hashCode() + " " + student.getNazwa());
        }
        for (Student student : listaLinked) {
            logger.info("--------- LINKED LIST ---------- " + student.hashCode() + " " + student.getNazwa());
        }
    }

    /**
     * get linked czas = 1,628
     * get array czas = odrazu
     *
     * add linked czas = 1,214
     * add array czas = 0,439
     */
    public void tesujemy(){
        for (int i = 0 ; i<1000 ;i++){
            listaLinked.add(i+55555, null);
        }
        logger.info("koniec");


    }
}
