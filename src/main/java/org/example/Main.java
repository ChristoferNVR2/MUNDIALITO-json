package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.InputStream;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try (InputStream inputStream = Main.class.getResourceAsStream("/teams.json")) {
            TeamsData teamsData = objectMapper.readValue(inputStream, TeamsData.class);
            List<Team> teamList = teamsData.getTeams();

            for (Team teamData : teamList) {
                Team team = getTeam(teamData);

                // Simulate matches or perform further processing with the team data
                System.out.println("Team: " + team.getName());
                for (Player2 player : team.getPlayers()) {
                    System.out.println(player.getInfo());
                }
            }
        } catch (Exception e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }

    private static Team getTeam(Team teamData) {
        Country country = teamData.getCountry();
        Team team = new Team(teamData.getName(), country);

        for (Player2 playerData : teamData.getPlayers()) {
            Player2 player = new Player2(
                    playerData.getName(),
                    playerData.getBirthDate(),
                    playerData.getBirthPlace(),
                    playerData.getPosition(),
                    playerData.getNumber(),
                    playerData.isTitular()
            );
            team.addPlayer(player);
        }
        return team;
    }
}
