package View;

import java.sql.SQLException;

public interface Command {
    void execute() throws SQLException;
    String Display();
}
