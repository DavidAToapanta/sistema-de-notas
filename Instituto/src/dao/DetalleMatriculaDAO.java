package dao;

import model.DetalleMatricula;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DetalleMatriculaDAO {
    private Connection connection;

    public DetalleMatriculaDAO(Connection connection){
        this.connection = connection;
    }

    public void crearDetalleMatricula(DetalleMatricula detalleMatricula) throws SQLException {
        String sql = "INSERT INTO tb_detalle_matricula(ma_id, ma_id_materia) VALUES (?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, detalleMatricula.getIdMatricula());
            stmt.setInt(2, detalleMatricula.getIdMateria());
            stmt.executeUpdate();
        }
    }

    public DetalleMatricula readDetalleMatricula(int id) throws SQLException {
        String sql = "SELECT * FROM tb_detalle_matricula WHERE dm_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                DetalleMatricula detalleMatricula = new DetalleMatricula();
                detalleMatricula.setId(rs.getInt("dm_id"));
                detalleMatricula.setIdMatricula(rs.getInt("ma_id"));
                detalleMatricula.setIdMateria(rs.getInt("ma_id_materia"));
                return detalleMatricula;
            }else{
                return null;
            }
        }
    }

    public void updateDetalleMatricula(DetalleMatricula detalleMatricula) throws SQLException{
        String sql = "UPDATE tb_detalle_matricula SET ma_id = ?, ma_id_materia = ? WHERE dm_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, detalleMatricula.getIdMatricula());
            stmt.setInt(2, detalleMatricula.getIdMateria());
            stmt.setInt(3, detalleMatricula.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteDetalleMatricula(int id) throws SQLException {
        String sql = "DELETE FROM tb_detalle_matricula WHERE dm_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

}
