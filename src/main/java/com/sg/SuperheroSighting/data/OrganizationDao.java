/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSighting.data;

import com.sg.SuperheroSighting.model.Organization;
import java.util.List;

/**
 *
 * @author matthewswanberg
 */
public interface OrganizationDao {
    
   public Organization addOrg(Organization Org);
   
   public Organization getOrg(int id);
   
   public List<Organization> getAllOrgs();
   
   public void editOrg(Organization changedOrg);
   
   public void deleteOrg(int id);
}
