package com.leocarlos10.backendSG_medica.Controller;

import org.springframework.web.bind.annotation.RestController;
import com.leocarlos10.backendSG_medica.Models.CitasPanel;
import com.leocarlos10.backendSG_medica.Models.DiagnosticoPanel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import org.springframework.http.ResponseEntity;
import com.leocarlos10.backendSG_medica.dto.ApiResponse;

@RestController
@RequestMapping("/api/panel")
public class PanelController extends Controller {

    @GetMapping("/citas-proximas")
    public ResponseEntity<ApiResponse<?>> obtenerCitasProximas() {
        List<CitasPanel> citas = panelService.obtenerCitasProximas();

        ApiResponse<?> response = ApiResponse.success("Citas próximas obtenidas", citas);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/citas-hoy")
    public ResponseEntity<ApiResponse<?>> obtenerCitasHoy() {
        List<CitasPanel> citas = panelService.obtenerCitasHoy();

        ApiResponse<?> response = ApiResponse.success("Citas de hoy obtenidas", citas);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/diagnosticos")
    public ResponseEntity<ApiResponse<?>> obtenerDiagnosticos() {
        List<DiagnosticoPanel> diagnosticos = panelService.obtenerDiagnosticos();

        ApiResponse<?> response = ApiResponse.success("Diagnósticos obtenidos", diagnosticos);
        return ResponseEntity.ok(response);
    }
}
