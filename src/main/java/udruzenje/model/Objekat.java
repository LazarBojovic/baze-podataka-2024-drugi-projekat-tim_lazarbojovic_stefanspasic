package udruzenje.model;

import lombok.Getter;

@Getter
public class Objekat {
    private int id;
    private String ime;
    private String tip;
    private int teloId;
    private double povrsina;
    private double cena;

    public Objekat(int id, String ime, String tip, int teloId, double povrsina, double cena) {
        this.id = id;
        this.ime = ime;
        this.tip = tip;
        this.teloId = teloId;
        this.povrsina = povrsina;
        this.cena = cena;
    }
}
