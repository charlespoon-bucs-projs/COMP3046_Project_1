package org.comp3046.it9.Entity;

import org.comp3046.it9.Utils.Convert;

import java.util.Date;

public class Movie {
    private final int id;
    private final String name;
    private final String type;
    private final Date date;
    private final String typeClass;
    private final String language;
    private final int length;
    private final String director;
    private final String cast;
    private final String location;
    private final int price;
    private final int startHour;
    private final int startMinute;

    public Movie(int id, String name, String type, Date date, String typeClass, String language, int length,
                 String director, String cast, String location, int price, int startHour, int startMinute) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.date = date;
        this.typeClass = typeClass;
        this.language = language;
        this.length = length;
        this.director = director;
        this.cast = cast;
        this.location = location;
        this.price = price;
        this.startHour = startHour;
        this.startMinute = startMinute;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public String getTypeClass() {
        return typeClass;
    }

    public String getLanguage() {
        return language;
    }

    public int getLength() {
        return length;
    }

    public String getDirector() {
        return director;
    }

    public String getCast() {
        return cast;
    }

    public String getLocation() {
        return location;
    }

    public int getPrice() {
        return price;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getStartMinute() {
        return startMinute;
    }

    @Override
    public String toString() {
        String msg = "Movie {\r\n";
        msg += "Id: %d, \r\n";
        msg += "Name: %s, \r\n";
        msg += "Type: %s, \r\n";
        msg += "Date: %s, \r\n";
        msg += "TypeClass: %s, \r\n";
        msg += "Language: %s, \r\n";
        msg += "Length: %d, \r\n";
        msg += "Director: %s, \r\n";
        msg += "Cast: %s, \r\n";
        msg += "Location: %s, \r\n";
        msg += "Price: %d, \r\n";
        msg += "StartHour: %d, \r\n";
        msg += "StartMinute: %d\r\n";
        msg += "}";
        return String.format(
                msg, getId(), getName(), getType(), Convert.dateToString(getDate()), getTypeClass(), getLanguage(),
                getLength(), getDirector(), getCast(), getLocation(), getPrice(), getStartHour(), getStartMinute());
    }
}
