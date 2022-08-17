package pl.derbin.readjsonfile.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String country;
    String main;
    String description;

    public Long getId() {
        return id;
    }

    public Weather setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Weather setName(String name) {
        this.name = name;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Weather setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getMain() {
        return main;
    }

    public Weather setMain(String main) {
        this.main = main;
        return this;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", main='" + main + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Weather setDescription(String description) {
        this.description = description;
        return this;
    }

    public Weather() {
    }

    public Weather(Long id, String name, String country, String main, String description) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.main = main;
        this.description = description;
    }


}
