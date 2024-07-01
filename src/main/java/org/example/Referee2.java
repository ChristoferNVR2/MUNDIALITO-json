package org.example;

import java.time.LocalDate;

public class Referee2 {
    private String name;
    private LocalDate birthDate;
    private String birthPlace;
    private String federation;
    private String role;
    private Country country;

    public Referee2() {
    }

    public Referee2(String name, LocalDate birthDate, String birthPlace, String federation, String role, Country country) {
        this.name = name;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.federation = federation;
        this.role = role;
        this.country = country;
    }

    public String getInfo() {
        return name + ", Federation: " + federation + ", Role: " + role;
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
