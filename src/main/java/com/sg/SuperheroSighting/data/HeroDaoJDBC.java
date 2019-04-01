/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSighting.data;

import com.sg.SuperheroSighting.mapper.HeroMapper;
import com.sg.SuperheroSighting.mapper.PowerMapper;
import com.sg.SuperheroSighting.model.Hero;
import com.sg.SuperheroSighting.model.Power;
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
public class HeroDaoJDBC implements HeroDao{
    
    @Autowired
    JdbcTemplate jdbc;
    

    @Override
    @Transactional
    public Hero addHero(Hero aHero) {
        final String INSERT_HERO = "insert into heroes(name, description) values(?, ?)";
        jdbc.update(INSERT_HERO, aHero.getName(), aHero.getDescription());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        aHero.setHeroId(newId);
        insertHeroPowers(aHero);
        return aHero;
    }
    
    private void insertHeroPowers(Hero aHero) {
        final String INSERT_HERO_POWERS = "insert into heroSuperpowers(heroId, powerId) "
                + " values(?,?)";
        for (Power aPower : aHero.getPowers()) {
            jdbc.update(INSERT_HERO_POWERS, aHero.getHeroId(), aPower.getId());
        }
    }

    @Override
    public Hero getHero(int id) {
        try {
            final String SELECT_HERO_BY_ID = "select * from heroes where id = ?";
            Hero hero = jdbc.queryForObject(SELECT_HERO_BY_ID, new HeroMapper(), id);
            hero.setPowers(getPowersForHero(id));
            return hero;
        } catch(DataAccessException ex) {
            return null;
        }
        }
    
    private List<Power> getPowersForHero(int heroId) {
        final String SELECT_POWERS_FOR_HERO = "select powers.* from heroes inner join "
                + " heroSuperpowers on heroes.id = heroSuperpowers.heroId inner join "
                + " powers on heroSuperpowers.powerId = powers.Id where heroes.id = ?";
        return jdbc.query(SELECT_POWERS_FOR_HERO, new PowerMapper(), heroId);
    }

    @Override
    public List<Hero> getAllHeroes() {
        final String SELECT_ALL_HEROES = "select * from heroes";
        List<Hero> heroes = jdbc.query(SELECT_ALL_HEROES, new HeroMapper());
        associatePowers(heroes);
        return heroes;
    }
    
    private void associatePowers(List<Hero> heroes){
        for (Hero hero : heroes) {
            hero.setPowers(getPowersForHero(hero.getHeroId()));
        }
    }

    @Override
    @Transactional
    public void editHero(Hero changedHero) {
        final String UPDATE_HERO = "update heroes set name = ?, description = ? where id = ?";
        jdbc.update(UPDATE_HERO, changedHero.getName(), changedHero.getDescription(), changedHero.getHeroId());
        
        final String DELETE_HEROES_POWERS = "delete from heroSuperpowers where heroId = ?";
        jdbc.update(DELETE_HEROES_POWERS, changedHero.getHeroId());
        insertHeroPowers(changedHero);
    }

    @Override
    @Transactional
    public void deleteHero(int id) {
        final String DELETE_HERO_POWERS = "delete from heroSuperpowers where heroId = ?";
        jdbc.update(DELETE_HERO_POWERS, id);
        
        final String DELETE_HERO_SIGHTINGS = "delete from heroAtSighting where heroId = ? ";
        jdbc.update(DELETE_HERO_SIGHTINGS, id);
        
        final String DELETE_HERO_FROM_ORG = "delete from organizationMembership where heroId = ? ";
        jdbc.update(DELETE_HERO_FROM_ORG, id);
        
        final String DELETE_HERO = "delete from heroes where id = ?";
        jdbc.update(DELETE_HERO, id);
    }
    
}
