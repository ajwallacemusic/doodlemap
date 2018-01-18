package com.doodlemap.doodlemap.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Breed {

    @Id
    @GeneratedValue
    private int id;

    @Size(min=1, max=100)
    private String name;

    @Size(min=1, max=1000)
    private String description;

    @ManyToMany(mappedBy = "breeds")
    private Set<Breeder> breeders;

    @ManyToMany(mappedBy = "parentBreeds")
    private Set<Breed> offspring;

    public void addChild(Breed child) {
        offspring.add(child);
    }

    @ManyToMany
    private Set<Breed> parentBreeds;

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

    public void setId(int id) {
        this.id = id;
    }

    public Set<Breeder> getBreeders() {
        return breeders;
    }

    public void setBreeders(Set<Breeder> breeders) {
        this.breeders = breeders;
    }

    public Set<Breed> getOffspring() {
        return offspring;
    }

    public void setOffspring(Set<Breed> offspring) {
        this.offspring = offspring;
    }

    public Set<Breed> getParentBreeds() {
        return parentBreeds;
    }

    public void setParentBreeds(Set<Breed> parentBreeds) {
        this.parentBreeds = parentBreeds;
    }
}
