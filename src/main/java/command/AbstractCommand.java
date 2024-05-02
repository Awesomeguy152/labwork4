package command;

import utils.DataInputHandler;

public abstract class AbstractCommand implements Command{
    private String name;
    private String description;
    protected DataInputHandler dataInputHandler;

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
