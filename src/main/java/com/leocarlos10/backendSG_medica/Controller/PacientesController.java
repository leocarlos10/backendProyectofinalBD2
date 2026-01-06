package com.leocarlos10.backendSG_medica.Controller;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.leocarlos10.backendSG_medica.Models.Paciente;
import com.leocarlos10.backendSG_medica.Models.Usuario;
import com.leocarlos10.backendSG_medica.dto.respuestasComunes.Response;
import com.leocarlos10.backendSG_medica.service.PacienteService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
public class PacientesController extends Controller {

    private final PacienteService pacienteService;

    @GetMapping("/ultima-cita")
    public ResponseEntity<Response<List<Paciente>>> obtenerPacientesConUltimaCita() {
        List<Paciente> pacientes = pacienteService.obtenerPacientesConUltimaCita();

        Response<List<Paciente>> response = Response.<List<Paciente>>builder()
                .responseCode(200)
                .responseMessage("Pacientes obtenidos exitosamente")
                .data(pacientes)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/por-estado/{estado}")
    public ResponseEntity<Response<List<Paciente>>> obtenerPacientesPorEstado(@PathVariable String estado) {
        List<Paciente> pacientes = pacienteService.obtenerPacientesPorEstado(estado);

        Response<List<Paciente>> response = Response.<List<Paciente>>builder()
                .responseCode(200)
                .responseMessage("Pacientes por estado obtenidos")
                .data(pacientes)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/por-cedula/{cedula}")
    public ResponseEntity<Response<Usuario>> obtenerPacientePorCedula(@PathVariable String cedula) {
        Usuario paciente = pacienteService.obtenerPacientePorCedula(cedula);

        Response<Usuario> response = Response.<Usuario>builder()
                .responseCode(200)
                .responseMessage("Paciente obtenido exitosamente")
                .data(paciente)
                .build();
        return ResponseEntity.ok(response);
    }
}
