package com.doodlemap.doodlemap.controllers;

import com.doodlemap.doodlemap.data.BreedDao;
import com.doodlemap.doodlemap.data.BreederDao;
import com.doodlemap.doodlemap.models.Breed;
import com.doodlemap.doodlemap.models.Breeder;
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

    //processes the form to add a new breed and then displays the new breed's page
    @RequestMapping(value = "add", method = RequestMethod.POST)
    private String processAddBreedForm(@ModelAttribute @Valid Breed newBreed,
                                       Errors errors, @RequestParam(required = false) int parentBreedOne,
                                       @RequestParam(required = false) int parentBreedTwo,
                                       @RequestParam(required = false) List<Breeder> breeders,
                                       Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add a Breed");
            model.addAttribute("breed", newBreed);
            model.addAttribute("breeders", breederDao.findAll());
            model.addAttribute("parentBreeds", breedDao.findAll());

            return "breeds/add";
        }

        if (parentBreedOne > 0 && parentBreedTwo > 0) {
            Breed parentOne = breedDao.findOne(parentBreedOne);
            Breed parentTwo = breedDao.findOne(parentBreedTwo);
            newBreed.addParent(parentOne);
            newBreed.addParent(parentTwo);
            parentOne.addChild(newBreed);
            parentTwo.addChild(newBreed);

            breedDao.save(newBreed);


        }

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

    //TODO remove breed
    //TODO edit breed
    //TODO breed parents

}
