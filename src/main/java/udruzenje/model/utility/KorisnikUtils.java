package udruzenje.model.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class KorisnikUtils {
    public static boolean registracijaKorisnika(String ime, String prezime, String korisnickoIme, String lozinka) {
        try (Connection connection = JDBCUtils.getConnection()){
            String query = "INSERT INTO korisnik (ime, prezime, korisnicko_ime, lozinka) VALUES (?, ?, ?, ?)";
            try(PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setString(1, ime);
                pstmt.setString(2, prezime);
                pstmt.setString(3, korisnickoIme);
                pstmt.setString(4, lozinka);
                int affectedRows = pstmt.executeUpdate();
                return affectedRows > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
