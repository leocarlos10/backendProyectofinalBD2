package com.leocarlos10.backendSG_medica.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.leocarlos10.backendSG_medica.Models.Usuario;



@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController extends Controller {

  
    
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.registrarUsuario(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody Usuario usuario) {
        return usuarioService.loginUsuario(usuario);
    }
}
