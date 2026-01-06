package com.leocarlos10.backendSG_medica.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import org.springframework.http.ResponseEntity;

import com.leocarlos10.backendSG_medica.dto.panel.CitasPanel;
import com.leocarlos10.backendSG_medica.dto.panel.DiagnosticoPanel;
import com.leocarlos10.backendSG_medica.dto.respuestasComunes.Response;
import com.leocarlos10.backendSG_medica.service.PanelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/panel")
@RequiredArgsConstructor
public class PanelController extends Controller {

    private final PanelService panelService;

    @GetMapping("/citas-proximas")
    public ResponseEntity<Response<List<CitasPanel>>> obtenerCitasProximas() {
        List<CitasPanel> citas = panelService.obtenerCitasProximas();

        Response<List<CitasPanel>> response = Response.<List<CitasPanel>>builder()
                .responseCode(200)
                .responseMessage("Citas próximas obtenidas")
                .data(citas)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/citas-hoy")
    public ResponseEntity<Response<List<CitasPanel>>> obtenerCitasHoy() {
        List<CitasPanel> citas = panelService.obtenerCitasHoy();

        Response<List<CitasPanel>> response = Response.<List<CitasPanel>>builder()
                .responseCode(200)
                .responseMessage("Citas de hoy obtenidas")
                .data(citas)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/diagnosticos")
    public ResponseEntity<Response<List<DiagnosticoPanel>>> obtenerDiagnosticos() {
        List<DiagnosticoPanel> diagnosticos = panelService.obtenerDiagnosticos();

        Response<List<DiagnosticoPanel>> response = Response.<List<DiagnosticoPanel>>builder()
                .responseCode(200)
                .responseMessage("Diagnósticos obtenidos")
                .data(diagnosticos)
                .build();
        return ResponseEntity.ok(response);
    }
}
