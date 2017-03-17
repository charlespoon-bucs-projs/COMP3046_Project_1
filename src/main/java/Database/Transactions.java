package Database;

import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;

import static Database.JooqGenerated.Tables.TRANSACTIONS;

public class Transactions {
    private Sqlite sqlite;

    public Transactions(Sqlite sqlite) {
        this.sqlite = sqlite;
    }

    public boolean createTransaction(int staffId,
                                     int customerId,
                                     int movieId,
                                     String seat,
                                     int total,
                                     int numberOfTickets) {
        DSLContext dsl = this.sqlite.getDsl();

        try {
            return dsl.insertInto(TRANSACTIONS,
                    TRANSACTIONS.STAFFID,
                    TRANSACTIONS.CUSTOMERID,
                    TRANSACTIONS.MOVIEID,
                    TRANSACTIONS.SEAT,
                    TRANSACTIONS.TOTAL,
                    TRANSACTIONS.NUMBEROFTICKETS)
                    .values(
                            staffId,
                            customerId,
                            movieId,
                            seat,
                            total,
                            numberOfTickets
                    )
                    .execute() == 1;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
