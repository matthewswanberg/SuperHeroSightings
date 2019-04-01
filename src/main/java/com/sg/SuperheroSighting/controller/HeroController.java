/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSighting.controller;

import com.sg.SuperheroSighting.model.Hero;
import com.sg.SuperheroSighting.model.Location;
import com.sg.SuperheroSighting.model.Organization;
import com.sg.SuperheroSighting.model.Power;
import com.sg.SuperheroSighting.service.SightingService;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes.Name;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author matthewswanberg
 */
@Controller
public class HeroController {

    @Autowired
    SightingService service;

    @GetMapping("heroes")
    public String displayHeroesAndVillains(Model model) {
        List<Hero> heroes = service.retrieveAllHeroes();
        List<Power> powers = service.retrieveAllPowers();
        model.addAttribute("heroes", heroes);
        model.addAttribute("powers", powers);
        return "heroes";
    }

    @PostMapping("addHero")
    public String addHero(Hero hero, HttpServletRequest request) {
        String[] powerIds = request.getParameterValues("powerId");

        List<Power> powers = new ArrayList<>();
        for (String powerId : powerIds) {
            powers.add(service.retrievePower(Integer.parseInt(powerId)));
        }

        hero.setPowers(powers);

        service.createHero(hero);
        return "redirect:/heroes";
    }

    @GetMapping("deleteHero")
    public String deleteHero(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("hero.id"));
        service.removeHero(id);

        return "redirect:/heroes";
    }

    @GetMapping("editHero")
    public String editHero(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Hero hero = service.retrieveHero(id);
        List<Power> powers = service.retrieveAllPowers();
        model.addAttribute("powers", powers);
        model.addAttribute("hero", hero);
        return "editHero";
    }
    
    @PostMapping("editHero")
    public String performEditHero(Hero hero, HttpServletRequest request, Model model) {
         String[] powerIds = request.getParameterValues("powerId");
        hero.setHeroId(Integer.parseInt(request.getParameter("id")));
        hero.setName(request.getParameter("name"));
        hero.setDescription(request.getParameter("description"));
        
        List<Power> powers = new ArrayList<>();
        for(String powerId : powerIds) {
            powers.add(service.retrievePower(Integer.parseInt(powerId)));
        }
        
        hero.setPowers(powers);
        service.editHero(hero);
        
        return "redirect:/heroes";
    }
}
