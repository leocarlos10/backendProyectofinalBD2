package com.leocarlos10.backendSG_medica.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import com.leocarlos10.backendSG_medica.Models.Usuario;
import com.leocarlos10.backendSG_medica.dto.respuestasComunes.ApiResponse;
import com.leocarlos10.backendSG_medica.dto.respuestasComunes.Response;
import com.leocarlos10.backendSG_medica.dto.usuario.LoginRequest;
import com.leocarlos10.backendSG_medica.dto.usuario.LoginResponse;
import com.leocarlos10.backendSG_medica.service.UsuarioService;

import jakarta.validation.Valid;
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
    public ResponseEntity<ApiResponse<?>> registrarUsuario(@Valid @RequestBody Usuario usuario) {

        Usuario usuarioRegistrado = usuarioService.registrarUsuario(usuario);

        ApiResponse<Usuario> response = ApiResponse.success("Usuario registrado exitosamente", usuarioRegistrado);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Realiza el login de un usuario
     * 
     * @param usuario: Datos de login (cédula y contraseña)
     * @return: ResponseEntity con ApiResponse conteniendo token
     */
    @PostMapping("/login")
    public ResponseEntity<Response<LoginResponse>> loginUsuario(@Valid @RequestBody LoginRequest request) {
        LoginResponse loginResponse = usuarioService.loginUsuario(request);
        Response<LoginResponse> response = Response.<LoginResponse>builder()
                .responseCode(200)
                .responseMessage("Login exitoso")
                .data(loginResponse)
                .build();
        return ResponseEntity.ok(response);
    }
}
