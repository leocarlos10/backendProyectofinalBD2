package com.leocarlos10.backendSG_medica.Controller;

import com.leocarlos10.backendSG_medica.Models.CrearDiagnosticoDTO;
import com.leocarlos10.backendSG_medica.Models.Diagnostico;
import com.leocarlos10.backendSG_medica.Models.UsuarioDiagnosticoDTO;
import com.leocarlos10.backendSG_medica.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/diagnostico")
public class DiagnosticoController extends Controller {

    @GetMapping("/con-usuario")
    public ResponseEntity<ApiResponse<?>> obtenerDiagnosticosConUsuario() {
        List<UsuarioDiagnosticoDTO> diagnosticos = diagnosticoService.obtenerDiagnosticosConUsuario();
        ApiResponse<?> response = ApiResponse.success("Diagnósticos obtenidos exitosamente", diagnosticos);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/por-usuario/{cedula}")
    public ResponseEntity<ApiResponse<?>> obtenerDiagnosticosPorUsuario(@PathVariable String cedula) {
        List<UsuarioDiagnosticoDTO> diagnosticos = diagnosticoService.obtenerDiagnosticosPorUsuario(cedula);
        ApiResponse<?> response = ApiResponse.success("Diagnósticos del usuario obtenidos", diagnosticos);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/por-historia/{idHistoria}")
    public ResponseEntity<ApiResponse<?>> obtenerDiagnosticosPorHistoria(@PathVariable int idHistoria) {
        List<Diagnostico> diagnosticos = diagnosticoService.obtenerDiagnosticosPorHistoria(idHistoria);
        ApiResponse<?> response = ApiResponse.success("Diagnósticos de la historia obtenidos", diagnosticos);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/obtener-todos")
    public ResponseEntity<ApiResponse<?>> obtenerTodos() {
        List<Diagnostico> diagnosticos = diagnosticoService.obtenerTodos();
        ApiResponse<?> response = ApiResponse.success("Diagnósticos obtenidos exitosamente", diagnosticos);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> obtenerPorId(@PathVariable Integer id) {
        Diagnostico diagnostico = diagnosticoService.obtenerPorId(id);
        ApiResponse<?> response = ApiResponse.success("Diagnóstico obtenido exitosamente", diagnostico);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> registrar(@RequestBody Diagnostico diagnostico) {
        Diagnostico diagnosticoRegistrado = diagnosticoService.registrarDiagnostico(diagnostico);
        ApiResponse<?> response = ApiResponse.success("Diagnóstico registrado correctamente", diagnosticoRegistrado);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<?>> actualizar(@RequestBody Diagnostico diagnostico) {
        Diagnostico diagnosticoActualizado = diagnosticoService.actualizarDiagnostico(diagnostico);
        ApiResponse<?> response = ApiResponse.success("Diagnóstico actualizado correctamente", diagnosticoActualizado);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ApiResponse<?>> eliminar(@PathVariable Integer id) {
        diagnosticoService.eliminarDiagnostico(id);
        ApiResponse<?> response = ApiResponse.success("Diagnóstico eliminado correctamente", null);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/crear-con-historia")
    public ResponseEntity<ApiResponse<?>> crearDiagnosticoConHistoria(@RequestBody CrearDiagnosticoDTO dto) {
        int resultado = diagnosticoService.crearDiagnosticoConHistoria(
                dto.getCedulaUsuario(),
                dto.getTratamiento(),
                dto.getObservaciones(),
                dto.getNotaCorta(),
                dto.getNotaLarga(),
                dto.getFecha());
        ApiResponse<?> response = ApiResponse.success("Diagnóstico con historia creado correctamente", resultado);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}