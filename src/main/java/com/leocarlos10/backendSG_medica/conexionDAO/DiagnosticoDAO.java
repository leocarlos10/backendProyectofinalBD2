package com.leocarlos10.backendSG_medica.conexionDAO;

import com.leocarlos10.backendSG_medica.Models.Diagnostico;
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
 * Garantiza que las operaciones de base de datos sean atómicas y consistentes, 
 * manejando automáticamente las transacciones.
 */

@Repository
@Transactional
public class DiagnosticoDAO implements DAO<Diagnostico> {
    
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

    @Override
    public int registrar(Diagnostico diagnostico) throws SQLException {
        String sql = "INSERT INTO diagnostico (tratamiento, observaciones, nota_corta, nota_larga, fecha, id_historia) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, 
            diagnostico.getTratamiento(),
            diagnostico.getObservaciones(),
            diagnostico.getNota_corta(),
            diagnostico.getNota_larga(),
            diagnostico.getFecha(),
            diagnostico.getId_historia()
        );
    }

    @Override
    public List<Diagnostico> obtenerTodo() throws SQLException {
        String sql = "SELECT * FROM diagnostico";
        return jdbcTemplate.query(sql, new DiagnosticoRowMapper());
    }

    @Override
    public Diagnostico obtenerPorId(int id) throws SQLException {
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
            diagnostico.getId_diagnostico()
        );
    }

    @Override
    public int eliminar(int id) throws SQLException {
        String sql = "DELETE FROM diagnostico WHERE id_diagnostico = ?";
        return jdbcTemplate.update(sql, id);
    }
} 