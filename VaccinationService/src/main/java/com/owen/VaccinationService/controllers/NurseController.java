package com.owen.VaccinationService.controllers;

import com.owen.VaccinationService.entities.Nurse;
import com.owen.VaccinationService.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nurse")
public class NurseController {

    @Autowired
    private DatabaseService databaseService;

    @PostMapping("/insert")
    public ResponseEntity<String> insertNurse(@RequestBody Nurse nurse) {
        String firstName = nurse.getFirstName();
        String middleInitial = nurse.getMiddleInitial();
        String lastName = nurse.getLastName();
        String employeeID = nurse.getEmployeeID();
        String gender = nurse.getGender();
        String phoneNumber = nurse.getPhoneNumber();
        String address = nurse.getAddress();

        // Calling the service method to insert the nurse
        databaseService.insertNurse(firstName, middleInitial, lastName, employeeID, gender, phoneNumber, address);

        return ResponseEntity.ok("Nurse inserted successfully");
    }

    @DeleteMapping("/delete/{id}")
    public String deleteNurse(@PathVariable String id) {
        // Removed the redundant declaration of nurseId since it's already being passed as a method parameter
        int rowsAffected = databaseService.deleteNurseById(id);

        if(rowsAffected > 0) {
            System.out.println("Nurse deleted successfully.");
            return "Nurse deleted successfully."; // Return a response
        } else {
            System.out.println("No nurse found with the provided ID.");
            return "No nurse found with the provided ID."; // Return a response
        }
    }
}
