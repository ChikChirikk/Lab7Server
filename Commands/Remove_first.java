package Commands;

import Controller.CommandWithLogin;
import Controller.CommandWithoutArg;
import Controller.HumanCollection;
import Human.HumanBeing;

import java.util.ArrayList;

/**
 * Remove first element
 *
 * @author Polina
 */
public class Remove_first implements CommandWithoutArg, CommandWithLogin {
    String name = "remove_first";
    String username;

    /**
     * @param arg ignore this
     * @return
     */
    public Object execute(Object arg) {
        HumanCollection humans = new HumanCollection();
        if (humans.getSize() == 0) return ("Коллекция пустая.");
        ArrayList<HumanBeing> humanBeings = humans.getArray();
        for (int i = 0; i < humanBeings.size(); i++) {
            if (humanBeings.get(i).isOwner(username)) {
                humans.removeHuman(i);
                System.out.println(1);
				return ("Первый челик успешно удален.");
            }
        }
        return "У вас нет своих элементов в коллекции.";
}

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setUsername(String login) {
        username = login;
    }
}