package Servicio;

import dao.ModuloDAO;
import model.Modulo;

import java.sql.Connection;
import java.sql.SQLException;

public class ModuloService {
    private ModuloDAO moduloDAO;

    public ModuloService(Connection connection){this.moduloDAO = new ModuloDAO(connection);}

    public void crearModulo(Modulo modulo){
        try {
            moduloDAO.crearModulo(modulo);
            System.out.println("Modulo creado");
        }catch (SQLException e){
            System.err.println("Error al crear el modulo: " + e.getSQLState());
        }
    }

    public Modulo buscarModulo(int id){
        try {
            Modulo modulo = moduloDAO.readModulo(id);
            if(modulo != null){
                System.out.println("Modulo encotrado: " + modulo.getNombre());
            }else{
                System.out.println("Modulo no encontrado");
            }
            return modulo;
        }catch (SQLException e){
            System.err.println("Error al buscar el modulo: " + e.getMessage());
            return  null;
        }
    }

    public void actualizarModulo(Modulo modulo){
        try {
            moduloDAO.updateModulo(modulo);
            System.out.println("Modulo actualizado");
        }catch (SQLException e){
            System.err.println("Error al actualizar el modulo: " + e.getMessage());
        }
    }

    public void eliminarModulo(int id){
        try {
            moduloDAO.deleteModulo(id);
            System.out.println("Modulo eliminado");
        }catch (SQLException e){
            System.out.println("Error al eliminar el modulo: " + e.getMessage());
        }
    }
}
