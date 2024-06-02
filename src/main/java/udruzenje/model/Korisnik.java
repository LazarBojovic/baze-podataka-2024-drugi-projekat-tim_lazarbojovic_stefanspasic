package udruzenje.model;

import lombok.Getter;

@Getter
public class Korisnik{
    private final int id;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String lozinka;

    public Korisnik(int id, String ime, String prezime, String korisnickoIme, String lozinka) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

}
