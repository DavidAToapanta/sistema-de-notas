package Servicio;

import dao.EstudianteDAO;
import model.Estudiante;

import java.sql.Connection;
import java.sql.SQLException;

public class EstudianteService {
    private EstudianteDAO estudianteDAO;

    public EstudianteService(Connection connection) {this.estudianteDAO = new EstudianteDAO(connection);}

    public void crearEstudiante(Estudiante estudiante){
        try {
            estudianteDAO.crearEstudiante(estudiante);
            System.out.println("Estudiante creado correctamente");
        }catch (SQLException e){
            System.out.println("Error al crear estudiante: " + e.getMessage());
        }
    }

    public Estudiante buscarEstudiante(int id){
        try {
            Estudiante estudiante = estudianteDAO.readEstudiante(id);
            if(estudiante != null){
                System.out.println("Estudiante encontrado correctamente: " +  estudiante.getNombres());
            }else{
                System.out.println("No se encotro al estudiante con el id: " + id);
            }
            return estudiante;
        }catch (SQLException e){
            System.out.println("Error al buscar estudiante: " + e.getMessage());
            return null;
        }
    }

    public void actualizarEstudiante(Estudiante estudiante){
        try {
            estudianteDAO.updateEstudiante(estudiante);
            System.out.println("Estudiante actualizado correctamente");
        }catch (SQLException e){
            System.out.println("Error al modificar estudiante: " + e.getMessage());
        }
    }

    public void eliminarEstudiante(int id){
        try {
            estudianteDAO.deleteEstudiante(id);
            System.out.println("Estudiante eliminado correctamente");
        }catch (SQLException e){
            System.out.println("Errror al eliminar el estudiante: " + e.getMessage());
        }
    }
}
