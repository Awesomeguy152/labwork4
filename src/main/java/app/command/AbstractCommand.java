package app.command;

import app.utils.DataInputHandler;
import app.utils.DataInputHandler;

public abstract class AbstractCommand implements app.command.Command {
    private String name;
    private String description;

    public static DataInputHandler dataInputHandler;

    public DataInputHandler getDataInputHandler() {
        return dataInputHandler;
    }

    public void setDataInputHandler(DataInputHandler dataInputHandler) {
        this.dataInputHandler = dataInputHandler;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public AbstractCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
