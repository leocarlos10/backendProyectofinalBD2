package com.leocarlos10.backendSG_medica.conexionDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component; 
import com.leocarlos10.backendSG_medica.Models.CitasPanel;

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
            return cita;
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

}
