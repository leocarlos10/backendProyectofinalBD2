package com.leocarlos10.backendSG_medica.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leocarlos10.backendSG_medica.Models.Paciente;
import com.leocarlos10.backendSG_medica.conexionDAO.UsuarioDAO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/pacientes")
public class PacientesController {
    @Autowired
    private UsuarioDAO paciente;

    @GetMapping("/ultima-cita")
    public List<Paciente> obtenerPacientesConUltimaCita() {
        try {
            return paciente.obtenerPacientesUltimaCita();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            return java.util.Collections.emptyList();
        }
    }

    @GetMapping("/por-estado/{estado}")
public List<Paciente> obtenerPacientesPorEstado(@PathVariable String estado) {
    try {
        return paciente.obtenerPacientesPorEstado(estado);
    } catch (java.sql.SQLException e) {
        e.printStackTrace();
        return java.util.Collections.emptyList();
    }
}

   
    
    
}
