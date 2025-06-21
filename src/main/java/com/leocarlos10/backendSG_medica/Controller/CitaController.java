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
import java.util.Map;




@RestController
@RequestMapping("/api/citas")
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
                    return ResponseHttp(HttpStatus.OK, Map.of("mensaje", "Cita registrada correctamente"));
                }else{
                    return ResponseHttp(HttpStatus.BAD_REQUEST, Map.of("mensaje", "No se pudo registrar la cita"));
                }
            } else{
                return ResponseHttp(HttpStatus.UNAUTHORIZED, Map.of("mensaje", "Token invalido"));
            }
        } catch (Exception e) {
            System.out.println("error registrarCita-CitaController" + e);
            return ResponseHttp(HttpStatus.INTERNAL_SERVER_ERROR, Map.of("mensaje", "Error al registrar cita"));
        }
    }

    @GetMapping("/obtener-todas")
    public ResponseEntity<?> citas(){
        try {
            List<Cita> citas =  citaDAO.obtenerTodo();
            if(!citas.isEmpty()){
                return ResponseHttp(HttpStatus.OK, Map.of("citas", citas));
            }
            return ResponseHttp(HttpStatus.NOT_FOUND, Map.of("mensaje", "No se encontraron citas"));
        } catch (java.sql.SQLException e) {
            System.out.println("error citas-CitaController" + e);
            return ResponseHttp(HttpStatus.INTERNAL_SERVER_ERROR, Map.of("mensaje", "Error al obtener las citas"));
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
    public ResponseEntity<?> eliminarCita(@PathVariable int id) {
        try {
            int filas = citaDAO.eliminar(id);
            if (filas > 0) {
                return ResponseHttp(HttpStatus.OK, Map.of("mensaje", "Cita eliminada correctamente","respuesta", true));
            } else {
                return ResponseHttp(HttpStatus.BAD_REQUEST, Map.of("mensaje", "No se pudo eliminar la cita","respuesta", false));
            }
        } catch (Exception e) {
            System.out.println("error eliminarCita-CitaController" + e);
            return ResponseHttp(HttpStatus.INTERNAL_SERVER_ERROR, Map.of("mensaje", "Error al eliminar la cita","respuesta", false));
        }
    }

    @GetMapping("/con-usuario")
    public ResponseEntity<?> obtenerCitasConUsuario() {
        try {
            List<CitaUsuarioDTO> citas = citaDAO.obtenerUsuarioCita();
            if (!citas.isEmpty()) {
                return ResponseHttp(HttpStatus.OK, Map.of("citas", citas));
            } else {
                return ResponseHttp(HttpStatus.NOT_FOUND, Map.of("mensaje", "No se encontraron citas con usuarios", "respuesta", false));
            }
        } catch (Exception e) {
            System.out.println("error obtenerCitasConUsuario-CitaController" + e);
            return ResponseHttp(HttpStatus.INTERNAL_SERVER_ERROR, Map.of("mensaje", "Error al obtener las citas con usuarios", "respuesta", false));
        }
    }

    @GetMapping("/get-cita-usuario/{cedula}")
    public ResponseEntity<?> obtenerCitaPorUsuario(@PathVariable String cedula) {
        try {
            List<Cita> citas = citaDAO.obtenerCitaPorUsuario(cedula);
            if(!citas.isEmpty()){
                
                return ResponseHttp(HttpStatus.OK, Map.of("citas", citas));
            }else{
                return ResponseHttp(HttpStatus.NOT_FOUND, Map.of("mensaje", "No se encontraron citas"));
            }
        } catch (Exception e) {
            System.out.println("error obtenerCitaPorUsuario-CitaController" + e);
            return ResponseHttp(HttpStatus.INTERNAL_SERVER_ERROR, Map.of("mensaje", "Error al obtener las citas"));
        }
    }
}
