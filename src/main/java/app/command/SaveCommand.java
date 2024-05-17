package app.command;

import app.MusicBandsManager;

public class SaveCommand extends AbstractCommand{
    public SaveCommand() {
        super("save", "save");
    }

    @Override
    public void execute(String[] commandParts) {
        MusicBandsManager.saveCollectionToFile();
        System.out.println("Коллекция сохранена в файл.");
    }
}
