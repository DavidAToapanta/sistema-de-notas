package dao;

import model.Modulo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModuloDAO {
    private Connection connection;

    public ModuloDAO(Connection connection){
        this.connection = connection;
    }

    public void crearModulo(Modulo modulo) throws SQLException {
        String sql = "INSERT INTO tb_modulo(mo_nombre, mo_nro_materias,ca_id) VALUES (?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, modulo.getNombre());
            stmt.setInt(2, modulo.getNumeroMateria());
            stmt.setInt(3, modulo.getIdCarrera());
            stmt.executeUpdate();
        }
    }

    public Modulo readModulo(int id) throws SQLException {
        String sql = "SELECT * FROM tb_modulo WHERE mo_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Modulo modulo = new Modulo();
                modulo.setId(rs.getInt("mo_id"));
                modulo.setNombre(rs.getString("mo_nombre"));
                modulo.setNumeroMateria(rs.getInt("mo_nro_materias"));
                modulo.setIdCarrera(rs.getInt("ca_id"));
                return modulo;
            }else{
                return null;
            }
        }
    }

    public void updateModulo(Modulo modulo) throws SQLException{
        String sql = "UPDATE tb_modulo SET mo_nombre = ?, mo_nro_materias = ?, ca_id = ? WHERE mo_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, modulo.getNombre());
            stmt.setInt(2, modulo.getNumeroMateria());
            stmt.setInt(3, modulo.getIdCarrera());
            stmt.setInt(4, modulo.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteModulo(int id) throws SQLException {
        String sql = "DELETE FROM tb_modulo WHERE mo_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
