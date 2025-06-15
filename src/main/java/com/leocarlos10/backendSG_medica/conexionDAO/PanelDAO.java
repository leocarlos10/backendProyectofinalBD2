package com.leocarlos10.backendSG_medica.conexionDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component; 
import com.leocarlos10.backendSG_medica.Models.CitasPanel;
import com.leocarlos10.backendSG_medica.Models.Diagnostico;
import com.leocarlos10.backendSG_medica.Models.DiagnosticoPanel;
import com.leocarlos10.backendSG_medica.Models.Usuario;

@Component
public class PanelDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final class CitasRowMapper implements RowMapper<CitasPanel> {
        @Override
        public CitasPanel mapRow(ResultSet rs, int rowNum) throws SQLException {
            CitasPanel cita = new CitasPanel();
            cita.setId_cita(rs.getInt("id_cita"));
            cita.setFechaHora(rs.getString("fechaHora"));
            cita.setEstado(rs.getString("estado"));
            cita.setMotivoC(rs.getString("motivoC"));
            cita.setRemitente(rs.getString("remitente"));
            cita.setTipoCita(rs.getString("tipo_cita"));
            cita.setNombreUsuario(rs.getString("nombre"));
            cita.setApellidoUsuario(rs.getString("apellido"));
            cita.setCedulaUsuario(rs.getString("cedula"));
            cita.setServicio(rs.getString("servicio"));
            return cita;
        }
            
}

private static final class DiagnosticoPanelRowMapper implements RowMapper<DiagnosticoPanel> {
        @Override
        public DiagnosticoPanel mapRow(ResultSet rs, int rowNum) throws SQLException {
        Diagnostico diagnostico = new Diagnostico();
        diagnostico.setId_diagnostico(rs.getInt("id_diagnostico"));
        diagnostico.setTratamiento(rs.getString("tratamiento"));
        diagnostico.setObservaciones(rs.getString("observaciones"));
        diagnostico.setNota_corta(rs.getString("nota_corta"));
        diagnostico.setNota_larga(rs.getString("nota_larga"));
        diagnostico.setFecha(rs.getDate("fecha").toLocalDate());

        Usuario usuario = new Usuario();
        usuario.setNombre(rs.getString("nombre"));
        usuario.setApellido(rs.getString("apellido"));
        usuario.setCedula(rs.getString("cedula_usuario"));

        return new DiagnosticoPanel(diagnostico, usuario);
    }
        
    }

    public List<CitasPanel> obtenerCitasProximas() {
        String sql = "CALL citas_proximas()";
        return jdbcTemplate.query(sql, new CitasRowMapper());
    }

    public List<CitasPanel> obtenerHoy() {
        String sql = "CALL citas_hoy()";
        return jdbcTemplate.query(sql, new CitasRowMapper());
    }

    public List<DiagnosticoPanel> obtenerDiagnosticos() {
        String sql = "CALL diagnosticos_recientes";
        return jdbcTemplate.query(sql, new DiagnosticoPanelRowMapper());
    }

}
