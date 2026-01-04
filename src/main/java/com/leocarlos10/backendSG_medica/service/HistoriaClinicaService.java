package com.leocarlos10.backendSG_medica.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.leocarlos10.backendSG_medica.Models.HistoriaClinica;
import com.leocarlos10.backendSG_medica.conexionDAO.HistoriaClinicaDAO;
import com.leocarlos10.backendSG_medica.exception.EntityNotFoundException;
import com.leocarlos10.backendSG_medica.exception.ValidationException;
import com.leocarlos10.backendSG_medica.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class HistoriaClinicaService {

    private static final Logger logger = LoggerFactory.getLogger(HistoriaClinicaService.class);

    @Autowired
    private HistoriaClinicaDAO historiaClinicaDAO;

    /**
     * Obtiene la historia clínica de un usuario por cédula
     * 
     * @param cedula: Cédula del usuario
     * @return: La historia clínica
     */
    public HistoriaClinica obtenerPorCedula(String cedula) {
        logger.info("Obteniendo historia clínica para usuario: {}", cedula);

        if (cedula == null || cedula.isEmpty()) {
            throw new ValidationException("La cédula del usuario es requerida");
        }

        try {
            HistoriaClinica historia = historiaClinicaDAO.obtenerPorId(cedula);
            if (historia == null) {
                throw new EntityNotFoundException("Historia Clínica", cedula);
            }
            return historia;
        } catch (Exception e) {
            logger.error("Error al obtener historia clínica: {}", e.getMessage(), e);
            throw new BusinessException("OBTENER_HISTORIA_ERROR", "Error al obtener la historia clínica", e);
        }
    }
}
