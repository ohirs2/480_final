package com.owen.VaccinationService.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DatabaseService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, List<Map<String, Object>>> getDatabaseSchema() {
        List<String> tableNames = jdbcTemplate.queryForList("SELECT name FROM sqlite_master WHERE type='table'", String.class);
        return tableNames.stream().collect(Collectors.toMap(
                tableName -> tableName,
                tableName -> jdbcTemplate.queryForList("PRAGMA table_info(" + tableName + ")")
        ));
    }
    public List<String> getAllTableNames() {
        String sql = "SELECT name FROM sqlite_master WHERE type='table'";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    public Map<String, List<Map<String, Object>>> getAllDataFromAllTables() {
        List<String> tableNames = getAllTableNames().stream()
                .filter(tableName -> !tableName.equals("sqlite_sequence"))
                .collect(Collectors.toList());

        return tableNames.stream().collect(Collectors.toMap(
                tableName -> tableName,
                tableName -> jdbcTemplate.queryForList("SELECT * FROM " + tableName)
        ));
    }

    public void clearAllDataFromTables() {
        List<String> tableNames = getAllTableNames();

        for (String tableName : tableNames) {
            // Special handling for sqlite_sequence table
            if (tableName.equals("sqlite_sequence")) {
                jdbcTemplate.update("DELETE FROM sqlite_sequence");
            } else {
                jdbcTemplate.update("DELETE FROM " + tableName);
            }
        }
    }

    public List<Map<String, Object>> getAllVaccineData() {
        return jdbcTemplate.queryForList("SELECT * FROM Vaccine");
    }

    public List<Map<String, Object>> getAllVaccineInventoryData() {
        return jdbcTemplate.queryForList("SELECT * FROM VaccineInventory");
    }

    public List<Map<String, Object>> getAllNurseData() {
        return jdbcTemplate.queryForList("SELECT * FROM Nurse");
    }

    public List<Map<String, Object>> getAllPatientData() {
        return jdbcTemplate.queryForList("SELECT * FROM Patient");
    }

    public List<Map<String, Object>> getAllNurseSchedulingData() {
        return jdbcTemplate.queryForList("SELECT * FROM NurseScheduling");
    }

    public List<Map<String, Object>> getAllVaccinationRecordData() {
        return jdbcTemplate.queryForList("SELECT * FROM VaccinationRecord");
    }

    public List<Map<String, Object>> getAllVaccinationSchedulingData() {
        return jdbcTemplate.queryForList("SELECT * FROM VaccinationScheduling");
    }

    public void insertNurse(String firstName, String middleInitial, String lastName, String employeeID, String gender, String phoneNumber, String address) {
        String sql = "INSERT INTO Nurse (FirstName, MiddleInitial, LastName, EmployeeID, Gender, PhoneNumber, Address) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, firstName, middleInitial, lastName, employeeID, gender, phoneNumber, address);
    }

    public void insertVaccine(String name, String company, int dosesRequired, String description) {
        String sql = "INSERT INTO Vaccine (Name, Company, DosesRequired, Description) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, name, company, dosesRequired, description);
    }

    public void insertVaccineInventory(int vaccineId, int quantityAvailable, int quantityOnHold) {
        String sql = "INSERT INTO VaccineInventory (VaccineID, QuantityAvailable, QuantityOnHold) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, vaccineId, quantityAvailable, quantityOnHold);
    }

    public void insertPatient(String firstName, String lastName, String ssn, int age, String gender, String race, String occupationClass, String phoneNumber, String address, String medicalHistory) {
        String sql = "INSERT INTO Patient (FirstName, LastName, SSN, Age, Gender, Race, OccupationClass, PhoneNumber, Address, MedicalHistory) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, firstName, lastName, ssn, age, gender, race, occupationClass, phoneNumber, address, medicalHistory);
    }

    public void insertNurseScheduling(int nurseId, String timeSlot) {
        String sql = "INSERT INTO NurseScheduling (NurseID, TimeSlot) VALUES (?, ?)";
        jdbcTemplate.update(sql, nurseId, timeSlot);
    }

    public void insertVaccinationRecord(int patientId, int vaccineId, int doseNumber, String scheduledTime, int nurseId, String status) {
        String sql = "INSERT INTO VaccinationRecord (PatientID, VaccineID, DoseNumber, ScheduledTime, NurseID, Status) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, patientId, vaccineId, doseNumber, scheduledTime, nurseId, status);
    }

    public void insertVaccinationScheduling(int vaccinationRecordId, String timeSlot) {
        String sql = "INSERT INTO VaccinationScheduling (VaccinationRecordID, TimeSlot) VALUES (?, ?)";
        jdbcTemplate.update(sql, vaccinationRecordId, timeSlot);
    }

    // Delete methods
    public int deleteVaccineById(int vaccineId) {
        String sql = "DELETE FROM Vaccine WHERE VaccineID = ?";
        return jdbcTemplate.update(sql, vaccineId);
    }

    public int deleteVaccineInventoryById(int vaccineInventoryId) {
        String sql = "DELETE FROM VaccineInventory WHERE VaccineInventoryID = ?";
        return jdbcTemplate.update(sql, vaccineInventoryId);
    }

    public int deletePatientById(String ssn) {
        String sql = "DELETE FROM Patient WHERE ssn = ?";
        return jdbcTemplate.update(sql, ssn);
    }

    public int deleteNurseSchedulingById(int nurseSchedulingId) {
        String sql = "DELETE FROM NurseScheduling WHERE NurseSchedulingID = ?";
        return jdbcTemplate.update(sql, nurseSchedulingId);
    }

    public int deleteVaccinationRecordById(int vaccinationRecordId) {
        String sql = "DELETE FROM VaccinationRecord WHERE VaccinationRecordID = ?";
        return jdbcTemplate.update(sql, vaccinationRecordId);
    }

    public int deleteVaccinationSchedulingById(int vaccinationSchedulingId) {
        String sql = "DELETE FROM VaccinationScheduling WHERE VaccinationSchedulingID = ?";
        return jdbcTemplate.update(sql, vaccinationSchedulingId);
    }

    public int deleteNurseById(String nurseId) {
        String sql = "DELETE FROM Nurse WHERE EmployeeID = ?";
        return jdbcTemplate.update(sql, nurseId);
    }


}
