package org.example;

public class Player2 {
    private String name;
    private String birthDate;
    private String birthPlace;
    private String position;
    private int number;
    private boolean isTitular;

    public Player2() {

    }

    public Player2(String name, String birthDate, String birthPlace, String position, int number, boolean isTitular) {
        this.name = name;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.position = position;
        this.number = number;
        this.isTitular = isTitular;
    }

    public String getInfo() {
        return name + ", Position: " + position + ", Number: " + number + ", Is Titular: " + (isTitular ? "Yes" : "No");
    }

//    public int calculateAge() {
//        return Period.between(birthDate, LocalDate.now()).getYears();
//    }

    public String getName() {
        return name;
    }

    public String getBirthDate() {
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

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public void setName(String name) {
        this.name = name;
    }
}
