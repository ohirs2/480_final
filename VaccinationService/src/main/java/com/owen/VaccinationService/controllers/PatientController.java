package com.owen.VaccinationService.controllers;

import com.owen.VaccinationService.entities.Patient;
import com.owen.VaccinationService.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private DatabaseService databaseService;

    @PostMapping("/insert")
    public ResponseEntity<String> insertPatient(@RequestBody Patient patient) {
        String firstName = patient.getFirstName();
        String lastName = patient.getLastName();
        String ssn = patient.getSsn();
        int age = patient.getAge();
        String gender = patient.getGender();
        String race = patient.getRace();
        String occupationClass = patient.getOccupationClass();
        String phoneNumber = patient.getPhoneNumber();
        String address = patient.getAddress();
        String medicalHistory = patient.getMedicalHistory();

        // Calling the service method to insert the patient
        databaseService.insertPatient(firstName, lastName, ssn, age, gender, race, occupationClass, phoneNumber, address, medicalHistory);

        return ResponseEntity.ok("Patient inserted successfully");
    }

    @DeleteMapping("/delete/{ssn}")
    public ResponseEntity<String> deletePatient(@PathVariable String ssn) {
        int rowsAffected = databaseService.deletePatientById(ssn);

        if(rowsAffected > 0) {
            return ResponseEntity.ok("Patient deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("No patient found with the provided SSN.");
        }
    }
}
