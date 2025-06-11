package Servicio;

import dao.MatriculaDAO;
import model.Matricula;

import java.sql.Connection;
import java.sql.SQLException;

public class MatriculaService {
    private MatriculaDAO matriculaDAO;

    public MatriculaService(Connection connection) {this.matriculaDAO = new MatriculaDAO(connection);}

    public void crearMatricula(Matricula matricula){
        try {
            matriculaDAO.crearMatricula( matricula );
            System.out.println("Matricula creada exitosamente");
        }catch (SQLException e) {
            System.err.println("Error al crear la matricula: " + e.getMessage());
        }
    }

    public Matricula leerMatricula(int id){
        try {
            Matricula matricula = matriculaDAO.readMatricula(id);
            if(matricula != null){
                System.out.println("Matricula encontrada existosamente: " + matricula.getPeriodo());
            }else {
                System.out.println("Matricula no encontrada por el id: " + id);
            }
            return matricula;
        }catch (SQLException e){
            System.err.println("Error al leer la matricula: " + e.getMessage());
            return null;
        }
    }

    public void actualizarMatricula(Matricula matricula){
        try {
            matriculaDAO.updateMatricula( matricula );
            System.out.println("Matricula actualizada exitosamente");
        }catch (SQLException e){
            System.err.println("Error al actualizar la matricula: " + e.getMessage());
        }
    }

    public void eliminarMatricula(int id){
        try {
            matriculaDAO.deleteMatricula(id);
            System.out.println("Matricula eliminada exitosamente");
        }catch (SQLException e){
            System.out.println("Error al eliminar la matricula: " + e.getMessage());
        }
    }
}
