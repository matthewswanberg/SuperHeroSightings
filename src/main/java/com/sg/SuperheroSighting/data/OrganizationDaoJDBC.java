/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSighting.data;

import com.sg.SuperheroSighting.mapper.HeroMapper;
import com.sg.SuperheroSighting.mapper.LocationMapper;
import com.sg.SuperheroSighting.mapper.OrganizationMapper;
import com.sg.SuperheroSighting.mapper.PowerMapper;
import com.sg.SuperheroSighting.model.Hero;
import com.sg.SuperheroSighting.model.Location;
import com.sg.SuperheroSighting.model.Organization;
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
public class OrganizationDaoJDBC implements OrganizationDao{
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    @Transactional
    public Organization addOrg(Organization org) {
        final String INSERT_ORG = "insert into organizations(`name`, `description`, locationId, " 
                + " phone, email) values(?, ?, ?, ?, ?)";
        jdbc.update(INSERT_ORG, org.getName(), org.getDescription(),
                org.getLocation().getId(), org.getPhone(), org.getEmail());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        org.setId(newId);
        insertOrgHeroes(org);
        return org;
    }
    
    private void insertOrgHeroes(Organization org) {
        final String INSERT_ORG_HERO = "insert into organizationMembership(heroId, organizationId) "
                + " values(?,?)";
        for (Hero hero : org.getHeroes()) {
            jdbc.update(INSERT_ORG_HERO, hero.getHeroId(), org.getId());
        }
    }

    @Override
    public Organization getOrg(int id) {
        try {
            final String SELECT_ORG_BY_ID = "SELECT * FROM organizations WHERE id = ?";
            Organization org = jdbc.queryForObject(SELECT_ORG_BY_ID, new OrganizationMapper(), id);
            org.setLocation(getLocationForOrganization(id));
            org.setHeroes(getHeroesForOrganization(id));
            return org;
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> getAllOrgs() {
        final String SELECT_ALL_ORGS = "select * from organizations";
        List<Organization> orgs = jdbc.query(SELECT_ALL_ORGS, new OrganizationMapper());
        associateLocationAndHeroes(orgs);
        return orgs;
    }
    
    private void associateLocationAndHeroes(List<Organization> orgs) {
        for (Organization org : orgs) {
            org.setLocation(getLocationForOrganization(org.getId()));
            org.setHeroes(getHeroesForOrganization(org.getId()));
        }
    }

    @Override
    public void editOrg(Organization changedOrg) {
        final String UPDATE_ORG = "update organizations set name = ?, description = ?, "
                + " locationId = ?, phone = ?, email = ? where id = ?";
        jdbc.update(UPDATE_ORG, changedOrg.getName(), changedOrg.getDescription(),
                changedOrg.getLocation().getId(), changedOrg.getPhone(),
                changedOrg.getEmail(), changedOrg.getId());
        
        final String DELETE_ORG_HEROES = "delete from organizationMembership where organizationId = ?";
        jdbc.update(DELETE_ORG_HEROES, changedOrg.getId());
        insertOrgHeroes(changedOrg);
    }

    @Override
    public void deleteOrg(int id) {
        final String DELETE_ORG_HEROES = "delete from organizationMembership where organizationId = ?";
        jdbc.update(DELETE_ORG_HEROES, id);
        
        final String DELETE_ORG = "delete from organizations where id = ?";
        jdbc.update(DELETE_ORG, id);
    }
    
    private Location getLocationForOrganization(int orgId) {
        final String SELECT_LOCATION_FOR_ORGANIZATION = "select locations.* from locations "
                + " inner join organizations where organizations.id = ?";
        return jdbc.queryForObject(SELECT_LOCATION_FOR_ORGANIZATION, new LocationMapper(), orgId);
    }
    
    private List<Hero> getHeroesForOrganization(int orgId) {
        final String SELECT_HEROES_IN_ORG = "select heroes.* from organizations inner join "
                + " organizationMembership on organizations.id = organizationMembership.organizationId "
                + " inner join heroes on organizationMembership.heroId = heroes.id where organizations.id = ?";
        List<Hero> heroes = jdbc.query(SELECT_HEROES_IN_ORG, new HeroMapper(), orgId);
        associatePowers(heroes);
        return heroes;
    }
    
    private List<Power> getPowersForHero(int heroId) {
        final String SELECT_POWERS_FOR_HERO = "select powers.* from heroes inner join "
                + " heroSuperpowers on heroes.id = heroSuperpowers.heroId inner join "
                + " powers on heroSuperpowers.powerId = powers.Id where heroes.id = ?";
        return jdbc.query(SELECT_POWERS_FOR_HERO, new PowerMapper(), heroId);
    }
    
        private void associatePowers(List<Hero> heroes){
        for (Hero hero : heroes) {
            hero.setPowers(getPowersForHero(hero.getHeroId()));
        }
    }
    
    
}
