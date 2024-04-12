package command;

import model.MusicBand;

import java.util.TreeSet;

public class ShowCommand implements Command{
    private TreeSet<MusicBand> data;

    public ShowCommand(TreeSet<MusicBand> data) {
        this.data = data;
    }

    @Override
    public void execute(String[] commandParts) {
        System.out.println(data);
    }
}
