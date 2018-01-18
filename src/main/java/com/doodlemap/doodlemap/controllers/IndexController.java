package com.doodlemap.doodlemap.controllers;

import com.doodlemap.doodlemap.data.BreedDao;
import com.doodlemap.doodlemap.data.BreederDao;
import com.doodlemap.doodlemap.models.Breed;
import com.doodlemap.doodlemap.models.Breeder;
import com.doodlemap.doodlemap.models.UsState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "")
public class IndexController {

    @Autowired
    public BreedDao breedDao;
    @Autowired
    public BreederDao breederDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    private String index(Model model) {

        model.addAttribute("title", "Home");
        model.addAttribute("states", UsState.values());

        return "index";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    private String index(Model model, @RequestParam UsState state) {

        return "redirect:/" + state;
    }

    @RequestMapping(value = "/{state}")
    private String viewState(@PathVariable UsState state, Model model) {

        //breederDao.findAll() is the problem (null pointer exception because of ?? maybe phone field? or is breeerDao not getting a value?)

        //Iterable<Breeder> breeders = breederDao.findAll();
        ArrayList<Breeder> breedersByState = new ArrayList<>();

        //loop over all breeders and check for breeders with the state in the path variable
        //Then add those breeders to a list which is passed to the model

        for (Breeder breeder : breederDao.findAll()) {
            if (breeder.getState() == state) {
                breedersByState.add(breeder);
            }
        }


        model.addAttribute("state", state);
        model.addAttribute("breeders", breedersByState);

        return "state-view";
    }

    //TODO add All States view
}
