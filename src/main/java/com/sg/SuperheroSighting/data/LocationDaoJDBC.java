/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSighting.data;

import com.sg.SuperheroSighting.mapper.LocationMapper;
import com.sg.SuperheroSighting.model.Location;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author matthewswanberg
 */
@Repository
public class LocationDaoJDBC implements LocationDao{
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    @Transactional
    public Location addLocation(Location aLocation) {
        final String INSERT_LOCATION = "INSERT INTO locations(name, description, address, latitude, longitude) "
                + " VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_LOCATION,
                aLocation.getName(),
                aLocation.getDescription(),
                aLocation.getAddress(),
                aLocation.getLatitude(),
                aLocation.getLongitude());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        aLocation.setId(newId);
        return aLocation;
    }

    @Override
    public Location getLocation(int id) {
        try {
            final String SELECT_LOCATION_BY_ID = "SELECT * FROM locations WHERE id = ?";
            Location location = jdbc.queryForObject(SELECT_LOCATION_BY_ID, new LocationMapper(), id);
            return location;
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        final String SELECT_ALL_LOCATIONS = " select * from locations ";
        return jdbc.query(SELECT_ALL_LOCATIONS, new LocationMapper());
    }

    @Override
    @Transactional
    public void editLocation(Location changedLocation) {
        final String UPDATE_LOCATION = "update locations set name = ?, description = ?, "
                + " address = ?, latitude = ?, longitude = ? where id = ?";
        jdbc.update(UPDATE_LOCATION, changedLocation.getName(), changedLocation.getDescription(),
                changedLocation.getAddress(), changedLocation.getLatitude(), 
                changedLocation.getLongitude(), changedLocation.getId());
    }

    @Override
    @Transactional
    public void deleteLocation(int id) {
        final String UPDATE_LOCATION_ORGANIZATION = "update organizations set locationId = 1 where locationId = ? ;";
        jdbc.update(UPDATE_LOCATION_ORGANIZATION, id);
        
        final String UPDATE_LOCATION_SIGHTING = "update sightings set locationId = 1 where locationId = ? ;";
        jdbc.update(UPDATE_LOCATION_SIGHTING, id);
        
        final String DELETE_LOCATION = "delete from locations where id = ? ;";
        jdbc.update(DELETE_LOCATION, id);
    }
    
}
