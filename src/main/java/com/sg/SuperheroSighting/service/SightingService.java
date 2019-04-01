/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSighting.service;

import com.sg.SuperheroSighting.model.Hero;
import com.sg.SuperheroSighting.model.Location;
import com.sg.SuperheroSighting.model.Organization;
import com.sg.SuperheroSighting.model.Power;
import com.sg.SuperheroSighting.model.Sighting;
import java.util.List;

/**
 *
 * @author matthewswanberg
 */
public interface SightingService {
    
    // Service Specific
    
    public List<Hero> GetHeroesAtLocation(Location alocation);
    
    public List<Location> GetLocationsForHero(Hero ahero);
    
    public List<Sighting> GetSightingsForDate(Sighting aSighting);
    
    public List<Hero> GetMembersOfOrg(Organization org);
    
    public List<Organization> GetOrgsForHero(Hero ahero);
    
    public void RecordSighting(Sighting aSighting);
    
    
    
    // Pass Throughs for Heroes
    public Hero createHero(Hero aHero);
   
    public Hero retrieveHero(int id);
   
    public List<Hero> retrieveAllHeroes();
   
    public void editHero(Hero changedHero);
   
    public void removeHero(int id);
   
    
   
    // Pass Through for Power
    public Power createPower(Power aPower);
    
    public Power retrievePower(int id);
    
    public List<Power> retrieveAllPowers();
    
    public void editPower(Power changedPower);
    
    public void removePower(int id);
    
    
    
    // Pass Through for Location
    public Location createLocation(Location aLocation);
    
    public Location retrieveLocation(int id);
    
    public List<Location> retrieveAllLocations();
    
    public void editLocation(Location changedLocation);
    
    public void removeLocation(int id);
    
    
    
    // Pass Through for Organization
    public Organization createOrg(Organization Org);
   
    public Organization retrieveOrg(int id);
   
    public List<Organization> retrieveAllOrgs();
    
    public void editOrg(Organization changedOrg);
   
    public void removeOrg(int id);
   
    
    
    // Pass Through for Sighting
    public Sighting createSighting(Sighting aSighting);
    
    public Sighting retrieveSighting(int id);
    
    public List<Sighting> retrieveAllSightings();
    
    public void editSighting(Sighting changedSighting);
    
    public void removeSighting(int id);
    
}
