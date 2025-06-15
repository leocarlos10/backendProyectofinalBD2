package com.leocarlos10.backendSG_medica.Controller;

import com.leocarlos10.backendSG_medica.Models.Diagnostico;
import com.leocarlos10.backendSG_medica.Models.UsuarioDiagnosticoDTO;
import com.leocarlos10.backendSG_medica.conexionDAO.DiagnosticoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/diagnostico")
public class DiagnosticoController extends Controller {

    @Autowired
    private DiagnosticoDAO diagnosticoDAO;

    @GetMapping("/con-usuario")
    public ResponseEntity<?> obtenerDiagnosticosConUsuario() {
        try {
            List<UsuarioDiagnosticoDTO> diagnosticos =  diagnosticoDAO.obtenerDiagnosticosConUsuario();
            if(!diagnosticos.isEmpty()){
                return ResponseHttp(HttpStatus.OK, Map.of("diagnosticos", diagnosticos));
            }else{
                return ResponseHttp(HttpStatus.NOT_FOUND, Map.of("mensaje", "No se encontraron diagnosticos"));
            }
        } catch (Exception e) {
            System.out.println("error obtenerDiagnosticosConUsuario-DiagnosticoController" + e);
            return ResponseHttp(HttpStatus.INTERNAL_SERVER_ERROR, Map.of("mensaje", "Error al obtener los diagnosticos"));
        }
    }

    @GetMapping("/por-usuario/{cedula}")
    public List<UsuarioDiagnosticoDTO> obtenerDiagnosticosPorUsuario(@PathVariable String cedula) {
        try {
            return diagnosticoDAO.obtenerDiagnosticosPorUsuario(cedula);
        } catch (Exception e) {
            e.printStackTrace();
            return java.util.Collections.emptyList();
        }
    }

    @GetMapping("/por-historia/{idHistoria}")
    public List<Diagnostico> obtenerDiagnosticosPorHistoria(@PathVariable int idHistoria) {
        try {
            return diagnosticoDAO.obtenerDiagnosticosPorHistoria(idHistoria);
        } catch (Exception e) {
            e.printStackTrace();
            return java.util.Collections.emptyList();
        }
    }

    @GetMapping("/obtener-todos")
    public ResponseEntity<?> obtenerTodos() {
        try {
            List<Diagnostico> diagnosticos = diagnosticoDAO.obtenerTodo();
            if(!diagnosticos.isEmpty()){
                return ResponseHttp(HttpStatus.OK, Map.of("diagnosticos", diagnosticos));
            }else{
                return ResponseHttp(HttpStatus.NOT_FOUND, Map.of("mensaje", "No se encontraron diagnosticos"));
            }
        } catch (Exception e) {
            System.out.println("error obtenerTodos-DiagnosticoController" + e);
            return ResponseHttp(HttpStatus.INTERNAL_SERVER_ERROR, Map.of("mensaje", "Error al obtener los diagnosticos"));
        }
    }

    @GetMapping("/{id}")
    public Diagnostico obtenerPorId(@PathVariable Integer id) {
        try {
            return diagnosticoDAO.obtenerPorId(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping
    public int registrar(@RequestBody Diagnostico diagnostico) {
        try {
            return diagnosticoDAO.registrar(diagnostico);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @PutMapping
    public int actualizar(@RequestBody Diagnostico diagnostico) {
        try {
            return diagnosticoDAO.actualizar(diagnostico);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @DeleteMapping("/{id}")
    public int eliminar(@PathVariable Integer id) {
        try {
            return diagnosticoDAO.eliminar(id);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}