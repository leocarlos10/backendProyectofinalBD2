package com.leocarlos10.backendSG_medica.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.leocarlos10.backendSG_medica.Models.CitasPanel;
import com.leocarlos10.backendSG_medica.Models.DiagnosticoPanel;
import com.leocarlos10.backendSG_medica.conexionDAO.PanelDAO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;



@RestController
@RequestMapping("/panel")

public class PanelController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PanelDAO panelDAO;

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

    @GetMapping("/citas-proximas")
    public List<CitasPanel> obtenerCitasProximas() {
        return panelDAO.obtenerCitasProximas();
    }

    @GetMapping("/citas-hoy")
    public List<CitasPanel> obtenerCitasHoy() {
        return panelDAO.obtenerHoy();
    }

    @GetMapping("/diagnosticos")
    public List<DiagnosticoPanel> obtenerDiagnosticos() {
        return panelDAO.obtenerDiagnosticos();
    }

}
