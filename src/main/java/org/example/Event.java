package org.example;

public abstract class Event {
    private Match match;
    private int minute;
    private Player player;

    public Event(Match match, int minute, Player player) {
        this.match = match;
        this.minute = minute;
        this.player = player;
    }

    public abstract String getInfo();

    // Getters
    public Match getMatch() {
        return match;
    }

    public int getMinute() {
        return minute;
    }

    public Player getPlayer() {
        return player;
    }
}
