package com.leocarlos10.backendSG_medica.Controller;

import com.leocarlos10.backendSG_medica.Models.Diagnostico;
import com.leocarlos10.backendSG_medica.Models.UsuarioDiagnosticoDTO;
import com.leocarlos10.backendSG_medica.conexionDAO.DiagnosticoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/diagnostico")
public class DiagnosticoController {

    @Autowired
    private DiagnosticoDAO diagnosticoDAO;

    @GetMapping("/con-usuario")
    public List<UsuarioDiagnosticoDTO> obtenerDiagnosticosConUsuario() {
        try {
            return diagnosticoDAO.obtenerDiagnosticosConUsuario();
        } catch (Exception e) {
            e.printStackTrace();
            return java.util.Collections.emptyList();
        }
    }

    @GetMapping("/por-usuario/{cedula}")
    public List<UsuarioDiagnosticoDTO> obtenerDiagnosticosPorUsuario(@PathVariable String cedula) {
        try {
            return diagnosticoDAO.obtenerDiagnosticosPorUsuario(cedula);
        } catch (Exception e) {
            e.printStackTrace();
            return java.util.Collections.emptyList();
        }
    }

    @GetMapping("/por-historia/{idHistoria}")
    public List<Diagnostico> obtenerDiagnosticosPorHistoria(@PathVariable int idHistoria) {
        try {
            return diagnosticoDAO.obtenerDiagnosticosPorHistoria(idHistoria);
        } catch (Exception e) {
            e.printStackTrace();
            return java.util.Collections.emptyList();
        }
    }

    @GetMapping
    public List<Diagnostico> obtenerTodos() {
        try {
            return diagnosticoDAO.obtenerTodo();
        } catch (Exception e) {
            e.printStackTrace();
            return java.util.Collections.emptyList();
        }
    }

    @GetMapping("/{id}")
    public Diagnostico obtenerPorId(@PathVariable Integer id) {
        try {
            return diagnosticoDAO.obtenerPorId(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping
    public int registrar(@RequestBody Diagnostico diagnostico) {
        try {
            return diagnosticoDAO.registrar(diagnostico);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @PutMapping
    public int actualizar(@RequestBody Diagnostico diagnostico) {
        try {
            return diagnosticoDAO.actualizar(diagnostico);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @DeleteMapping("/{id}")
    public int eliminar(@PathVariable Integer id) {
        try {
            return diagnosticoDAO.eliminar(id);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}