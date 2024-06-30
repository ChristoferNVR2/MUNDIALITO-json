package org.example;

public class Country {
    private String name;
    private String code;

    public Country(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public Country() {

    }

    public String getInfo() {
        return "Country: " + name + ", Code: " + code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
