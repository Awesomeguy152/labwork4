package app.command;

import app.MusicBandsManager;
import app.model.MusicBand;
import app.utils.CommandManager;
import app.utils.DataSource;

import java.util.TreeSet;

public class AddIfMinCommand extends AbstractCommand{
    public AddIfMinCommand() {
        super("addIfMin", "addIfMin");
    }

    @Override
    public void execute(String[] commandParts) {
        TreeSet<MusicBand> bands = DataSource.getCollection();
        MusicBand min = bands.first();
        for (MusicBand band : bands){
            if(band.compareTo(min) < 0){
                min = band;
            }
        }
        MusicBand addedBand = MusicBandsManager.createMusicBand();

        if (addedBand.compareTo(min) < 0){
            addedBand.setId(DataSource.getNextId());
            DataSource.getCollection().add(addedBand);
            System.out.println("Элемент добавлен в коллекцию,");
        } else {
            System.out.println("Введенный элемент больше, чем наименьший в коллекции");
        }
    }
}
