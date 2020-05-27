package Commands;

import Controller.CommandWithoutArg;
import Controller.HumanCollection;

/**
 * show info about collection
 *
 * @author Polina
 */
public class Info implements CommandWithoutArg {
    HumanCollection humans = new HumanCollection();
    String name = "info";

    public String getName() {
        return name;
    }

    /**
     * @param arg ignore this
     * @return
     */
    public Object execute(Object arg) {
        return (humans.getInfo());
    }
}