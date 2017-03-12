package Entity;

public class Transactions {
    private final int id;
    private final int staffId;
    private final int customerId;
    private final int movieId;

    public Transactions(int id, int staffId, int memberId, int movieId) {
        this.id = id;
        this.staffId = staffId;
        this.customerId = memberId;
        this.movieId = movieId;
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
