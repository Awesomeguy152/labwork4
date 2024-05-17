package app.command;

import app.model.MusicBand;
import app.utils.DataSource;

import java.util.ArrayList;
import java.util.List;

public class FilterLessThanNumberOfParticipantsCommand extends AbstractCommand{
    public FilterLessThanNumberOfParticipantsCommand() {
        super("filterLessThanNumberOfParticipants", "filterLessThanNumberOfParticipants");
    }

    @Override
    public void execute(String[] commandParts) {
        if(commandParts.length < 2){
            System.out.println("Необходимо указать число");
            return;
        }
        int number;
        try {
            number = Integer.parseInt(commandParts[1]);
            if(number < 0){ throw new RuntimeException();};
        } catch (NumberFormatException e) {
            System.out.println("Неверно указано число");
            return;
        }

        for(MusicBand band : DataSource.getCollection()){
            if(band.getNumberOfParticipants() != null && band.getNumberOfParticipants().compareTo(number) < 0){
                System.out.println(band);
            }
        }
    }
}
