package lambdy.baeldung;

/**
 * klasa która chce użyć metody z interfejsu ale nie chce go implementować
 */
public class KlasaA {
    /**
     * przykład na użycie klasy anonimowej
     * klasa anonimowa może korzystać z pól klasy zamykajacej
     */
    private FunctInterface functInterface = new FunctInterface() {
        Integer zmiennaLokalna = 10;
        @Override
        public String printString(String string) {
            System.out.println("bezpośrednia implementacja metody z interfejsu w metodzie klasy");
            System.out.println("wartość argumentu: "+string);
//            this.zmiennaLokalna  = 20;
            System.out.println("zmianna lokalna: "+zmiennaLokalna.hashCode() + " wartosc "+zmiennaLokalna);
            return string;
        }
    };
    public void bezposrednieUzycieBezLambd(){
        functInterface.printString("implementacja przed lambdami");
    }

    /**
     * wyrażenia lambda nie moga sie odwoływać do pól instancyjnych
     */
    private FunctInterface functInterfaceLambdy = (argument) -> {
        System.out.println("tworzenie obiektu interfejsu przy pomocy lambd");
        System.out.println("wartosc argumentu: "+argument);
//        System.out.println("zmianna lokalna: "+this.zmiennaLokalna.hashCode() + " wartosc "+this.zmiennaLokalna);//todo w lambdach nie można odwoływać się do zmiennych instancyjnych
        return argument;
    };

    public void bezposrednieUzycieZLambdami(){
        functInterfaceLambdy.printString("implementacja z lambadmi");
    }

    /**
     * lambdy pozwalaja odwoływać się do już istniejacych metod
     * warunkiem jest taki sam zestaw argumentów i zwracany typ
     * @param argument
     * @return
     */
    private FunctInterface functInterfaceReferencjaDoMetody = this::metodaDoUzyciaReferecji;
    private String metodaDoUzyciaReferecji(String argument){
        System.out.println("wykorzystanie referncji do metody");
        System.out.println("wartosc argumentu: "+argument);
        return argument;
    }
    public void wywolanieMetodyReferencyjnej(){
        functInterfaceReferencjaDoMetody.printString("metoda referencyjna");
    }
}
