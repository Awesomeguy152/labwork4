package app.command;

import app.utils.DataSource;

public class ClearCommand extends AbstractCommand{
    public ClearCommand() {
        super("clear", "clear");
    }

    @Override
    public void execute(String[] commandParts) {
        DataSource.getCollection().clear();
        System.out.println("Колекция очищена.");
    }
}
