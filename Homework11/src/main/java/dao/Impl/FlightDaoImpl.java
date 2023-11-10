package dao.Impl;

import config.JdbcConfig;
import dao.FlightDao;
import entity.Airport;
import entity.Flight;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightDaoImpl implements FlightDao {

    JdbcConfig config = JdbcConfig.getInstance();

    private static final String FLIGHT_CREATE_QUERY = "insert into flight values (default, ?, ?, ?)";
    private static final String FLIGHT_UPDATE_QUERY = "update flight set departureLocation = ?, destinationLocation = ?, price = ? where id = ?";
    private static final String FLIGHT_DELETE_QUERY = "delete from flight where id = ?";
    private static final String FLIGHT_FIND_ONE_QUERY =  "select * from flight where id = ";
    private static final String FLIGHT_FIND_ALL_QUERY = "select * from flight";
    private static final String EXISTS_FLIGHT_BY_ID_QUERY = "select count(*) as count from flight where id = ";
    @Override
    public void create(Flight entity) {
        try(PreparedStatement ps = config.getConnection().prepareStatement(FLIGHT_CREATE_QUERY)) {
            ps.setString(1, entity.getDepartureLocation());
            ps.setString(2, entity.getDestinationLocation());
            ps.setFloat(3, entity.getPrice());
            ps.executeUpdate();
        }catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void update(Flight entity) {
        try(PreparedStatement ps = config.getConnection().prepareStatement(FLIGHT_UPDATE_QUERY)) {
            ps.setString(1, entity.getDepartureLocation());
            ps.setString(2, entity.getDestinationLocation());
            ps.setFloat(3, entity.getPrice());
            ps.setLong(4, entity.getId());
            ps.executeUpdate();
        }catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement ps = config.getConnection().prepareStatement(FLIGHT_DELETE_QUERY)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public Flight findOne(Long id) {
        try(ResultSet rs = config.getStatement().executeQuery(FLIGHT_FIND_ONE_QUERY + id)) {
            while (rs.next()) {
                return buildFlightByResultSet(rs);
            }
        }catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            throw new RuntimeException();
        }
        return null;
    }

    @Override
    public List<Flight> findAll() {
        List<Flight> flights = new ArrayList<>();
        try(ResultSet rs = config.getStatement().executeQuery(FLIGHT_FIND_ALL_QUERY)) {
            while (rs.next()) {
                flights.add(buildFlightByResultSet(rs));
            }
            return flights;
        }catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public boolean existsById(String id) {
        try(ResultSet rs = config.getStatement().executeQuery(EXISTS_FLIGHT_BY_ID_QUERY + id)) {
            while (rs.next()) {
                int count = rs.getInt("count");
                return count > 0;
            }
        } catch (SQLException ex) {
            System.out.println("Error = " + ex.getMessage());
        }
        return false;
    }

    private Flight buildFlightByResultSet(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String departureLocation = rs.getString("departureLocation");
        String destinationLocation = rs.getString("destinationLocation");
        float price = rs.getFloat("price");
        Flight flight = new Flight();
        flight.setId(id);
        flight.setDepartureLocation(departureLocation);
        flight.setDestinationLocation(destinationLocation);
        flight.setPrice(price);
        return flight;
    }
}
