package kolekcje.sety;

import kolekcje.model.Student;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

@Component
public class SetEx {

    private final ObjectFactory<Student> objectFactory;
    /**
     * HasSet jest najszybszy ze wszystkich i to on głownie jest wykorzystywany
     * nie zapewnia sortowania
     *
     * Treeset zapenia sortowanie ale wszystkie operacje CRUD robi wolniej
     * O(Log(n)) czasu na operacje
     *
     * LinkedHaset  jest pomiedzy wczesniejszymi, duza sybkosc i pewne sortowanie ale inne niz treeset
     */
    private Set<Student> setHash = new HashSet<>();
    private Set<Student> setTree = new TreeSet<>();

    private Set<Student> setLinked = new LinkedHashSet<>();

    @Autowired
    public SetEx(ObjectFactory<Student> objectFactory) {
        this.objectFactory = objectFactory;
        dodajStudentowDoSet(setHash);
        dodajStudentowDoSet(setTree);
    }

    private Student nowyStudent(int id) {
        Student student = objectFactory.getObject();
        student.setId(id);
        student.setNazwa("student nr: " + id);
        return student;
    }

    private void dodajStudentowDoSet(Set<Student> set) {
        for (int i = 0; i < 10000; i++) {
            set.add(nowyStudent(i));
        }
    }

    public void testujemy(){
//        setHash.
    }
}
