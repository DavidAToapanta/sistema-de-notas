package menu;

import Servicio.DetalleMatriculaService;
import model.DetalleMatricula;

import java.sql.Connection;
import java.util.Scanner;

public class MenuDetalleMatriculas {
    private final DetalleMatriculaService detalleService;
    private final Scanner scanner;

    public MenuDetalleMatriculas(Connection connection) {
        this.detalleService = new DetalleMatriculaService(connection);
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n===== MENÚ DETALLE MATRÍCULAS =====");
            System.out.println("1. Crear detalle de matrícula");
            System.out.println("2. Leer detalle de matrícula");
            System.out.println("3. Actualizar detalle de matrícula");
            System.out.println("4. Eliminar detalle de matrícula");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> crearDetalle();
                case 2 -> leerDetalle();
                case 3 -> actualizarDetalle();
                case 4 -> eliminarDetalle();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void crearDetalle() {
        System.out.println("\n--- Crear Detalle de Matrícula ---");
        System.out.print("Ingrese ID de matrícula (ma_id): ");
        int idMatricula = scanner.nextInt();
        System.out.print("Ingrese ID de materia (ma_id_materia): ");
        int idMateria = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer

        DetalleMatricula detalle = new DetalleMatricula(idMatricula, idMateria);
        detalleService.crearDetalleMatricula(detalle);
    }

    private void leerDetalle() {
        System.out.println("\n--- Leer Detalle de Matrícula ---");
        System.out.print("Ingrese ID del detalle (dm_id): ");
        int id = scanner.nextInt();
        scanner.nextLine();

        DetalleMatricula detalle = detalleService.leerDetalleMatricula(id);
        if (detalle != null) {
            System.out.println("ID Detalle: " + detalle.getId());
            System.out.println("ID Matrícula: " + detalle.getIdMatricula());
            System.out.println("ID Materia: " + detalle.getIdMateria());
        }
    }

    private void actualizarDetalle() {
        System.out.println("\n--- Actualizar Detalle de Matrícula ---");
        System.out.print("Ingrese ID del detalle a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        DetalleMatricula existente = detalleService.leerDetalleMatricula(id);
        if (existente == null) {
            System.out.println("No se encontró el detalle con ID: " + id);
            return;
        }

        System.out.print("Ingrese nuevo ID de matrícula: ");
        int nuevoIdMatricula = scanner.nextInt();
        System.out.print("Ingrese nuevo ID de materia: ");
        int nuevoIdMateria = scanner.nextInt();
        scanner.nextLine();

        DetalleMatricula actualizado = new DetalleMatricula(nuevoIdMatricula, nuevoIdMateria);
        actualizado.setId(id);
        detalleService.actualizarDetalleMatricula(actualizado);
    }

    private void eliminarDetalle() {
        System.out.println("\n--- Eliminar Detalle de Matrícula ---");
        System.out.print("Ingrese ID del detalle a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        detalleService.eliminarDetalleMatricula(id);
    }
}
