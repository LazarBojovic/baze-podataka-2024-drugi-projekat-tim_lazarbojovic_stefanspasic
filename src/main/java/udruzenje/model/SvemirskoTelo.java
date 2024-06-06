package udruzenje.model;

import lombok.Getter;

@Getter
public class SvemirskoTelo {

    private final int id;
    private String  ime;
    private String tip;
    private String teloID;
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

    public SvemirskoTelo(int id, String ime, boolean nastanjiva, String tip, String teloIme) {
        this.id = id;
        this.ime = ime;
        this.nastanjiva = nastanjiva;
        this.tip = tip;
        this.teloID = teloIme;
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
