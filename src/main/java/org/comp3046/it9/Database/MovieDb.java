package org.comp3046.it9.Database;

import org.comp3046.it9.Entity.Movie;
import org.comp3046.it9.Utils.Convert;
import org.jooq.DSLContext;
import org.jooq.Record13;
import org.jooq.Result;
import org.jooq.exception.DataAccessException;

import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

import static org.comp3046.it9.Database.JooqGenerated.tables.Movie.MOVIE;

public class MovieDb {
    private final Sqlite sqlite;

    public MovieDb(Sqlite sqlite) {
        this.sqlite = sqlite;
    }

    private boolean createMovie(String name, String type, Date date,
                                String typeClass, String language,
                                int length, String director,
                                String cast, String location,
                                int price, int startHour, int startMinute) {
        DSLContext dsl = this.sqlite.getDsl();

        try {
            return dsl.insertInto(MOVIE,
                    MOVIE.MOVIE_NAME,
                    MOVIE.MOVIE_TYPE,
                    MOVIE.MOVIE_DATE,
                    MOVIE.MOVIE_CLASS,
                    MOVIE.MOVIE_LANG,
                    MOVIE.MOVIE_LENGTH,
                    MOVIE.MOVIE_DIRECTOR,
                    MOVIE.MOVIE_CAST,
                    MOVIE.MOVIE_LOCATION,
                    MOVIE.MOVIE_PRICE,
                    MOVIE.MOVIE_STARTHOUR,
                    MOVIE.MOVIE_STARTMINUTE)
                    .values(
                            name.trim(),
                            type.trim(),
                            Convert.dateToString(date).trim(),
                            typeClass.trim(),
                            language.trim(),
                            length,
                            director.trim(),
                            cast.trim(),
                            location.trim(),
                            price,
                            startHour,
                            startMinute
                    )
                    .execute() == 1;
            // IF need to know new ID, call SQL "SELECT last_insert_rowid()"
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean createMovie(Movie m) {
        return this.createMovie(
                m.getName(), m.getType(), m.getDate(),
                m.getTypeClass(), m.getLanguage(), m.getLength(),
                m.getDirector(), m.getCast(), m.getLocation(),
                m.getPrice(), m.getStartHour(), m.getStartMinute());
    }

    private boolean updateMovie(int movieId, String name, String type, Date date,
                                String typeClass, String language,
                                int length, String director,
                                String cast, String location, int price,
                                int startHour, int startMinute) {
        DSLContext dsl = this.sqlite.getDsl();

        try {
            return dsl.update(MOVIE)
                    .set(MOVIE.MOVIE_NAME, name.trim())
                    .set(MOVIE.MOVIE_TYPE, type.trim())
                    .set(MOVIE.MOVIE_DATE, Convert.dateToString(date).trim())
                    .set(MOVIE.MOVIE_CLASS, typeClass.trim())
                    .set(MOVIE.MOVIE_LANG, language.trim())
                    .set(MOVIE.MOVIE_LENGTH, length)
                    .set(MOVIE.MOVIE_DIRECTOR, director.trim())
                    .set(MOVIE.MOVIE_CAST, cast.trim())
                    .set(MOVIE.MOVIE_LOCATION, location.trim())
                    .set(MOVIE.MOVIE_PRICE, price)
                    .set(MOVIE.MOVIE_STARTHOUR, startHour)
                    .set(MOVIE.MOVIE_STARTMINUTE, startMinute)
                    .where(MOVIE.MID.equal(movieId))
                    .execute() == 1;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateMovie(Movie m) {
        return this.updateMovie(
                m.getId(), m.getName(), m.getType(),
                m.getDate(), m.getTypeClass(), m.getLanguage(),
                m.getLength(), m.getDirector(), m.getCast(),
                m.getLocation(), m.getPrice(), m.getStartHour(),
                m.getStartMinute());
    }

    public boolean removeMovie(int movieId) {
        DSLContext dsl = this.sqlite.getDsl();

        try {
            return dsl.delete(MOVIE)
                    .where(MOVIE.MID.equal(movieId))
                    .execute() == 1;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public Map<Integer, Movie> getMoviesList() {
        DSLContext dsl = this.sqlite.getDsl();

        Result<Record13<Integer, String, String, String, String, String, Integer, String, String, String, Integer, Integer, Integer>> fetch = dsl
                .select(MOVIE.MID,
                        MOVIE.MOVIE_NAME,
                        MOVIE.MOVIE_TYPE,
                        MOVIE.MOVIE_DATE,
                        MOVIE.MOVIE_CLASS,
                        MOVIE.MOVIE_LANG,
                        MOVIE.MOVIE_LENGTH,
                        MOVIE.MOVIE_DIRECTOR,
                        MOVIE.MOVIE_CAST,
                        MOVIE.MOVIE_LOCATION,
                        MOVIE.MOVIE_PRICE,
                        MOVIE.MOVIE_STARTHOUR,
                        MOVIE.MOVIE_STARTMINUTE)
                .from(MOVIE)
                .fetch();

        return fetch.stream().map(r -> new Movie(
                r.get(MOVIE.MID),
                r.get(MOVIE.MOVIE_NAME).trim(),
                r.get(MOVIE.MOVIE_TYPE).trim(),
                Convert.stringToDate(r.get(MOVIE.MOVIE_DATE).trim(), null),
                r.get(MOVIE.MOVIE_CLASS).trim(),
                r.get(MOVIE.MOVIE_LANG).trim(),
                r.get(MOVIE.MOVIE_LENGTH),
                r.get(MOVIE.MOVIE_DIRECTOR).trim(),
                r.get(MOVIE.MOVIE_CAST).trim(),
                r.get(MOVIE.MOVIE_LOCATION).trim(),
                r.get(MOVIE.MOVIE_PRICE),
                r.get(MOVIE.MOVIE_STARTHOUR),
                r.get(MOVIE.MOVIE_STARTMINUTE)
        )).collect(Collectors.toMap(Movie::getId, c -> c));
    }

    /*
    public String getMoviesNames() {
        DSLContext dsl = this.sqlite.getDsl();

        Result<Record1<String>> fetch = dsl
                .select(MOVIE.MOVIE_NAME)
                .distinctOn(MOVIE.MOVIE_NAME)
                .from(MOVIE)
                .fetch();

        fetch.stream().map(r -> (String) r.get(0)).toArray(String[]::new);
    }
    */

    public Movie getMovieById(int movieId) {
        DSLContext dsl = this.sqlite.getDsl();

        Result<Record13<Integer, String, String, String, String, String, Integer, String, String, String, Integer, Integer, Integer>> fetch = dsl
                .select(MOVIE.MID,
                        MOVIE.MOVIE_NAME,
                        MOVIE.MOVIE_TYPE,
                        MOVIE.MOVIE_DATE,
                        MOVIE.MOVIE_CLASS,
                        MOVIE.MOVIE_LANG,
                        MOVIE.MOVIE_LENGTH,
                        MOVIE.MOVIE_DIRECTOR,
                        MOVIE.MOVIE_CAST,
                        MOVIE.MOVIE_LOCATION,
                        MOVIE.MOVIE_PRICE,
                        MOVIE.MOVIE_STARTHOUR,
                        MOVIE.MOVIE_STARTMINUTE)
                .from(MOVIE)
                .where(MOVIE.MID.equal(movieId))
                .limit(2)
                .fetch();

        if (fetch.size() < 1)
            return null;
        else if (fetch.size() > 1)
            throw new DataAccessException("size > 1");

        Record13<Integer, String, String, String, String, String, Integer, String, String, String, Integer, Integer, Integer> fetchSingle = fetch.get(0);
        return new Movie(
                fetchSingle.get(MOVIE.MID),
                fetchSingle.get(MOVIE.MOVIE_NAME).trim(),
                fetchSingle.get(MOVIE.MOVIE_TYPE).trim(),
                Convert.stringToDate(fetchSingle.get(MOVIE.MOVIE_DATE).trim(), null),
                fetchSingle.get(MOVIE.MOVIE_CLASS).trim(),
                fetchSingle.get(MOVIE.MOVIE_LANG).trim(),
                fetchSingle.get(MOVIE.MOVIE_LENGTH),
                fetchSingle.get(MOVIE.MOVIE_DIRECTOR).trim(),
                fetchSingle.get(MOVIE.MOVIE_CAST).trim(),
                fetchSingle.get(MOVIE.MOVIE_LOCATION).trim(),
                fetchSingle.get(MOVIE.MOVIE_PRICE),
                fetchSingle.get(MOVIE.MOVIE_STARTHOUR),
                fetchSingle.get(MOVIE.MOVIE_STARTMINUTE));
    }
}
