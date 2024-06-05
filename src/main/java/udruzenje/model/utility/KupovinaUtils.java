package udruzenje.model.utility;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class KupovinaUtils {
    public static boolean kupovinaObjekta(int korisnik_id, int objekat_id, Date polazak, Date dolazak, String brVozila) {
        try (Connection connection = JDBCUtils.getConnection()){
            String query = "INSERT INTO kupovina (korisnik_id, objekat_id, polazak, dolazak, brVozila) VALUES (?, ?, ?, ?, ?)";
            try(PreparedStatement pstmt = connection.prepareStatement(query)) {
                System.out.println("Pokusavam da dodam " + korisnik_id + " kupovinu objekta: " + objekat_id);
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
}
