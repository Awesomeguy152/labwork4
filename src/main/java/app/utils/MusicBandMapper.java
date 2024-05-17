package app.utils;

import app.model.MusicBand;
import app.model.MusicBandForXml;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MusicBandMapper {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    public static List<MusicBand> toMusicBandList(List<MusicBandForXml> xmlMusicBands){
        List<MusicBand> list = new ArrayList<>();
        for(MusicBandForXml band : xmlMusicBands){
            MusicBand musicBand = new MusicBand();
            musicBand.setId(band.getId());
            musicBand.setName(band.getName());
            String timeString = band.getCreationDate();
            LocalDateTime time = LocalDateTime.parse(timeString, formatter);
            ZonedDateTime zonedDateTime = ZonedDateTime.of(time, ZoneId.of("UTC"));
            musicBand.setCreationDate(zonedDateTime);
            musicBand.setCoordinates(band.getCoordinates());
            musicBand.setGenre(band.getGenre());
            musicBand.setNumberOfParticipants(band.getNumberOfParticipants());
            musicBand.setFrontMan(band.getFrontMan());
            list.add(musicBand);
        }
        return list;
    }
    public static List<MusicBandForXml> toMusicBandForXmlList(List<MusicBand> musicBands){
        List<MusicBandForXml> list = new ArrayList<>();
        for(MusicBand band : musicBands){
            MusicBandForXml musicBand = new MusicBandForXml();
            musicBand.setId(band.getId());
            musicBand.setName(band.getName());
            String timeString = band.getCreationDate().format(formatter);
            musicBand.setCreationDate(timeString);
            musicBand.setCoordinates(band.getCoordinates());
            musicBand.setGenre(band.getGenre());
            musicBand.setNumberOfParticipants(band.getNumberOfParticipants());
            musicBand.setFrontMan(band.getFrontMan());
            list.add(musicBand);
        }
        return list;
    }
}
