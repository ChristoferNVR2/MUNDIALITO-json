package org.example;

import java.util.ArrayList;
import java.util.List;

public class Match {
    private Team team1;
    private Team team2;
    private List<Referee> referees;
    private List<Goal> goals;
    private List<Card> cards;
    private int attendance;
    private String stage;
    private Stadium stadium;
    private String date;

    public Match(Team team1, Team team2, List<Referee> referees, String stage, Stadium stadium, String date) {
        this.team1 = team1;
        this.team2 = team2;
        this.referees = referees;
        this.goals = new ArrayList<>();
        this.cards = new ArrayList<>();
        this.attendance = 0;
        this.stage = stage;
        this.stadium = stadium;
        this.date = date;
    }

    public void addGoal(int minute, Player2 player) {
        Goal goal = new Goal(this, minute, player);
        goals.add(goal);
    }

    public void addCard(int minute, Player2 player, String cardType) {
        Card card = new Card(this, minute, player, cardType);
        cards.add(card);
    }

    public void setAttendance(int attendance) {
        if (attendance <= stadium.getCapacity()) {
            this.attendance = attendance;
        }
    }

    public String getSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Match: ").append(team1.getName()).append(" vs ").append(team2.getName()).append(", Stage: ").append(stage)
                .append("\nDate: ").append(date).append("\nAttendance: ").append(attendance).append("\nGoals:\n");

        for (Goal goal : goals) {
            summary.append(goal.getInfo()).append("\n");
        }

        summary.append("Cards:\n");
        for (Card card : cards) {
            summary.append(card.getInfo()).append("\n");
        }

        return summary.toString();
    }

    public String getResult() {
        int team1Goals = 0;
        int team2Goals = 0;

        for (Goal goal : goals) {
            if (goal.getPlayer().getTeam().equals(team1)) {
                team1Goals++;
            } else if (goal.getPlayer().getTeam().equals(team2)) {
                team2Goals++;
            }
        }

        return "Result: " + team1.getName() + " " + team1Goals + " - " + team2Goals + " " + team2.getName();
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public List<Referee> getReferees() {
        return referees;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public List<Card> getCards() {
        return cards;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public String getDate() {
        return date;
    }
}

