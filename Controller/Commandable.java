package Controller;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

/**
 * interface for all commands
 * @author Diana
 */
public interface Commandable extends Serializable {
	Object execute(Object object) throws IOException, SQLException;
	String getName();
}
