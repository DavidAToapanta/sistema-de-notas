package Servicio;

import dao.CarreraDAO;
import model.Carrera;

import java.sql.Connection;
import java.sql.SQLException;

public class CarreraService {
    private CarreraDAO carreraDAO;

    public CarreraService(Connection connection) {this.carreraDAO = new CarreraDAO(connection);}


    public void crearCarrera(Carrera carrera) {
        try {
            carreraDAO.create(carrera);
            System.out.println("La Carrera fue creada correctamente");
        }catch (SQLException E){
            System.out.println("Error al crear la carrera: " + E.getMessage());
        }
    }

     public Carrera leerCarrera(int id) {
         try {
             Carrera carrera = carreraDAO.read(id);
             if(carrera != null){
                 System.out.println("La Carrera fue encontrada: "  + carrera.getNombre());
             }else{
                 System.out.println("La Carrera no se encontro con el ID: " + id);
             }
             return carrera;
         }catch (SQLException E){
             System.out.println("Error al leer la carrera: " + E.getMessage());
             return null;
         }
     }

     public void actualizarCarrera(Carrera carrera) {
         try {
             carreraDAO.update(carrera);
             System.out.println("La Carrera fue actualizada correctamente");
         }catch (SQLException E){
             System.out.println("Error al actualizar la carrera: " + E.getMessage());
         }
     }

     public void eliminarCarrera(int id) {
         try {
             carreraDAO.delete(id);
             System.out.println("La Carrera fue eliminada correctamente");
         }catch (SQLException e){
             System.out.println("Error al eliminar la carrera: " + e.getMessage());
         }
     }

}


