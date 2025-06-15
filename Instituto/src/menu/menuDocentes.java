package menu;

import Servicio.DocenteService;
import model.Docente;

import java.sql.Connection;
import java.util.Scanner;

public class menuDocentes {
    private DocenteService docenteService;
    private Scanner scanner;

    public menuDocentes(Connection connection) {
        this.docenteService = new DocenteService(connection);
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE DOCENTES ===");
            System.out.println("1. Crear Docente");
            System.out.println("2. Buscar Docente");
            System.out.println("3. Actualizar Docente");
            System.out.println("4. Eliminar Docente");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    crearDocente();
                    break;
                case 2:
                    buscarDocente();
                    break;
                case 3:
                    actualizarDocente();
                    break;
                case 4:
                    eliminarDocente();
                    break;
                case 5:
                    System.out.println("Saliendo del menú de docentes...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 5);
    }

    private void crearDocente() {
        System.out.println("\n--- CREAR DOCENTE ---");
        System.out.print("Ingrese el nombre del docente: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el apellido del docente: ");
        String apellido = scanner.nextLine();

        Docente docente = new Docente(nombre, apellido);
        docenteService.crearDocente(docente);
    }

    private void buscarDocente() {
        System.out.println("\n--- BUSCAR DOCENTE ---");
        System.out.print("Ingrese el ID del docente a buscar: ");
        int id = scanner.nextInt();

        Docente docente = docenteService.buscarDocente(id);
        if (docente != null) {
            System.out.println("\n--- INFORMACIÓN DEL DOCENTE ---");
            System.out.println("ID: " + docente.getIdDocente());
            System.out.println("Nombre: " + docente.getNombre());
            System.out.println("Apellido: " + docente.getApellido());
        }
    }

    private void actualizarDocente() {
        System.out.println("\n--- ACTUALIZAR DOCENTE ---");
        System.out.print("Ingrese el ID del docente a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        // Primero buscar si existe el docente
        Docente docenteExistente = docenteService.buscarDocente(id);
        if (docenteExistente != null) {
            System.out.println("Docente actual:");
            System.out.println("Nombre: " + docenteExistente.getNombre());
            System.out.println("Apellido: " + docenteExistente.getApellido());

            System.out.print("Ingrese el nuevo nombre (actual: " + docenteExistente.getNombre() + "): ");
            String nuevoNombre = scanner.nextLine();
            if (nuevoNombre.trim().isEmpty()) {
                nuevoNombre = docenteExistente.getNombre();
            }

            System.out.print("Ingrese el nuevo apellido (actual: " + docenteExistente.getApellido() + "): ");
            String nuevoApellido = scanner.nextLine();
            if (nuevoApellido.trim().isEmpty()) {
                nuevoApellido = docenteExistente.getApellido();
            }

            Docente docenteActualizado = new Docente(nuevoNombre, nuevoApellido);
            docenteActualizado.setId(id);
            docenteService.actualizarDocente(docenteActualizado);
        }
    }

    private void eliminarDocente() {
        System.out.println("\n--- ELIMINAR DOCENTE ---");
        System.out.print("Ingrese el ID del docente a eliminar: ");
        int id = scanner.nextInt();

        // Primero mostrar la información del docente antes de eliminar
        Docente docente = docenteService.buscarDocente(id);
        if (docente != null) {
            System.out.println("¿Está seguro de eliminar al docente?");
            System.out.println("Nombre: " + docente.getNombre() + " " + docente.getApellido());
            System.out.print("Confirmar eliminación (s/n): ");
            scanner.nextLine(); // Limpiar buffer
            String confirmacion = scanner.nextLine();

            if (confirmacion.equalsIgnoreCase("s") || confirmacion.equalsIgnoreCase("sí")) {
                docenteService.eliminarDocente(id);
            } else {
                System.out.println("Eliminación cancelada.");
            }
        }
    }
}
