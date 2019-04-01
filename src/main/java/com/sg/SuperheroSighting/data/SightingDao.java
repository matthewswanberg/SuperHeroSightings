/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSighting.data;

import com.sg.SuperheroSighting.model.Sighting;
import java.util.List;

/**
 *
 * @author matthewswanberg
 */
public interface SightingDao {
    
    public Sighting addSighting(Sighting aSighting);
    
    public Sighting getSighting(int id);
    
    public List<Sighting> getAllSightings();
    
    public void editSighting(Sighting changedSighting);
    
    public void deleteSighting(int id);
}
