package org.example;

import java.time.LocalDate;

public class Player extends Person {
    private String position;
    private int number;
    private Team team;
    private boolean isTitular; // Added isTitular attribute

    public Player(String name, LocalDate birthDate, String birthPlace, String position, int number, boolean isTitular) {
        super(name, birthDate, birthPlace);
        this.position = position;
        this.number = number;
        this.isTitular = isTitular;
    }

    public Player(String name, LocalDate birthDate, String birthPlace, String position, int number, Team team, boolean isTitular) {
        super(name, birthDate, birthPlace);
        this.position = position;
        this.number = number;
        this.team = team;
        this.isTitular = isTitular;
    }

    @Override
    public String getInfo() {
        return super.getName() + ", Age: " + super.calculateAge() + ", Position: " + position + ", Number: " + number + ", Is Titular: " + (isTitular ? "Yes" : "No");
    }

    public String getPosition() {
        return position;
    }

    public int getNumber() {
        return number;
    }

    public Team getTeam() {
        return team;
    }

    public boolean isTitular() {
        return isTitular;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setTitular(boolean isTitular) {
        this.isTitular = isTitular;
    }

    @Override
    public String toString() {
        return super.getName() + ", Age: " + super.calculateAge() + ", Position: " + position + ", Number: " + number + ", Is Titular: " + (isTitular ? "Yes" : "No");
    }
}
