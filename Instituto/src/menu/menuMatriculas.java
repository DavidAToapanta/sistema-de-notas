package menu;

import Servicio.MatriculaService;
import model.Matricula;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Scanner;

public class menuMatriculas {
    private MatriculaService matriculaService;
    private Scanner scanner;

    public menuMatriculas(Connection connection) {
        this.matriculaService = new MatriculaService(connection);
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("\n===== MENÚ DE MATRÍCULAS =====");
            System.out.println("1. Crear matrícula");
            System.out.println("2. Leer matrícula");
            System.out.println("3. Actualizar matrícula");
            System.out.println("4. Eliminar matrícula");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar el buffer

            switch (opcion) {
                case 1 -> crearMatricula();
                case 2 -> leerMatricula();
                case 3 -> actualizarMatricula();
                case 4 -> eliminarMatricula();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida, intente nuevamente.");
            }
        } while (opcion != 0);
    }

    private void crearMatricula() {
        System.out.println("\n--- Crear Matrícula ---");

        LocalDate fecha = leerFecha("Ingrese la fecha (AAAA-MM-DD): ");
        System.out.print("Ingrese el periodo: ");
        String periodo = scanner.nextLine();

        System.out.print("Ingrese ID de la carrera: ");
        int idCarrera = scanner.nextInt();

        System.out.print("Ingrese ID del estudiante: ");
        int idEstudiante = scanner.nextInt();
        scanner.nextLine(); // limpiar el buffer

        Matricula matricula = new Matricula(fecha, periodo, idCarrera, idEstudiante);
        matriculaService.crearMatricula(matricula);
    }

    private void leerMatricula() {
        System.out.println("\n--- Leer Matrícula ---");
        System.out.print("Ingrese el ID de la matrícula: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer

        Matricula matricula = matriculaService.leerMatricula(id);
        if (matricula != null) {
            System.out.println("ID: " + matricula.getId());
            System.out.println("Fecha: " + matricula.getFecha());
            System.out.println("Periodo: " + matricula.getPeriodo());
            System.out.println("ID Carrera: " + matricula.getIdCarrera());
            System.out.println("ID Estudiante: " + matricula.getIdEstudiante());
        }
    }

    private void actualizarMatricula() {
        System.out.println("\n--- Actualizar Matrícula ---");
        System.out.print("Ingrese el ID de la matrícula a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer

        Matricula existente = matriculaService.leerMatricula(id);
        if (existente == null) {
            System.out.println("No se encontró la matrícula con ID: " + id);
            return;
        }

        LocalDate fecha = leerFecha("Ingrese la nueva fecha (AAAA-MM-DD): ");
        System.out.print("Ingrese el nuevo periodo: ");
        String periodo = scanner.nextLine();
        System.out.print("Ingrese el nuevo ID de carrera: ");
        int idCarrera = scanner.nextInt();
        System.out.print("Ingrese el nuevo ID de estudiante: ");
        int idEstudiante = scanner.nextInt();
        scanner.nextLine();

        Matricula actualizada = new Matricula(fecha, periodo, idCarrera, idEstudiante);
        actualizada.setId(id);
        matriculaService.actualizarMatricula(actualizada);
    }

    private void eliminarMatricula() {
        System.out.println("\n--- Eliminar Matrícula ---");
        System.out.print("Ingrese el ID de la matrícula a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer

        matriculaService.eliminarMatricula(id);
    }

    private LocalDate leerFecha(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return LocalDate.parse(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Formato de fecha inválido. Intente de nuevo.");
            }
        }
    }

}
