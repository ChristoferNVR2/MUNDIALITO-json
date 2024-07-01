package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try (InputStream inputStream = Main.class.getResourceAsStream("/teams.json")) {
            TeamsData teams = objectMapper.readValue(inputStream, TeamsData.class);
            List<Team> teamList = teams.getTeams();

            Scanner scanner = new Scanner(System.in);

            System.out.println("Select Team 1:");
            for (int i = 0; i < teamList.size(); i++) {
                System.out.println((i + 1) + ": " + teamList.get(i).getName());
            }
            int team1Index = scanner.nextInt() - 1;
            Team team1 = teamList.get(team1Index);

            System.out.println("Select Team 2:");
            for (int i = 0; i < teamList.size(); i++) {
                System.out.println((i + 1) + ": " + teamList.get(i).getName());
            }
            int team2Index = scanner.nextInt() - 1;
            Team team2 = teamList.get(team2Index);

            System.out.println("Enter Match Stage:");
            scanner.nextLine();  // Consume newline
            String stage = scanner.nextLine();

            System.out.println("Enter Stadium Name:");
            String stadiumName = scanner.nextLine();
            System.out.println("Enter Stadium Location:");
            String stadiumLocation = scanner.nextLine();
            System.out.println("Enter Stadium Capacity:");
            int stadiumCapacity = scanner.nextInt();
            System.out.println("Does the Stadium have a roof? (true/false):");
            boolean hasRoof = scanner.nextBoolean();
            Stadium stadium = new Stadium(stadiumName, stadiumLocation, stadiumCapacity, hasRoof);

            System.out.println("Enter Match Date (YYYY-MM-DD):");
            scanner.nextLine();  // Consume newline
            String date = scanner.nextLine();

            System.out.println("Enter Number of Referees:");
            int numReferees = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            List<Referee> referees = new ArrayList<>();
            for (int i = 0; i < numReferees; i++) {
                System.out.println("Enter Referee " + (i + 1) + " Name:");
                String refName = scanner.nextLine();
                System.out.println("Enter Referee " + (i + 1) + " Birth Date (YYYY-MM-DD):");
                LocalDate refBirthDate = LocalDate.parse(scanner.nextLine());
                System.out.println("Enter Referee " + (i + 1) + " Birth Place:");
                String refBirthPlace = scanner.nextLine();
                System.out.println("Enter Referee " + (i + 1) + " Federation:");
                String refFederation = scanner.nextLine();
                System.out.println("Enter Referee " + (i + 1) + " Role:");
                String refRole = scanner.nextLine();
                System.out.println("Enter Referee " + (i + 1) + " Country:");
                Country refCountry = new Country(scanner.nextLine(), scanner.nextLine());
                referees.add(new Referee(refName, refBirthDate, refBirthPlace, refFederation, refRole, refCountry));
            }

            Match match = new Match(team1, team2, referees, stage, stadium, date);

            boolean running = true;
            while (running) {
                System.out.println("\nMatch Menu:");
                System.out.println("1. Add Goal");
                System.out.println("2. Add Card");
                System.out.println("3. Set Attendance");
                System.out.println("4. Get Match Summary");
                System.out.println("5. Get Match Result");
                System.out.println("6. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        System.out.println("Enter Minute of Goal:");
                        int goalMinute = scanner.nextInt();
                        scanner.nextLine();  // Consume newline
                        System.out.println("Enter Player Name:");
                        String goalPlayerName = scanner.nextLine();
                        Player2 goalPlayer = findPlayerByName(team1, team2, goalPlayerName);
                        if (goalPlayer != null) {
                            match.addGoal(goalMinute, goalPlayer);
                            System.out.println("Goal added.");
                        } else {
                            System.out.println("Player not found.");
                        }
                        break;
                    case 2:
                        System.out.println("Enter Minute of Card:");
                        int cardMinute = scanner.nextInt();
                        scanner.nextLine();  // Consume newline
                        System.out.println("Enter Player Name:");
                        String cardPlayerName = scanner.nextLine();
                        Player2 cardPlayer = findPlayerByName(team1, team2, cardPlayerName);
                        if (cardPlayer != null) {
                            System.out.println("Enter Card Type (Yellow/Red):");
                            String cardType = scanner.nextLine();
                            match.addCard(cardMinute, cardPlayer, cardType);
                            System.out.println("Card added.");
                        } else {
                            System.out.println("Player not found.");
                        }
                        break;
                    case 3:
                        System.out.println("Enter Attendance:");
                        int attendance = scanner.nextInt();
                        match.setAttendance(attendance);
                        System.out.println("Attendance set.");
                        break;
                    case 4:
                        System.out.println(match.getSummary());
                        break;
                    case 5:
                        System.out.println(match.getResult());
                        break;
                    case 6:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Player2 findPlayerByName(Team team1, Team team2, String name) {
        for (Player2 player : team1.getPlayers()) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        for (Player2 player : team2.getPlayers()) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
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
                    playerData.getTeam(),
                    playerData.isTitular()
            );
            team.addPlayer(player);
        }
        return team;
    }
}
