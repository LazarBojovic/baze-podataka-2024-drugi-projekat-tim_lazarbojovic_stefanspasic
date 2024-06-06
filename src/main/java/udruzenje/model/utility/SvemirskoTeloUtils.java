package udruzenje.model.utility;

import udruzenje.model.Misija;
import udruzenje.model.Objekat;
import udruzenje.model.SvemirskoTelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SvemirskoTeloUtils {

    public static boolean loginZahtev(String username, String password){
        try (Connection connection = JDBCUtils.getConnection()){
            String query = "SELECT * FROM korisnik WHERE korisnicko_ime = ? AND lozinka = ?";
            try(PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        }} catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static List<SvemirskoTelo> selectSvaTela() {

        List<SvemirskoTelo> planetLista = new ArrayList<>();
        try (Connection connection = JDBCUtils.getConnection()){

            String query = "SELECT p.id, p.ime, p.tip, p.nastanjiva, COALESCE(d.ime, 'Nije Satelit') AS ime_planete FROM svemirskotelo p LEFT JOIN svemirskotelo d on p.telo_id = d.id WHERE p.nastanjiva = 1";
            try (PreparedStatement pstmt = connection.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("p.id");
                    String ime = rs.getString("p.ime");
                    boolean nastanjiva = rs.getBoolean("p.nastanjiva");
                    String tip = rs.getString("p.tip");
                    String teloId = rs.getString("ime_planete");

                    SvemirskoTelo planet = new SvemirskoTelo(id, ime, nastanjiva,tip,teloId);
                    planetLista.add(planet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return planetLista;
    }

    public static int getKorisnik(String korisnickoIme) {

        try (Connection connection = JDBCUtils.getConnection()){

            String query = "SELECT id FROM Korisnik WHERE korisnicko_ime = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(query)){
                pstmt.setString(1, korisnickoIme);
                try (ResultSet rs = pstmt.executeQuery()) {
                    rs.next();
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static List<Misija> selectMisijeBySvemirskoTelo(int idKorisnika, SvemirskoTelo svemirskoTelo) {

        List<Misija> misijeLista = new ArrayList<>();
        try (Connection connection = JDBCUtils.getConnection()) {
            String query = "SELECT * FROM misija m join svemirskotelo p on m.telo_id = p.id WHERE m.telo_id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setInt(1, svemirskoTelo.getId());
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("m.id");
                        String tip = rs.getString("tip_misije");
                        String imePlanete = rs.getString("p.ime");
                        Date pocetak = rs.getDate("pocetak");
                        Date kraj = rs.getDate("kraj");

                        Misija misija = new Misija(id, tip, imePlanete, pocetak, kraj);
                        misijeLista.add(misija);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return misijeLista;
    }
}
