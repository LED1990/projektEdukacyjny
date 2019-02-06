package wzorce.factory;

public class ZgloszeniaFaktory {
    public static Zgloszenia getZgloszenie(String typ){
        if (typ.equals("A")){
            return new ZgloszenieA();
        }
        if (typ.equals("B")){
            return new ZgloszenieB();
        }
        return null;
    }
}
