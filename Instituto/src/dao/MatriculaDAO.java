package dao;

import model.Matricula;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MatriculaDAO {
    private Connection connection;

    public MatriculaDAO(Connection connection) {this.connection = connection;}

    public void crearMatricula(Matricula matricula) throws SQLException {
        String sql = "INSERT INTO tb_matricula(ma_fecha, ma_periodo, ca_id, es_id) VALUES (?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setDate(1, java.sql.Date.valueOf(matricula.getFecha()));
            stmt.setString(2, matricula.getPeriodo());
            stmt.setInt(3, matricula.getIdCarrera());
            stmt.setInt(4, matricula.getIdEstudiante());
            stmt.executeUpdate();
        }
    }

    public Matricula readMatricula(int id) throws SQLException {
        String sql = "SELECT * FROM tb_matricula WHERE ma_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Matricula matricula = new Matricula();
                matricula.setId(rs.getInt("ma_id"));
                matricula.setFecha(rs.getDate("ma_fecha").toLocalDate());
                matricula.setPeriodo(rs.getString("ma_periodo"));
                matricula.setIdCarrera(rs.getInt("ca_id"));
                matricula.setIdEstudiante(rs.getInt("es_id"));
                return matricula;
            }else{
                return null;
            }
        }
    }

    public void updateMatricula(Matricula matricula) throws SQLException{
        String sql = "UPDATE tb_matricula SET ma_fecha = ?, ma_periodo = ?, ca_id = ?, es_id = ? WHERE ma_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setDate(1, java.sql.Date.valueOf(matricula.getFecha()));
            stmt.setString(2, matricula.getPeriodo());
            stmt.setInt(3, matricula.getIdCarrera());
            stmt.setInt(4, matricula.getIdEstudiante());
            stmt.setInt(5, matricula.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteMatricula(int id) throws SQLException {
        String sql = "DELETE FROM tb_matricula WHERE ma_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
