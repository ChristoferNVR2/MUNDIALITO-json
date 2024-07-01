package org.example;

import java.time.LocalDate;
import java.time.Period;

public class Player2 {
    private String name;
    private LocalDate birthDate;
    private String birthPlace;
    private String position;
    private int number;
    private Team team;
    private boolean isTitular;

    public Player2() {

    }

    public Player2(String name, LocalDate birthDate, String birthPlace, String position, int number, Team team, boolean isTitular) {
        this.name = name;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.position = position;
        this.number = number;
        this.team = team;
        this.isTitular = isTitular;
    }

    public String getInfo() {
        return name + ", Age: "+ calculateAge() + ", Position: " + position + ", Number: " + number + ", Is Titular: " + (isTitular ? "Yes" : "No");
    }

    public String getShortInfo() {
        return name + ", " + position + ", " + number;
    }

    public int calculateAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
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

    public void setIsTitular(boolean isTitular) {
        this.isTitular = isTitular;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
