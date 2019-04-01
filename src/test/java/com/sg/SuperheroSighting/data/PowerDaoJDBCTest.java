/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSighting.data;

import com.sg.SuperheroSighting.model.Hero;
import com.sg.SuperheroSighting.model.Power;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
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
public class PowerDaoJDBCTest {
    
    @Autowired
    PowerDao powerDao;
    
    @Autowired
    HeroDao heroDao;
    
    public PowerDaoJDBCTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        List<Power> powers = powerDao.getAllPowers();
        for(Power aPower : powers) {
            powerDao.deletePower(aPower.getId());
        }
    }
    
    @After
    public void tearDown() {
        List<Power> powers = powerDao.getAllPowers();
        for(Power aPower : powers) {
            powerDao.deletePower(aPower.getId());
        }
    }

    @Test
    public void testAddAndGetPower() {
        Power power = new Power();
        power.setName("Laser Vision");
        power.setDescription("pew pew pew");
        power = powerDao.addPower(power);
        
        Power fromDao = powerDao.getPower(power.getId());
        Assert.assertEquals(power, fromDao);
    }
    
    @Test
    public void testGetAllPowers(){
        Power power = new Power();
        power.setName("Lots of Weapons");
        power.setDescription("Rich people are allowed to cheat like this");
        power = powerDao.addPower(power);
        
        Power power2 = new Power();
        power2.setName("Psychic Powers");
        power2.setDescription("See in the future etc.");
        power2 = powerDao.addPower(power2);
        
        List<Power> powers = powerDao.getAllPowers();
        
        assertEquals(2, powers.size());
        assertTrue(powers.contains(power));
        assertTrue(powers.contains(power2));
    }
    
    @Test
    public void testUpdatePower(){
        Power power = new Power();
        power.setName("Flight");
        power.setDescription("Spread your wings and fly");
        power = powerDao.addPower(power);
        
        Power fromDao = powerDao.getPower(power.getId());
        assertEquals(power, fromDao);
        
        power.setDescription("Never mind, it's a jet pack.");
        powerDao.editPower(power);
        
        assertNotEquals(power, fromDao);
        
        fromDao = powerDao.getPower(power.getId());
        
        assertEquals(power, fromDao);
    }
    
    @Test
    public void testDeletePowerById() {
        Power power = new Power();
        power.setName("Lots of Weapons");
        power.setDescription("Rich people are allowed to cheat like this");
        power = powerDao.addPower(power);
        List<Power> powers = new ArrayList<>();
        powers.add(power);
        
        Hero hero = new Hero();
        hero.setName("testHero");
        hero.setDescription("They're here for a test");
        hero.setPowers(powers);
        hero = heroDao.addHero(hero);
        
        Power fromDao = powerDao.getPower(power.getId());
        assertEquals(power, fromDao);
        
        powerDao.deletePower(power.getId());
        
        fromDao = powerDao.getPower(power.getId());
        assertNull(fromDao);
    }
}
