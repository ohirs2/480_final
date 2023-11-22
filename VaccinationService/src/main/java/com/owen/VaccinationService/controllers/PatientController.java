package com.owen.VaccinationService.controllers;

import com.owen.VaccinationService.entities.Patient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @GetMapping
    public ResponseEntity<String> info() {
        return ResponseEntity.ok("You are a patient!");
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerPatient(@RequestBody Patient patient) {
        // Implement patient registration logic
        return ResponseEntity.ok("Patient registered successfully");
    }

    // More endpoints for Patient...
}
