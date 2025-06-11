package Servicio;

import dao.DocenteDAO;
import model.Docente;

import java.sql.Connection;
import java.sql.SQLException;

public class DocenteService {
    private DocenteDAO docenteDAO;

    public DocenteService(Connection connection) {this.docenteDAO = new DocenteDAO(connection);}

    public void crearDocente(Docente docente) {
        try {
            docenteDAO.createDocente(docente);
            System.out.println("Docente ha sido creado");
        }catch (SQLException e){
            System.out.println("Error al encontrar al docente" + e.getMessage());
        }
    }

    public Docente buscarDocente(int id) {
        try {
            Docente docente = docenteDAO.readDocente(id);
            if(docente != null){
                System.out.println("Docente encontrado" + docente.getNombre());
            }else{
                System.out.println("No se encontro el id del docente");
            }
            return docente;
        }catch (SQLException e){
            System.out.println("Error al encontrar al docente" + e.getMessage());
            return null;
        }
    }

    public void actualizarDocente(Docente docente){
        try {
            docenteDAO.updateDocente(docente);
            System.out.println("Docente ha sido actualizado");
        }catch (SQLException e){
            System.out.println("Error al actualizar al docente" + e.getMessage());
        }
    }

    public void eliminarDocente(int id) {
        try {
            docenteDAO.deleteDocente(id);
            System.out.println("Docente ha sido eliminado");
        }catch (SQLException e){
            System.out.println("Error al eliminar al docente" + e.getMessage());
        }
    }
}
