package com.leocarlos10.backendSG_medica.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leocarlos10.backendSG_medica.Models.Paciente;
import com.leocarlos10.backendSG_medica.Models.Usuario;
import com.leocarlos10.backendSG_medica.conexionDAO.UsuarioDAO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;




@RestController
@RequestMapping("/api/pacientes")
public class PacientesController extends Controller{
    @Autowired
    private UsuarioDAO pacienteDAO;

    @GetMapping("/ultima-cita")
    public ResponseEntity<?> obtenerPacientesConUltimaCita() {
        try {
            List<Paciente> pacientes = pacienteDAO.obtenerPacientesUltimaCita();
            if(!pacientes.isEmpty()){
                return ResponseHttp(HttpStatus.OK, Map.of("pacientes", pacientes));
            } else{
                return ResponseHttp(HttpStatus.NOT_FOUND, Map.of("mensaje", "No se encontraron pacientes"));
            }
        } catch (java.sql.SQLException e) {
            System.out.println("error obtenerPacientesConUltimaCita-PacientesController" + e);
            return ResponseHttp(HttpStatus.INTERNAL_SERVER_ERROR, Map.of("mensaje", "Error al obtener los pacientes"));
        }
    }

    @GetMapping("/por-estado/{estado}")
    public List<Paciente> obtenerPacientesPorEstado(@PathVariable String estado) {
        try {
            return pacienteDAO.obtenerPacientesPorEstado(estado);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            return java.util.Collections.emptyList();
        }
    }

    @GetMapping("/por-cedula/{cedula}")
    public ResponseEntity<?> obtenerPacientePorCedula(@PathVariable String cedula) {
        try {
            Usuario paciente = pacienteDAO.obtenerPorId(cedula);
            if(paciente != null){
                return ResponseHttp(HttpStatus.OK, Map.of("paciente", paciente));
            } else{
                return ResponseHttp(HttpStatus.NOT_FOUND, Map.of("mensaje", "Paciente no encontrado"));
            }
        } catch (Exception e) {
            System.out.println("error obtenerPacientePorCedula-PacientesController" + e);
            return ResponseHttp(HttpStatus.INTERNAL_SERVER_ERROR, Map.of("mensaje", "Error al obtener el paciente"));
        }
    }
}
