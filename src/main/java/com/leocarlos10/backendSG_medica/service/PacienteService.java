package com.leocarlos10.backendSG_medica.service;

import org.springframework.stereotype.Service;
import com.leocarlos10.backendSG_medica.Models.Paciente;
import com.leocarlos10.backendSG_medica.Models.Usuario;
import com.leocarlos10.backendSG_medica.conexionDAO.UsuarioDAO;
import com.leocarlos10.backendSG_medica.exception.EntityNotFoundException;
import com.leocarlos10.backendSG_medica.exception.ValidationException;

import lombok.RequiredArgsConstructor;

import com.leocarlos10.backendSG_medica.exception.BusinessException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteService extends service{

    
    private final UsuarioDAO usuarioDAO;
    /**
     * Obtiene pacientes con su última cita
     * 
     * @return: Lista de pacientes
     */
    public List<Paciente> obtenerPacientesConUltimaCita() {
        logger.info("Obteniendo pacientes con última cita");
        try {
            List<Paciente> pacientes = usuarioDAO.obtenerPacientesUltimaCita();
            if (pacientes.isEmpty()) {
                throw new EntityNotFoundException("No se encontraron pacientes");
            }
            return pacientes;
        } catch (Exception e) {
            logger.error("Error al obtener pacientes: {}", e.getMessage(), e);
            throw new BusinessException("OBTENER_PACIENTES_ERROR", "Error al obtener los pacientes", e);
        }
    }

    /**
     * Obtiene pacientes por estado
     * 
     * @param estado: Estado del paciente
     * @return: Lista de pacientes con ese estado
     */
    public List<Paciente> obtenerPacientesPorEstado(String estado) {
        logger.info("Obteniendo pacientes por estado: {}", estado);

        if (estado == null || estado.isEmpty()) {
            throw new ValidationException("El estado del paciente es requerido");
        }

        try {
            List<Paciente> pacientes = usuarioDAO.obtenerPacientesPorEstado(estado);
            if (pacientes.isEmpty()) {
                throw new EntityNotFoundException("No se encontraron pacientes con estado: " + estado);
            }
            return pacientes;
        } catch (Exception e) {
            logger.error("Error al obtener pacientes por estado: {}", e.getMessage(), e);
            throw new BusinessException("OBTENER_PACIENTES_ESTADO_ERROR", "Error al obtener los pacientes", e);
        }
    }

    /**
     * Obtiene un paciente por cédula
     * 
     * @param cedula: Cédula del paciente
     * @return: El paciente
     */
    public Usuario obtenerPacientePorCedula(String cedula) {
        logger.info("Obteniendo paciente con cédula: {}", cedula);

        if (cedula == null || cedula.isEmpty()) {
            throw new ValidationException("La cédula del paciente es requerida");
        }

        try {
            Usuario paciente = usuarioDAO.obtenerPorId(cedula);
            if (paciente == null) {
                throw new EntityNotFoundException("Paciente", cedula);
            }
            return paciente;
        } catch (Exception e) {
            logger.error("Error al obtener paciente: {}", e.getMessage(), e);
            throw new BusinessException("OBTENER_PACIENTE_ERROR", "Error al obtener el paciente", e);
        }
    }
}
