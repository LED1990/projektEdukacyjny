package interfejsy;

/**
 * wszystkie pola w interfejsie sa ukrycie static i final
 * wszystkie metody (zwykłe) sa ukrycie public abstract
 * metod statycznych nie można nadpisywać
 * metody defaultowe można nadpisywać
 */
public interface TestowyInterface {

    String poleInterfejsu = "w interfejsie";

    void zwyklaMetoda();
    static void statycznaMetoda(){
        System.out.println("metoda statyczna w interfejsie");
    }

    default void metodaDefaultowa(){
        System.out.println("metoda defaultowa w interfejsie");
    }

}
