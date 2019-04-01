/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSighting.controller;

import com.sg.SuperheroSighting.model.Location;
import com.sg.SuperheroSighting.service.SightingService;
import static com.sun.org.apache.xalan.internal.lib.ExsltMath.power;
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author matthewswanberg
 */
@Controller
public class LocationController {
    
    @Autowired
    SightingService service;
    
    @GetMapping("locations")
    public String displayAllPowers(Model model) {
        List<Location> locations = service.retrieveAllLocations();
        model.addAttribute("locations", locations);
        return "locations";
    }
    
    @PostMapping("addLocation")
    public String addLocation(HttpServletRequest request) {
        String Name = request.getParameter("name");
        String Description = request.getParameter("description");
        String Address = request.getParameter("address");
        String Latitude = request.getParameter("latitude");
        String Longitude = request.getParameter("longitude");
        
        Location location = new Location();
        location.setName(Name);
        location.setDescription(Description);
        location.setAddress(Address);
        location.setLatitude(new BigDecimal(Latitude));
        location.setLongitude(new BigDecimal(Longitude));
        
        service.createLocation(location);
        
        return "redirect:/locations";
    }
    
    @GetMapping("deleteLocation")
    public String deleteLocation(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        service.removeLocation(id);
        
        return "redirect:/locations";
    }
    
    @GetMapping("editLocation")
    public String editPower(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Location location = service.retrieveLocation(id);
        
        model.addAttribute("location", location);
        return "editLocation";
    }
    
    @PostMapping("editLocation")
    public String performEditPower(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Location location = service.retrieveLocation(id);
        
        location.setName(request.getParameter("name"));
        location.setDescription(request.getParameter("description"));
        location.setAddress(request.getParameter("address"));
        location.setLatitude(new BigDecimal(request.getParameter("latitude")));
        location.setLongitude(new BigDecimal(request.getParameter("longitude")));
        
        service.editLocation(location);
        
        return "redirect:/locations";
    }
}
