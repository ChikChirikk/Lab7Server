package Connection;

import DataBase.HumansDataBase;
import DataBase.UsersDataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseController {
    private final String url = "jdbc:postgresql://localhost:5432/studs";
    private final String user = "postgres";
//    private final String url = "jdbc:postgresql://pg:5432/studs";
//    private final String user = "s283945";
    private final String password = "iow988";
    private Connection connection;
    private Statement statement;
    private static UsersDataBase users;
    private static HumansDataBase humans;

    public DataBaseController() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement();
        users = new UsersDataBase(connection);
        humans = new HumansDataBase(connection);
    }

    protected static UsersDataBase getUserDataBase() {
        return users;
    }

    public static HumansDataBase getHumansDataBase() {
        return humans;
    }
}