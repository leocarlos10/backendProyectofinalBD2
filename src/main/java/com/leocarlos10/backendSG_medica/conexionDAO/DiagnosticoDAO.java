package com.leocarlos10.backendSG_medica.conexionDAO;

import com.leocarlos10.backendSG_medica.Models.Diagnostico;
import com.leocarlos10.backendSG_medica.Models.Usuario;
import com.leocarlos10.backendSG_medica.Models.UsuarioDiagnosticoDTO;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @Repository: Indica que esta clase es un repositorio de Spring
 * @Transactional: Indica que esta clase es transaccional,
 *                 Garantiza que las operaciones de base de datos sean atómicas
 *                 y consistentes,
 *                 manejando automáticamente las transacciones.
 */

@Repository
@Transactional
public class DiagnosticoDAO implements DAO<Diagnostico, Integer> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final class DiagnosticoRowMapper implements RowMapper<Diagnostico> {
        @Override
        public Diagnostico mapRow(ResultSet rs, int rowNum) throws SQLException {
            Diagnostico diagnostico = new Diagnostico();
            diagnostico.setId_diagnostico(rs.getInt("id_diagnostico"));
            diagnostico.setTratamiento(rs.getString("tratamiento"));
            diagnostico.setObservaciones(rs.getString("observaciones"));
            diagnostico.setNota_corta(rs.getString("nota_corta"));
            diagnostico.setNota_larga(rs.getString("nota_larga"));
            diagnostico.setFecha(rs.getDate("fecha").toLocalDate());
            diagnostico.setId_historia(rs.getInt("id_historia"));
            return diagnostico;
        }
    }

    private static final class DiagnosticoUsuarioRowMapper implements RowMapper<UsuarioDiagnosticoDTO> {
        @Override
        public UsuarioDiagnosticoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            Diagnostico diagnostico = new Diagnostico(
                    rs.getInt("id_diagnostico"),
                    rs.getString("tratamiento"),
                    rs.getString("observaciones"),
                    rs.getString("nota_corta"),
                    rs.getString("nota_larga"),
                    rs.getDate("fecha_diagnostico").toLocalDate(),
                    rs.getInt("id_historia"));
            Usuario usuario = new Usuario(
                    rs.getString("cedula_usuario"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("correo"),
                    rs.getString("telefono"),
                    rs.getString("ciudad"),
                    rs.getDate("fecha_nacimiento_usuario").toLocalDate());
            UsuarioDiagnosticoDTO dto = new UsuarioDiagnosticoDTO();
            dto.setUsuario(usuario);
            dto.setDiagnostico(diagnostico);
            return dto;
        }
    }

    @Override
    public int registrar(Diagnostico diagnostico) throws SQLException {
        String sql = "INSERT INTO diagnostico (tratamiento, observaciones, nota_corta, nota_larga, fecha, id_historia) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                diagnostico.getTratamiento(),
                diagnostico.getObservaciones(),
                diagnostico.getNota_corta(),
                diagnostico.getNota_larga(),
                diagnostico.getFecha(),
                diagnostico.getId_historia());
    }

    @Override
    public List<Diagnostico> obtenerTodo() throws SQLException {
        String sql = "SELECT * FROM diagnostico";
        return jdbcTemplate.query(sql, new DiagnosticoRowMapper());
    }

    @Override
    public Diagnostico obtenerPorId(Integer id) throws SQLException {
        String sql = "SELECT * FROM diagnostico WHERE id_diagnostico = ?";
        return jdbcTemplate.queryForObject(sql, new DiagnosticoRowMapper(), id);
    }

    @Override
    public int actualizar(Diagnostico diagnostico) throws SQLException {
        String sql = "UPDATE diagnostico SET tratamiento = ?, observaciones = ?, nota_corta = ?, nota_larga = ?, fecha = ?, id_historia = ? WHERE id_diagnostico = ?";
        return jdbcTemplate.update(sql,
                diagnostico.getTratamiento(),
                diagnostico.getObservaciones(),
                diagnostico.getNota_corta(),
                diagnostico.getNota_larga(),
                diagnostico.getFecha(),
                diagnostico.getId_historia(),
                diagnostico.getId_diagnostico());
    }

    @Override
    public int eliminar(Integer id) throws SQLException {
        String sql = "DELETE FROM diagnostico WHERE id_diagnostico = ?";
        return jdbcTemplate.update(sql, id);
    }

    public List<UsuarioDiagnosticoDTO> obtenerDiagnosticosConUsuario() throws SQLException {
        String sql = "CALL diagnosticos_recientes_completo()"; // Asegúrate de que la relación sea correcta
        return jdbcTemplate.query(sql, new DiagnosticoUsuarioRowMapper());
    }

    public List<UsuarioDiagnosticoDTO> obtenerDiagnosticosPorUsuario(String cedula) throws SQLException {
        String sql = "Call diagnosticos_por_usuario(?)"; // Asegúrate de que la relación sea correcta
        return jdbcTemplate.query(sql, new DiagnosticoUsuarioRowMapper(), cedula);
    }

    public List<Diagnostico> obtenerDiagnosticosPorHistoria(int idHistoria) throws SQLException {
        String sql = "SELECT * FROM diagnostico WHERE id_historia = ?";
        return jdbcTemplate.query(sql, new DiagnosticoRowMapper(), idHistoria);
    }

    public int crearDiagnosticoConHistoria(
            String cedulaUsuario,
            String tratamiento,
            String observaciones,
            String notaCorta,
            String notaLarga,
            java.sql.Date fecha) throws SQLException {
        String sql = "CALL crear_diagnostico_con_historia(?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, cedulaUsuario, tratamiento, observaciones, notaCorta, notaLarga, fecha);
    }

}