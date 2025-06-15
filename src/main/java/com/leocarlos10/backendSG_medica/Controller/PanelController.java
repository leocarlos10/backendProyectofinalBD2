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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Map;



@RestController
@RequestMapping("/api/panel")

public class PanelController extends Controller{
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
    public ResponseEntity<?> obtenerCitasProximas() {
        try {
            List<CitasPanel> citas = panelDAO.obtenerCitasProximas();
            if(!citas.isEmpty()){
                return ResponseHttp(HttpStatus.OK, Map.of("citas", citas));
            }
            return ResponseHttp(HttpStatus.NOT_FOUND, Map.of("mensaje", "No se encontraron citas"));
        } catch (Exception e) {
            System.out.println("error obtenerCitasProximas-PanelController" + e);
            return ResponseHttp(HttpStatus.INTERNAL_SERVER_ERROR, Map.of("mensaje", "No hay citas Proximas"));
        }
    }

    @GetMapping("/citas-hoy")
    public List<CitasPanel> obtenerCitasHoy() {
        return panelDAO.obtenerHoy();
    }

    @GetMapping("/diagnosticos")
    public ResponseEntity<?> obtenerDiagnosticos() {
        try {
            List<DiagnosticoPanel> diagnosticos = panelDAO.obtenerDiagnosticos();
            if(!diagnosticos.isEmpty()){
                return ResponseHttp(HttpStatus.OK, Map.of("diagnosticos", diagnosticos));
            }
            return ResponseHttp(HttpStatus.NOT_FOUND, Map.of("mensaje", "No hay diagnosticos"));
        } catch (Exception e) {
            System.out.println("error obtenerDiagnosticos-PanelController" + e);
            return ResponseHttp(HttpStatus.INTERNAL_SERVER_ERROR, Map.of("mensaje", "No hay diagnosticos"));
        }
    }

}
