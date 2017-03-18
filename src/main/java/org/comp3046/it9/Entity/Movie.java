package org.comp3046.it9.Entity;

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

    public Movie(int id, String name, String type, Date date, String typeClass, String language, int length,
                 String director, String cast, String location, int price) {
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
}
