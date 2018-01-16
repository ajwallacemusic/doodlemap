package com.doodlemap.doodlemap.models;

import com.doodlemap.doodlemap.models.validators.ValidBreederObject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Breeder {

    //Fields
    @Id
    @GeneratedValue
    private int id;

    //@NotNull
    @Size(min=1, max=100, message = "Name must not be empty.")
    private String name;

    @Size(min=1, max=100, message = "Yikes! We really like our breeders to have a website.")
    private String url;

    private String phone;

    private int[] addBreedId;

    private int[] removeBreedId;

    private int breederFormId;

    @ManyToMany
    private Set<Breed> breeds;

    public void addBreed(Breed breed) {
        breeds.add(breed);
    }

    public void removeBreed(Breed breed) {
        breeds.remove(breed);
    }

    //Might need to convert to list of states
    private UsState state;


    //Constructors
    public Breeder() {}

    public Breeder(String name, String url, String phone, UsState state) {
        this.name = name;
        this.url = url;
        this.phone = phone;
        this.state = state;
    }

    //Getters & Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Breed> getBreeds() {
        return breeds;
    }

    public void setBreeds(Set<Breed> breeds) {
        this.breeds = breeds;
    }

    public UsState getState() {
        return state;
    }

    public void setState(UsState state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

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

    public int getBreederFormId() {
        return id;
    }

    public void setBreederFormId(int breederFormId) {
        this.breederFormId = breederFormId;
    }
}
