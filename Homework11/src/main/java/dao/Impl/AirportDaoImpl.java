package dao.Impl;

import config.JdbcConfig;
import dao.AirportDao;
import entity.Airport;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirportDaoImpl implements AirportDao {

    JdbcConfig config = JdbcConfig.getInstance();

private static final String AIRPORT_CREATE_QUERY = "insert into airport values (default, ?)";
private static final String AIRPORT_UPDATE_QUERY = "update airport set name = ? where id = ?";
private static final String AIRPORT_DELETE_QUERY = "delete from  airport where id = ?";
private static final String AIRPORT_FIND_ONE_QUERY = "select * from airport where id = ";
private static final String AIRPORT_FIND_ALL_QUERY = "select * from airport";
private static final String EXISTS_AIRPORT_BY_ID_QUERY = "select count(*) as count from airport where id = ";

    @Override
    public void create(Airport entity) {
        try(PreparedStatement ps = config.getConnection().prepareStatement(AIRPORT_CREATE_QUERY)) {
           ps.setString(1, entity.getName());
           ps.executeUpdate();
        }catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void update(Airport entity) {
        try(PreparedStatement ps = config.getConnection().prepareStatement(AIRPORT_UPDATE_QUERY)) {
            ps.setString(1, entity.getName());
            ps.setLong(2, entity.getId());
            ps.executeUpdate();
        }catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement ps = config.getConnection().prepareStatement(AIRPORT_DELETE_QUERY)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public Airport findOne(Long id) {
        try(ResultSet rs = config.getStatement().executeQuery(AIRPORT_FIND_ONE_QUERY + id)) {
            while (rs.next()) {
                return buildAirportByResultSet(rs);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            throw new RuntimeException();
        }
        return null;
    }

    @Override
    public List<Airport> findAll() {
        List<Airport> airports = new ArrayList<>();
        try(ResultSet rs = config.getStatement().executeQuery(AIRPORT_FIND_ALL_QUERY)) {
            while (rs.next()) {
                airports.add(buildAirportByResultSet(rs));
            }
            return airports;
        }catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return airports;
    }

    @Override
    public boolean existsById(String id) {
        try(ResultSet rs = config.getStatement().executeQuery(EXISTS_AIRPORT_BY_ID_QUERY + id)) {
            while (rs.next()) {
                int count = rs.getInt("count");
                return count > 0;
            }
        } catch (SQLException ex) {
            System.out.println("Error = " + ex.getMessage());
        }
        return false;
    }

    private Airport buildAirportByResultSet(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String name = rs.getString("name");
        Airport airport = new Airport();
        airport.setId(id);
        airport.setName(name);
        return airport;
    }
}
