package Entity;

public class Transaction {
    private final int id;
    private final int staffId;
    private final int customerId;
    private final int movieId;
    private final String stringeat;
    private final int total;
    private final int numberOfTickets;
    private final boolean cancelled;


    public Transaction(int id, int staffId, int memberId, int movieId, String stringeat, int total, int numberOfTickets, boolean cancelled) {
        this.id = id;
        this.staffId = staffId;
        this.customerId = memberId;
        this.movieId = movieId;
        this.stringeat = stringeat;
        this.total = total;
        this.numberOfTickets = numberOfTickets;
        this.cancelled = cancelled;
    }

    public int getId() {
        return id;
    }

    public int getStaffId() {
        return staffId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getStringeat() {
        return stringeat;
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

    public Staff getStaff() {
        // TODO: dynamic fetch reference if not stored reference,
        // TODO: or return the cached reference if available.
        throw new UnsupportedOperationException("Not Yet Implemented");
    }

    public Customer getCustomer() {
        // TODO: dynamic fetch reference if not stored reference,
        // TODO: or return the cached reference if available.
        throw new UnsupportedOperationException("Not Yet Implemented");
    }

    public Movie getMovie() {
        // TODO: dynamic fetch reference if not stored reference,
        // TODO: or return the cached reference if available.
        throw new UnsupportedOperationException("Not Yet Implemented");
    }
}
