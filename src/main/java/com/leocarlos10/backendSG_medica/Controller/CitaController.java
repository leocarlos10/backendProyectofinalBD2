package com.leocarlos10.backendSG_medica.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.leocarlos10.backendSG_medica.Models.Cita;
import com.leocarlos10.backendSG_medica.conexionDAO.CitaDAO;

import java.util.List;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;




@RestController
@RequestMapping("/citas")


public class CitaController {

    @Autowired
    private CitaDAO citaDAO;

    @GetMapping("/obtener-todas")
    public List<Cita> citas(){
        try {
            return citaDAO.obtenerTodo();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            return java.util.Collections.emptyList();
        }
    }

    @GetMapping("/papelera")
    public List<Cita> obtenerPapeleraCitas() {
        try {
            return citaDAO.obtenerPapeleraCita();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            return java.util.Collections.emptyList();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> obtenerPorId(@PathVariable int id) {
        try {
            Cita cita = citaDAO.obtenerPorId(id);
            return ResponseEntity.ok(cita);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarCita(@RequestBody Cita cita) {
        try {
            int filas = citaDAO.actualizar(cita);
            if (filas > 0) {
                return ResponseEntity.ok("Cita actualizada correctamente");
            } else {
                return ResponseEntity.badRequest().body("No se pudo actualizar la cita");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarCita(@PathVariable int id) {
        try {
            int filas = citaDAO.eliminar(id);
            if (filas > 0) {
                return ResponseEntity.ok("Cita eliminada correctamente");
            } else {
                return ResponseEntity.badRequest().body("No se pudo eliminar la cita");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

}
