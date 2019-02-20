package lambdy;

public class KlasaImpl {
    public void metodaKlasy(InterfejsZMetoda interfejsZMetoda){
        interfejsZMetoda.metodaInterfejsu(10);
        interfejsZMetoda.metodaDefaultowa();
        InterfejsZMetoda.metodaStatyczna();
        System.out.println("koniec działań!!!");
    }

    static Integer metodaKlssyImplStatyczna(Integer arg){
        System.out.println("metoda klasy Impl static " + arg);
        return 0;
    }
    Integer metodaKlssyImplInstancyjna(Integer arg){
        System.out.println("metoda klasy Impl instancyjna " + arg);
        return 0;
    }
}
