package com.leocarlos10.backendSG_medica.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.leocarlos10.backendSG_medica.Models.Cita;
import com.leocarlos10.backendSG_medica.Models.CitaUsuarioDTO;
import java.util.List;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestHeader;




@RestController
@RequestMapping("/citas")
public class CitaController extends Controller{

    /**
     * Este metodo es el encargado de registrar una cita
     * @param cita: La cita a registrar
     * @param token: El token de autenticacion
     * @return: Un objeto de tipo ResponseEntity que contiene el resultado de la operación
     * este objeto puede ser el token(200), bad request(400) o un error(500) o un unauthorized(401).
     */
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarCita(@RequestBody Cita cita, @RequestHeader(value = "Authorization") String token) {

        try {
            if(jwt.validarToken(token)){
                int filas = citaDAO.registrar(cita);
                if(filas > 0){
                    return ResponseHttp(HttpStatus.OK, respuesta("mensaje", "Cita registrada correctamente"));
                }else{
                    return ResponseHttp(HttpStatus.BAD_REQUEST, respuesta("mensaje", "No se pudo registrar la cita"));
                }
            } else{
                return ResponseHttp(HttpStatus.UNAUTHORIZED, respuesta("mensaje", "Token invalido"));
            }
        } catch (Exception e) {
            System.out.println("error registrarCita-CitaController" + e);
            return ResponseHttp(HttpStatus.INTERNAL_SERVER_ERROR, respuesta("mensaje", "Error al registrar cita"));
        }
    }

    @GetMapping("/obtener-todas")
    public List<Cita> citas(){
        try {
            return citaDAO.obtenerTodo();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            return java.util.Collections.emptyList();
        }
    }

    @GetMapping("/papelera")
    public List<Cita> obtenerPapeleraCitas() {
        try {
            return citaDAO.obtenerPapeleraCita();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            return java.util.Collections.emptyList();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> obtenerPorId(@PathVariable int id) {
        try {
            Cita cita = citaDAO.obtenerPorId(id);
            return ResponseEntity.ok(cita);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarCita(@RequestBody Cita cita) {
        try {
            int filas = citaDAO.actualizar(cita);
            if (filas > 0) {
                return ResponseEntity.ok("Cita actualizada correctamente");
            } else {
                return ResponseEntity.badRequest().body("No se pudo actualizar la cita");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarCita(@PathVariable int id) {
        try {
            int filas = citaDAO.eliminar(id);
            if (filas > 0) {
                return ResponseEntity.ok("Cita eliminada correctamente");
            } else {
                return ResponseEntity.badRequest().body("No se pudo eliminar la cita");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/con-usuario")
    public List<CitaUsuarioDTO> obtenerCitasConUsuario() {
    try {
        return citaDAO.obtenerUsuarioCita();
    } catch (Exception e) {
        e.printStackTrace();
        return java.util.Collections.emptyList();
    }
}

}
