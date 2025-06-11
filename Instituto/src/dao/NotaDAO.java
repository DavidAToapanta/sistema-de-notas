package dao;

import model.Nota;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NotaDAO {
    private Connection connection;

    public NotaDAO(Connection connection) {this.connection = connection;}

    public void crearNota(Nota nota) throws SQLException {
        String sql = "INSERT INTO tb_nota(dm_id, no_parcial_i, no_parcial_ii, no_examen_final, no_promedio, no_aprobado) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, nota.getIdDetalleMatricula());
            stmt.setDouble(2, nota.getPacrial1());
            stmt.setDouble(3, nota.getPacrial2());
            stmt.setDouble(4, nota.getExamenFinal());
            stmt.setDouble(5, nota.getPromedio());
            stmt.setBoolean(6, nota.isAprovado());
            stmt.executeUpdate();
        }
    }

    public Nota readNota(int id) throws SQLException {
        String sql = "SELECT * FROM tb_nota WHERE no_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Nota nota = new Nota();
                nota.setId(rs.getInt("no_id"));
                nota.setIdDetalleMatricula(rs.getInt("dm_id"));
                nota.setPacrial1(rs.getDouble("no_parcial_i"));
                nota.setPacrial2(rs.getDouble("no_parcial_ii"));
                nota.setExamenFinal(rs.getDouble("no_examen_final"));
                nota.setPromedio(rs.getDouble("no_promedio"));
                nota.setAprovado(rs.getBoolean("no_aprobado"));
                return nota;
            }else{
                return null;
            }
        }
    }

    public void updateNota(Nota nota) throws SQLException{
        String sql = "UPDATE tb_nota SET dm_id = ?, no_parcial_i = ?, no_parcial_ii = ?, no_examen_final = ?, no_promedio = ?, no_aprobado = ? WHERE no_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, nota.getIdDetalleMatricula());
            stmt.setDouble(2, nota.getPacrial1());
            stmt.setDouble(3, nota.getPacrial2());
            stmt.setDouble(4, nota.getExamenFinal());
            stmt.setDouble(5, nota.getPromedio());
            stmt.setBoolean(6, nota.isAprovado());
            stmt.setInt(7, nota.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteNota(int id) throws SQLException {
        String sql = "DELETE FROM tb_nota WHERE no_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
