package org.example;

import java.time.LocalDate;

public class TechnicalStaff extends Person {
    private String role;
    private Team team;

    public TechnicalStaff(String name, LocalDate birthDate, String birthPlace, String role, Team team) {
        super(name, birthDate, birthPlace);
        this.role = role;
        this.team = team;
    }

    @Override
    public String getInfo() {
        return super.getName() + ", Role: " + role;
    }

    public String getRole() {
        return role;
    }

    public Team getTeam() {
        return team;
    }
}
