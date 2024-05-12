package com.jyotish.api.core.controller;

import com.jyotish.api.core.models.input.BirthData;
import com.jyotish.api.core.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rules")
public class RuleController {

    @Autowired
    @Qualifier("person1")
    private BirthData person1;

    @Autowired
    @Qualifier("person2")
    private BirthData person2;

    @Autowired
    @Qualifier("person3")
    private BirthData person3;

    @Autowired
    private RuleService ruleService;

    @GetMapping("/apply/{personNumber}")
    public String apply(@PathVariable Integer personNumber) throws Exception {
        Object personData = getClass().getDeclaredField("person" + personNumber).get(this);
        return ruleService.apply((BirthData) personData);
    }


    @GetMapping("/misc/{personNumber}")
    public String misc(@PathVariable Integer personNumber) throws Exception {
        Object personData = getClass().getDeclaredField("person" + personNumber).get(this);
        return ruleService.misc((BirthData) personData);
    }

}
