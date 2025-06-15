package menu;

import Servicio.NotaService;
import model.Nota;

import java.sql.Connection;
import java.util.Scanner;

public class menuNotas {
    private NotaService notaService;
    private Scanner scanner;

    public menuNotas(Connection connection) {
        this.notaService = new NotaService(connection);
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n===== MENÚ NOTAS =====");
            System.out.println("1. Crear nota");
            System.out.println("2. Leer nota");
            System.out.println("3. Actualizar nota");
            System.out.println("4. Eliminar nota");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> crearNota();
                case 2 -> leerNota();
                case 3 -> actualizarNota();
                case 4 -> eliminarNota();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void crearNota() {
        System.out.println("\n--- Crear Nota ---");
        System.out.print("Ingrese ID del detalle de matrícula (dm_id): ");
        int dmId = scanner.nextInt();
        System.out.print("Ingrese nota del primer parcial: ");
        double parcial1 = scanner.nextDouble();
        System.out.print("Ingrese nota del segundo parcial: ");
        double parcial2 = scanner.nextDouble();
        System.out.print("Ingrese nota del examen final: ");
        double examenFinal = scanner.nextDouble();
        scanner.nextLine(); // limpiar buffer

        double promedio = (parcial1 + parcial2 + examenFinal) / 3.0;
        boolean aprobado = promedio >= 7.0;

        Nota nota = new Nota(dmId, parcial1, parcial2, examenFinal, promedio);
        nota.setAprovado(aprobado);
        notaService.crearNota(nota);
    }

    private void leerNota() {
        System.out.println("\n--- Leer Nota ---");
        System.out.print("Ingrese ID de la nota (no_id): ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Nota nota = notaService.leerNota(id);
        if (nota != null) {
            System.out.println("ID Nota: " + nota.getId());
            System.out.println("Detalle Matrícula ID: " + nota.getIdDetalleMatricula());
            System.out.println("Parcial 1: " + nota.getPacrial1());
            System.out.println("Parcial 2: " + nota.getPacrial2());
            System.out.println("Examen Final: " + nota.getExamenFinal());
            System.out.println("Promedio: " + nota.getPromedio());
            System.out.println("¿Aprobado?: " + (nota.isAprovado() ? "Sí" : "No"));
        }
    }

    private void actualizarNota() {
        System.out.println("\n--- Actualizar Nota ---");
        System.out.print("Ingrese ID de la nota a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Nota existente = notaService.leerNota(id);
        if (existente == null) {
            System.out.println("No se encontró la nota con ID: " + id);
            return;
        }

        System.out.print("Nuevo ID detalle matrícula (dm_id): ");
        int dmId = scanner.nextInt();
        System.out.print("Nueva nota parcial 1: ");
        double parcial1 = scanner.nextDouble();
        System.out.print("Nueva nota parcial 2: ");
        double parcial2 = scanner.nextDouble();
        System.out.print("Nueva nota examen final: ");
        double examenFinal = scanner.nextDouble();
        scanner.nextLine();

        double promedio = (parcial1 + parcial2 + examenFinal) / 3.0;
        boolean aprobado = promedio >= 7.0;

        Nota nota = new Nota(dmId, parcial1, parcial2, examenFinal, promedio);
        nota.setAprovado(aprobado);
        nota.setId(id);

        notaService.actualizarNota(nota);
    }

    private void eliminarNota() {
        System.out.println("\n--- Eliminar Nota ---");
        System.out.print("Ingrese ID de la nota a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        notaService.eliminarNota(id);
    }
}
