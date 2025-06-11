package Servicio;

import dao.MateriaDAO;
import model.Materia;

import java.sql.Connection;
import java.sql.SQLException;

public class MateriaService {
    private MateriaDAO materiaDAO;

    public MateriaService(Connection connection){this.materiaDAO = new MateriaDAO(connection);}

    public void crearMateria(Materia materia){
        try {
           materiaDAO.crearMateria(materia);
            System.out.println("Materia creada correctamente");
        }catch (SQLException e){
            System.err.println("Error al crear la materia: " + e.getMessage());
        }
    }

    public Materia buscarMateria(int id){
        try {
            Materia materia = materiaDAO.readMateria(id);
            if(materia != null){
                System.out.println("Materia encontrada correctamente: " + materia.getNombre());
            }else{
                System.out.println("La materia no fue encontrada con el id: " + id);
            }
            return materia;
        }catch (SQLException e){
            System.err.println("Error al buscar la materia: " + e.getMessage());
            return null;
        }
    }

    public void actualizarMateria(Materia materia){
        try{
            materiaDAO.updateMateria(materia);
            System.out.println("Materia actualizada correctamente");
        }catch (SQLException e){
            System.err.println("Error al actulizar la materia: " + e.getMessage());
        }
    }

    public void eliminarMateria(int id){
        try {
            materiaDAO.deleteMateria(id);
            System.out.println("Materia eliminada correctamente");
        }catch (SQLException e){
            System.out.println("Error al elimina la materia: " + e.getMessage());
        }
    }
}
