package Commands;

import Controller.CommandWithLogin;
import Controller.CommandWithObject;
import Controller.CommandWithoutArg;
import Controller.HumanCollection;
import DataBase.*;
import Human.HumanBeing;

import java.sql.SQLException;


public class Add implements CommandWithObject, CommandWithoutArg, CommandWithLogin {
    String name = "add";
    String username;

    public String getName() {
        return name;
    }

    public Object execute(Object arg) throws SQLException {
        HumansDataBase humansDataBase = DataBase.getHumansDataBase();
        HumanCollection humans = new HumanCollection();
        if (!((HumanBeing) arg).getName().equals(null)) {
            HumanBeing human = (HumanBeing) arg;
            human.setOwner(username);
            humans.addToCollection(human);
            humansDataBase.loadCollection(humans.getArray());
            return "Челик успешно добавлен в коллекцию";
        } else return "";
    }

    @Override
    public boolean check(String arg) {
        return true;
    }

    @Override
    public String whyFailed() {
        return null;
    }

    @Override
    public void setUsername(String login) {
        username = login;
    }
}