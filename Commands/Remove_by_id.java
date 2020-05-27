package Commands;

import Controller.CommandWithLogin;
import Controller.Commandable;
import Controller.HumanCollection;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Removes element by its id
 *
 * @author Diana
 */
public class Remove_by_id implements Commandable, CommandWithLogin {
    String name = "remove_by_id";
    String username;

    public String getName() {
        return name;
    }

    /**
     * @param arg id
     * @return
     */
    public Object execute(Object arg) {
        HumanCollection humans = new HumanCollection();
        try {
            int id = Integer.parseInt((String) arg);
            //if (humans.findIndexOfElemById(id) != -1) return "Нет челика с таким id.";
            if (humans.getArray().stream().filter(h -> h.getIdAndCheck(username) == id ).count() != 0) {
                humans.setArray((ArrayList) humans.getArray().stream()
                        .filter(h -> h.getId() == Long.parseLong((String) arg))
                        .collect(Collectors.toList()));
                return "Челик успешно удален";
            } else return "У вас нет права удалить этого челика.";

        } catch (NumberFormatException exp) {
            return ("Значение аргумента должно быть типа:\"long\".");

        }

    }

    @Override
    public void setUsername(String login) {
        username = login;
    }
}
