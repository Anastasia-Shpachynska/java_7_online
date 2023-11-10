package dao.Impl;

import config.JdbcConfig;
import dao.AirportFlightDao;
import entity.Airport;
import entity.AirportFlight;
import entity.Flight;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirportFlightDaoImpl implements AirportFlightDao {

    JdbcConfig config = JdbcConfig.getInstance();

    private static final String AIRPORT_FLIGHT_CREATE_QUERY = "insert into airportflight values (?, ?, ?)";
    private static final String AIRPORT_FLIGHT_UPDATE_QUERY = "update airportflight set first_airport_id = ?, second_airport_id = ? where flight_id = ?";
    private static final String AIRPORT_FLIGHT_DELETE_FLIGHT = "delete from airportflight where flight_id = ?";
    private static final String AIRPORT_FLIGHT_DELETE_AIRPORT = "delete from airportflight where first_airport_id = ? or second_airport_id = ?";
    private static final String FIND_AIRPORT_AND_ITS_FLIGHTS = "select * from airportflight where first_airport_id = ? or second_airport_id = ?";
    private static final String FIND_FLIGHT_AND_ITS_AIRPORTS = "select * from airportflight where flight_id = ";
    private static final String FIND_ALL_FLIGHTS = "select * from airportflight";
    private static final String FIND_ALL_AIRPORTS = "SELECT DISTINCT first_airport_id  FROM airportflight\n" +
            "UNION\n" +
            "SELECT DISTINCT second_airport_id FROM airportflight order by first_airport_id";
    private static final String EXISTS_FLIGHT_BY_ID_QUERY = "select count(*) as count from airportflight where flight_id = ";
    private static final String EXISTS_AIRPORT_BY_ID_QUERY = "select count(*) as count from airportflight where first_airport_id = ? or second_airport_id = ?";

    @Override
    public void create(AirportFlight entity) {
        try(PreparedStatement ps = config.getConnection().prepareStatement(AIRPORT_FLIGHT_CREATE_QUERY)) {
            ps.setLong(1, entity.getFirstAirportId());
            ps.setLong(2, entity.getFlightId());
            ps.setLong(3, entity.getSecondAirportId());
            ps.executeUpdate();
        }catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void update(AirportFlight entity) {
        try(PreparedStatement ps = config.getConnection().prepareStatement(AIRPORT_FLIGHT_UPDATE_QUERY)) {
            ps.setLong(1, entity.getFirstAirportId());
            ps.setLong(2, entity.getSecondAirportId());
            ps.setLong(3, entity.getFlightId());
            ps.executeUpdate();
        }catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement ps = config.getConnection().prepareStatement(AIRPORT_FLIGHT_DELETE_FLIGHT)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteAirport(Long id) {
        try(PreparedStatement ps = config.getConnection().prepareStatement(AIRPORT_FLIGHT_DELETE_AIRPORT)) {
           ps.setLong(1, id);
           ps.setLong(2, id);
           ps.executeUpdate();
        }catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public List<AirportFlight> findAirportFlights(Long id) {
       List<AirportFlight> airportFlights = new ArrayList<>();
       try(PreparedStatement ps = config.getConnection().prepareStatement(FIND_AIRPORT_AND_ITS_FLIGHTS)) {
           ps.setLong(1, id);
           ps.setLong(2, id);

           try(ResultSet rs = ps.executeQuery()) {
               while (rs.next()) {
                   airportFlights.add(buildAirportFlightByResultSet(rs));
               }
               return airportFlights;
           }
       }catch (SQLException ex) {
           System.out.println("Error: " + ex.getMessage());
       }
       return airportFlights;
    }

    @Override
    public List<AirportFlight> findFlightAirports(Long id) {
        List<AirportFlight> airportFlights = new ArrayList<>();
        try(ResultSet rs = config.getStatement().executeQuery(FIND_FLIGHT_AND_ITS_AIRPORTS + id)) {
            while (rs.next()) {
                airportFlights.add(buildAirportFlightByResultSet(rs));
            }
            return airportFlights;
        }catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return airportFlights;
    }

    @Override
    public List<String> findAllAirports() {
        List<String> airports = new ArrayList<>();
        try(ResultSet rs = config.getStatement().executeQuery(FIND_ALL_AIRPORTS)) {
           while (rs.next()) {
               airports.add(rs.getString("first_airport_id"));
           }
           return airports;
        }catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return airports;
    }

    @Override
    public List<AirportFlight> findAllFlight() {
       List<AirportFlight> airportFlights = new ArrayList<>();
       try(ResultSet rs = config.getStatement().executeQuery(FIND_ALL_FLIGHTS)) {
           while (rs.next()) {
               airportFlights.add(buildAirportFlightByResultSet(rs));
           }
           return airportFlights;
       }catch (SQLException ex) {
           System.out.println("Error: " + ex.getMessage());
       }
       return airportFlights;
    }

    @Override
    public boolean existsByIdFlight(Long id) {
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

    public boolean existsByIdAirport(Long id) {
        try(PreparedStatement ps = config.getConnection().prepareStatement(EXISTS_AIRPORT_BY_ID_QUERY)) {
            ps.setLong(1, id);
            ps.setLong(2, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int count = rs.getInt("count");
                return count > 0;
            }
        } catch (SQLException ex) {
            System.out.println("Error = " + ex.getMessage());
        }
        return false;
    }
    private AirportFlight buildAirportFlightByResultSet(ResultSet rs) throws SQLException {
        Long firstAirportId = rs.getLong("first_airport_id");
        Long flightId = rs.getLong("flight_id");
        Long secondAirportId = rs.getLong("second_airport_id");
        AirportFlight airportFlight = new AirportFlight();
        airportFlight.setFirstAirportId(firstAirportId);
        airportFlight.setFlightId(flightId);
        airportFlight.setSecondAirportId(secondAirportId);
        return airportFlight;
    }
}
