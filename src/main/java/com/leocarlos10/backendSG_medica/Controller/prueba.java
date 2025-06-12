package com.leocarlos10.backendSG_medica.Controller;

import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


@RestController
@RequestMapping("/api")
public class prueba {

     @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/usuarios")
    public List<String> getUsuarios() {
        return List.of("usuario1", "usuario2", "usuario3", "usuario4", "usuario5");
    }

    @GetMapping("/test")
    public String test() {
        return "Prueba exitosa";
    }

    @GetMapping("/db-test")
    public String dbTest() {
        try {
        jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            // Si la consulta se ejecuta correctamente, significa que la conexión es exitosa
        return "Conexión a la base de datos exitosa";
        } catch (Exception e) {
            return "Conexión a la base de datos fallida: " + e.getMessage();
        }
    }
    
}

