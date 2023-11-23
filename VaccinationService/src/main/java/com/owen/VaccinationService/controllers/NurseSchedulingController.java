package com.owen.VaccinationService.controllers;
import com.owen.VaccinationService.entities.NurseScheduling;
import com.owen.VaccinationService.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nursescheduling")
public class NurseSchedulingController {

    @Autowired
    private DatabaseService databaseService;

    @PostMapping("/insert")
    public ResponseEntity<String> insertNurseScheduling(@RequestBody NurseScheduling nurseScheduling) {
        int nurseId = nurseScheduling.getNurseId();
        String timeSlot = nurseScheduling.getTimeSlot();

        // Calling the service method to insert the nurse scheduling
        databaseService.insertNurseScheduling(nurseId, timeSlot);

        return ResponseEntity.ok("Nurse scheduling inserted successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteNurseScheduling(@PathVariable int id) {
        int rowsAffected = databaseService.deleteNurseSchedulingById(id);

        if(rowsAffected > 0) {
            return ResponseEntity.ok("Nurse scheduling deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("No nurse scheduling found with the provided ID.");
        }
    }
}
