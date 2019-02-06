package wzorce.factory;

public class Main {
    public static void main(String[] args) {
        Zgloszenia zgloszenieA = ZgloszeniaFaktory.getZgloszenie("A");
        Zgloszenia zgloszenieB = ZgloszeniaFaktory.getZgloszenie("B");

        zgloszenieA.dodajNoweZgloszenie();
        zgloszenieA.usunZgloszenie();

        zgloszenieB.dodajNoweZgloszenie();
        zgloszenieB.usunZgloszenie();
    }

}
