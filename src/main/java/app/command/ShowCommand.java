package app.command;

import app.model.MusicBand;
import app.utils.DataSource;

import java.util.TreeSet;

public class ShowCommand extends AbstractCommand {


    public ShowCommand() {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");

    }

    @Override
    public void execute(String[] commandParts) {
        TreeSet<MusicBand> musicBands = DataSource.getCollection();
        if(musicBands.isEmpty()){
            System.out.println("Коллекция пуста.");
        }
        for(MusicBand band: musicBands){
            System.out.println(band);
        }
    }
}
