package lambdy;

import lambdy.baeldung.KlasaA;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
//        main.staraWersja();
//        main.wersjaZlambdami();

//        main.lambdyIReferencjeMetod();
//        main.lambdyIReferencjeMetodInstancyjnych();

        KlasaA klasaA = new KlasaA();
        klasaA.bezposrednieUzycieBezLambd();
        klasaA.bezposrednieUzycieZLambdami();
        klasaA.wywolanieMetodyReferencyjnej();


    }

    /**
     * przed lambdami żeby użyć metody interfesu należało najpierw stworzyć obiekt implementujcy lub użyć anonimowego interfejsu
     * to jest przykład na anonimowy interfejs
     */
    void staraWersja(){
        KlasaImpl klasa = new KlasaImpl();
        klasa.metodaKlasy(new InterfejsZMetoda() {
            @Override
            public Integer metodaInterfejsu(Integer arg) {
                System.out.println("sposób implementacji interfejsu do java 7");
                return 7;
            }
        });
    }

    /**
     * od kiedy weszły lambdy zapis i implementacja metody znaczaco się uprościła
     */
    void wersjaZlambdami(){
         int zmienna = 111;
        KlasaImpl klasa = new KlasaImpl();
        klasa.metodaKlasy((Integer arg) ->{
            System.out.println("wersja z lambdami "+zmienna);
            arg+=20;
            System.out.println("wersja z lambdami "+arg);
            return arg;
        });
    }

    void lambdyIReferencjeMetod(){
        InterfejsZMetoda interfejsZMetoda = KlasaImpl::metodaKlssyImplStatyczna;
        interfejsZMetoda.metodaInterfejsu(20);
    }
    void lambdyIReferencjeMetodInstancyjnych(){
        KlasaImpl klasa = new KlasaImpl();
        InterfejsZMetoda interfejsZMetoda2 = klasa::metodaKlssyImplInstancyjna;
        interfejsZMetoda2.metodaInterfejsu(99);
    }
}
