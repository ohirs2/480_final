package com.owen.VaccinationService.controllers;

import com.owen.VaccinationService.entities.Vaccine;
import com.owen.VaccinationService.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vaccine")
public class VaccineController {

    @Autowired
    private DatabaseService databaseService;

    @PostMapping("/insert")
    public ResponseEntity<String> insertVaccine(@RequestBody Vaccine vaccine) {
        String name = vaccine.getName();
        String company = vaccine.getCompany();
        int dosesRequired = vaccine.getDosesRequired();
        String description = vaccine.getDescription();

        // Calling the service method to insert the vaccine
        databaseService.insertVaccine(name, company, dosesRequired, description);

        return ResponseEntity.ok("Vaccine inserted successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVaccine(@PathVariable int id) {
        int rowsAffected = databaseService.deleteVaccineById(id);

        if(rowsAffected > 0) {
            return ResponseEntity.ok("Vaccine deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("No vaccine found with the provided ID.");
        }
    }
}
