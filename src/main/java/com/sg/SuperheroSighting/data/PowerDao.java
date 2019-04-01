/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSighting.data;

import com.sg.SuperheroSighting.model.Power;
import java.util.List;

/**
 *
 * @author matthewswanberg
 */
public interface PowerDao {
    
    public Power addPower(Power aPower);
    
    public Power getPower(int id);
    
    public List<Power> getAllPowers();
    
    public void editPower(Power changedPower);
    
    public void deletePower(int id);
    
}
