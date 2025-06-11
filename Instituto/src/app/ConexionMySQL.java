package app;

import dao.ModuloDAO;
import model.Modulo;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionMySQL {

    public static void main(String[] args) {
        try {
            // 1. Conexión a la base de datos
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/instituto_quito", "root", "admin"
            );

            // 2. Crear instancia del DAO
            ModuloDAO moduloDAO = new ModuloDAO(conn);

            // 3. Crear un nuevo módulo
         
            // 4. Leer módulo insertado
            Modulo moduloLeido = moduloDAO.readModulo(3); // Cambia el ID según tu base
            if (moduloLeido != null) {
                System.out.println("📦 Módulo leído:");
                System.out.println("ID: " + moduloLeido.getId());
                System.out.println("Nombre: " + moduloLeido.getNombre());
                System.out.println("Nro. Materias: " + moduloLeido.getNumeroMateria());
                System.out.println("Carrera ID: " + moduloLeido.getIdCarrera());
            } else {
                System.out.println("⚠️ Módulo no encontrado");
            }

            // 5. Actualizar el módulo
           

            // 6. Borrar módulo

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}

