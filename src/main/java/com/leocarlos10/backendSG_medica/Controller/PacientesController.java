package com.leocarlos10.backendSG_medica.Controller;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.leocarlos10.backendSG_medica.Models.Paciente;
import com.leocarlos10.backendSG_medica.Models.Usuario;
import com.leocarlos10.backendSG_medica.dto.ApiResponse;
import com.leocarlos10.backendSG_medica.service.PacienteService;


import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
public class PacientesController extends Controller  {

    private final PacienteService pacienteService;

    @GetMapping("/ultima-cita")
    public ResponseEntity<ApiResponse<?>> obtenerPacientesConUltimaCita() {
        List<Paciente> pacientes = pacienteService.obtenerPacientesConUltimaCita();

        ApiResponse<?> response = ApiResponse.success("Pacientes obtenidos exitosamente", pacientes);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/por-estado/{estado}")
    public ResponseEntity<ApiResponse<?>> obtenerPacientesPorEstado(@PathVariable String estado) {
        List<Paciente> pacientes = pacienteService.obtenerPacientesPorEstado(estado);

        ApiResponse<?> response = ApiResponse.success("Pacientes por estado obtenidos", pacientes);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/por-cedula/{cedula}")
    public ResponseEntity<ApiResponse<?>> obtenerPacientePorCedula(@PathVariable String cedula) {
        Usuario paciente = pacienteService.obtenerPacientePorCedula(cedula);

        ApiResponse<?> response = ApiResponse.success("Paciente obtenido exitosamente", paciente);
        return ResponseEntity.ok(response);
    }
}
