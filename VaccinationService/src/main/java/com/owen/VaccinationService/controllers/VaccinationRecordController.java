package com.owen.VaccinationService.controllers;
import com.owen.VaccinationService.entities.VaccinationRecord;
import com.owen.VaccinationService.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vaccinationrecord")
public class VaccinationRecordController {

    @Autowired
    private DatabaseService databaseService;

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllVaccinationRecordData() {
        return ResponseEntity.ok(databaseService.getAllVaccinationRecordData());
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insertVaccinationRecord(@RequestBody VaccinationRecord vaccinationRecord) {
        int patientId = vaccinationRecord.getPatientId();
        int vaccineId = vaccinationRecord.getVaccineId();
        int doseNumber = vaccinationRecord.getDoseNumber();
        String scheduledTime = vaccinationRecord.getScheduledTime();
        int nurseId = vaccinationRecord.getNurseId();
        String status = vaccinationRecord.getStatus();

        // Calling the service method to insert the vaccination record
        databaseService.insertVaccinationRecord(patientId, vaccineId, doseNumber, scheduledTime, nurseId, status);

        return ResponseEntity.ok("Vaccination record inserted successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVaccinationRecord(@PathVariable int id) {
        int rowsAffected = databaseService.deleteVaccinationRecordById(id);

        if(rowsAffected > 0) {
            return ResponseEntity.ok("Vaccination record deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("No vaccination record found with the provided ID.");
        }
    }
}
