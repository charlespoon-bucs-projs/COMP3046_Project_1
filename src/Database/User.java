package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    public Sqlite sqlite = new Sqlite();

    public boolean checkUserCredentialsValid(String name, String password) {
        try {
            Connection conn = sqlite.getConnection();
            String sql = "SELECT 1 FROM User WHERE name=? AND password=? LIMIT 1";
            ResultSet rs = null;
            PreparedStatement pst = conn.prepareStatement(sql);

            Sqlite.setParams(pst, name, password);

            rs = pst.executeQuery();
            return rs != null && rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
