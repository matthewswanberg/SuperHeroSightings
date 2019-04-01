/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSighting.data;

import com.sg.SuperheroSighting.mapper.HeroMapper;
import com.sg.SuperheroSighting.mapper.LocationMapper;
import com.sg.SuperheroSighting.mapper.PowerMapper;
import com.sg.SuperheroSighting.mapper.SightingMapper;
import com.sg.SuperheroSighting.model.Hero;
import com.sg.SuperheroSighting.model.Location;
import com.sg.SuperheroSighting.model.Power;
import com.sg.SuperheroSighting.model.Sighting;
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
public class SightingDaoJDBC implements SightingDao{
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    @Transactional
    public Sighting addSighting(Sighting aSighting) {
        final String INSERT_SIGHTING = "insert into sightings(sightingDate, description, locationId) "
                + " values(?, ?, ?)";
        jdbc.update(INSERT_SIGHTING, aSighting.getDate(), aSighting.getDescription(), aSighting.getLocation().getId());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        aSighting.setId(newId);
        insertSightingHeroes(aSighting);
        return aSighting;
    }
    
    private void insertSightingHeroes(Sighting sight) {
        final String INSERT_SIGHTING_HEROES = "insert into heroAtSighting(heroId, sightingId) "
                + " values(?,?) ";
        for (Hero hero : sight.getHeroes()) {
            jdbc.update(INSERT_SIGHTING_HEROES, hero.getHeroId(), sight.getId());
        }
    }

    @Override
    public Sighting getSighting(int id) {
        try {
            final String SELECT_SIGHTING = "SELECT * FROM sightings WHERE id = ?";
            Sighting sight = jdbc.queryForObject(SELECT_SIGHTING, new SightingMapper(), id);
            sight.setLocation(getLocationForSighting(id));
            sight.setHeroes(getHeroesForSighting(id));
            return sight;
        } catch(DataAccessException ex) {
            return null;
        }
    }
    
    private Location getLocationForSighting(int sightId) {
        final String SELECT_LOCATION_FOR_SIGHTING = "select locations.* from locations "
                + " inner join sightings where sightings.id = ?";
        return jdbc.queryForObject(SELECT_LOCATION_FOR_SIGHTING, new LocationMapper(), sightId);
    }
    
    private List<Hero> getHeroesForSighting(int sightId) {
        final String SELECT_HEROES_IN_ORG = "select heroes.* from sightings inner join "
                + " heroAtSighting on sightings.id = heroAtSighting.sightingId "
                + " inner join heroes on heroAtSighting.heroId = heroes.id where sightings.id = ?";
         List<Hero> heroes = jdbc.query(SELECT_HEROES_IN_ORG, new HeroMapper(), sightId);
         associatePowers(heroes);
         return heroes;
    }
    
    private void associatePowers(List<Hero> heroes){
        for (Hero hero : heroes) {
            hero.setPowers(getPowersForHero(hero.getHeroId()));
        }
    }
    
    private List<Power> getPowersForHero(int heroId) {
        final String SELECT_POWERS_FOR_HERO = "select powers.* from heroes inner join "
                + " heroSuperpowers on heroes.id = heroSuperpowers.heroId inner join "
                + " powers on heroSuperpowers.powerId = powers.Id where heroes.id = ?";
        return jdbc.query(SELECT_POWERS_FOR_HERO, new PowerMapper(), heroId);
    }

    @Override
    public List<Sighting> getAllSightings() {
        final String SELECT_ALL_SIGHTINGS = "select * from sightings";
        List<Sighting> sights = jdbc.query(SELECT_ALL_SIGHTINGS, new SightingMapper());
        associateLocationAndHeroes(sights);
        return sights;
    }
    
    private void associateLocationAndHeroes(List<Sighting> sights) {
        for (Sighting sight : sights) {
            sight.setLocation(getLocationForSighting(sight.getId()));
            sight.setHeroes(getHeroesForSighting(sight.getId()));
        }
    }

    @Override
    @Transactional
    public void editSighting(Sighting changedSighting) {
        final String UPDATE_SIGHT = "update sightings set sightingDate = ?, description = ?, "
                + " locationId = ? where id = ?";
        jdbc.update(UPDATE_SIGHT, changedSighting.getDate().toString(), changedSighting.getDescription(),
            changedSighting.getLocation().getId(), changedSighting.getId());
        
        final String DELETE_SIGHTING_HEROES = "delete from heroAtSighting where sightingId = ?";
        jdbc.update(DELETE_SIGHTING_HEROES, changedSighting.getId());
        insertSightingHeroes(changedSighting);
    }

    @Override
    @Transactional
    public void deleteSighting(int id) {
        final String DELETE_SIGHTING_HEROES = "delete from heroAtSighting where sightingId = ?";
        jdbc.update(DELETE_SIGHTING_HEROES, id);
        
        final String DELETE_SIGHTING = "delete from sightings where id = ?";
        jdbc.update(DELETE_SIGHTING, id);
    }
    
}
