package com.leocarlos10.backendSG_medica.conexionDAO;

import com.leocarlos10.backendSG_medica.Models.Paciente;
import com.leocarlos10.backendSG_medica.Models.Usuario;
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
public class UsuarioDAO implements DAO<Usuario, String> {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private static final class UsuarioRowMapper implements RowMapper<Usuario> {
        @Override
        public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
            Usuario usuario = new Usuario();
            usuario.setCedula(rs.getString("cedula"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setApellido(rs.getString("apellido"));
            usuario.setCorreo(rs.getString("correo"));
            usuario.setTelefono(rs.getString("telefono"));
            usuario.setCiudad(rs.getString("ciudad"));
            usuario.setPass(rs.getString("pass"));
            usuario.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
            return usuario;
        }
    }

    private static final class PacientesRowMapper implements RowMapper<Paciente> {
        @Override
        public Paciente mapRow(ResultSet rs, int rowNum) throws SQLException {
            Paciente paciente = new Paciente();
            paciente.setId_paciente(rs.getInt("id_paciente"));
            paciente.setNombre(rs.getString("nombre"));
            paciente.setApellido(rs.getString("apellido"));
            paciente.setCedula(rs.getString("cedula"));
            paciente.setTelefono(rs.getString("telefono"));
            paciente.setEmail(rs.getString("email"));
            paciente.setFecha_nacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
            paciente.setUltima_cita(rs.getDate("ultima_cita").toLocalDate());
            paciente.setEstado(rs.getString("estado_ultima_cita"));
            return paciente;
        }
    
        
    }

    @Override
    public int registrar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (cedula, nombre, apellido, correo, telefono, ciudad, pass, fechaNacimiento) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, 
            usuario.getCedula(),
            usuario.getNombre(),
            usuario.getApellido(),
            usuario.getCorreo(),
            usuario.getTelefono(),
            usuario.getCiudad(),
            usuario.getPass(),
            usuario.getFechaNacimiento()
        );
    }

    @Override
    public List<Usuario> obtenerTodo() throws SQLException {
        String sql = "SELECT * FROM usuario";
        return jdbcTemplate.query(sql, new UsuarioRowMapper());
    }

    @Override
    public Usuario obtenerPorId(String id) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE cedula = ?";
        return jdbcTemplate.queryForObject(sql, new UsuarioRowMapper(), id);
    }

    @Override
    public int actualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario SET nombre = ?, apellido = ?, correo = ?, telefono = ?, ciudad = ?, pass = ? WHERE cedula = ?";
        return jdbcTemplate.update(sql,
            usuario.getNombre(),
            usuario.getApellido(),
            usuario.getCorreo(),
            usuario.getTelefono(),
            usuario.getCiudad(),
            usuario.getPass(),
            usuario.getCedula()
        );
    }

    @Override
    public int eliminar(String id) throws SQLException {
        String sql = "DELETE FROM usuario WHERE cedula = ?";
        return jdbcTemplate.update(sql, id);
    }

    public List<Paciente> obtenerPacientesUltimaCita() throws SQLException {
        String sql = "CALL obtener_pacientes_con_citas_y_estado()";
        return jdbcTemplate.query(sql, new PacientesRowMapper());
    }

    public List<Paciente> obtenerPacientesPorEstado(String estado) throws SQLException {
        String sql = "CALL pacientes_por_estado(?)";
        return jdbcTemplate.query(sql, new PacientesRowMapper(), estado);
    }

   
} 