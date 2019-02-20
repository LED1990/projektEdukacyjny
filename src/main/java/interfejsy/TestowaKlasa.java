package interfejsy;

public class TestowaKlasa implements TestowyInterface {

      String zmienna;

    @Override
    public void zwyklaMetoda() {
        System.out.println("nadpisana metoda z interfejsu");
    }

    @Override
    public void metodaDefaultowa() {
        System.out.println("nadpisana metoda defaultowa");
    }
}
