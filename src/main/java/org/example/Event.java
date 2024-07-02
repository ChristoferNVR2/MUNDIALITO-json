package org.example;

public abstract class Event {
    private Match match;
    private int minute;
    private Player2 player;
    private Team team;

    public Event(Match match, int minute, Player2 player, Team team) {
        this.match = match;
        this.minute = minute;
        this.player = player;
        this.team = team;
    }

    public abstract String getInfo();

    // Getters
    public Match getMatch() {
        return match;
    }

    public int getMinute() {
        return minute;
    }

    public Player2 getPlayer() {
        return player;
    }

    public Team getTeam() {
        return team;
    }
}
