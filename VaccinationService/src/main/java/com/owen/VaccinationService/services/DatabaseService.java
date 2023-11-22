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
}
