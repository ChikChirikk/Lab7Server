package Commands;

import Controller.CommandWithLogin;
import Controller.CommandWithObject;
import Controller.CommandWithoutArg;
import Controller.HumanCollection;
import Human.HumanBeing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Removes all movies from collection
 *
 * @author Diana
 */
public class Remove_lower implements CommandWithoutArg, CommandWithObject, CommandWithLogin {
    String whyFailed;
    String name = "remove_lower";
    String username;

    /**
     * @param arg ignore this
     * @return
     */
    @Override
    public Object execute(Object arg) throws IOException {
        HumanCollection humans = new HumanCollection();
        try {
            if (((HumanBeing) arg).getName() != null) {
                humans.setArray((ArrayList) humans.getArray().stream()
                        .filter(h -> h.compareToAndCheckUser(username, (HumanBeing) arg) > 0)
                        .collect(Collectors.toList()));
                return ("Все элементы коллекции, меньшие, чем заданный успешно удалены");
            } else return "";
        } catch (Exception e) {
            return ("Коллекция итак пустая.");
        }
    }

    @Override
    public boolean check(String arg) {
        HumanCollection humans = new HumanCollection();
        if (humans.getSize() > 0) {
            for (HumanBeing human : humans.getArray()) {
                if (human.isOwner(username)) return true;
            }
            whyFailed = "У вас нет своих элементов в коллекции.";
            return false;
        } else {
            whyFailed = "Коллекция итак пустая";
            return false;
        }
    }

    @Override
    public String getName() {
        return name;
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
