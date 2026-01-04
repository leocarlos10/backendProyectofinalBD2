package com.leocarlos10.backendSG_medica.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import com.leocarlos10.backendSG_medica.Models.HistoriaClinica;
import com.leocarlos10.backendSG_medica.dto.ApiResponse;
import com.leocarlos10.backendSG_medica.service.HistoriaClinicaService;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/historia-cli")
@RequiredArgsConstructor
public class HistoriaCli_Controller extends Controller  {


    private final HistoriaClinicaService historiaClinicaService;

    @GetMapping("/obtener-historia-cli/{cedula}")
    public ResponseEntity<ApiResponse<?>> obtenerHistoriaCli(@PathVariable String cedula) {
        HistoriaClinica historia = historiaClinicaService.obtenerPorCedula(cedula);

        ApiResponse<?> response = ApiResponse.success("Historia clínica obtenida exitosamente", historia);
        return ResponseEntity.ok(response);
    }
}
