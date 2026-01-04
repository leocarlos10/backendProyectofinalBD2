package com.leocarlos10.backendSG_medica.service;

import org.springframework.stereotype.Service;
import com.leocarlos10.backendSG_medica.Models.CitasPanel;
import com.leocarlos10.backendSG_medica.Models.DiagnosticoPanel;
import com.leocarlos10.backendSG_medica.conexionDAO.PanelDAO;
import com.leocarlos10.backendSG_medica.exception.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

import com.leocarlos10.backendSG_medica.exception.BusinessException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PanelService extends service {

    private final PanelDAO panelDAO;

    /**
     * Obtiene las citas próximas
     * 
     * @return: Lista de citas próximas
     */
    public List<CitasPanel> obtenerCitasProximas() {
        logger.info("Obteniendo citas próximas");
        try {
            List<CitasPanel> citas = panelDAO.obtenerCitasProximas();
            if (citas.isEmpty()) {
                throw new EntityNotFoundException("No se encontraron citas próximas");
            }
            return citas;
        } catch (Exception e) {
            logger.error("Error al obtener citas próximas: {}", e.getMessage(), e);
            throw new BusinessException("OBTENER_CITAS_PROXIMAS_ERROR", "Error al obtener citas próximas", e);
        }
    }

    /**
     * Obtiene las citas de hoy
     * 
     * @return: Lista de citas de hoy
     */
    public List<CitasPanel> obtenerCitasHoy() {
        logger.info("Obteniendo citas de hoy");
        try {
            List<CitasPanel> citas = panelDAO.obtenerHoy();
            if (citas.isEmpty()) {
                throw new EntityNotFoundException("No hay citas para hoy");
            }
            return citas;
        } catch (Exception e) {
            logger.error("Error al obtener citas de hoy: {}", e.getMessage(), e);
            throw new BusinessException("OBTENER_CITAS_HOY_ERROR", "Error al obtener citas de hoy", e);
        }
    }

    /**
     * Obtiene los diagnósticos del panel
     * 
     * @return: Lista de diagnósticos
     */
    public List<DiagnosticoPanel> obtenerDiagnosticos() {
        logger.info("Obteniendo diagnósticos del panel");
        try {
            List<DiagnosticoPanel> diagnosticos = panelDAO.obtenerDiagnosticos();
            if (diagnosticos.isEmpty()) {
                throw new EntityNotFoundException("No hay diagnósticos disponibles");
            }
            return diagnosticos;
        } catch (Exception e) {
            logger.error("Error al obtener diagnósticos: {}", e.getMessage(), e);
            throw new BusinessException("OBTENER_DIAGNOSTICOS_PANEL_ERROR", "Error al obtener diagnósticos", e);
        }
    }
}
