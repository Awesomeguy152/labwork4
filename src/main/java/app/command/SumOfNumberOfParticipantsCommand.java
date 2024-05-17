package app.command;

import app.model.MusicBand;
import app.utils.DataSource;
import com.ctc.wstx.shaded.msv_core.verifier.AbstractVerifier;

public class SumOfNumberOfParticipantsCommand extends AbstractCommand {
    public SumOfNumberOfParticipantsCommand() {
        super("sumOfNumberOfParticipants", "sumOfNumberOfParticipants");
    }

    @Override
    public void execute(String[] commandParts) {
        int sum = 0;
        for(MusicBand band : DataSource.getCollection()){
            if(band.getNumberOfParticipants() != null){
                sum += band.getNumberOfParticipants();
            }
        }
        System.out.println("Сумма всех количеств участников групп среди всей коллекции - " + sum);
    }
}
