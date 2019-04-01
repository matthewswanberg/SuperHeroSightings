/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSighting.data;

import com.sg.SuperheroSighting.model.Location;
import java.util.List;

/**
 *
 * @author matthewswanberg
 */
public interface LocationDao {
    
    public Location addLocation(Location aLocation);
    
    public Location getLocation(int id);
    
    public List<Location> getAllLocations();
    
    public void editLocation(Location changedLocation);
    
    public void deleteLocation(int id);
    
}
