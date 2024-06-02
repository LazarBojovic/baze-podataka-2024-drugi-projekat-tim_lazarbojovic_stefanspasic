package udruzenje.model;

import lombok.Getter;

@Getter
public class Planeta {

    private final int id;
    private String  ime;
    private String tip;
    private int planetaId;
    private boolean istrazen;
    private boolean nastanjiva;
    private double  udaljenost;
    private double  nizaTemp;
    private double  visaTemp;
    private double  procenatKiseonika;
    private double  procenatGasa;
    private double  gravitacija;
    private double  brzinaOrbitiranja;
    private int brUmrlih;
    private int godinaIstrazivanja;

    //public Planeta(int id, String ime, boolean istrazen, boolean nastanjiva, double udaljenost, double nizaTemp, double visaTemp, double procenatKiseonika, double procenatGasa, double gravitacija, double brzinaOrbitiranja, int brUmrlih, int godinaIstrazivanja) {
    public Planeta(int id, String ime, boolean nastanjiva,String tip, int planetaId) {
        this.id = id;
        this.ime = ime;
        this.nastanjiva = nastanjiva;
        this.tip = tip;
        this.planetaId = planetaId;
//        this.nastanjiva = nastanjiva;
//        this.udaljenost = udaljenost;
//        this.nizaTemp = nizaTemp;
//        this.visaTemp = visaTemp;
//        this.procenatKiseonika = procenatKiseonika;
//        this.procenatGasa = procenatGasa;
//        this.gravitacija = gravitacija;
//        this.brzinaOrbitiranja = brzinaOrbitiranja;
//        this.brUmrlih = brUmrlih;
//        this.godinaIstrazivanja = godinaIstrazivanja;
    }

}
