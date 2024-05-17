package app.command;

import app.MusicBandsManager;
import app.model.MusicBand;
import app.model.MusicGenre;
import app.utils.DataSource;

import java.util.Arrays;

public class CountLessThanGenreCommand extends AbstractCommand{
    public CountLessThanGenreCommand() {
        super("countLessThanGenre", "countLessThanGenre");
    }

    @Override
    public void execute(String[] commandParts) {
        MusicGenre genre= MusicBandsManager
                .inputMusicGenre("Введите жанр: " + Arrays.toString(MusicGenre.values()), true);

        int count = 0;
        for(MusicBand band : DataSource.getCollection()){
            MusicGenre bandGenre = band.getGenre();
            if(bandGenre!= null && bandGenre.ordinal() < genre.ordinal()){
                count ++;
            }
        }

        System.out.println("Количество элементов коллеции, поле Genre которого меньше, чем " + genre + " - " + count);
    }
}
