package com.leocarlos10.backendSG_medica.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.leocarlos10.backendSG_medica.Models.Cita;
import com.leocarlos10.backendSG_medica.Models.CitaUsuarioDTO;
import com.leocarlos10.backendSG_medica.dto.ApiResponse;
import com.leocarlos10.backendSG_medica.jwt.JWTUtil;
import com.leocarlos10.backendSG_medica.service.CitaService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
@RequiredArgsConstructor
public class CitaController extends Controller  {

    private final CitaService citaService;
    private final JWTUtil jwt;

    @PostMapping("/registrar")
    public ResponseEntity<ApiResponse<?>> registrarCita(@RequestBody Cita cita,
            @RequestHeader(value = "Authorization") String token) {
        jwt.validarToken(token);

        Cita citaRegistrada = citaService.registrarCita(cita);

        ApiResponse<?> response = ApiResponse.success("Cita registrada correctamente", citaRegistrada);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/obtener-todas")
    public ResponseEntity<ApiResponse<?>> citas() {
        List<Cita> citas = citaService.obtenerTodasLasCitas();

        ApiResponse<?> response = ApiResponse.success("Citas obtenidas exitosamente", citas);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/papelera")
    public ResponseEntity<ApiResponse<?>> obtenerPapeleraCitas() {
        List<Cita> citas = citaService.obtenerPapeleraCitas();

        ApiResponse<?> response = ApiResponse.success("Citas de papelera obtenidas", citas);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> obtenerPorId(@PathVariable int id) {
        Cita cita = citaService.obtenerPorId(id);

        ApiResponse<?> response = ApiResponse.success("Cita obtenida exitosamente", cita);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<ApiResponse<?>> actualizarCita(@RequestBody Cita cita) {
        Cita citaActualizada = citaService.actualizarCita(cita);

        ApiResponse<?> response = ApiResponse.success("Cita actualizada correctamente", citaActualizada);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ApiResponse<?>> eliminarCita(@PathVariable int id) {
        citaService.eliminarCita(id);

        ApiResponse<?> response = ApiResponse.success("Cita eliminada correctamente", null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/con-usuario")
    public ResponseEntity<ApiResponse<?>> obtenerCitasConUsuario() {
        List<CitaUsuarioDTO> citas = citaService.obtenerCitasConUsuario();

        ApiResponse<?> response = ApiResponse.success("Citas con usuario obtenidas", citas);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-cita-usuario/{cedula}")
    public ResponseEntity<ApiResponse<?>> obtenerCitaPorUsuario(@PathVariable String cedula) {
        List<Cita> citas = citaService.obtenerCitasPorUsuario(cedula);

        ApiResponse<?> response = ApiResponse.success("Citas del usuario obtenidas", citas);
        return ResponseEntity.ok(response);
    }
}
