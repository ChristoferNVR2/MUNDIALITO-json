package org.example;

public class Goal extends Event {

    public Goal(Match match, int minute, Player2 player, Team team) {
        super(match, minute, player, team);
    }

    @Override
    public String getInfo() {
        return "Goal by " + getPlayer().getName() + " at " + getMinute() + " minute in match " + getMatch().getTeam1().getName() + " vs " + getMatch().getTeam2().getName();
    }
}
