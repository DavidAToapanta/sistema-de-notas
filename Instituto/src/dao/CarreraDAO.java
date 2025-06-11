package dao;

import model.Carrera;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarreraDAO {
    private Connection connection;

    public CarreraDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Carrera carrera) throws SQLException {
        String sql = "INSERT INTO tb_carrera(ca_nombre, ca_nro_modulos,ca_tiempo_cursado) VALUES (?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, carrera.getNombre());
            stmt.setInt(2, carrera.getNumModulo());
            stmt.setInt(3, carrera.getTiempoDuracion());
            stmt.executeUpdate();
        }
    }

    public Carrera read(int id) throws SQLException {
        String sql = "SELECT * FROM tb_carrera WHERE ca_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Carrera carrera = new Carrera();
                carrera.setId(rs.getInt("ca_id"));
                carrera.setNombre(rs.getString("ca_nombre"));
                carrera.setNumModulo(rs.getInt("ca_nro_modulos"));
                carrera.setTiempoDuracion(rs.getInt("ca_tiempo_cursado"));
                return carrera;
            }else{
                return null;
            }
        }
    }

    public void update(Carrera carrera) throws SQLException{
        String sql = "UPDATE tb_carrera SET ca_nombre = ?, ca_nro_modulos = ?, ca_tiempo_cursado = ? WHERE ca_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, carrera.getNombre());
            stmt.setInt(2, carrera.getNumModulo());
            stmt.setInt(3, carrera.getTiempoDuracion());
            stmt.setInt(4, carrera.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM tb_carrera WHERE ca_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }



}
