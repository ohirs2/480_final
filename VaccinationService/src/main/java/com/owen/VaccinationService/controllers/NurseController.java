package com.owen.VaccinationService.controllers;

import com.owen.VaccinationService.entities.Nurse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nurse")
public class NurseController {

    @GetMapping
    public ResponseEntity<String> info() {
        return ResponseEntity.ok("You are a nurse!");
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateNurse(@RequestBody Nurse nurse) {
        // Implement nurse update logic
        return ResponseEntity.ok("Nurse updated successfully");
    }

    // More endpoints for Nurse...
}
