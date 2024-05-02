package command;

import model.MusicBand;
import utils.DataSource;


import java.util.TreeSet;

public class ShowCommand extends AbstractCommand{


    public ShowCommand() {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");

    }

    @Override
    public void execute(String[] commandParts) {
        System.out.println(DataSource.getCollection());
    }
}
