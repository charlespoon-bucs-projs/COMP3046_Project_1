package Entity;

public class Transactions {
    private final int id;
    private final int staffId; // TODO: or store staff object reference?
    private final int memberId; // TODO: or store member object reference?
    private final int movieId; // TODO: or store movie object reference?

    public Transactions(int id, int staffId, int memberId, int movieId) {
        this.id = id;
        this.staffId = staffId;
        this.memberId = memberId;
        this.movieId = movieId;
    }

    public int getId() {
        return id;
    }

    public int getStaffId() {
        return staffId;
    }

    public int getMemberId() {
        return memberId;
    }

    public int getMovieId() {
        return movieId;
    }
}
