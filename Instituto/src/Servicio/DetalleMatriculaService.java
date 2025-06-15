package Servicio;

import dao.DetalleMatriculaDAO;
import model.DetalleMatricula;

import java.sql.Connection;
import java.sql.SQLException;

public class DetalleMatriculaService {
    private DetalleMatriculaDAO detalleMatriculaDAO;

    public DetalleMatriculaService(Connection connection) {this.detalleMatriculaDAO = new DetalleMatriculaDAO(connection);}

    public void crearDetalleMatricula(DetalleMatricula detalle) {
        try {
            detalleMatriculaDAO.crearDetalleMatricula(detalle);
            System.out.println("Detalle de matrícula creado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al crear el detalle de matrícula: " + e.getMessage());
        }
    }

    public DetalleMatricula leerDetalleMatricula(int id) {
        try {
            DetalleMatricula detalle = detalleMatriculaDAO.readDetalleMatricula(id);
            if (detalle != null) {
                System.out.println("Detalle de matrícula encontrado con ID: " + id);
            } else {
                System.out.println("No se encontró ningún detalle de matrícula con ID: " + id);
            }
            return detalle;
        } catch (SQLException e) {
            System.out.println("Error al leer el detalle de matrícula: " + e.getMessage());
            return null;
        }
    }

    public void actualizarDetalleMatricula(DetalleMatricula detalle) {
        try {
            detalleMatriculaDAO.updateDetalleMatricula(detalle);
            System.out.println("Detalle de matrícula actualizado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar el detalle de matrícula: " + e.getMessage());
        }
    }

    public void eliminarDetalleMatricula(int id) {
        try {
            detalleMatriculaDAO.deleteDetalleMatricula(id);
            System.out.println("Detalle de matrícula eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar el detalle de matrícula: " + e.getMessage());
        }
    }
}
