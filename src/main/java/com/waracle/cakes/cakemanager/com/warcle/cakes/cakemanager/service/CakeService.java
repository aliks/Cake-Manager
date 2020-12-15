package com.waracle.cakes.cakemanager.com.warcle.cakes.cakemanager.service;

import com.waracle.cakes.cakemanager.com.warcle.cakes.cakemanager.model.Cake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CakeService {

    @Autowired
    private CakeRepository cakeRepository;

    public void save(List<Cake> cakes) {
        cakeRepository.saveAll(cakes);

    }

    public List<Cake> getAllCakes() {
        List<Cake> cakes = new ArrayList<>();
        Iterable<Cake> cakesIterable = cakeRepository.findAll();
        cakesIterable.forEach(cake -> cakes.add(cake));
        return cakes;
    }

    public Cake add(Cake cake) {
        return cakeRepository.save(cake);
    }

}