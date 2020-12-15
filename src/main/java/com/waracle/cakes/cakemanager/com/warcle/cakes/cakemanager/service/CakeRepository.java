package com.waracle.cakes.cakemanager.com.warcle.cakes.cakemanager.service;

import com.waracle.cakes.cakemanager.com.warcle.cakes.cakemanager.model.Cake;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CakeRepository extends CrudRepository<Cake, Long> {

}
