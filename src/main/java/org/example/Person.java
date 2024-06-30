package org.example;

import java.time.LocalDate;
import java.time.Period;

public abstract class Person {
    protected String name;
    protected LocalDate birthDate;
    protected String birthPlace;

    public Person(String name, LocalDate birthDate, String birthPlace) {
        this.name = name;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
    }

    public abstract String getInfo();

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

    public String toString2() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", birthPlace='" + birthPlace + '\'' +
                '}';
    }
}
