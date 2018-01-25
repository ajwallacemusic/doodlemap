package com.doodlemap.doodlemap.controllers;

import com.doodlemap.doodlemap.data.BreedDao;
import com.doodlemap.doodlemap.data.BreederDao;
import com.doodlemap.doodlemap.models.Breed;
import com.doodlemap.doodlemap.models.Breeder;
import com.doodlemap.doodlemap.models.UsState;
import com.doodlemap.doodlemap.models.forms.EditBreederForm;
import com.doodlemap.doodlemap.models.validators.ValidBreederObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.Set;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.size;

@Controller
@RequestMapping(value = "breeders")
public class BreederController {

    //database access objects
    @Autowired
    private BreedDao breedDao;

    @Autowired
    private BreederDao breederDao;

    //index for Breeders
    @RequestMapping(value = "")
    private String index(Model model) {

        model.addAttribute("title", "Breeders");
        model.addAttribute("breeders", breederDao.findAll());

        return "breeders/index";
    }

    //Add a Breeder
    @RequestMapping(value = "add", method = RequestMethod.GET)
    private String displayAddBreederForm(Model model) {

        model.addAttribute("title", "Add a Breeder");
        model.addAttribute(new Breeder());
        if (breedDao.findAll() != null) {
            model.addAttribute("breeds", breedDao.findAll());
        }
        model.addAttribute("states", UsState.values());

        return "breeders/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    private String processAddBreederForm(@ModelAttribute @Valid Breeder newBreeder, Errors errors,
                                         @RequestParam(required = false) int[] breeds, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add a Breeder");
            model.addAttribute("breeder", newBreeder);
            if (breedDao.findAll() != null) {
                model.addAttribute("breeds", breedDao.findAll());
            }
            model.addAttribute("states", UsState.values());

            return "breeders/add";
        }

        if (breeds != null) {
            for (int id : breeds) {
                newBreeder.addBreed(breedDao.findOne(id));
                breederDao.save(newBreeder);
            }
        }

        breederDao.save(newBreeder);

        return "redirect:";
    }

    @RequestMapping(value = "view/{id}")
    private String view(@PathVariable int id, Model model) {

        Breeder theBreeder = breederDao.findOne(id);
        model.addAttribute("breeder", theBreeder);

        return "breeders/view";
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    private String displayEditForm(@PathVariable int id, Model model) {

        Breeder theBreeder = breederDao.findOne(id);

        ArrayList<Breed> breedersBreeds = new ArrayList<>(theBreeder.getBreeds());
        Iterable<Breed> allTheBreeds = breedDao.findAll();
        ArrayList<Breed> addableBreeds = new ArrayList<>();


        for (Breed breed : allTheBreeds) {
            if (!breedersBreeds.contains(breed)) {
                addableBreeds.add(breed);
            }
        }

        //EditBreederForm editBreederForm = new EditBreederForm(theBreeder, breedersBreeds, addableBreeds);
        model.addAttribute("breeder", theBreeder);
        model.addAttribute("addableBreeds", addableBreeds);
        model.addAttribute("currentBreeds", breedersBreeds);
        model.addAttribute("states", UsState.values());

        return "breeders/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    private String processEditForm(@ModelAttribute @Valid Breeder breeder, Errors errors,
                                   Model model, @RequestParam(required = false) String deleteBreeder) {

        Breeder theBreeder = breederDao.findOne(breeder.getId());


        if (errors.hasErrors()) {
            System.out.println(errors);

            ArrayList<Breed> breedersBreeds = new ArrayList<>(theBreeder.getBreeds());
            Iterable<Breed> allTheBreeds = breedDao.findAll();
            ArrayList<Breed> addableBreeds = new ArrayList<>();


            for (Breed breed : allTheBreeds) {
                if (!breedersBreeds.contains(breed)) {
                    addableBreeds.add(breed);
                }
            }

            model.addAttribute("breeder", breeder);
            model.addAttribute("addableBreeds", addableBreeds);
            model.addAttribute("currentBreeds", breedersBreeds);
            model.addAttribute("states", UsState.values());
            model.addAttribute("theBreederId", breeder.getId());

            return "breeders/edit";
        }

        //Add & Remove Breeds

        if (breeder.getAddBreedId() != null) {
            for (int breedId : breeder.getAddBreedId()) {
                theBreeder.addBreed(breedDao.findOne(breedId));
                breederDao.save(theBreeder);
            }
        }

        if (breeder.getRemoveBreedId() != null) {
            for (int breedId : breeder.getRemoveBreedId()) {
                theBreeder.removeBreed(breedDao.findOne(breedId));
                breederDao.save(theBreeder);
            }
        }

        //Edit Breeder Name
        String breederName = breeder.getName();
        theBreeder.setName(breederName);
        breederDao.save(theBreeder);

        //Edit Breeder Phone
        String breederPhone = breeder.getPhone();
        theBreeder.setPhone(breederPhone);
        breederDao.save(theBreeder);

        //Edit Breeder Url
        String breederUrl = breeder.getUrl();
        theBreeder.setUrl(breederUrl);
        breederDao.save(theBreeder);

        //Edit State
        UsState state = breeder.getState();
        theBreeder.setState(state);
        breederDao.save(theBreeder);

        //Delete Breeder
        if (deleteBreeder != null && theBreeder.getBreeds() !=null) {
            for (Breed breed : theBreeder.getBreeds()) {
                theBreeder.removeBreed(breed);
                breederDao.save(theBreeder);
            }
            breederDao.delete(theBreeder);
            return "redirect:";

        } else if (deleteBreeder != null) {
            breederDao.delete(theBreeder);
            return "redirect:";
        }


        return "redirect:/breeders/view/" + theBreeder.getId();
    }


}
