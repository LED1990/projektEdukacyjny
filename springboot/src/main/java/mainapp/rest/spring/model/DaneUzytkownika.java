package mainapp.rest.spring.model;

import org.springframework.stereotype.Component;

@Component
public class DaneUzytkownika {
    private String imie;
    private String nazwisko;
    private String uuid;

    public DaneUzytkownika() {
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
