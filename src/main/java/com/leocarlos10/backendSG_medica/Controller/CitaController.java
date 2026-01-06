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
import jakarta.validation.Valid;
import com.leocarlos10.backendSG_medica.Models.Cita;
import com.leocarlos10.backendSG_medica.dto.cita.CitaUsuarioDTO;
import com.leocarlos10.backendSG_medica.dto.respuestasComunes.Response;
import com.leocarlos10.backendSG_medica.jwt.JWTUtil;
import com.leocarlos10.backendSG_medica.service.CitaService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
@RequiredArgsConstructor
public class CitaController extends Controller {

    private final CitaService citaService;
    private final JWTUtil jwt;

    @PostMapping("/registrar")
    public ResponseEntity<Response<Cita>> registrarCita(@Valid @RequestBody Cita cita,
            @RequestHeader(value = "Authorization") String token) {
        jwt.validarToken(token);

        Cita citaRegistrada = citaService.registrarCita(cita);

        Response<Cita> response = Response.<Cita>builder()
                .responseCode(201)
                .responseMessage("Cita registrada correctamente")
                .data(citaRegistrada)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/obtener-todas")
    public ResponseEntity<Response<List<Cita>>> citas() {
        List<Cita> citas = citaService.obtenerTodasLasCitas();

        Response<List<Cita>> response = Response.<List<Cita>>builder()
                .responseCode(200)
                .responseMessage("Citas obtenidas exitosamente")
                .data(citas)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/papelera")
    public ResponseEntity<Response<List<Cita>>> obtenerPapeleraCitas() {
        List<Cita> citas = citaService.obtenerPapeleraCitas();

        Response<List<Cita>> response = Response.<List<Cita>>builder()
                .responseCode(200)
                .responseMessage("Citas de papelera obtenidas")
                .data(citas)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Cita>> obtenerPorId(@PathVariable int id) {
        Cita cita = citaService.obtenerPorId(id);

        Response<Cita> response = Response.<Cita>builder()
                .responseCode(200)
                .responseMessage("Cita obtenida exitosamente")
                .data(cita)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Response<Boolean>> actualizarCita(@Valid @RequestBody Cita cita) {
        boolean estado = citaService.actualizarCita(cita);

        Response<Boolean> response = Response.<Boolean>builder()
                .responseCode(200)
                .responseMessage("Cita actualizada correctamente")
                .data(estado)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Response<Boolean>> eliminarCita(@PathVariable int id) {
       boolean estado = citaService.eliminarCita(id);

        Response<Boolean> response = Response.<Boolean>builder()
                .responseCode(200)
                .responseMessage("Cita eliminada correctamente")
                .data(estado)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/con-usuario")
    public ResponseEntity<Response<List<CitaUsuarioDTO>>> obtenerCitasConUsuario() {
        List<CitaUsuarioDTO> citas = citaService.obtenerCitasConUsuario();

        Response<List<CitaUsuarioDTO>> response = Response.<List<CitaUsuarioDTO>>builder()
                .responseCode(200)
                .responseMessage("Citas con usuario obtenidas")
                .data(citas)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-cita-usuario/{cedula}")
    public ResponseEntity<Response<List<Cita>>> obtenerCitaPorUsuario(@PathVariable String cedula) {
        List<Cita> citas = citaService.obtenerCitasPorUsuario(cedula);

        Response<List<Cita>> response = Response.<List<Cita>>builder()
                .responseCode(200)
                .responseMessage("Citas del usuario obtenidas")
                .data(citas)
                .build();
        return ResponseEntity.ok(response);
    }
}
