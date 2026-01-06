package com.leocarlos10.backendSG_medica.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import com.leocarlos10.backendSG_medica.Models.HistoriaClinica;
import com.leocarlos10.backendSG_medica.dto.respuestasComunes.Response;
import com.leocarlos10.backendSG_medica.service.HistoriaClinicaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/historia-cli")
@RequiredArgsConstructor
public class HistoriaCli_Controller extends Controller {

    private final HistoriaClinicaService historiaClinicaService;

    @GetMapping("/obtener-historia-cli/{cedula}")
    public ResponseEntity<Response<HistoriaClinica>> obtenerHistoriaCli(@PathVariable String cedula) {
        HistoriaClinica historia = historiaClinicaService.obtenerPorCedula(cedula);

        Response<HistoriaClinica> response = Response.<HistoriaClinica>builder()
                .responseCode(200)
                .responseMessage("Historia clínica obtenida exitosamente")
                .data(historia)
                .build();
        return ResponseEntity.ok(response);
    }
}
