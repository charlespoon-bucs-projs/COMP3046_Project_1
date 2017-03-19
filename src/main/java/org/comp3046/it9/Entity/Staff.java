package org.comp3046.it9.Entity;

public class Staff {
    private final int id;
    private final String name;
    private final String username;

    public Staff(int id, String name, String username) {
        this.id = id;
        this.name = name;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        String msg = "Staff {\r\n";
        msg += "Id: %d\r\n";
        msg += "Name: %s\r\n";
        msg += "Username: %s\r\n";
        msg += "}";
        return String.format(msg, getId(), getName(), getUsername());
    }
}
