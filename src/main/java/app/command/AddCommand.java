package app.command;

import app.MusicBandsManager;
import app.model.MusicBand;
import app.utils.DataSource;

import java.util.TreeSet;

public class AddCommand extends AbstractCommand {
    public AddCommand() {
        super("add", "добавить новый элемент в коллекцию");
    }

    @Override
    public void execute(String[] commandParts) {
        MusicBand musicBand = MusicBandsManager.createMusicBand();
        musicBand.setId(DataSource.getNextId());
        DataSource.getCollection().add(musicBand);
        System.out.println("Элемент добавлен в коллекцию,");
    }
}
