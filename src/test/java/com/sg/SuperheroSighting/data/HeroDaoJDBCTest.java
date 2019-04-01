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
public class HeroDaoJDBCTest {
    
    @Autowired
    HeroDao heroDao;
    
    @Autowired
    PowerDao powerDao;
    
    public HeroDaoJDBCTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        List<Hero> heros = heroDao.getAllHeroes();
        for(Hero hero : heros) {
            heroDao.deleteHero(hero.getHeroId());
        }
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAddandGetHero() {
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
        
        Hero fromDao = heroDao.getHero(hero.getHeroId());
        assertEquals(hero, fromDao);
        
    }
    
    @Test
    public void testGetAllHeroes() {
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
        
        Hero hero2 = new Hero();
        hero2.setName("testHero2");
        hero2.setDescription("They're here for a test again");
        hero2.setPowers(powers);
        hero2 = heroDao.addHero(hero2);
        
        List<Hero> heroes = heroDao.getAllHeroes();
        
        assertEquals(2, heroes.size());
        assertTrue(heroes.contains(hero));
        assertTrue(heroes.contains(hero2));
    }
    
    @Test
    public void testUpdateHero() {
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
        
        Hero fromDao = heroDao.getHero(hero.getHeroId());
        assertEquals(hero, fromDao);
        
        hero.setDescription("Now this is different.");
        heroDao.editHero(hero);
        
        assertNotEquals(fromDao, hero);
        
        fromDao = heroDao.getHero(hero.getHeroId());
        
        assertEquals(hero, fromDao);
    }
    
    @Test
    public void testDeleteHeroById() {
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
        
        Hero fromDao = heroDao.getHero(hero.getHeroId());
        assertEquals(hero, fromDao);
        
        heroDao.deleteHero(hero.getHeroId());
        fromDao = heroDao.getHero(hero.getHeroId());
        assertNull(fromDao);
    }
}
