package Commands;

import Controller.CommandWithoutArg;
import Controller.HumanCollection;
import DataBase.DataBase;
import DataBase.HumansDataBase;
import Utilites.JSONDecoder;
import Utilites.WriterToFile;

import java.io.IOException;
import java.sql.SQLException;

/**
 * // * save collection to file
 * // * @author Polina
 * //
 */
public class Save implements CommandWithoutArg {
    HumanCollection humans = new HumanCollection();
    String name = "save";

    /**
     * @param arg ignore this
     */
    @Override
    public Object execute(Object arg) throws IOException, SQLException {
        HumansDataBase humansDataBase= DataBase.getHumansDataBase();
        humansDataBase.loadCollection(humans.getArray());
        System.out.println("Коллекция успешно сохранена.");
		return null;
    }

    @Override
    public String getName() {
        return name;
    }
}