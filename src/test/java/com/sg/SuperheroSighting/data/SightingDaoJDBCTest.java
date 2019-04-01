/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSighting.data;

import com.sg.SuperheroSighting.model.Hero;
import com.sg.SuperheroSighting.model.Location;
import com.sg.SuperheroSighting.model.Power;
import com.sg.SuperheroSighting.model.Sighting;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
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
public class SightingDaoJDBCTest {
    
    @Autowired
    SightingDao sightingDao;
    
    @Autowired
    LocationDao locationDao;
    
    @Autowired
    HeroDao heroDao;
    
    @Autowired
    PowerDao powerDao;
    
    public SightingDaoJDBCTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        List<Sighting> sightings = sightingDao.getAllSightings();
        for(Sighting sighting : sightings) {
            sightingDao.deleteSighting(sighting.getId());
        }
 
        List<Hero> heroes = heroDao.getAllHeroes();
        for(Hero hero : heroes) {
            heroDao.deleteHero(hero.getHeroId());
        }
        
        List<Power> powers = powerDao.getAllPowers();
        for(Power power : powers) {
            powerDao.deletePower(power.getId());
        }
        
        List<Location> locations = locationDao.getAllLocations();
        for(Location location : locations) {
            locationDao.deleteLocation(location.getId());
        }
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAddAndGetSighting() {
        Power power = new Power();
        power.setName("Lots of Weapons");
        power.setDescription("Rich people are allowed to cheat like this");
        power = powerDao.addPower(power);
        List<Power> powers = new ArrayList<>();
        powers.add(power);
        
        Location location = new Location();
        location.setName("Test Location");
        location.setDescription("This is a test location.");
        location.setAddress("1334 Maple Road");
        location.setLatitude(new BigDecimal("15.000000"));
        location.setLongitude(new BigDecimal("20.000000"));
        location = locationDao.addLocation(location);
        
        Hero hero = new Hero();
        hero.setName("name of Hero");
        hero.setDescription("This is a hero");
        hero.setPowers(powers);
        hero = heroDao.addHero(hero);
        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);
        
        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setDescription("Test Sighting.");
        sighting.setLocation(location);
        sighting.setHeroes(heroes);
        sighting = sightingDao.addSighting(sighting);
        
        Sighting fromDao = sightingDao.getSighting(sighting.getId());
        assertEquals(sighting, fromDao);
    }
    
    @Test
    public void testGetAllSightings(){
        Power power = new Power();
        power.setName("Lots of Weapons");
        power.setDescription("Rich people are allowed to cheat like this");
        power = powerDao.addPower(power);
        List<Power> powers = new ArrayList<>();
        powers.add(power);
        
        Location location = new Location();
        location.setName("Test Location");
        location.setDescription("This is a test location.");
        location.setAddress("1334 Maple Road");
        location.setLatitude(new BigDecimal("15.000000"));
        location.setLongitude(new BigDecimal("20.000000"));
        location = locationDao.addLocation(location);
        
        Hero hero = new Hero();
        hero.setName("name of Hero");
        hero.setDescription("This is a hero");
        hero.setPowers(powers);
        hero = heroDao.addHero(hero);
        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);
        
        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setDescription("Test Sighting.");
        sighting.setLocation(location);
        sighting.setHeroes(heroes);
        sighting = sightingDao.addSighting(sighting);
        
        Sighting sighting2 = new Sighting();
        sighting2.setDate(LocalDate.now());
        sighting2.setDescription("Test Sighting 2.");
        sighting2.setLocation(location);
        sighting2.setHeroes(heroes);
        sighting2 = sightingDao.addSighting(sighting2);
        
        List<Sighting> sightings = sightingDao.getAllSightings();
        
        assertEquals(2, sightings.size());
        assertTrue(sightings.contains(sighting));
        assertTrue(sightings.contains(sighting2));
    }
    
    @Test
    public void testUpdateSightings() {
        Power power = new Power();
        power.setName("Lots of Weapons");
        power.setDescription("Rich people are allowed to cheat like this");
        power = powerDao.addPower(power);
        List<Power> powers = new ArrayList<>();
        powers.add(power);
        
        Location location = new Location();
        location.setName("Test Location");
        location.setDescription("This is a test location.");
        location.setAddress("1334 Maple Road");
        location.setLatitude(new BigDecimal("15.000000"));
        location.setLongitude(new BigDecimal("20.000000"));
        location = locationDao.addLocation(location);
        
        Hero hero = new Hero();
        hero.setName("name of Hero");
        hero.setDescription("This is a hero");
        hero.setPowers(powers);
        hero = heroDao.addHero(hero);
        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);
        
        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setDescription("Test Sighting.");
        sighting.setLocation(location);
        sighting.setHeroes(heroes);
        sighting = sightingDao.addSighting(sighting);
        
        Sighting fromDao = sightingDao.getSighting(sighting.getId());
        assertEquals(sighting, fromDao);
        
        sighting.setDescription("changing this for testing");
        sightingDao.editSighting(sighting);
        
        assertNotEquals(sighting, fromDao);
        
        fromDao = sightingDao.getSighting(sighting.getId());
        
        assertEquals(sighting, fromDao);
    }
    
    @Test
    public void testDeleteSightings() {
        Power power = new Power();
        power.setName("Lots of Weapons");
        power.setDescription("Rich people are allowed to cheat like this");
        power = powerDao.addPower(power);
        List<Power> powers = new ArrayList<>();
        powers.add(power);
        
        Location location = new Location();
        location.setName("Test Location");
        location.setDescription("This is a test location.");
        location.setAddress("1334 Maple Road");
        location.setLatitude(new BigDecimal("15.000000"));
        location.setLongitude(new BigDecimal("20.000000"));
        location = locationDao.addLocation(location);
        
        Hero hero = new Hero();
        hero.setName("name of Hero");
        hero.setDescription("This is a hero");
        hero.setPowers(powers);
        hero = heroDao.addHero(hero);
        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);
        
        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setDescription("Test Sighting.");
        sighting.setLocation(location);
        sighting.setHeroes(heroes);
        sighting = sightingDao.addSighting(sighting);
        
        Sighting fromDao = sightingDao.getSighting(sighting.getId());
        assertEquals(sighting, fromDao);
        
        sightingDao.deleteSighting(sighting.getId());
        
        fromDao = sightingDao.getSighting(sighting.getId());
        assertNull(fromDao);
    }
    
}
