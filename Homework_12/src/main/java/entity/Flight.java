package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "flights")
public class Flight extends BaseEntity {

    @Column(name = "start_flight")
    private String DepartureLocation;

    @Column(name = "end_flight")
    private String DestinationLocation;
    private float price;

    @ManyToMany(mappedBy = "flights")

    private Set<Airport> airports;

    public Set<Airport> getAirports() {
        return airports;
    }

    public void setAirports(Set<Airport> airports) {
        this.airports = airports;
    }

    public String getDepartureLocation() {
        return DepartureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        DepartureLocation = departureLocation;
    }

    public String getDestinationLocation() {
        return DestinationLocation;
    }

    public void setDestinationLocation(String destinationLocation) {
        DestinationLocation = destinationLocation;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id='" + getId() + '\'' +
                " DepartureLocation='" + DepartureLocation + '\'' +
                ", DestinationLocation='" + DestinationLocation + '\'' +
                ", price=" + price +
                '}';
    }
}
