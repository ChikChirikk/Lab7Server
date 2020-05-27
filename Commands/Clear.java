package Commands;

import Controller.CommandWithLogin;
import Controller.CommandWithoutArg;
import Controller.HumanCollection;
import Human.HumanBeing;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Removes all movies from collection
 *
 * @author Polina
 */
public class Clear implements CommandWithoutArg, CommandWithLogin {
    String name = "clear";
    String username;

    public String getName() {
        return name;
    }

    /**
     * @param arg ignore this
     * @return
     */
    public Object execute(Object arg) {
        HumanCollection humans = new HumanCollection();
        if (humans.getSize() > 0) {
            if (((ArrayList<HumanBeing>) humans.getArray()).stream()
                    .filter(h -> h.isOwner(username))
                    .count() > 0) {
                ArrayList<HumanBeing> newHumans = (ArrayList<HumanBeing>) humans.getArray().stream()
                        .filter(h -> h.isOwner(username))
                        .collect(Collectors.toList());
                return "Все ваши челики успешно удалены.";
            } else return "У вас нет своих элементов в коллекции.";
        } else return "Коллекция итак пустая.";

    }

    @Override
    public void setUsername(String login) {
        username = login;
    }
}