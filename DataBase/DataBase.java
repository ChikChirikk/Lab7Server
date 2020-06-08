package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    private final String url = "jdbc:postgresql://pg:5432/studs";
    private final String user = "s283945";
    private final String password = "******";
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
