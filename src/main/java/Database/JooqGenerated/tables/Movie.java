/*
 * This file is generated by jOOQ.
*/
package Database.JooqGenerated.tables;


import Database.JooqGenerated.DefaultSchema;
import Database.JooqGenerated.tables.records.MovieRecord;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.TableImpl;


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
public class Movie extends TableImpl<MovieRecord> {

    private static final long serialVersionUID = 1093890956;

    /**
     * The reference instance of <code>Movie</code>
     */
    public static final Movie MOVIE = new Movie();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MovieRecord> getRecordType() {
        return MovieRecord.class;
    }

    /**
     * The column <code>Movie.MID</code>.
     */
    public final TableField<MovieRecord, Integer> MID = createField("MID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>Movie.Movie_name</code>.
     */
    public final TableField<MovieRecord, String> MOVIE_NAME = createField("Movie_name", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>Movie.Movie_type</code>.
     */
    public final TableField<MovieRecord, String> MOVIE_TYPE = createField("Movie_type", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>Movie.Movie_date</code>.
     */
    public final TableField<MovieRecord, String> MOVIE_DATE = createField("Movie_date", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>Movie.Movie_class</code>.
     */
    public final TableField<MovieRecord, String> MOVIE_CLASS = createField("Movie_class", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>Movie.Movie_lang</code>.
     */
    public final TableField<MovieRecord, String> MOVIE_LANG = createField("Movie_lang", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>Movie.Movie_length</code>.
     */
    public final TableField<MovieRecord, Integer> MOVIE_LENGTH = createField("Movie_length", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>Movie.Movie_director</code>.
     */
    public final TableField<MovieRecord, String> MOVIE_DIRECTOR = createField("Movie_director", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>Movie.Movie_cast</code>.
     */
    public final TableField<MovieRecord, String> MOVIE_CAST = createField("Movie_cast", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>Movie.Movie_location</code>.
     */
    public final TableField<MovieRecord, String> MOVIE_LOCATION = createField("Movie_location", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * Create a <code>Movie</code> table reference
     */
    public Movie() {
        this("Movie", null);
    }

    /**
     * Create an aliased <code>Movie</code> table reference
     */
    public Movie(String alias) {
        this(alias, MOVIE);
    }

    private Movie(String alias, Table<MovieRecord> aliased) {
        this(alias, aliased, null);
    }

    private Movie(String alias, Table<MovieRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return DefaultSchema.DEFAULT_SCHEMA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Movie as(String alias) {
        return new Movie(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Movie rename(String name) {
        return new Movie(name, null);
    }
}