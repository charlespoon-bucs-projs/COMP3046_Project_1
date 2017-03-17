package Database;

import org.jooq.DSLContext;
import org.jooq.Record11;
import org.jooq.Result;
import org.jooq.exception.DataAccessException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

import static Database.JooqGenerated.tables.Movie.MOVIE;

public class Movie {
    private Sqlite sqlite;

    public Movie(Sqlite sqlite) {
        this.sqlite = sqlite;
    }

    public boolean createMovie(String name, String type, Date date,
                               String typeClass, String language,
                               int length, String director,
                               String cast, String location) {
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
                    MOVIE.MOVIE_LOCATION)
                    .values(
                            name,
                            type,
                            Utils.Convert.dateToString(date),
                            typeClass,
                            language,
                            length,
                            director,
                            cast,
                            location
                    )
                    .execute() == 1;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateMovie(int movieId, String name, String type, Date date,
                               String typeClass, String language,
                               int length, String director,
                               String cast, String location) {
        DSLContext dsl = this.sqlite.getDsl();

        try {
            return dsl.update(MOVIE)
                    .set(MOVIE.MOVIE_NAME, name)
                    .set(MOVIE.MOVIE_TYPE, type)
                    .set(MOVIE.MOVIE_DATE, Utils.Convert.dateToString(date))
                    .set(MOVIE.MOVIE_CLASS, typeClass)
                    .set(MOVIE.MOVIE_LANG, language)
                    .set(MOVIE.MOVIE_LENGTH, length)
                    .set(MOVIE.MOVIE_DIRECTOR, director)
                    .set(MOVIE.MOVIE_CAST, cast)
                    .set(MOVIE.MOVIE_LOCATION, location)
                    .where(MOVIE.MID.equal(movieId))
                    .execute() == 1;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
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

    public Map<Integer, Entity.Movie> getMoviesList() {
        DSLContext dsl = this.sqlite.getDsl();

        Result<Record11<Integer, String, String, String, String, String, Integer, String, String, String, Integer>> fetch = dsl
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
                        MOVIE.MOVIE_PRICE)
                .from(MOVIE)
                .fetch();

        return fetch.stream().map(r -> new Entity.Movie(
                r.get(MOVIE.MID),
                r.get(MOVIE.MOVIE_NAME),
                r.get(MOVIE.MOVIE_TYPE),
                Utils.Convert.stringToDate(r.get(MOVIE.MOVIE_DATE), null),
                r.get(MOVIE.MOVIE_CLASS),
                r.get(MOVIE.MOVIE_LANG),
                r.get(MOVIE.MOVIE_LENGTH),
                r.get(MOVIE.MOVIE_DIRECTOR),
                r.get(MOVIE.MOVIE_CAST),
                r.get(MOVIE.MOVIE_LOCATION)

        )).collect(Collectors.toMap(Entity.Movie::getId, c -> c));
    }

    public Entity.Movie getMovieById(int movieId) {
        DSLContext dsl = this.sqlite.getDsl();

        Result<Record11<Integer, String, String, String, String, String, Integer, String, String, String, Integer>> fetch = dsl
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
                        MOVIE.MOVIE_PRICE)
                .from(MOVIE)
                .where(MOVIE.MID.equal(movieId))
                .limit(2)
                .fetch();

        if (fetch.size() < 1)
            return null;
        else if (fetch.size() > 1)
            throw new DataAccessException("size > 1");

        Record11<Integer, String, String, String, String, String, Integer, String, String, String, Integer> fetchSingle = fetch.get(0);
        return new Entity.Movie(
                fetchSingle.get(MOVIE.MID),
                fetchSingle.get(MOVIE.MOVIE_NAME),
                fetchSingle.get(MOVIE.MOVIE_TYPE),
                Utils.Convert.stringToDate(fetchSingle.get(MOVIE.MOVIE_DATE), null),
                fetchSingle.get(MOVIE.MOVIE_CLASS),
                fetchSingle.get(MOVIE.MOVIE_LANG),
                fetchSingle.get(MOVIE.MOVIE_LENGTH),
                fetchSingle.get(MOVIE.MOVIE_DIRECTOR),
                fetchSingle.get(MOVIE.MOVIE_CAST),
                fetchSingle.get(MOVIE.MOVIE_LOCATION)
        );
    }
}
