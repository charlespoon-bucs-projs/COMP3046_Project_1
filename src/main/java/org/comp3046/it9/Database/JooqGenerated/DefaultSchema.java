/*
 * This file is generated by jOOQ.
*/
package org.comp3046.it9.Database.JooqGenerated;


import org.comp3046.it9.Database.JooqGenerated.tables.Customer;
import org.comp3046.it9.Database.JooqGenerated.tables.Movie;
import org.comp3046.it9.Database.JooqGenerated.tables.Staff;
import org.comp3046.it9.Database.JooqGenerated.tables.Transactions;
import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class DefaultSchema extends SchemaImpl {

    /**
     * The reference instance of <code></code>
     */
    public static final DefaultSchema DEFAULT_SCHEMA = new DefaultSchema();
    private static final long serialVersionUID = -2104977786;
    /**
     * The table <code>Customer</code>.
     */
    public final Customer CUSTOMER = org.comp3046.it9.Database.JooqGenerated.tables.Customer.CUSTOMER;

    /**
     * The table <code>Movie</code>.
     */
    public final Movie MOVIE = org.comp3046.it9.Database.JooqGenerated.tables.Movie.MOVIE;

    /**
     * The table <code>Staff</code>.
     */
    public final Staff STAFF = org.comp3046.it9.Database.JooqGenerated.tables.Staff.STAFF;

    /**
     * The table <code>Transactions</code>.
     */
    public final Transactions TRANSACTIONS = org.comp3046.it9.Database.JooqGenerated.tables.Transactions.TRANSACTIONS;

    /**
     * No further instances allowed
     */
    private DefaultSchema() {
        super("", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
                Customer.CUSTOMER,
                Movie.MOVIE,
                Staff.STAFF,
                Transactions.TRANSACTIONS);
    }
}