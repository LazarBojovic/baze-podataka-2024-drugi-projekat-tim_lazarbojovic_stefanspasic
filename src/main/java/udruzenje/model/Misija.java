package udruzenje.model;

import lombok.Getter;
import java.sql.Date;

@Getter
public class Misija {

    private int id;
    private String tip;
    private String imeTela;
    private Date pocetak;
    private Date kraj;

    public Misija(int id, String tip, String imeTela, Date pocetak, Date kraj) {
        this.id = id;
        this.tip = tip;
        this.imeTela = imeTela;
        this.pocetak = pocetak;
        this.kraj = kraj;
    }
}
