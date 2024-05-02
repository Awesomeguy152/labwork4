package command;

public class AddCommand extends AbstractCommand{
    public AddCommand() {
        super("add", "добавить новый элемент в коллекцию");
    }

    @Override
    public void execute(String[] commandParts) {

    }
}
