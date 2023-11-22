package com.owen.VaccinationService.controllers;

import com.owen.VaccinationService.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class DatabaseController {


    @Autowired
    private DatabaseService databaseService;


    @GetMapping("/info")
    public String info() {
        return "Final project for CS 480 - Database Systems";
    }

    @GetMapping("/tables")
    public List<String> getAllTables() {
        return databaseService.getAllTableNames();
    }

    @GetMapping("/schema")
    public Map<String, List<Map<String, Object>>> getDatabaseSchema() {
        return databaseService.getDatabaseSchema();
    }


}
