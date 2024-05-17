package app.command;

import app.MusicBandsManager;
import app.model.MusicBand;
import app.utils.DataSource;

import java.util.ArrayList;
import java.util.List;

public class UpdateCommand extends AbstractCommand{
    public UpdateCommand() {
        super("update", "update");
    }

    @Override
    public void execute(String[] commandParts) {
        if(commandParts.length < 2){
            System.out.println("Необходимо указать id обновляемой записи");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(commandParts[1]);
        } catch (NumberFormatException e) {
            System.out.println("Неверно уазан id");
            return;
        }

        MusicBand band = MusicBandsManager.createMusicBand();
        boolean delResult = DataSource.getCollection().removeIf(b -> b.getId() == id);
        if(delResult){
            DataSource.getCollection().add(band);
            System.out.println("Элемент обновлен");
        } else {
            System.out.println("Элемента с таким id не существует");
        }

    }
}
