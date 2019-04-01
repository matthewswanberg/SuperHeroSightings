/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSighting.data;

import com.sg.SuperheroSighting.model.Hero;
import com.sg.SuperheroSighting.model.Location;
import com.sg.SuperheroSighting.model.Organization;
import com.sg.SuperheroSighting.model.Power;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
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
public class OrganizationDaoJDBCTest {
    
    @Autowired
    OrganizationDao orgDao;
    
    @Autowired
    LocationDao locationDao;
    
    @Autowired
    HeroDao heroDao;
    
    @Autowired
    PowerDao powerDao;
    
    
    public OrganizationDaoJDBCTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        List<Organization> orgs = orgDao.getAllOrgs();
        for(Organization org : orgs) {
            orgDao.deleteOrg(org.getId());
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
        List<Organization> orgs = orgDao.getAllOrgs();
        for(Organization org : orgs) {
            orgDao.deleteOrg(org.getId());
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

    @Test
    public void testAddAndGetOrganization() {
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
        
        Organization org = new Organization();
        org.setName("Test Org");
        org.setDescription("Test Description");
        org.setLocation(location);
        org.setPhone("5553332222");
        org.setEmail("email@email.com");
        org.setHeroes(heroes);
        org = orgDao.addOrg(org);
        
        Organization fromDao = orgDao.getOrg(org.getId());
        assertEquals(org, fromDao);
        
    }
    
    @Test
    public void testGetAllOrganizations(){
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
        
        Organization org = new Organization();
        org.setName("Test Org");
        org.setDescription("Test Description");
        org.setLocation(location);
        org.setPhone("5553332222");
        org.setEmail("email@email.com");
        org.setHeroes(heroes);
        org = orgDao.addOrg(org);
        
        Organization org2 = new Organization();
        org2.setName("Test Org 2");
        org2.setDescription("Test Second Description");
        org2.setLocation(location);
        org2.setPhone("7773332222");
        org2.setEmail("email2@email.com");
        org2.setHeroes(heroes);
        org2 = orgDao.addOrg(org2);
        
        List<Organization> orgs = orgDao.getAllOrgs();
        
        assertEquals(2, orgs.size());
        assertTrue(orgs.contains(org));
        assertTrue(orgs.contains(org2));
    }
    
    @Test
    public void testUpdateOrganization(){
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
        
        Organization org = new Organization();
        org.setName("Test Org");
        org.setDescription("Test Description");
        org.setLocation(location);
        org.setPhone("5553332222");
        org.setEmail("email@email.com");
        org.setHeroes(heroes);
        org = orgDao.addOrg(org);
        
        Organization fromDao = orgDao.getOrg(org.getId());
        assertEquals(org, fromDao);
        
        org.setDescription("We're changing this description for testing purposes.");
        orgDao.editOrg(org);
        
        assertNotEquals(org, fromDao);
        
        fromDao = orgDao.getOrg(org.getId());
        
        assertEquals(org, fromDao);
        
    }
    
    @Test
    public void testDeleteOrg(){
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
        
        Organization org = new Organization();
        org.setName("Test Org");
        org.setDescription("Test Description");
        org.setLocation(location);
        org.setPhone("5553332222");
        org.setEmail("email@email.com");
        org.setHeroes(heroes);
        org = orgDao.addOrg(org);
        
        Organization fromDao = orgDao.getOrg(org.getId());
        assertEquals(org, fromDao);
        
        orgDao.deleteOrg(org.getId());
        
        fromDao = orgDao.getOrg(org.getId());
        assertNull(fromDao);
    }
    
}
