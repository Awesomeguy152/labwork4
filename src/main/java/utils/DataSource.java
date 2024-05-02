package utils;

import model.MusicBand;

import java.util.TreeSet;

public class DataSource {
    private static TreeSet<MusicBand> data = new TreeSet();
    public static TreeSet<MusicBand> getCollection(){
        return data;
    }
    private static void setCollection(TreeSet<MusicBand> data){
        DataSource.data = data;
    }
    private DataSource(){

    }
}
