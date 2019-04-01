/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSighting.service;

import com.sg.SuperheroSighting.data.HeroDao;
import com.sg.SuperheroSighting.data.LocationDao;
import com.sg.SuperheroSighting.data.OrganizationDao;
import com.sg.SuperheroSighting.data.PowerDao;
import com.sg.SuperheroSighting.data.SightingDao;
import com.sg.SuperheroSighting.model.Hero;
import com.sg.SuperheroSighting.model.Location;
import com.sg.SuperheroSighting.model.Organization;
import com.sg.SuperheroSighting.model.Power;
import com.sg.SuperheroSighting.model.Sighting;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author matthewswanberg
 */
@Service
public class SightingServiceImpl implements SightingService{
    
    @Autowired
    SightingDao sightingDao;
    
    @Autowired
    LocationDao locationDao;
    
    @Autowired
    HeroDao heroDao;
    
    @Autowired
    PowerDao powerDao;
    
    @Autowired
    OrganizationDao orgDao;

    @Override
    public List<Hero> GetHeroesAtLocation(Location alocation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Location> GetLocationsForHero(Hero ahero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sighting> GetSightingsForDate(Sighting aSighting) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Hero> GetMembersOfOrg(Organization org) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Organization> GetOrgsForHero(Hero ahero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void RecordSighting(Sighting aSighting) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    //Pass Through Hero
    
    @Override
    public Hero createHero(Hero aHero) {
       return heroDao.addHero(aHero);
    }

    @Override
    public Hero retrieveHero(int id) {
        return heroDao.getHero(id);
    }

    @Override
    public List<Hero> retrieveAllHeroes() {
        return heroDao.getAllHeroes();
    }

    @Override
    public void editHero(Hero changedHero) {
        heroDao.editHero(changedHero);
    }

    @Override
    public void removeHero(int id) {
        heroDao.deleteHero(id);
    }
    
    
    //Pass Through Power

    @Override
    public Power createPower(Power aPower) {
        return powerDao.addPower(aPower);
    }

    @Override
    public Power retrievePower(int id) {
        return powerDao.getPower(id);
    }

    @Override
    public List<Power> retrieveAllPowers() {
        return powerDao.getAllPowers();
    }

    @Override
    public void editPower(Power changedPower) {
        powerDao.editPower(changedPower);
    }

    @Override
    public void removePower(int id) {
        powerDao.deletePower(id);
    }
    
    
    //Pass Through Location

    @Override
    public Location createLocation(Location aLocation) {
        return locationDao.addLocation(aLocation);
    }

    @Override
    public Location retrieveLocation(int id) {
        return locationDao.getLocation(id);
    }

    @Override
    public List<Location> retrieveAllLocations() {
        return locationDao.getAllLocations();
    }

    @Override
    public void editLocation(Location changedLocation) {
        locationDao.editLocation(changedLocation);
    }

    @Override
    public void removeLocation(int id) {
        locationDao.deleteLocation(id);
    }

    
    // Pass Through Org
   
    @Override
    public Organization createOrg(Organization Org) {
        return orgDao.addOrg(Org);
    }

    @Override
    public Organization retrieveOrg(int id) {
        return orgDao.getOrg(id);
    }

    @Override
    public List<Organization> retrieveAllOrgs() {
        return orgDao.getAllOrgs();
    }

    @Override
    public void editOrg(Organization changedOrg) {
        orgDao.editOrg(changedOrg);
    }

    @Override
    public void removeOrg(int id) {
        orgDao.deleteOrg(id);
    }
    
    
    // Pass Through

    @Override
    public Sighting createSighting(Sighting aSighting) {
        return sightingDao.addSighting(aSighting);
    }

    @Override
    public Sighting retrieveSighting(int id) {
        return sightingDao.getSighting(id);
    }

    @Override
    public List<Sighting> retrieveAllSightings() {
        return sightingDao.getAllSightings();
    }

    @Override
    public void editSighting(Sighting changedSighting) {
        sightingDao.editSighting(changedSighting);
    }

    @Override
    public void removeSighting(int id) {
        sightingDao.deleteSighting(id);
    }
}
