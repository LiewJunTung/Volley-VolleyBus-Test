package org.example.jt.realapp2.model;

/**
 * Created by jt on 1/19/15.
 */
public class Weather {
    private String city;
    private String main;
    private String description;

    public Weather(String city, String main, String description) {
        this.city = city;
        this.main = main;
        this.description = description;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Weather: " + '\n' +
                "city: " + city + '\n' +
                "main:" + main + '\n' +
                "description: " + description + '\n';
    }
}
