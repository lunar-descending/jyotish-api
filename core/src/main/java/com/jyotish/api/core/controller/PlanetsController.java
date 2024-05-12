package com.jyotish.api.core.controller;

import com.jyotish.api.core.models.input.BirthData;
import com.jyotish.api.core.service.PlanetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/planets")
public class PlanetsController {

    @Autowired
    @Qualifier("person1")
    private BirthData person1;

    @Autowired
    @Qualifier("person2")
    private BirthData person2;

    @Autowired
    private PlanetsService planetsService;

    @GetMapping("/get")
    public Map<String, Map<String, Object>> getPlanets() {
        return planetsService.getPlanetResponseMap(person1);
    }

}
