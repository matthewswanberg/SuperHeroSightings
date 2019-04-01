/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSighting.data;

import com.sg.SuperheroSighting.model.Location;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author matthewswanberg
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationDaoJDBCTest {
    
    @Autowired
    LocationDao locationDao;
    
    public LocationDaoJDBCTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        List<Location> locations = locationDao.getAllLocations();
        for(Location location : locations) {
            locationDao.deleteLocation(location.getId());
        }
        Location unknown = new Location();
        unknown.setName("Unknown");
        unknown.setDescription("who knows");
        unknown.setAddress("n/a");
        unknown.setLatitude(new BigDecimal("0.000000"));
        unknown.setLongitude(new BigDecimal("0.000000"));
        unknown = locationDao.addLocation(unknown);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAddandGetLocation() {
        Location location = new Location();
        location.setName("Test Location");
        location.setDescription("This is a test location.");
        location.setAddress("1334 Maple Road");
        location.setLatitude(new BigDecimal("15.000000"));
        location.setLongitude(new BigDecimal("20.000000"));
        location = locationDao.addLocation(location);
        
        Location fromDao = locationDao.getLocation(location.getId());
        assertEquals(location, fromDao);
    }
    
    @Test
    public void testGetAllLocations() {
        Location location = new Location();
        location.setName("Test Location");
        location.setDescription("This is a test location.");
        location.setAddress("1334 Maple Road");
        location.setLatitude(new BigDecimal("15.000000"));
        location.setLongitude(new BigDecimal("20.000000"));
        location = locationDao.addLocation(location);
        
        Location location2 = new Location();
        location2.setName("Test Location 2");
        location2.setDescription("This is a second test location.");
        location2.setAddress("9999 Maple Road");
        location2.setLatitude(new BigDecimal("15.111111"));
        location2.setLongitude(new BigDecimal("20.222222"));
        location2 = locationDao.addLocation(location2);
        
        List<Location> locations = locationDao.getAllLocations();
        
        assertEquals(3, locations.size());
        assertTrue(locations.contains(location));
        assertTrue(locations.contains(location2));
    }
    
    @Test
    public void testUpdateLocation() {
        Location location = new Location();
        location.setName("Test Location");
        location.setDescription("This is a test location.");
        location.setAddress("1334 Maple Road");
        location.setLatitude(new BigDecimal("15.111111"));
        location.setLongitude(new BigDecimal("20.222222"));
        location = locationDao.addLocation(location);
        
        Location fromDao = locationDao.getLocation(location.getId());
        assertEquals(location, fromDao);
        
        location.setDescription("I'm changing this!");
        locationDao.editLocation(location);
        
        assertNotEquals(location, fromDao);
        
        fromDao = locationDao.getLocation(location.getId());
        
        assertEquals(location, fromDao);
    }
    
    @Test
    public void testDeleteLocation() {
        Location location = new Location();
        location.setName("Test Location");
        location.setDescription("This is a test location.");
        location.setAddress("1334 Maple Road");
        location.setLatitude(new BigDecimal("15.000000"));
        location.setLongitude(new BigDecimal("20.000000"));
        location = locationDao.addLocation(location);
        
        Location fromDao = locationDao.getLocation(location.getId());
        assertEquals(location, fromDao);
        
        locationDao.deleteLocation(location.getId());
        fromDao = locationDao.getLocation(location.getId());
        assertNull(fromDao);
    }
    
}
