package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try (
            InputStream teamInputStream = Main.class.getResourceAsStream("/teams.json");
            InputStream stadiumInputStream = Main.class.getResourceAsStream("/stadiums.json");
            InputStream refereeInputStream = Main.class.getResourceAsStream("/referees.json")
        ) {
            TeamsData teams = objectMapper.readValue(teamInputStream, TeamsData.class);
            List<Team> teamList = teams.getTeams();

            StadiumsData stadiumsData = objectMapper.readValue(stadiumInputStream, StadiumsData.class);
            List<Stadium> stadiumList = stadiumsData.getStadiums();

            RefereesData refereesData = objectMapper.readValue(refereeInputStream, RefereesData.class);
            List<Referee2> refereeList = refereesData.getReferees();

            Scanner scanner = new Scanner(System.in);

            System.out.println("Select Team 1:");
            for (int i = 0; i < teamList.size(); i++) {
                System.out.println((i + 1) + ": " + teamList.get(i).getName());
            }

            int team1Index = scanner.nextInt() - 1;
            Team team1 = teamList.get(team1Index);

            int team2Index;
            Team team2;

            while (true) {
                System.out.println("Select Team 2:");
                for (int i = 0; i < teamList.size(); i++) {
                    if (i == team1Index) {
                        continue;
                    }
                    System.out.println((i + 1) + ": " + teamList.get(i).getName());
                }
                team2Index = scanner.nextInt() - 1;
                if (team2Index != team1Index) {
                    team2 = teamList.get(team2Index);
                    break;
                } else {
                    System.out.println("Team 2 cannot be the same as Team 1. Try again.");
                }
            }

            System.out.println("Enter Match Stage:");
            scanner.nextLine();  // Consume newline
            String stage = scanner.nextLine();

            System.out.println("Select Stadium:");
            for (int i = 0; i < stadiumList.size(); i++) {
                System.out.println((i + 1) + ": " + stadiumList.get(i).getName() + " in " + stadiumList.get(i).getLocation());
            }
            int stadiumIndex = scanner.nextInt() - 1;
            Stadium stadium = stadiumList.get(stadiumIndex);

            System.out.println("Enter Match Date (YYYY-MM-DD):");
            scanner.nextLine();  // Consume newline
            String date = scanner.nextLine();

//            System.out.println("Select 3 Referees:");
//            List<Referee2> matchReferees = new ArrayList<>();
//            for (int i = 0; i < 3; i++) {
//                System.out.println("Select Referee " + (i + 1) + ":");
//                for (int j = 0; j < refereeList.size(); j++) {
//                    System.out.println((j + 1) + ": " + refereeList.get(j).getInfo());
//                }
//                int refereeIndex = scanner.nextInt() - 1;
//                matchReferees.add(refereeList.get(refereeIndex));
//            }

            Referee2 mainReferee = selectMainReferee(refereeList, scanner);

            List<Referee2> selectedLinesmen = new ArrayList<>();
            selectedLinesmen.add(mainReferee); // Ensure main referee is not selected again

            for (int i = 0; i < 2; i++) {
                Referee2 linesman = selectLinesman(refereeList, selectedLinesmen, scanner);
                selectedLinesmen.add(linesman);
            }

            Match match = new Match(team1, team2, selectedLinesmen, stage, stadium, date);

            System.out.println("Enter Attendance:");
            System.out.println("Stadium Capacity: " + stadium.getCapacity());
            int attendance = scanner.nextInt();
            match.setAttendance(attendance);
            System.out.println("Attendance set to " + match.getAttendance());

            boolean running = true;
            while (running) {
                System.out.println("\nMatch Menu:");
                System.out.println("1. Add Goal");
                System.out.println("2. Add Card");
                System.out.println("3. Get Match Summary");
                System.out.println("4. Get Match Result");
                System.out.println("5. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        System.out.println("Enter Minute of Goal:");
                        int goalMinute = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Enter Team number:");
                        System.out.println(match.getTeam1().getName() + ": 1");
                        System.out.println(match.getTeam2().getName() + ": 2");
                        int goalTeamNumber = scanner.nextInt();
                        scanner.nextLine();

                        Team goalTeam;

                        if (goalTeamNumber == 1) {
                            System.out.println("Titular Players - " + team1.getName() + ":");
                            displayTitularPlayers(team1.getPlayers());
                            goalTeam = team1;
                            System.out.println();
                        } else if (goalTeamNumber == 2) {
                            System.out.println("Titular Players - " + team2.getName() + ":");
                            displayTitularPlayers(team2.getPlayers());
                            System.out.println();
                            goalTeam = team2;
                        } else {
                            System.out.println("Invalid team number. Try again.");
                            break;
                        }

                        System.out.println("Enter Player Name:");
                        String goalPlayerName = scanner.nextLine();

                        Player2 goalPlayer = findPlayerByName(team1, team2, goalPlayerName);

                        if (goalPlayer != null) {
                            match.addGoal(goalMinute, goalPlayer, goalTeam);
                            System.out.println("Goal added.");
                        } else {
                            System.out.println("Player not found.");
                        }
                        break;
                    case 2:
                        System.out.println("Enter Minute of Card:");
                        int cardMinute = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Enter Team number:");
                        System.out.println(match.getTeam1().getName() + ": 1");
                        System.out.println(match.getTeam2().getName() + ": 2");
                        int cardTeamNumber = scanner.nextInt();
                        scanner.nextLine();

                        Team cardTeam;

                        if (cardTeamNumber == 1) {
                            System.out.println("Titular Players - " + team1.getName() + ":");
                            displayTitularPlayers(team1.getPlayers());
                            cardTeam = team1;
                            System.out.println();
                        } else if (cardTeamNumber == 2) {
                            System.out.println("Titular Players - " + team2.getName() + ":");
                            displayTitularPlayers(team2.getPlayers());
                            System.out.println();
                            cardTeam = team2;
                        } else {
                            System.out.println("Invalid team number. Try again.");
                            break;
                        }

                        System.out.println("Enter Player Name:");
                        String cardPlayerName = scanner.nextLine();

                        Player2 cardPlayer = findPlayerByName(team1, team2, cardPlayerName);

                        if (cardPlayer != null) {
                            System.out.println("Enter Card Type (Yellow/Red):");
                            String cardType = scanner.nextLine();
                            match.addCard(cardMinute, cardPlayer, cardType, cardTeam);
                            System.out.println("Card added.");
                        } else {
                            System.out.println("Player not found.");
                        }
                        break;
                    case 3:
                        System.out.println(match.getSummary());
                        break;
                    case 4:
                        System.out.println(match.getResult());
                        break;
                    case 5:
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

    private static Referee2 selectMainReferee(List<Referee2> refereeList, Scanner scanner) {
        while (true) {
            System.out.println("Select Main Referee:");
            List<Referee2> refereeList2 = refereeList.subList(0, 10);
            for (int i = 0; i < refereeList2.size(); i++) {
                System.out.println((i + 1) + ": " + refereeList2.get(i).getInfo());
            }
            int refereeIndex = scanner.nextInt() - 1;
            if (refereeIndex >= 0 && refereeIndex < refereeList.size()) {
                Referee2 selectedReferee = refereeList.get(refereeIndex);
                refereeList.remove(refereeIndex); // Remove selected referee from list to prevent re-selection
                return selectedReferee;
            } else {
                System.out.println("Invalid selection. Please enter a valid number.");
            }
        }
    }

    private static Referee2 selectLinesman(List<Referee2> refereeList, List<Referee2> selectedLinesmen, Scanner scanner) {
        while (true) {
            System.out.println("Select Linesman:");
            for (int i = 0; i < refereeList.size(); i++) {
                if (!selectedLinesmen.contains(refereeList.get(i))) {
                    System.out.println((i + 1) + ": " + refereeList.get(i).getInfo());
                }
            }
            int refereeIndex = scanner.nextInt() - 1;
            if (refereeIndex >= 0 && refereeIndex < refereeList.size() && !selectedLinesmen.contains(refereeList.get(refereeIndex))) {
                Referee2 selectedLinesman = refereeList.get(refereeIndex);
                selectedLinesmen.add(selectedLinesman); // Add selected linesman to selectedLinesmen list
                return selectedLinesman;
            } else {
                System.out.println("Referee already selected or invalid selection. Please enter a valid number.");
            }
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

    private static void displayTitularPlayers(List<Player2> players) {
        for (Player2 player : players) {
            if (player.isTitular()) {
                System.out.println(player.getShortInfo());
            }
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
                    playerData.getTeam(),
                    playerData.isTitular()
            );
            team.addPlayer(player);
        }
        return team;
    }
}
