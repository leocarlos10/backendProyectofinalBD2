package com.leocarlos10.backendSG_medica.conexionDAO;
import com.leocarlos10.backendSG_medica.Models.Comentario;
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
public class ComentarioDAO implements DAO<Comentario> {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private static final class ComentarioRowMapper implements RowMapper<Comentario> {
        @Override
        public Comentario mapRow(ResultSet rs, int rowNum) throws SQLException {
            Comentario comentario = new Comentario();
            comentario.setId_comentario(rs.getInt("id_comentario"));
            comentario.setDescripcion(rs.getString("descripcion"));
            comentario.setCedula_usuario(rs.getString("cedula_usuario"));
            comentario.setId_curso(rs.getInt("id_curso"));
            return comentario;
        }
    }

    @Override
    public int registrar(Comentario comentario) throws SQLException {
        String sql = "INSERT INTO comentario (descripcion, cedula_usuario, id_curso) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, 
            comentario.getDescripcion(),
            comentario.getCedula_usuario(),
            comentario.getId_curso()
        );
    }

    @Override
    public List<Comentario> obtenerTodo() throws SQLException {
        String sql = "SELECT * FROM comentario";
        return jdbcTemplate.query(sql, new ComentarioRowMapper());
    }

    @Override
    public Comentario obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM comentario WHERE id_comentario = ?";
        return jdbcTemplate.queryForObject(sql, new ComentarioRowMapper(), id);
    }

    @Override
    public int actualizar(Comentario comentario) throws SQLException {
        String sql = "UPDATE comentario SET descripcion = ?, cedula_usuario = ?, id_curso = ? WHERE id_comentario = ?";
        return jdbcTemplate.update(sql,
            comentario.getDescripcion(),
            comentario.getCedula_usuario(),
            comentario.getId_curso(),
            comentario.getId_comentario()
        );
    }

    @Override
    public int eliminar(int id) throws SQLException {
        String sql = "DELETE FROM comentario WHERE id_comentario = ?";
        return jdbcTemplate.update(sql, id);
    }
} 