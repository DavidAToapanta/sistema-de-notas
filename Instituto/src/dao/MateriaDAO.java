package dao;

import model.Materia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MateriaDAO {
    private Connection connection;

    public MateriaDAO(Connection connection) {this.connection = connection;}

    public void crearMateria(Materia materia) throws SQLException {
        String sql = "INSERT INTO tb_materia(ma_nombre, ma_nro_creditos, ma_tiempo_dias, ma_horarios, do_id, mo_id) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, materia.getNombre());
            stmt.setInt(2, materia.getNumCreditos());
            stmt.setInt(3, materia.getDuracionDias());
            stmt.setString(4, materia.getHorario());
            stmt.setInt(5, materia.getIdDocente());
            stmt.setInt(6, materia.getIdModulo());
            stmt.executeUpdate();
        }
    }

    public Materia readMateria(int id) throws SQLException {
        String sql = "SELECT * FROM tb_materia WHERE ma_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Materia materia = new Materia();
                materia.setId(rs.getInt("ma_id"));
                materia.setNombre(rs.getString("ma_nombre"));
                materia.setNumCreditos(rs.getInt("ma_nro_creditos"));
                materia.setDuracionDias(rs.getInt("ma_tiempo_dias"));
                materia.setHorario(rs.getString("ma_horarios"));
                materia.setIdDocente(rs.getInt("do_id"));
                materia.setIdModulo(rs.getInt("mo_id"));
                return materia;
            }else{
                return null;
            }
        }
    }

    public void updateMateria(Materia materia) throws SQLException{
        String sql = "UPDATE tb_materia SET ma_nombre = ?, ma_nro_creditos = ?, ma_tiempo_dias = ?, ma_horarios = ?, do_id = ?, mo_id = ? WHERE ma_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, materia.getNombre());
            stmt.setInt(2, materia.getNumCreditos());
            stmt.setInt(3, materia.getDuracionDias());
            stmt.setString(4, materia.getHorario());
            stmt.setInt(5, materia.getIdDocente());
            stmt.setInt(6, materia.getIdModulo());
            stmt.setInt(7, materia.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteMateria(int id) throws SQLException {
        String sql = "DELETE FROM tb_materia WHERE ma_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
