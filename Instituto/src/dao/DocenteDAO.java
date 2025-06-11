package dao;


import model.Docente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DocenteDAO {
    private Connection connection;

    public DocenteDAO(Connection connection) {
        this.connection = connection;
    }

    public void createDocente(Docente docente) throws SQLException {
        String sql = "INSERT INTO tb_docente(do_nombres, do_apellidos) VALUES (?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, docente.getNombre());
            stmt.setString(2, docente.getApellido());
            stmt.executeUpdate();
        }
    }

    public Docente readDocente(int id) throws SQLException {
        String sql = "SELECT * FROM tb_docente WHERE do_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Docente docente = new Docente();
                docente.setId(rs.getInt("do_id"));
                docente.setNombre(rs.getString("do_nombres"));
                docente.setApellido(rs.getString("do_apellidos"));
                return docente;
            }else{
                return null;
            }
        }
    }

    public void updateDocente(Docente docente) throws SQLException{
        String sql = "UPDATE tb_carrera SET do_nombres = ?, do_nombres = ? WHERE do_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, docente.getNombre());
            stmt.setString(2, docente.getApellido());
            stmt.executeUpdate();
        }
    }

    public void deleteDocente(int id) throws SQLException {
        String sql = "DELETE FROM tb_carrera WHERE do_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
