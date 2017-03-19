package org.comp3046.it9.Entity;

import org.comp3046.it9.Database.CustomerDb;
import org.comp3046.it9.Database.MovieDb;
import org.comp3046.it9.Database.Sqlite;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class Transaction {
    private final int id;
    private final int customerId;
    private final int movieId;
    private final String seat;
    private final int total;
    private final int numberOfTickets;
    private final boolean cancelled;

    private Movie cachedMovie = null;
    private Customer cachedCustomer = null;
    private Staff cachedStaff = null;

    public Transaction(int id, int memberId, int movieId, String seat, int total, int numberOfTickets, boolean cancelled) {
        this.id = id;
        this.customerId = memberId;
        this.movieId = movieId;
        this.seat = seat;
        this.total = total;
        this.numberOfTickets = numberOfTickets;
        this.cancelled = cancelled;
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getSeat() {
        return seat;
    }

    public int getTotal() {
        return total;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public String toString() {
        String msg = "Transaction {\r\n";
        msg += "Id: %d\r\n";
        msg += "CustomerId: %d\r\n";
        msg += "MovieId: %d\r\n";
        msg += "Seat: %s\r\n";
        msg += "Total: %d\r\n";
        msg += "NumberOfTickets: %d\r\n";
        msg += "IsCancelled: %s\r\n";
        msg += "}";

        return String.format(
                msg, getId(), getCustomerId(), getMovieId(), getSeat(),
                getTotal(), getNumberOfTickets(), (isCancelled() ? "True" : "False"));
    }

    public Customer getCustomer() {
        if (cachedCustomer != null) return cachedCustomer;

        try (Sqlite sqlite = new Sqlite()) {
            CustomerDb customerDb = new CustomerDb(sqlite);
            cachedCustomer = customerDb.getCustomerByUid(getCustomerId());
            return cachedCustomer;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Movie getMovie() {
        if (cachedMovie != null) return cachedMovie;

        try (Sqlite sqlite = new Sqlite()) {
            MovieDb movieDb = new MovieDb(sqlite);
            cachedMovie = movieDb.getMovieById(getMovieId());
            return cachedMovie;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
