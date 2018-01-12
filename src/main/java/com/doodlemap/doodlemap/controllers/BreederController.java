package com.doodlemap.doodlemap.controllers;

import com.doodlemap.doodlemap.data.BreedDao;
import com.doodlemap.doodlemap.data.BreederDao;
import com.doodlemap.doodlemap.models.Breed;
import com.doodlemap.doodlemap.models.Breeder;
import com.doodlemap.doodlemap.models.UsState;
import com.doodlemap.doodlemap.models.forms.EditBreederForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;

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
        model.addAttribute("breeds", breedDao.findAll());
        model.addAttribute("states", UsState.values());

        return "breeders/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    private String processAddBreederForm(@ModelAttribute @Valid Breeder newBreeder, Errors errors,
                                         @RequestParam(required = false) int[] breeds, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add a Breeder");
            model.addAttribute("breeder", newBreeder);
            model.addAttribute("breeds", breedDao.findAll());
            model.addAttribute("states", UsState.values());

            return "breeders/add";
        }


        for (int id : breeds) {
            newBreeder.addBreed(breedDao.findOne(id));
            System.out.println(breeds);
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

        EditBreederForm editBreederForm = new EditBreederForm(theBreeder, breedersBreeds, addableBreeds);
        model.addAttribute("form", editBreederForm);

        return "breeders/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    private String processEditForm(@ModelAttribute @Valid EditBreederForm editBreederForm, Errors errors,
                                   Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("breeder", editBreederForm.getBreeder());
            model.addAttribute("breeds", breedDao.findAll());
        }

        //Add & Remove Breeds
        Breeder theBreeder = breederDao.findOne(editBreederForm.getBreederId());

        if (editBreederForm.getAddBreedId() != null) {
            for (int id : editBreederForm.getAddBreedId()) {
                theBreeder.addBreed(breedDao.findOne(id));
                breederDao.save(theBreeder);
            }
        }

        if (editBreederForm.getRemoveBreedId() != null) {
            for (int id : editBreederForm.getRemoveBreedId()) {
                theBreeder.removeBreed(breedDao.findOne(id));
                breederDao.save(theBreeder);
            }
        }

        //Edit Breeder Name
        String breederName = editBreederForm.getBreeder().getName();
        theBreeder.setName(breederName);
        breederDao.save(theBreeder);

        //Edit Breeder Phone
        String breederPhone = editBreederForm.getBreeder().getPhone();
        theBreeder.setPhone(breederPhone);
        breederDao.save(theBreeder);

        //Edit Breeder Url
        String breederUrl = editBreederForm.getBreeder().getUrl();
        theBreeder.setUrl(breederUrl);
        breederDao.save(theBreeder);


        //TODO edit state
        //TODO validation on edits

        return "redirect:view/" + theBreeder.getId();
    }

    /*
    TODO remove breeder
     */
}
