package app.utils;

import app.model.MusicBand;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Iterator;
import java.util.TreeSet;

public class DataSource {
    private static long currentId;
    private static ZonedDateTime timeOfInitialization;
    private static TreeSet<MusicBand> data = new TreeSet<>();
    public static TreeSet<MusicBand> getCollection(){
        return data;
    }
    public static void setCollection(TreeSet<MusicBand> data){
        if (data.size() > 0) {
            ZonedDateTime minDate = null;
            long maxId = 1;
            Iterator<MusicBand> iterator = data.iterator();
            while (iterator.hasNext()){
                MusicBand band = iterator.next();
                if(minDate == null){
                    minDate = band.getCreationDate();
                }
                if(band.getCreationDate().compareTo(minDate) < 0){
                    minDate = band.getCreationDate();
                }
                if(band.getId() > maxId){
                    maxId = band.getId();
                }
            }
            timeOfInitialization = minDate;
            currentId = maxId;
        }
        DataSource.data = data;
    }

    public static long getNextId() {
        return ++currentId;
    }

    private DataSource(){

    }

    public static ZonedDateTime getTimeOfInitialization() {
        return timeOfInitialization;
    }
}
