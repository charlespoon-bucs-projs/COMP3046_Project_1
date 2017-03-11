/*
 * This file is generated by jOOQ.
*/
package Database.JooqGenerated.tables.records;


import Database.JooqGenerated.tables.Movie;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record10;
import org.jooq.Row10;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MovieRecord extends TableRecordImpl<MovieRecord> implements Record10<Integer, String, String, String, String, String, Integer, String, String, String> {

    private static final long serialVersionUID = -1627033526;

    /**
     * Setter for <code>Movie.MID</code>.
     */
    public void setMid(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>Movie.MID</code>.
     */
    public Integer getMid() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>Movie.Movie_name</code>.
     */
    public void setMovieName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>Movie.Movie_name</code>.
     */
    public String getMovieName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>Movie.Movie_type</code>.
     */
    public void setMovieType(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>Movie.Movie_type</code>.
     */
    public String getMovieType() {
        return (String) get(2);
    }

    /**
     * Setter for <code>Movie.Movie_date</code>.
     */
    public void setMovieDate(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>Movie.Movie_date</code>.
     */
    public String getMovieDate() {
        return (String) get(3);
    }

    /**
     * Setter for <code>Movie.Movie_class</code>.
     */
    public void setMovieClass(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>Movie.Movie_class</code>.
     */
    public String getMovieClass() {
        return (String) get(4);
    }

    /**
     * Setter for <code>Movie.Movie_lang</code>.
     */
    public void setMovieLang(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>Movie.Movie_lang</code>.
     */
    public String getMovieLang() {
        return (String) get(5);
    }

    /**
     * Setter for <code>Movie.Movie_length</code>.
     */
    public void setMovieLength(Integer value) {
        set(6, value);
    }

    /**
     * Getter for <code>Movie.Movie_length</code>.
     */
    public Integer getMovieLength() {
        return (Integer) get(6);
    }

    /**
     * Setter for <code>Movie.Movie_director</code>.
     */
    public void setMovieDirector(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>Movie.Movie_director</code>.
     */
    public String getMovieDirector() {
        return (String) get(7);
    }

    /**
     * Setter for <code>Movie.Movie_cast</code>.
     */
    public void setMovieCast(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>Movie.Movie_cast</code>.
     */
    public String getMovieCast() {
        return (String) get(8);
    }

    /**
     * Setter for <code>Movie.Movie_location</code>.
     */
    public void setMovieLocation(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>Movie.Movie_location</code>.
     */
    public String getMovieLocation() {
        return (String) get(9);
    }

    // -------------------------------------------------------------------------
    // Record10 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row10<Integer, String, String, String, String, String, Integer, String, String, String> fieldsRow() {
        return (Row10) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row10<Integer, String, String, String, String, String, Integer, String, String, String> valuesRow() {
        return (Row10) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Movie.MOVIE.MID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Movie.MOVIE.MOVIE_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Movie.MOVIE.MOVIE_TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Movie.MOVIE.MOVIE_DATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return Movie.MOVIE.MOVIE_CLASS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return Movie.MOVIE.MOVIE_LANG;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field7() {
        return Movie.MOVIE.MOVIE_LENGTH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return Movie.MOVIE.MOVIE_DIRECTOR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field9() {
        return Movie.MOVIE.MOVIE_CAST;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field10() {
        return Movie.MOVIE.MOVIE_LOCATION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getMid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getMovieName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getMovieType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getMovieDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getMovieClass();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getMovieLang();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value7() {
        return getMovieLength();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getMovieDirector();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value9() {
        return getMovieCast();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value10() {
        return getMovieLocation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieRecord value1(Integer value) {
        setMid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieRecord value2(String value) {
        setMovieName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieRecord value3(String value) {
        setMovieType(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieRecord value4(String value) {
        setMovieDate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieRecord value5(String value) {
        setMovieClass(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieRecord value6(String value) {
        setMovieLang(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieRecord value7(Integer value) {
        setMovieLength(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieRecord value8(String value) {
        setMovieDirector(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieRecord value9(String value) {
        setMovieCast(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieRecord value10(String value) {
        setMovieLocation(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovieRecord values(Integer value1, String value2, String value3, String value4, String value5, String value6, Integer value7, String value8, String value9, String value10) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached MovieRecord
     */
    public MovieRecord() {
        super(Movie.MOVIE);
    }

    /**
     * Create a detached, initialised MovieRecord
     */
    public MovieRecord(Integer mid, String movieName, String movieType, String movieDate, String movieClass, String movieLang, Integer movieLength, String movieDirector, String movieCast, String movieLocation) {
        super(Movie.MOVIE);

        set(0, mid);
        set(1, movieName);
        set(2, movieType);
        set(3, movieDate);
        set(4, movieClass);
        set(5, movieLang);
        set(6, movieLength);
        set(7, movieDirector);
        set(8, movieCast);
        set(9, movieLocation);
    }
}