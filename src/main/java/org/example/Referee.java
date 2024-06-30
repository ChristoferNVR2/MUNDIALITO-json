package org.example;

import java.time.LocalDate;

public class Referee extends Person {
    private String federation;
    private String role;
    private Country country;

    public Referee(String name, LocalDate birthDate, String birthPlace, String federation, String role, Country country) {
        super(name, birthDate, birthPlace);
        this.federation = federation;
        this.role = role;
        this.country = country;
    }

    @Override
    public String getInfo() {
        return super.getName() + ", Federation: " + federation + ", Role: " + role;
    }

    public String getFederation() {
        return federation;
    }

    public String getRole() {
        return role;
    }

    public Country getCountry() {
        return country;
    }
}
