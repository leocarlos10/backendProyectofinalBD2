package com.leocarlos10.backendSG_medica.conexionDAO;

import com.leocarlos10.backendSG_medica.Models.Usuario;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class UsuarioDAO implements DAO<Usuario> {
    
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
            return usuario;
        }
    }

    @Override
    public int registrar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (cedula, nombre, apellido, correo, telefono, ciudad, pass) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, 
            usuario.getCedula(),
            usuario.getNombre(),
            usuario.getApellido(),
            usuario.getCorreo(),
            usuario.getTelefono(),
            usuario.getCiudad(),
            usuario.getPass()
        );
    }

    @Override
    public List<Usuario> obtenerTodo() throws SQLException {
        String sql = "SELECT * FROM usuario";
        return jdbcTemplate.query(sql, new UsuarioRowMapper());
    }

    @Override
    public Usuario obtenerPorId(int id) throws SQLException {
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
    public int eliminar(int id) throws SQLException {
        String sql = "DELETE FROM usuario WHERE cedula = ?";
        return jdbcTemplate.update(sql, id);
    }
} 