package com.owen.VaccinationService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VaccinationController {
    @GetMapping("/info")
    public String help() {
        return "Final project for CS 480 - Database Systems";
    }





}
