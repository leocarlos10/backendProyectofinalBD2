package com.leocarlos10.backendSG_medica.conexionDAO;

import com.leocarlos10.backendSG_medica.Models.Curso;
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
public class CursoDAO implements DAO<Curso,Integer> {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private static final class CursoRowMapper implements RowMapper<Curso> {
        @Override
        public Curso mapRow(ResultSet rs, int rowNum) throws SQLException {
            Curso curso = new Curso();
            curso.setId_curso(rs.getInt("id_curso"));
            curso.setTitulo(rs.getString("titulo"));
            curso.setDescripcion(rs.getString("descripcion"));
            curso.setFecha_publicacion(rs.getDate("fecha_publicacion").toLocalDate());
            return curso;
        }
    }

    @Override
    public int registrar(Curso curso) throws SQLException {
        String sql = "INSERT INTO curso (titulo, descripcion, fecha_publicacion) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, 
            curso.getTitulo(),
            curso.getDescripcion(),
            curso.getFecha_publicacion()
        );
    }

    @Override
    public List<Curso> obtenerTodo() throws SQLException {
        String sql = "SELECT * FROM curso";
        return jdbcTemplate.query(sql, new CursoRowMapper());
    }

    @Override
    public Curso obtenerPorId(Integer id) throws SQLException {
        String sql = "SELECT * FROM curso WHERE id_curso = ?";
        return jdbcTemplate.queryForObject(sql, new CursoRowMapper(), id);
    }

    @Override
    public int actualizar(Curso curso) throws SQLException {
        String sql = "UPDATE curso SET titulo = ?, descripcion = ?, fecha_publicacion = ? WHERE id_curso = ?";
        return jdbcTemplate.update(sql,
            curso.getTitulo(),
            curso.getDescripcion(),
            curso.getFecha_publicacion(),
            curso.getId_curso()
        );
    }

    @Override
    public int eliminar(Integer id) throws SQLException {
        String sql = "DELETE FROM curso WHERE id_curso = ?";
        return jdbcTemplate.update(sql, id);
    }
} 