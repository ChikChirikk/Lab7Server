package Commands;

import Controller.CommandWithoutArg;
import Controller.HumanCollection;

import java.util.stream.Collectors;

/**
 * show all elements of collection
 *
 * @author Polina
 */
public class Show implements CommandWithoutArg {
    HumanCollection humans = new HumanCollection();
    String name = "show";

    public String getName() {
        return name;
    }

    /**
     * @param arg ignore this
     * @return
     */
    @Override
    public Object execute(Object arg) {
        if (humans.getSize() != 0) {
            String shownCollection = humans.getArray().stream().map(h -> h.getHuman()).collect(Collectors.joining("\n"));
            return shownCollection;
        } else
            return ("Коллекция пустая.");
    }
}

