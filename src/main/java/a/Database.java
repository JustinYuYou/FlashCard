package a;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private Connection connection;

    public Connection openConnection() {
        try {
            //The Structure for this Connection is driver:language:path
            //The path assumes you start in the root of your project unless given a non-relative path
            final String dbPath = "C:\\Users\\ChenTing Yu\\IdeaProjects\\flashcard\\src\\main\\database\\flashcard.db";
            final String CONNECTION_URL = "jdbc:sqlite:" + dbPath;

            // Open a database connection to the file given in the path
            connection = DriverManager.getConnection(CONNECTION_URL);

            // Start a transaction
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            if(connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return connection;
    }

    public Connection getConnection() {
        if(connection == null) {
            return openConnection();
        } else {
            return connection;
        }
    }

    public void closeConnection(boolean commit) {
        try {
            if (commit) {
                //This will commit the changes to the database
                connection.commit();
            } else {
                //If we find out something went wrong, pass a false into closeConnection and this
                //will rollback any changes we made during this connection
                connection.rollback();
            }

            connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
