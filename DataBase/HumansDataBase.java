package DataBase;


import Controller.HumanCollection;
import Human.HumanBeing;

import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class HumansDataBase {
    Statement statement;
    Connection connection;

    public HumansDataBase(Connection connection) throws SQLException {
        this.connection = connection;
        this.statement = connection.createStatement();
        this.createHumansDB();
        this.createDateCreation();
        HumanCollection.setDateCreation(this.getDateCreation());
    }

    private LocalDate getDateCreation() throws SQLException {
        String sql = " SELECT * FROM datecreation";
        ResultSet rs = statement.executeQuery(sql);
        if (rs.next()) return LocalDate.parse(rs.getString(1));
        else return LocalDate.now();
    }


    private void createDateCreation() throws SQLException {
        try {
            String sql = "CREATE TABLE datecreation (dateCreation text NOT NULL)";
            statement.execute(sql);
            String sql1 = "INSERT INTO datecreation (dateCreation) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setString(1, LocalDate.now().toString());
            preparedStatement.execute();
        } catch (Exception e) {
        }
    }

    public void createHumansDB() throws SQLException {
        try {
//            String createSequence = "CREATE SEQUENCE id_sequence start 1 increment 1;";
//            statement.execute(createSequence);
            String createTableSQL = "CREATE TABLE humans " +
                    "(id BIGINT PRIMARY KEY NOT NULL ," +
                    " owner TEXT NOT NULL , " +
                    " name TEXT NOT NULL , " +
                    " x BIGINT  NOT NULL , " +
                    " y BIGINT NOT NULL , " +
                    " creationDate TEXT NOT NULL , " +
                    " realHero BOOLEAN, " +
                    " hasToothpick BOOLEAN, " +
                    " impactSpeed DOUBLE PRECISION NOT NULL , " +
                    " soundtrackName TEXT NOT NULL , " +
                    " minutesOfWaiting DOUBLE PRECISION NOT NULL , " +
                    " weaponType TEXT NOT NULL , " +
                    " carName TEXT NOT NULL , " +
                    " carCOOL BOOLEAN NOT NULL )";
            statement.execute(createTableSQL);
        } catch (Exception e) {
        }
    }

    public void loadCollection(ArrayList<HumanBeing> humans) throws SQLException {
        for (HumanBeing human : humans) {
            this.insertHuman(human);
        }
    }

    public ArrayList<HumanBeing> getCollection() throws SQLException, ParseException {
        ArrayList<HumanBeing> humans = new ArrayList<HumanBeing>();
        String sql = " SELECT * FROM humans";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            HumanBeing human = new HumanBeing();
            human.setId(rs.getLong("id"));
            human.setOwner(rs.getString("owner"));
            human.setName(rs.getString("name"));
            HumanBeing.Coordinates coordinates = human.new Coordinates();
            coordinates.setX(rs.getLong("x"));
            coordinates.setY(rs.getLong("y"));
            human.setCoordinates(coordinates);
            human.setCreationDate(LocalDate.parse(rs.getString("creationdate")));
            human.setRealHero(rs.getBoolean("realhero"));
            human.setHasToothpick(rs.getBoolean("hastoothpick"));
            human.setImpactSpeed(rs.getDouble("impactspeed"));
            human.setSoundtrackName(rs.getString("soundtrackname"));
            human.setMinutesOfWaiting(rs.getDouble("minutesofwaiting"));
            human.setWeaponType(HumanBeing.WeaponType.valueOf(rs.getString("weapontype")));
            HumanBeing.Car car = human.new Car();
            car.setCarName(rs.getString("carname"));
            car.setCarCool(rs.getBoolean("carcool"));
            human.setCar(car);
            humans.add(human);
        }
        return humans;
    }

    public void insertHuman(HumanBeing human) throws SQLException {
        try {
            String sql = "INSERT INTO humans (id, owner, name, x, y, creationDate, realHero, hasToothpick, impactSpeed, soundtrackName, minutesOfWaiting, " +
                    "weaponType, carName, carCool) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, human.getId());
            preparedStatement.setString(2, human.getOwner());
            preparedStatement.setString(3, human.getName());
            preparedStatement.setLong(4, human.getCoordinates().getX());
            preparedStatement.setLong(5, human.getCoordinates().getY());
            LocalDate localDate = human.getCreationDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedString = localDate.format(formatter);
            preparedStatement.setString(6, formattedString);
            preparedStatement.setBoolean(7, human.isRealHero());
            preparedStatement.setBoolean(8, human.isHasToothpick());
            preparedStatement.setDouble(9, human.getImpactSpeed());
            preparedStatement.setString(10, human.getSoundtrackName());
            preparedStatement.setDouble(11, human.getMinutesOfWaiting());
            preparedStatement.setString(12, human.getWeaponType().toString());
            preparedStatement.setString(13, human.getCar().getCarName());
            preparedStatement.setBoolean(14, human.getCar().isCarCool());
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
