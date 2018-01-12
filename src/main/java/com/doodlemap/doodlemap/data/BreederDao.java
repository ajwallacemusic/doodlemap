package com.doodlemap.doodlemap.data;

import com.doodlemap.doodlemap.models.Breeder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface BreederDao extends CrudRepository<Breeder, Integer> {
}
