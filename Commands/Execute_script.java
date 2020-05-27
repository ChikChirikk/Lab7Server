package Commands;

import Controller.Commandable;

import java.io.IOException;

public class Execute_script implements Commandable {
    String name = "execute_script";

    public String getName() {
        return name;
    }

    /**
     * @param arg of file
     */
    public Object execute(Object arg) throws IOException {
        return null;
    }
}
