package com.leocarlos10.backendSG_medica.Controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.leocarlos10.backendSG_medica.Models.Pediatra;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import com.leocarlos10.backendSG_medica.conexionDAO.PediatraDAO;
import com.leocarlos10.backendSG_medica.dto.respuestasComunes.Response;

@RestController
@RequestMapping("/api/pediatra")
@RequiredArgsConstructor
public class PediatraController extends Controller {

    private final PediatraDAO pediatraDAO;

    /**
     * Login de pediatra - Se mantiene con DAO directo temporalmente
     * Puede refactorizarse a un PediatraService en el futuro
     */
    @PostMapping("/login")
    public ResponseEntity<Response<Map<String, String>>> login(@Valid @RequestBody Pediatra pediatra) {
        try {
            Pediatra ped = pediatraDAO.obtenerPediatra(pediatra);
            if (ped != null) {
                Map<String, String> data = new HashMap<>();
                data.put("usuario", ped.getUsuario());

                Response<Map<String, String>> response = Response.<Map<String, String>>builder()
                        .responseCode(200)
                        .responseMessage("Login de pediatra exitoso")
                        .data(data)
                        .build();
                return ResponseEntity.ok(response);
            } else {
                throw new com.leocarlos10.backendSG_medica.exception.ValidationException(
                        "Usuario o contraseña incorrectos");
            }
        } catch (Exception e) {
            logger.error("Error en login de pediatra: {}", e.getMessage(), e);
            throw new com.leocarlos10.backendSG_medica.exception.BusinessException("LOGIN_ERROR",
                    "Error al iniciar sesión", e);
        }
    }
}
