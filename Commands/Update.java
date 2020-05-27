package Commands;

import Controller.CommandWithLogin;
import Controller.CommandWithObject;
import Controller.HumanCollection;
import Human.HumanBeing;

import java.util.ArrayList;

/**
 * add update element in collection by its id
 *
 * @author Polina
 */
public class Update implements CommandWithObject, CommandWithLogin {
    static long arg;
    String whyFailed;
    String name = "update.";
    String username;

    public String getName() {
        return name;
    }

    /**
     * @param hum id of element
     * @return
     */
    public Object execute(Object hum) {
        HumanCollection humans = new HumanCollection();
        if (((HumanBeing) hum).getName() != null) {
            HumanBeing human = (HumanBeing) hum;
            ArrayList collection = new ArrayList();
            collection = humans.getArray();
            collection.set((int) arg, human);
            humans.setArray(collection);
            return ("Челик [id:" + arg + "] успешно обновлен.");
        }
        return "";
    }


    @Override
    public boolean check(String arg) {
        HumanCollection humans = new HumanCollection();
        try {
            int id = Integer.parseInt(arg);
            Update.arg = id;
            if (humans.getSize() == 0) {
                whyFailed = "Коллекция итак пустая";
                return false;
            } else if (humans.findIndexOfElemById(id) == -1) {
                whyFailed = "Такого элемента нет в коллекции";
                return false;
            } else if (!humans.findHumanById(id).isOwner(username)) {
                whyFailed = "У вас нет права обновить этого челика.";
                return false;
            } else return true;
        } catch (Exception e) {
            whyFailed = "Неверный формат аргумента";
            return false;
        }
    }

    @Override
    public String whyFailed() {
        return whyFailed;
    }

    @Override
    public void setUsername(String login) {
        username = login;
    }
}

