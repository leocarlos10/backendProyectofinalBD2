package com.leocarlos10.backendSG_medica.Controller;

import com.leocarlos10.backendSG_medica.Models.Diagnostico;
import com.leocarlos10.backendSG_medica.dto.diagnostico.CrearDiagnosticoDTO;
import com.leocarlos10.backendSG_medica.dto.respuestasComunes.Response;
import com.leocarlos10.backendSG_medica.dto.usuario.UsuarioDiagnosticoDTO;
import com.leocarlos10.backendSG_medica.service.DiagnosticoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/diagnostico")
@RequiredArgsConstructor
public class DiagnosticoController extends Controller {

    protected final DiagnosticoService diagnosticoService;

    @GetMapping("/con-usuario")
    public ResponseEntity<Response<List<UsuarioDiagnosticoDTO>>> obtenerDiagnosticosConUsuario() {
        List<UsuarioDiagnosticoDTO> diagnosticos = diagnosticoService.obtenerDiagnosticosConUsuario();
        Response<List<UsuarioDiagnosticoDTO>> response = Response.<List<UsuarioDiagnosticoDTO>>builder()
                .responseCode(200)
                .responseMessage("Diagnósticos obtenidos exitosamente")
                .data(diagnosticos)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/por-usuario/{cedula}")
    public ResponseEntity<Response<List<UsuarioDiagnosticoDTO>>> obtenerDiagnosticosPorUsuario(
            @PathVariable String cedula) {
        List<UsuarioDiagnosticoDTO> diagnosticos = diagnosticoService.obtenerDiagnosticosPorUsuario(cedula);
        Response<List<UsuarioDiagnosticoDTO>> response = Response.<List<UsuarioDiagnosticoDTO>>builder()
                .responseCode(200)
                .responseMessage("Diagnósticos del usuario obtenidos")
                .data(diagnosticos)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/por-historia/{idHistoria}")
    public ResponseEntity<Response<List<Diagnostico>>> obtenerDiagnosticosPorHistoria(@PathVariable int idHistoria) {
        List<Diagnostico> diagnosticos = diagnosticoService.obtenerDiagnosticosPorHistoria(idHistoria);
        Response<List<Diagnostico>> response = Response.<List<Diagnostico>>builder()
                .responseCode(200)
                .responseMessage("Diagnósticos de la historia obtenidos")
                .data(diagnosticos)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/obtener-todos")
    public ResponseEntity<Response<List<Diagnostico>>> obtenerTodos() {
        List<Diagnostico> diagnosticos = diagnosticoService.obtenerTodos();
        Response<List<Diagnostico>> response = Response.<List<Diagnostico>>builder()
                .responseCode(200)
                .responseMessage("Diagnósticos obtenidos exitosamente")
                .data(diagnosticos)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Diagnostico>> obtenerPorId(@PathVariable Integer id) {
        Diagnostico diagnostico = diagnosticoService.obtenerPorId(id);
        Response<Diagnostico> response = Response.<Diagnostico>builder()
                .responseCode(200)
                .responseMessage("Diagnóstico obtenido exitosamente")
                .data(diagnostico)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response<Diagnostico>> registrar(@Valid @RequestBody Diagnostico diagnostico) {
        Diagnostico diagnosticoRegistrado = diagnosticoService.registrarDiagnostico(diagnostico);
        Response<Diagnostico> response = Response.<Diagnostico>builder()
                .responseCode(201)
                .responseMessage("Diagnóstico registrado correctamente")
                .data(diagnosticoRegistrado)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<Response<Diagnostico>> actualizar(@Valid @RequestBody Diagnostico diagnostico) {
        Diagnostico diagnosticoActualizado = diagnosticoService.actualizarDiagnostico(diagnostico);
        Response<Diagnostico> response = Response.<Diagnostico>builder()
                .responseCode(200)
                .responseMessage("Diagnóstico actualizado correctamente")
                .data(diagnosticoActualizado)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Response<Void>> eliminar(@PathVariable Integer id) {
        diagnosticoService.eliminarDiagnostico(id);
        Response<Void> response = Response.<Void>builder()
                .responseCode(200)
                .responseMessage("Diagnóstico eliminado correctamente")
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/crear-con-historia")
    public ResponseEntity<Response<Integer>> crearDiagnosticoConHistoria(@Valid @RequestBody CrearDiagnosticoDTO dto) {
        int resultado = diagnosticoService.crearDiagnosticoConHistoria(
                dto.getCedulaUsuario(),
                dto.getTratamiento(),
                dto.getObservaciones(),
                dto.getNotaCorta(),
                dto.getNotaLarga(),
                dto.getFecha());
        Response<Integer> response = Response.<Integer>builder()
                .responseCode(201)
                .responseMessage("Diagnóstico con historia creado correctamente")
                .data(resultado)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}