package com.owen.VaccinationService.controllers;

import com.owen.VaccinationService.entities.Nurse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public ResponseEntity<String> info() {
        return ResponseEntity.ok("You are an admin!");
    }
    @PostMapping("/nurses/register")
    public ResponseEntity<String> registerNurse(@RequestBody Nurse nurse) {
        // Implement nurse registration logic
        return ResponseEntity.ok("Nurse registered successfully");
    }
    @PutMapping("/nurses/{id}/update")
    public ResponseEntity<String> updateNurseInfo(@PathVariable Long id, @RequestBody Nurse nurse) {
        // Implement nurse info update logic
        return ResponseEntity.ok("Nurse info updated successfully");
    }
}
