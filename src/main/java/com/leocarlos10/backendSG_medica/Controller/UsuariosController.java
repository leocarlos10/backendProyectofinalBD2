package com.leocarlos10.backendSG_medica.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import com.leocarlos10.backendSG_medica.Models.Usuario;
import com.leocarlos10.backendSG_medica.dto.ApiResponse;
import com.leocarlos10.backendSG_medica.service.UsuarioService;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuariosController extends Controller {

    private final UsuarioService usuarioService;

    /**
     * Registra un nuevo usuario
     * 
     * @param usuario: Datos del usuario a registrar
     * @return: ResponseEntity con ApiResponse
     */
    @PostMapping("/registrar")
    public ResponseEntity<ApiResponse<?>> registrarUsuario(@RequestBody Usuario usuario) {
        logger.info("Registrando nuevo usuario con cédula: {}", usuario.getCedula());

        Usuario usuarioRegistrado = usuarioService.registrarUsuario(usuario);

        ApiResponse<?> response = ApiResponse.success("Usuario registrado exitosamente", usuarioRegistrado);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Realiza el login de un usuario
     * 
     * @param usuario: Datos de login (cédula y contraseña)
     * @return: ResponseEntity con ApiResponse conteniendo token
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> loginUsuario(@RequestBody Usuario usuario) {
        logger.info("Login para usuario: {}", usuario.getCedula());

        Map<String, String> resultado = usuarioService.loginUsuario(usuario);

        ApiResponse<?> response = ApiResponse.success("Login exitoso", resultado);
        return ResponseEntity.ok(response);
    }
}
