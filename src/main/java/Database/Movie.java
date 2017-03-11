package Database;

import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        String strDate = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'")).format(date);

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
                            strDate,
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
        String strDate = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'")).format(date);

        DSLContext dsl = this.sqlite.getDsl();

        try {
            return dsl.update(MOVIE)
                    .set(MOVIE.MOVIE_NAME, name)
                    .set(MOVIE.MOVIE_TYPE, type)
                    .set(MOVIE.MOVIE_DATE, strDate)
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
}
