package app.command;

import app.model.MusicBand;
import app.utils.DataSource;

public class RemoveByIdCommand extends AbstractCommand{
    public RemoveByIdCommand() {
        super("removeById", "removeById");
    }

    @Override
    public void execute(String[] commandParts) {
        if(commandParts.length < 2){
            System.out.println("Необходимо указать id удаляемой записи");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(commandParts[1]);
        } catch (NumberFormatException e) {
            System.out.println("Неверно уазан id");
            return;
        }
        MusicBand deletedBand = null;
        for(MusicBand band : DataSource.getCollection()){
            if(band.getId() == id){
                deletedBand = band;
            }
        }
        DataSource.getCollection().remove(deletedBand);
        System.out.println("Запись удалена.");
    }
}
