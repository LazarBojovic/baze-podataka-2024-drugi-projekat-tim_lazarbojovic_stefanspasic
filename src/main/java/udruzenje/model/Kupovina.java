package udruzenje.model;

import lombok.Getter;

import java.sql.Date;
@Getter
public class Kupovina {
    private int id;
    private int korisnik_id;
    private String ojekatIme;
    private Date polazak;
    private Date dolazak;
    private String brVozila;

    public Kupovina(int id, int korisnik_id, String objekatIme, Date polazak, Date dolazak, String brVozila) {
        this.id = id;
        this.korisnik_id = korisnik_id;
        this.ojekatIme = objekatIme;
        this.polazak = polazak;
        this.dolazak = dolazak;
        this.brVozila = brVozila;
    }
}
