package udruzenje.model.utility;

import udruzenje.model.Korisnik;
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

            String query = "SELECT id,ime,nastanjiva,tip,telo_id FROM svemirskotelo";
            try (PreparedStatement pstmt = connection.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String ime = rs.getString("ime");
                    boolean nastanjiva = rs.getBoolean("nastanjiva");
                    String tip = rs.getString("tip");
                    int teloId = rs.getInt("telo_id");

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
}
