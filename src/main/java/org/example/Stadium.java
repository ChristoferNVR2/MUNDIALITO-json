package org.example;

public class Stadium {
    private String name;
    private String location;
    private int capacity;
    private boolean hasRoof;

    public Stadium(String name, String location, int capacity, boolean hasRoof) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.hasRoof = hasRoof;
    }

    public String getInfo() {
        return "Stadium: " + name + ", Location: " + location + ", Capacity: " + capacity + ", Has Roof: " + hasRoof;
    }

    public boolean isFull(int attendance) {
        return attendance >= capacity;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isHasRoof() {
        return hasRoof;
    }
}
