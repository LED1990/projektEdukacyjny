package wzorce.singleton;

public class MSingleton {

    private static MSingleton instancja;

    private MSingleton(){}

    public static MSingleton getInstance(){
        if (instancja == null){
            instancja =  new MSingleton();
        }
        return instancja;
    }
}
