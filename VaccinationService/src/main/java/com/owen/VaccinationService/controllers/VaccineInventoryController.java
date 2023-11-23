package com.owen.VaccinationService.controllers;
import com.owen.VaccinationService.entities.VaccineInventory;
import com.owen.VaccinationService.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vaccineinventory")
public class VaccineInventoryController {

    @Autowired
    private DatabaseService databaseService;


    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllVaccineInventoryData() {
        return ResponseEntity.ok(databaseService.getAllVaccineInventoryData());
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insertVaccineInventory(@RequestBody VaccineInventory vaccineInventory) {
        int vaccineId = vaccineInventory.getVaccineId();
        int quantityAvailable = vaccineInventory.getQuantityAvailable();
        int quantityOnHold = vaccineInventory.getQuantityOnHold();

        // Calling the service method to insert the vaccine inventory
        databaseService.insertVaccineInventory(vaccineId, quantityAvailable, quantityOnHold);

        return ResponseEntity.ok("Vaccine inventory inserted successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVaccineInventory(@PathVariable int id) {
        int rowsAffected = databaseService.deleteVaccineInventoryById(id);

        if(rowsAffected > 0) {
            return ResponseEntity.ok("Vaccine inventory deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("No vaccine inventory found with the provided ID.");
        }
    }
}
