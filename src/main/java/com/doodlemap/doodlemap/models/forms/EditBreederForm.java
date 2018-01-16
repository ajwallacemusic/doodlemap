package com.doodlemap.doodlemap.models.forms;

import com.doodlemap.doodlemap.models.Breed;
import com.doodlemap.doodlemap.models.Breeder;
import com.doodlemap.doodlemap.models.validators.ValidBreederObject;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class EditBreederForm {

    @Valid
    private Breeder breeder;

    private Iterable<Breed> currentBreeds;

    private Iterable<Breed> addableBreeds;

    private int breederId;

    private int[] addBreedId;

    private int[] removeBreedId;

    public EditBreederForm(Breeder breeder, Iterable<Breed> currentBreeds, Iterable<Breed> addableBreeds) {
        this.breeder = breeder;
        this.currentBreeds = currentBreeds;
        this.addableBreeds = addableBreeds;
    }

    public EditBreederForm() {};

    public Breeder getBreeder() {
        return breeder;
    }

    public void setBreeder(Breeder breeder) {
        this.breeder = breeder;
    }

    public Iterable<Breed> getCurrentBreeds() {
        return currentBreeds;
    }

    public void setCurrentBreeds(Iterable<Breed> currentBreeds) {
        this.currentBreeds = currentBreeds;
    }

    public Iterable<Breed> getAddableBreeds() {
        return addableBreeds;
    }

    public void setAddableBreeds(Iterable<Breed> addableBreeds) {
        this.addableBreeds = addableBreeds;
    }

    public int getBreederId() {
        return breederId;
    }

    public void setBreederId(int breederId) {
        this.breederId = breederId;
    }

    public int[] getAddBreedId() {
        return addBreedId;
    }

    public void setAddBreedId(int[] addBreedId) {
        this.addBreedId = addBreedId;
    }

    public int[] getRemoveBreedId() {
        return removeBreedId;
    }

    public void setRemoveBreedId(int[] removeBreedId) {
        this.removeBreedId = removeBreedId;
    }

}
