package com.edsf.spc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demo {
    @RequestMapping(value="/demo",method = RequestMethod.GET)
    public String demoSpringBoot(){
        return "Demo of SPC Boot";
    }
}
