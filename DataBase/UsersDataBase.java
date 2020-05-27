package DataBase;

import java.sql.*;

public class UsersDataBase {
    Statement statement;
    Connection connection;

    public UsersDataBase(Connection connection) throws SQLException {
        this.connection = connection;
        this.statement = connection.createStatement();
        this.createUsersDB();
    }

    public void createUsersDB() throws SQLException {
        try {
            String createTableSQL = "CREATE TABLE users " +
                    "(login TEXT, " +
                    " password TEXT)";
            statement.execute(createTableSQL);
        } catch (Exception e) {
        }
    }

    public void addUser(String login, String password) throws SQLException {
        String sql = "INSERT INTO users (login, password) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        preparedStatement.execute();
    }

    public boolean isValue(String word, String value) throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT " + word + " FROM users");
        while (rs.next())
            if (value.equals(rs.getString(1)))
                return true;
        return false;
    }


}


