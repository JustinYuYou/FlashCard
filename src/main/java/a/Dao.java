package a;

import javax.xml.transform.Result;
import java.sql.*;

public class Dao {
    private Connection connection;

    public Dao(Connection connection) {
        this.connection = connection;
    }

    public String findWord() {
        String sql = "select word from flashcard order by rand()";
        ResultSet rs = null;
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            rs = stmt.executeQuery();

            if(rs.next()) {
                String word = rs.getString(2);
                return word;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertWord(String word) {
        String sql = "INSERT INTO flashcard (word) values (?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(2, word);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
