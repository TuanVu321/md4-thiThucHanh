package com.example.demo.controller;

import com.example.demo.model.City;
import com.example.demo.model.Country;
import com.example.demo.service.City.ICityService;
import com.example.demo.service.Country.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CityController {
    @Autowired
    private ICityService cityService;
    @Autowired
    private ICountryService countryService;
    @ModelAttribute("country")
    public Page<Country> countries(@PageableDefault(10)Pageable pageable){
        return countryService.findAll(pageable);
    }
    @GetMapping("/")
    public ModelAndView home(@PageableDefault(10)Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("listCity",cityService.findAll(pageable));
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("city",new City());
        return modelAndView;
    }

    @PostMapping("/savecreate")
    public ModelAndView save(@ModelAttribute("city")City city){
        cityService.save(city);
        return new ModelAndView("redirect:/");
    }
    @GetMapping("/showCity/{id}")
    public ModelAndView showCity(@PathVariable("id")Long id){
        ModelAndView modelAndView = new ModelAndView("city");
        modelAndView.addObject("city",cityService.findById(id).get());
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable("id")Long id){
        cityService.delete(id);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable("id")Long id){
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("city",cityService.findById(id).get());
        return modelAndView;
    }
    @PostMapping("/edit")
    public ModelAndView edit(@ModelAttribute("city")City city ){
        cityService.save(city);
        return new ModelAndView("redirect:/");
    }











}
