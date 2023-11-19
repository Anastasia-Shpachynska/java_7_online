package entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "airports")
public class Airport extends BaseEntity {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @ManyToMany
    @JoinTable(
            name = "air_flight",
            joinColumns = @JoinColumn(name = "airport_id"),
            inverseJoinColumns = @JoinColumn(name = "flight_id")
    )

    private Set<Flight> flights;

    public Set<Flight> getFlights() {
        return flights;
    }

    public void setFlights(Set<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "id='" + getId() + '\'' +
                " name='" + name + '\'' +
                '}';
    }
}
