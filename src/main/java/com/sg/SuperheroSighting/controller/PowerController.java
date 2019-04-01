/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSighting.controller;

import com.sg.SuperheroSighting.model.Power;
import com.sg.SuperheroSighting.service.SightingService;
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
public class PowerController {
    
    @Autowired
    SightingService service;
    
    @GetMapping("powers")
    public String displayAllPowers(Model model) {
        List<Power> powers = service.retrieveAllPowers();
        model.addAttribute("powers", powers);
        return "powers";
    }
    
    @PostMapping("addPower")
    public String addTeacher(HttpServletRequest request) {
        String Name = request.getParameter("name");
        String Description = request.getParameter("description");
        
        Power power = new Power();
        power.setName(Name);
        power.setDescription(Description);
        
        service.createPower(power);
        
        return "redirect:/powers";
    }
    
    @GetMapping("deletePower")
    public String deletePower(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        service.removePower(id);
        
        return "redirect:/powers";
    }
    
    @GetMapping("editPower")
    public String editPower(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Power power = service.retrievePower(id);
        
        model.addAttribute("power", power);
        return "editPower";
    }
    
    @PostMapping("editPower")
    public String performEditPower(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Power power = service.retrievePower(id);
        
        power.setName(request.getParameter("name"));
        power.setDescription(request.getParameter("description"));
        
        service.editPower(power);
        
        return "redirect:/powers";
    }
}
