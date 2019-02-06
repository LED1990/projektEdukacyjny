package wzorce.singleton;

import junit.framework.TestCase;
import org.junit.Assert;

public class Main extends TestCase {
    MSingleton singleton;
    MSingleton singleton1;

    public static void main(String [ ] args)
    {
        Main main = new Main();
        main.test();
    }
    void test(){
        singleton = MSingleton.getInstance();
        singleton1 =  MSingleton.getInstance();

        System.out.println(singleton);
        System.out.println(singleton1);

        Assert.assertSame(singleton, singleton1);

    }

}
