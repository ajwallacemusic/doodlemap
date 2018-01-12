package com.doodlemap.doodlemap.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Breed {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @ManyToMany(mappedBy = "breeds")
    private Set<Breeder> breeders;

    @ManyToMany(mappedBy = "parentBreeds")
    private List<Breed> offspring = new ArrayList<>();

    public void addChild(Breed child) {
        offspring.add(child);
    }

    @ManyToMany
    private List<Breed> parentBreeds = new ArrayList<>();

    public void addParent(Breed parent) {
        parentBreeds.add(parent);
    }


    //Constructors
    public Breed(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Breed() {}


    //Getters & Setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public Set<Breeder> getBreeders() {
        return breeders;
    }

    public void setBreeders(Set<Breeder> breeders) {
        this.breeders = breeders;
    }

    public List<Breed> getOffspring() {
        return offspring;
    }

    public void setOffspring(List<Breed> offspring) {
        this.offspring = offspring;
    }

    public List<Breed> getParentBreeds() {
        return parentBreeds;
    }

    public void setParentBreeds(List<Breed> parentBreeds) {
        this.parentBreeds = parentBreeds;
    }
}
