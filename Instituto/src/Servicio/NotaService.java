package Servicio;

import dao.NotaDAO;
import model.Nota;

import java.sql.Connection;
import java.sql.SQLException;

public class NotaService {
    private NotaDAO notaDAO;

    public NotaService(Connection connection) {
        this.notaDAO = new NotaDAO(connection);
    }

    public void crearNota(Nota nota) {
        try {
            notaDAO.crearNota(nota);
            System.out.println("Nota creada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al crear la nota: " + e.getMessage());
        }
    }

    public Nota leerNota(int id) {
        try {
            Nota nota = notaDAO.readNota(id);
            if (nota != null) {
                System.out.println("Nota encontrada con ID: " + id);
            } else {
                System.out.println("No se encontró ninguna nota con ID: " + id);
            }
            return nota;
        } catch (SQLException e) {
            System.out.println("Error al leer la nota: " + e.getMessage());
            return null;
        }
    }

    public void actualizarNota(Nota nota) {
        try {
            notaDAO.updateNota(nota);
            System.out.println("Nota actualizada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar la nota: " + e.getMessage());
        }
    }

    public void eliminarNota(int id) {
        try {
            notaDAO.deleteNota(id);
            System.out.println("Nota eliminada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar la nota: " + e.getMessage());
        }
    }
}
