package wzorce.builder;

import java.util.logging.Logger;

public class Main {

    private final Logger log = Logger.getLogger(getClass().getName());

    public static void main(String [ ] args)
    {
        Komputer komputer = new Komputer.KomputerBuilder("10G","HDD").pojemnosc(100).stan(true).build();
        Komputer komputer2 = new Komputer.KomputerBuilder("100G","HDD1").pojemnosc(200).build();
        Komputer komputer3 = new Komputer.KomputerBuilder("1000G","HDD2").stan(false).build();

        Main main = new Main();
        main.wyswietlKomputer(komputer);
        main.wyswietlKomputer(komputer2);
        main.wyswietlKomputer(komputer3);

    }

    public void wyswietlKomputer(Komputer komputer){
        log.info("ram: "+komputer.getRam());
        log.info("dysk: "+komputer.getDysk());
        log.info("stanNowy: "+komputer.isStanNowy());
        log.info("pojemnosc: "+komputer.getPojemnosc());
        log.info("-------------------");
    }
}
