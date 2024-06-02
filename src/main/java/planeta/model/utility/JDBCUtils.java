package planeta.model.utility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBCUtils {
    public static Connection connection = null;


    public static void connect(){
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "lakimunze");
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/udruzenjezus", properties);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean loginZahtev(String username, String password){
        String query = "SELECT * FROM korisnik WHERE korisnicko_ime = ? AND lozinka = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static List<String> selectSvePlanete() {

        List<String> planetLista = new ArrayList<>();
        String query = "SELECT p.naziv AS planet_name, s.naziv AS satellite_name " +
                "FROM planeta p LEFT JOIN satelit s ON p.id = s.id_planeta";
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String planetName = rs.getString("ime");
                String satelliteName = rs.getString("satellite_name");
                if (satelliteName != null) {
                    planetLista.add(planetName + " - Satelit: " + satelliteName);
                } else {
                    planetLista.add(planetName + " - Nema satelita");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return planetLista;
    }
}
