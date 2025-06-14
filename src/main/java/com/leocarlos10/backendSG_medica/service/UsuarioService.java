package com.leocarlos10.backendSG_medica.service;

import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import com.leocarlos10.backendSG_medica.Models.Usuario;

import java.util.HashMap;

import org.springframework.http.HttpStatus;

@Service
public class UsuarioService extends service {


    /**
     * Este metodo es el encargado de registrar un usuario en la base de datos
     * @param usuario: El usuario a registrar
     * @return: Un objeto de tipo ResponseEntity que contiene el resultado de la operación
     */
    public ResponseEntity<?> registrarUsuario(Usuario usuario) {
        
        try {
            int resultado = usuarioDAO.registrar(usuario);
            if(resultado > 0 ){
                return ResponseHttp( HttpStatus.OK, respuesta("mensaje", "Usuario registrado correctamente"));
            } else{
                return ResponseHttp(HttpStatus.BAD_REQUEST, respuesta("mensaje", "Error al registrar usuario"));
            }
        } catch (Exception e) {
            System.out.println("error registrarUsuario-UsuarioService" + e);
            return ResponseHttp(HttpStatus.INTERNAL_SERVER_ERROR, respuesta("mensaje", "Error al registrar usuario"));
           
        }
    }

    /**
     * Este metodo es el encargado de realizar el inicio de sesion del usuario
     * @param usuario: El usuario a iniciar sesion
     * @return: Un objeto de tipo ResponseEntity que contiene el resultado de la operación
     * este objeto puede ser el token(200), bad request(400) o un error(500.
     */
    public ResponseEntity<?> loginUsuario(Usuario usuario) {
        
        try {
            Usuario user = usuarioDAO.obtenerPorId(usuario.getCedula());
            if(user != null){
                if(usuario.getPass().equals(user.getPass())){
                    // instancio el response aqui porque necesito mandar el token y el nombre del usuario
                    response = new HashMap<>();
                    String token = jwt.create(usuario.getCedula(), usuario.getNombre());
                    response.put("token", token);
                    response.put("nombre", user.getNombre());
                    return ResponseHttp(HttpStatus.OK, response);

                } else {
                    return ResponseHttp(HttpStatus.BAD_REQUEST, respuesta("mensaje", "Contraseña incorrecta"));
                }
            } else {
                return ResponseHttp(HttpStatus.BAD_REQUEST, respuesta("mensaje", "Usuario no encontrado"));
            }
        } catch (Exception e) {
            System.out.println("Error loginUusario-UsuarioService" + e);
            return ResponseHttp(HttpStatus.INTERNAL_SERVER_ERROR, respuesta("mensaje", "Error al iniciar sesión"));
        }
    }

    
}
