package com.doodlemap.doodlemap.controllers;

import com.doodlemap.doodlemap.data.BreedDao;
import com.doodlemap.doodlemap.data.BreederDao;
import com.doodlemap.doodlemap.models.Breed;
import com.doodlemap.doodlemap.models.Breeder;
import org.hibernate.validator.constraints.ModCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("breeds")
public class BreedController {

    //database access objects
    @Autowired
    private BreedDao breedDao;

    @Autowired
    private BreederDao breederDao;

    //index for breeds
    @RequestMapping("")
    private String index(Model model) {

        model.addAttribute("title", "Breeds");
        model.addAttribute("breeds", breedDao.findAll());

        return "breeds/index";
    }

    //renders form to add a new breed
    @RequestMapping(value = "add", method = RequestMethod.GET)
    private String displayAddBreedForm(Model model) {

        model.addAttribute("title", "Add a Breed");
        model.addAttribute(new Breed());
        model.addAttribute("breeders", breederDao.findAll());
        model.addAttribute("parentBreeds", breedDao.findAll());

        return "breeds/add";
    }
/*  Request Params for future features of add function
    @RequestParam(required = false) int parentBreedOne,
    @RequestParam(required = false) int parentBreedTwo,
    @RequestParam(required = false) List<Breeder> breeders,
    */
    //processes the form to add a new breed and then displays the new breed's page
    @RequestMapping(value = "add", method = RequestMethod.POST)
    private String processAddBreedForm(@ModelAttribute @Valid Breed newBreed,
                                       Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add a Breed");
            model.addAttribute("breed", newBreed);
            model.addAttribute("breeders", breederDao.findAll());
            model.addAttribute("parentBreeds", breedDao.findAll());

            return "breeds/add";
        }

        /* logic for parentBreed feature to come later

        if (parentBreedOne > 0 && parentBreedTwo > 0) {
            Breed parentOne = breedDao.findOne(parentBreedOne);
            Breed parentTwo = breedDao.findOne(parentBreedTwo);
            newBreed.addParent(parentOne);
            newBreed.addParent(parentTwo);
            parentOne.addChild(newBreed);
            parentTwo.addChild(newBreed);

            breedDao.save(newBreed);


        }
        */

        breedDao.save(newBreed);

        return "redirect:view?breed=" + newBreed.getName();
    }

    @RequestMapping(value = "view", method = RequestMethod.GET)
    private String viewBreed(@RequestParam("breed") String breed, Model model) {

        Breed showBreed = null;

        for (Breed theBreed : breedDao.findAll()) {
            if (theBreed.getName().contains(breed)) {
                showBreed = theBreed;
            }
        }

        model.addAttribute("title", showBreed.getName());
        model.addAttribute("breed", showBreed);

        return "breeds/view";
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    private String editBreed(@RequestParam("breed") String breed, Model model) {

        Breed editBreed = null;

        for (Breed theBreed : breedDao.findAll()) {
            if (theBreed.getName().contains(breed)) {
                editBreed = theBreed;
            }
        }

        model.addAttribute("title", "Edit " + editBreed.getName());
        model.addAttribute("breed", editBreed);

        return "breeds/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    private String editBreed(@ModelAttribute @Valid Breed breed, Errors errors, Model model, @RequestParam(required = false) String deleteBreed) {

        Breed theBreed = breedDao.findOne(breed.getId());

        if (errors.hasErrors()) {
            model.addAttribute("breed", breed);

            return "breeds/edit";
        }


        String breedName = breed.getName();
        theBreed.setName(breedName);
        breedDao.save(theBreed);

        String breedDesc = breed.getDescription();
        theBreed.setDescription(breedDesc);
        breedDao.save(theBreed);

        //Delete Breeder
        if (deleteBreed != null && theBreed.getBreeders() !=null) {
            for (Breeder breeder : theBreed.getBreeders()) {
                Breeder theBreeder = breederDao.findOne(breeder.getId());
                theBreeder.removeBreed(theBreed);
                breederDao.save(theBreeder);
            }
            breedDao.delete(theBreed);
            return "redirect:";

        } else if (deleteBreed != null) {
            breedDao.delete(theBreed);
            return "redirect:";
        }


        return "redirect:view?breed=" + theBreed.getName();
    }


    //TODO parent breeds

}
