package com.doodlemap.doodlemap.data;

import com.doodlemap.doodlemap.models.Breed;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface BreedDao extends CrudRepository<Breed, Integer>{
}
