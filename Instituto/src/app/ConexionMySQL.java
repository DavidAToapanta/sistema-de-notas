package app;

import menu.*;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class ConexionMySQL {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/instituto_quito";
        String user = "root";
        String password = "admin"; // ← reemplaza con tu contraseña real

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            Scanner scanner = new Scanner(System.in);
            int opcion;

            do {
                System.out.println("\n===== MENÚ PRINCIPAL =====");
                System.out.println("1. Carreras");
                System.out.println("2. Detalle Matrículas");
                System.out.println("3. Docentes");
                System.out.println("4. Estudiantes");
                System.out.println("5. Materias");
                System.out.println("6. Matrículas");
                System.out.println("7. Módulos");
                System.out.println("8. Notas");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1 -> new menuCarreras(connection).mostrarMenu();
                    case 2 -> new MenuDetalleMatriculas(connection).mostrarMenu();
                    case 3 -> new menuDocentes(connection).mostrarMenu();
                    case 4 -> new menuEstudiantes(connection).mostrarMenu();
                    case 5 -> new menuMaterias(connection).mostrarMenu();
                    case 6 -> new menuMatriculas(connection).mostrarMenu();
                    case 7 -> new menuModulos(connection).mostrarMenu();
                    case 8 -> new menuNotas(connection).mostrarMenu();
                    case 0 -> System.out.println("Saliendo del sistema...");
                    default -> System.out.println("Opción inválida. Intente de nuevo.");
                }

            } while (opcion != 0);

        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }
}

