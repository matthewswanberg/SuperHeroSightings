/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSighting.data;

import com.sg.SuperheroSighting.model.Hero;
import java.util.List;

/**
 *
 * @author matthewswanberg
 */
public interface HeroDao {
    
   public Hero addHero(Hero aHero);
   
   public Hero getHero(int id);
   
   public List<Hero> getAllHeroes();
   
   public void editHero(Hero changedHero);
   
   public void deleteHero(int id);
       
    
}
