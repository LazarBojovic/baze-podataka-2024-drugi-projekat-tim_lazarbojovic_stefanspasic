package udruzenje.model.utility;

import udruzenje.model.Planeta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanetaUtils {

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


    public static List<Planeta> selectSvePlanete() {

        List<Planeta> planetLista = new ArrayList<>();
        try (Connection connection = JDBCUtils.getConnection()){

            String query = "SELECT id,ime,nastanjiva,tip,planeta_id FROM planeta";
            try (PreparedStatement pstmt = connection.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String ime = rs.getString("ime");
                    boolean nastanjiva = rs.getBoolean("nastanjiva");
                    String tip = rs.getString("tip");
                    int planetaId = rs.getInt("planeta_id");

                    Planeta planet = new Planeta(id, ime, nastanjiva,tip,planetaId);
                    planetLista.add(planet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return planetLista;
    }

}
