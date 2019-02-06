package wzorce.builder;

public class Komputer {

    private String ram;
    private String dysk;
    private Integer pojemnosc;
    private boolean stanNowy;

    private Komputer(KomputerBuilder komputerBuilder) {
        this.ram = komputerBuilder.ram;
        this.dysk = komputerBuilder.dysk;
        this.pojemnosc = komputerBuilder.pojemnosc;
        this.stanNowy = komputerBuilder.stanNowy;
    }

    public String getRam() {
        return ram;
    }

    public String getDysk() {
        return dysk;
    }

    public Integer getPojemnosc() {
        return pojemnosc;
    }

    public boolean isStanNowy() {
        return stanNowy;
    }

    static class KomputerBuilder{
        private String ram;
        private String dysk;
        private Integer pojemnosc;
        private boolean stanNowy;

        KomputerBuilder(String ram, String dysk) {
            this.ram = ram;
            this.dysk = dysk;
        }

        KomputerBuilder pojemnosc(Integer pojemnosc){
            this.pojemnosc = pojemnosc;
            return this;
        }

        KomputerBuilder stan(boolean stanNowy){
            this.stanNowy = stanNowy;
            return this;
        }

        Komputer build(){
            return new Komputer(this);
        }
    }
}
