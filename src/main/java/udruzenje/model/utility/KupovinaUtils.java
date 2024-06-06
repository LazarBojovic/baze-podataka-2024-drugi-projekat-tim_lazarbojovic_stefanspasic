package udruzenje.model.utility;

import udruzenje.model.Kupovina;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KupovinaUtils {


    public static boolean kupovinaObjekta(int korisnik_id, int objekat_id, Date polazak, Date dolazak, String brVozila) {
        try (Connection connection = JDBCUtils.getConnection()){
            String query = "INSERT INTO kupovina (korisnik_id, objekat_id, polazak, dolazak, brVozila) VALUES (?, ?, ?, ?, ?)";
            try(PreparedStatement pstmt = connection.prepareStatement(query)) {
                //System.out.println("Pokusavam da dodam " + korisnik_id + " kupovinu objekta: " + objekat_id);
                pstmt.setInt(1, korisnik_id);
                pstmt.setInt(2, objekat_id);
                pstmt.setDate(3, polazak);
                pstmt.setDate(4, dolazak);
                pstmt.setString(5, brVozila);
                int affectedRows = pstmt.executeUpdate();
                return affectedRows > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Kupovina> selectKupovineByKorisnik(int idKorisnika) {

        List<Kupovina> kupovineList = new ArrayList<>();
        try (Connection connection = JDBCUtils.getConnection()) {
            String query = "SELECT * FROM kupovina k join objekat o on k.objekat_id = o.id WHERE korisnik_id = ?";

            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setInt(1, idKorisnika);

                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        int korisnikId = rs.getInt("korisnik_id");
                        String objekatIme = rs.getString("o.ime");
                        Date polazak = rs.getDate("polazak");
                        Date dolazak = rs.getDate("dolazak");
                        String brVozila = rs.getString("brVozila");
                        Kupovina kupovina = new Kupovina(id, korisnikId, objekatIme, polazak, dolazak, brVozila);
                        kupovineList.add(kupovina);

                    }
                }
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
        System.out.println("Pokusavam da dodam otvorim kupovine");
        return kupovineList;
    }
}
