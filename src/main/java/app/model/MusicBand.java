package app.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.ZonedDateTime;

public class MusicBand implements Comparable<MusicBand> {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    @JsonFormat(pattern = "dd.MM.yyyy hh:mm")
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer numberOfParticipants; //Поле может быть null, Значение поля должно быть больше 0
    private app.model.MusicGenre genre; //Поле может быть null
    private app.model.Person frontMan; //Поле может быть null

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(Integer numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public app.model.MusicGenre getGenre() {
        return genre;
    }

    public void setGenre(app.model.MusicGenre genre) {
        this.genre = genre;
    }

    public app.model.Person getFrontMan() {
        return frontMan;
    }

    public void setFrontMan(app.model.Person frontMan) {
        this.frontMan = frontMan;
    }

    @Override
    public String toString() {
        return "MusicBand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", numberOfParticipants=" + numberOfParticipants +
                ", genre=" + genre +
                ", frontMan=" + frontMan +
                '}';
    }

    @Override
    public int compareTo(MusicBand o) {
        int firstNumber = numberOfParticipants == null ? 0 : numberOfParticipants;
        int secondNumber = o.numberOfParticipants == null ? 0 : o.numberOfParticipants;
        return firstNumber - secondNumber;
    }
}
