package lambdy;

public interface InterfejsZMetoda {
    Integer metodaInterfejsu(Integer argument);

    default void metodaDefaultowa(){
        System.out.println("metoda defaultowa");
    }

    static void metodaStatyczna(){
        System.out.println("metoda statyczna");
    }
}
