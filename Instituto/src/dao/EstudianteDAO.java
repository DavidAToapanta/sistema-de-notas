package dao;

import model.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstudianteDAO {
    private Connection connection;

    public EstudianteDAO(Connection connection) {this.connection = connection;}

    public void crearEstudiante(Estudiante estudiante) throws SQLException {
        String sql = "INSERT INTO tb_estudiante(es_tipo_identificacion, es_identificacion, es_nombres, es_apellidos, es_direccion, " +
                "es_estado_civil, es_cargas_familiares, es_pais, es_ciudad, es_telefono, es_trabaja, es_lugar_trabajo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, estudiante.getTipoIdentificacion());
            stmt.setString(2, estudiante.getIdentificacion());
            stmt.setString(3, estudiante.getNombres());
            stmt.setString(4, estudiante.getApellidos());
            stmt.setString(5, estudiante.getDireccion());
            stmt.setString(6, estudiante.getEstadoCivil());
            stmt.setInt(7, estudiante.getCargasFamiliares());
            stmt.setString(8, estudiante.getPais());
            stmt.setString(9, estudiante.getCiudad());
            stmt.setString(10, estudiante.getTelefono());
            stmt.setBoolean(11, estudiante.isTrabaja());
            stmt.setString(12, estudiante.getLugarTrabajo());
            stmt.executeUpdate();
        }
    }

    public Estudiante readEstudiante(int id) throws SQLException {
        String sql = "SELECT * FROM tb_estudiante WHERE es_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Estudiante estudiante = new Estudiante();
                estudiante.setId(rs.getInt("es_id"));
                estudiante.setTipoIdentificacion(rs.getString("es_tipo_identificacion"));
                estudiante.setIdentificacion(rs.getString("es_identificacion"));
                estudiante.setNombres(rs.getString("es_nombres"));
                estudiante.setApellidos(rs.getString("es_apellidos"));
                estudiante.setDireccion(rs.getString("es_direccion"));
                estudiante.setEstadoCivil(rs.getString("es_estado_civil"));
                estudiante.setCargasFamiliares(rs.getInt("es_cargas_familiares"));
                estudiante.setPais(rs.getString("es_pais"));
                estudiante.setCiudad(rs.getString("es_ciudad"));
                estudiante.setTelefono(rs.getString("es_telefono"));
                estudiante.setTrabaja(rs.getBoolean("es_trabaja"));
                estudiante.setLugarTrabajo(rs.getString("es_lugar_trabajo"));

                return estudiante;
            }else{
                return null;
            }
        }
    }

    public void updateEstudiante(Estudiante estudiante) throws SQLException{
        String sql = "UPDATE tb_estudiante SET es_tipo_identificacion = ?, es_identificacion = ?, es_nombres = ?, es_apellidos = ?, es_direccion = ?, es_estado_civil = ?, " +
                "es_cargas_familiares = ?, es_pais = ?, es_ciudad = ?, es_telefono = ?, es_trabaja = ?, es_lugar_trabajo = ? WHERE es_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, estudiante.getTipoIdentificacion());
            stmt.setString(2, estudiante.getIdentificacion());
            stmt.setString(3, estudiante.getNombres());
            stmt.setString(4, estudiante.getApellidos());
            stmt.setString(5, estudiante.getDireccion());
            stmt.setString(6, estudiante.getEstadoCivil());
            stmt.setInt(7, estudiante.getCargasFamiliares());
            stmt.setString(8, estudiante.getPais());
            stmt.setString(9, estudiante.getCiudad());
            stmt.setString(10, estudiante.getTelefono());
            stmt.setBoolean(11, estudiante.isTrabaja());
            stmt.setString(12, estudiante.getLugarTrabajo());
            stmt.setInt(13, estudiante.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteEstudiante(int id) throws SQLException {
        String sql = "DELETE FROM tb_estudiante WHERE es_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
