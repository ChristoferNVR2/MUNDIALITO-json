package org.example;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private Country country;
    private List<Player2> players;
    private List<TechnicalStaff> technicalStaff;

    public Team(String name, Country country) {
        this.name = name;
        this.country = country;
        this.players = new ArrayList<>();
        this.technicalStaff = new ArrayList<>();
    }


    public Team() {

    }

    public void addPlayer(Player2 player) {
        if (players.size() < 20) {
            players.add(player);
//            player.setTeam(this); // Setting the player's team
        } else {
            System.out.println("The team already has 20 players.");
        }
    }

    public void removePlayer(Player2 player) {
        players.remove(player);
//        player.setTeam(null); // Removing the player's team
    }

    public void addTechnicalStaff(TechnicalStaff staff) {
        technicalStaff.add(staff);
    }

    public void removeTechnicalStaff(TechnicalStaff staff) {
        technicalStaff.remove(staff);
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    public List<Player2> getPlayers() {
        return players;
    }

    public List<TechnicalStaff> getTechnicalStaff() {
        return technicalStaff;
    }
}
