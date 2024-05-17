package app.command;

public class ExitCommand extends AbstractCommand{

    public ExitCommand() {
        super("exit", "exit");
    }

    @Override
    public void execute(String[] commandParts) {
        System.exit(0);
    }
}
