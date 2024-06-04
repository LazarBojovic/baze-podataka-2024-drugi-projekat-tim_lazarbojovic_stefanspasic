package udruzenje.model.utility;

import udruzenje.model.Objekat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ObjekatUtils {
    public static List<Objekat> selectObjektiBySvemirskoTelo(int teloId) {
        List<Objekat> objektiList = new ArrayList<>();
        try (Connection connection = JDBCUtils.getConnection()) {
            String query = "SELECT * FROM objekat WHERE telo_id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setInt(1, teloId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String ime = rs.getString("ime");
                        String tip = rs.getString("tip");
                        int teloID = rs.getInt("telo_id");
                        int povrsina = rs.getInt("povrsina");
                        int cena = rs.getInt("cena");





                        Objekat objekat = new Objekat(id, ime, tip, teloID, povrsina, cena);
                        objektiList.add(objekat);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return objektiList;
    }
}
