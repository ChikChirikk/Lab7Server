package Commands;

import Controller.CommandWithLogin;
import Controller.CommandWithoutArg;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * shows the last 5 commands
 *
 * @author Diana
 */
public class History implements CommandWithoutArg, CommandWithLogin {
    static ArrayList history = new ArrayList();
    String name = "history";
    String username;

    /**
     * @param arg ignore this
     */
    public Object execute(Object arg) {
        String res = "Последние выполненные команды:\n";
        try {
            res += history.stream().limit(5).collect(Collectors.joining("\n"));
        } catch (Exception e) {
            res += history.stream().collect(Collectors.joining("\n"));
        }
        return res;
    }

    public void addToHistory(String commandName) {
        if (!(commandName.equals("history")))
            history.add(commandName);
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
