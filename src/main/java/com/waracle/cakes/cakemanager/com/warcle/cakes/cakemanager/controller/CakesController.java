package com.waracle.cakes.cakemanager.com.warcle.cakes.cakemanager.controller;

import com.waracle.cakes.cakemanager.com.warcle.cakes.cakemanager.model.Cake;
import com.waracle.cakes.cakemanager.com.warcle.cakes.cakemanager.service.CakeService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.thymeleaf.TemplateEngine;

@Controller
public class CakesController {

    private static final Logger logger
            = LoggerFactory.getLogger(CakesController.class);

    private static final String ACCEPT_HTML = "text/html";

    private final CakeService cakeService;
    private final TemplateEngine htmlTemplateEngine;

    @Autowired
    public CakesController(CakeService cakeService, TemplateEngine htmlTemplateEngine) {
        this.cakeService = cakeService;
        this.htmlTemplateEngine = htmlTemplateEngine;
    }

    @GetMapping(value = {"/", "/cakes"})
    public ModelAndView getAllCakes(@RequestHeader(value = "Accept") String accept) {

        ModelAndView model;
        if (accept != null && accept.contains(ACCEPT_HTML)) {
            model = new ModelAndView("cakes");
            model.addObject("header", "Cakes");
            model.addObject("cakes", cakeService.getAllCakes());

        } else {
            model = new ModelAndView();
            model.setView(new MappingJackson2JsonView());
            model.addObject(cakeService.getAllCakes());
        }

        return model;
    }

    @GetMapping(value = "/cake-form")
    public String cakeForm(Model model) {
        model.addAttribute(new Cake());
        return "cake-form";
    }

    @PostMapping(value = "/new-cake")
    public String addCakeUi(@ModelAttribute Cake cake)
    {
        if (StringUtils.isNotEmpty(cake.getTitle())
                && StringUtils.isNotEmpty(cake.getDescription())) {
            Cake savedCake = cakeService.add(cake);
            if (savedCake != null) {
                return "redirect:/cakes";
            }
        }
        return "/cake-form";
    }


    @PostMapping(value = "/cakes")
    public ResponseEntity<Object> addCakeApi(@RequestBody Cake cake)
    {
        Cake savedCake = null;
        if (StringUtils.isNotEmpty(cake.getTitle())
                && StringUtils.isNotEmpty(cake.getDescription())) {
            savedCake = cakeService.add(cake);
            if (savedCake != null) {
                return new ResponseEntity<>(savedCake, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>("Failed to save entity",
                HttpStatus.BAD_REQUEST);

    }
    

}
