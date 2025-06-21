package com.leocarlos10.backendSG_medica.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.leocarlos10.backendSG_medica.Models.HistoriaClinica;
import java.util.Map;

@RestController
@RequestMapping("/api/historia-cli")
public class HistoriaCli_Controller extends Controller {

    @GetMapping("/obtener-historia-cli/{cedula}")
    public ResponseEntity<?> obtenerHistoriaCli(@PathVariable String cedula) {
        try {
            HistoriaClinica historia = historiaCli_Controller.obtenerPorId(cedula);
            if (historia != null) {
                return ResponseHttp(HttpStatus.OK, Map.of("respuesta", historia));
            } else {
                return ResponseHttp(HttpStatus.NOT_FOUND, Map.of("mensaje", "No se encontró la historia clinica", "respuesta", false));
            }
        } catch (Exception e) {
            System.out.println("error obtenerHistoriaCli-HistoriaCli_Controller" + e);
            return ResponseHttp(HttpStatus.INTERNAL_SERVER_ERROR, Map.of("mensaje", "Error al obtener la historia clinica", "respuesta", false));
        }
    }
    
}
