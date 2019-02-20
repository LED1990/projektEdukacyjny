package stringEdu;

import interfejsy.TestowaKlasa;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.test();
    }

    void test() {
        String zmienna = "abc";
        String zmienna2 = "abc";

        System.out.println(zmienna.hashCode());
        System.out.println(zmienna2.hashCode());

    }
}
