package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    private final String url = "jdbc:postgresql://localhost:5432/studs";
    private final String user = "postgres";
    private final String password = "iow988";
    private Connection connection;
    private Statement statement;
    private static UsersDataBase users;
    private static HumansDataBase humans;

    public DataBase() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement();
        users = new UsersDataBase(connection);
        humans = new HumansDataBase(connection);
    }

    public static UsersDataBase getUserDataBase() {
        return users;
    }

    public static HumansDataBase getHumansDataBase() {
        return humans;
    }
    public DataBase getDataBase(){
        return this;
    }
}