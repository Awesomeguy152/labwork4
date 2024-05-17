package app.command;

import app.MusicBandsManager;
import app.model.MusicBand;
import app.utils.DataSource;

import java.util.ArrayList;
import java.util.List;

public class RemoveGreaterCommand extends AbstractCommand{
    public RemoveGreaterCommand() {
        super("removeGreater", "removeGreater");
    }

    @Override
    public void execute(String[] commandParts) {
        MusicBand delBand = MusicBandsManager.createMusicBand();

        List<MusicBand> deletedBands = new ArrayList<>();
        for(MusicBand band : DataSource.getCollection()){
            if(band.compareTo(delBand) > 0){
                deletedBands.add(band);
            }
        }
        DataSource.getCollection().removeAll(deletedBands);
        System.out.println("Все элементы больше, чем заданный удалены");
    }
}
