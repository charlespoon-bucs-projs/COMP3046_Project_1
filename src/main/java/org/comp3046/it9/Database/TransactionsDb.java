package org.comp3046.it9.Database;

import org.comp3046.it9.Entity.Transaction;
import org.jooq.DSLContext;
import org.jooq.Record7;
import org.jooq.Result;
import org.jooq.exception.DataAccessException;

import java.util.Map;
import java.util.stream.Collectors;

import static org.comp3046.it9.Database.JooqGenerated.Tables.CUSTOMER;
import static org.comp3046.it9.Database.JooqGenerated.Tables.TRANSACTIONS;

public class TransactionsDb {
    private final Sqlite sqlite;

    public TransactionsDb(Sqlite sqlite) {
        this.sqlite = sqlite;
    }

    public boolean createTransaction(int customerId,
                                      int movieId,
                                      String seat,
                                      int total,
                                      int numberOfTickets,
                                      boolean cancelled) {
        DSLContext dsl = this.sqlite.getDsl();

        try {
            return dsl.insertInto(TRANSACTIONS,
                    TRANSACTIONS.CUSTOMERID,
                    TRANSACTIONS.MOVIEID,
                    TRANSACTIONS.SEAT,
                    TRANSACTIONS.TOTAL,
                    TRANSACTIONS.NUMBEROFTICKETS,
                    TRANSACTIONS.ISCANCELLED)
                    .values(
                            customerId,
                            movieId,
                            seat,
                            total,
                            numberOfTickets,
                            cancelled ? 1 : 0
                    )
                    .execute() == 1;
            // IF need to know new ID, call SQL "SELECT last_insert_rowid()"
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Deprecated
    private boolean createTransaction(Transaction t) {
        return this.createTransaction(t.getCustomerId(), t.getMovieId(), t.getSeat(), t.getTotal(), t.getNumberOfTickets(), t.isCancelled());
    }

    public boolean updateTransaction(int tid,
                                     int customerId,
                                     int movieId,
                                     String seat,
                                     int total,
                                     int numberOfTickets,
                                     boolean cancelled) {
        DSLContext dsl = this.sqlite.getDsl();

        try {
            return dsl.update(TRANSACTIONS)
                    .set(TRANSACTIONS.CUSTOMERID, customerId)
                    .set(TRANSACTIONS.MOVIEID, movieId)
                    .set(TRANSACTIONS.SEAT, seat)
                    .set(TRANSACTIONS.TOTAL, total)
                    .set(TRANSACTIONS.NUMBEROFTICKETS, numberOfTickets)
                    .set(TRANSACTIONS.ISCANCELLED, cancelled ? 1 : 0)
                    .where(TRANSACTIONS.TID.equal(tid))
                    .execute() == 1;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public Map<Integer, Transaction> getTransactions() {
        DSLContext dsl = this.sqlite.getDsl();

        Result<Record7<Integer, Integer, Integer, String, Integer, Integer, Integer>> fetch = dsl
                .select(TRANSACTIONS.TID,
                        TRANSACTIONS.CUSTOMERID,
                        TRANSACTIONS.MOVIEID,
                        TRANSACTIONS.SEAT,
                        TRANSACTIONS.TOTAL,
                        TRANSACTIONS.NUMBEROFTICKETS,
                        TRANSACTIONS.ISCANCELLED)
                .from(TRANSACTIONS)
                .fetch();

        return fetch.stream().map(r -> new Transaction(
                r.get(TRANSACTIONS.TID),
                r.get(TRANSACTIONS.CUSTOMERID),
                r.get(TRANSACTIONS.MOVIEID),
                r.get(TRANSACTIONS.SEAT),
                r.get(TRANSACTIONS.TOTAL),
                r.get(TRANSACTIONS.NUMBEROFTICKETS),
                r.get(TRANSACTIONS.ISCANCELLED) != 0
        )).collect(Collectors.toMap(Transaction::getId, c -> c));
    }

    public Map<Integer, Transaction> getTransactionsByMemberMobile(int mobile) {
        DSLContext dsl = this.sqlite.getDsl();

        Result<Record7<Integer, Integer, Integer, String, Integer, Integer, Integer>> fetch = dsl
                .select(TRANSACTIONS.TID,
                        TRANSACTIONS.CUSTOMERID,
                        TRANSACTIONS.MOVIEID,
                        TRANSACTIONS.SEAT,
                        TRANSACTIONS.TOTAL,
                        TRANSACTIONS.NUMBEROFTICKETS,
                        TRANSACTIONS.ISCANCELLED)
                .from(TRANSACTIONS)
                .innerJoin(CUSTOMER)
                .on(TRANSACTIONS.CUSTOMERID.equal(CUSTOMER.UID))
                .where(CUSTOMER.MOBILE.equal(mobile))
                .fetch();

        return fetch.stream().map(r -> new Transaction(
                r.get(TRANSACTIONS.TID),
                r.get(TRANSACTIONS.CUSTOMERID),
                r.get(TRANSACTIONS.MOVIEID),
                r.get(TRANSACTIONS.SEAT),
                r.get(TRANSACTIONS.TOTAL),
                r.get(TRANSACTIONS.NUMBEROFTICKETS),
                r.get(TRANSACTIONS.ISCANCELLED) != 0
        )).collect(Collectors.toMap(Transaction::getId, c -> c));
    }

    public Map<Integer, Transaction> getTransactionsByMemberId(int memberId) {
        DSLContext dsl = this.sqlite.getDsl();

        Result<Record7<Integer, Integer, Integer, String, Integer, Integer, Integer>> fetch = dsl
                .select(TRANSACTIONS.TID,
                        TRANSACTIONS.CUSTOMERID,
                        TRANSACTIONS.MOVIEID,
                        TRANSACTIONS.SEAT,
                        TRANSACTIONS.TOTAL,
                        TRANSACTIONS.NUMBEROFTICKETS,
                        TRANSACTIONS.ISCANCELLED)
                .from(TRANSACTIONS)
                .where(TRANSACTIONS.CUSTOMERID.equal(memberId))
                .fetch();

        return fetch.stream().map(r -> new Transaction(
                r.get(TRANSACTIONS.TID),
                r.get(TRANSACTIONS.CUSTOMERID),
                r.get(TRANSACTIONS.MOVIEID),
                r.get(TRANSACTIONS.SEAT),
                r.get(TRANSACTIONS.TOTAL),
                r.get(TRANSACTIONS.NUMBEROFTICKETS),
                r.get(TRANSACTIONS.ISCANCELLED) != 0
        )).collect(Collectors.toMap(Transaction::getId, c -> c));
    }

    public Transaction getTransactionById(int tid) {
        DSLContext dsl = this.sqlite.getDsl();

        Result<Record7<Integer, Integer, Integer, String, Integer, Integer, Integer>> fetch = dsl
                .select(TRANSACTIONS.TID,
                        TRANSACTIONS.CUSTOMERID,
                        TRANSACTIONS.MOVIEID,
                        TRANSACTIONS.SEAT,
                        TRANSACTIONS.TOTAL,
                        TRANSACTIONS.NUMBEROFTICKETS,
                        TRANSACTIONS.ISCANCELLED)
                .from(TRANSACTIONS)
                .where(TRANSACTIONS.TID.equal(tid))
                .limit(2)
                .fetch();

        if (fetch.size() < 1)
            return null;
        else if (fetch.size() > 1)
            throw new DataAccessException("size > 1");

        Record7<Integer, Integer, Integer, String, Integer, Integer, Integer> fetch1 = fetch.get(0);
        return new Transaction(
                fetch1.get(TRANSACTIONS.TID),
                fetch1.get(TRANSACTIONS.CUSTOMERID),
                fetch1.get(TRANSACTIONS.MOVIEID),
                fetch1.get(TRANSACTIONS.SEAT),
                fetch1.get(TRANSACTIONS.TOTAL),
                fetch1.get(TRANSACTIONS.NUMBEROFTICKETS),
                fetch1.get(TRANSACTIONS.ISCANCELLED) != 0
        );
    }

    // Transactions cannot be removed, but only set its state to 'cancelled'.
}
