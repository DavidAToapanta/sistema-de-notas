package menu;

import Servicio.MateriaService;
import model.Materia;

import java.sql.Connection;
import java.util.Scanner;

public class menuMaterias {
    private MateriaService materiaService;
    private Scanner scanner;

    public menuMaterias(Connection connection) {
        this.materiaService = new MateriaService(connection);
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE MATERIAS ===");
            System.out.println("1. Crear Materia");
            System.out.println("2. Buscar Materia");
            System.out.println("3. Actualizar Materia");
            System.out.println("4. Eliminar Materia");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    crearMateria();
                    break;
                case 2:
                    buscarMateria();
                    break;
                case 3:
                    actualizarMateria();
                    break;
                case 4:
                    eliminarMateria();
                    break;
                case 5:
                    System.out.println("Saliendo del menú de materias...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 5);
    }

    private void crearMateria() {
        System.out.println("\n--- CREAR MATERIA ---");

        System.out.print("Ingrese el nombre de la materia: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el número de créditos: ");
        int numCreditos = scanner.nextInt();

        System.out.print("Ingrese la duración en días: ");
        int duracionDias = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        System.out.print("Ingrese el horario: ");
        String horario = scanner.nextLine();

        System.out.print("Ingrese el ID del docente: ");
        int idDocente = scanner.nextInt();

        System.out.print("Ingrese el ID del módulo: ");
        int idModulo = scanner.nextInt();

        Materia materia = new Materia(nombre, numCreditos, duracionDias, horario, idDocente, idModulo);
        materiaService.crearMateria(materia);
    }

    private void buscarMateria() {
        System.out.println("\n--- BUSCAR MATERIA ---");
        System.out.print("Ingrese el ID de la materia a buscar: ");
        int id = scanner.nextInt();

        Materia materia = materiaService.buscarMateria(id);
        if (materia != null) {
            mostrarInformacionMateria(materia);
        }
    }

    private void actualizarMateria() {
        System.out.println("\n--- ACTUALIZAR MATERIA ---");
        System.out.print("Ingrese el ID de la materia a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        // Primero buscar si existe la materia
        Materia materiaExistente = materiaService.buscarMateria(id);
        if (materiaExistente != null) {
            System.out.println("\nMateria actual:");
            mostrarInformacionMateria(materiaExistente);

            System.out.println("\n--- INGRESE LOS NUEVOS DATOS ---");
            System.out.println("(Presione Enter para mantener el valor actual)");

            System.out.print("Nuevo nombre (actual: " + materiaExistente.getNombre() + "): ");
            String nuevoNombre = scanner.nextLine();
            if (nuevoNombre.trim().isEmpty()) {
                nuevoNombre = materiaExistente.getNombre();
            }

            System.out.print("Nuevo número de créditos (actual: " + materiaExistente.getNumCreditos() + "): ");
            String creditosStr = scanner.nextLine();
            int nuevoNumCreditos = creditosStr.trim().isEmpty() ?
                    materiaExistente.getNumCreditos() : Integer.parseInt(creditosStr);

            System.out.print("Nueva duración en días (actual: " + materiaExistente.getDuracionDias() + "): ");
            String duracionStr = scanner.nextLine();
            int nuevaDuracionDias = duracionStr.trim().isEmpty() ?
                    materiaExistente.getDuracionDias() : Integer.parseInt(duracionStr);

            System.out.print("Nuevo horario (actual: " + materiaExistente.getHorario() + "): ");
            String nuevoHorario = scanner.nextLine();
            if (nuevoHorario.trim().isEmpty()) {
                nuevoHorario = materiaExistente.getHorario();
            }

            System.out.print("Nuevo ID del docente (actual: " + materiaExistente.getIdDocente() + "): ");
            String docenteStr = scanner.nextLine();
            int nuevoIdDocente = docenteStr.trim().isEmpty() ?
                    materiaExistente.getIdDocente() : Integer.parseInt(docenteStr);

            System.out.print("Nuevo ID del módulo (actual: " + materiaExistente.getIdModulo() + "): ");
            String moduloStr = scanner.nextLine();
            int nuevoIdModulo = moduloStr.trim().isEmpty() ?
                    materiaExistente.getIdModulo() : Integer.parseInt(moduloStr);

            Materia materiaActualizada = new Materia(nuevoNombre, nuevoNumCreditos, nuevaDuracionDias,
                    nuevoHorario, nuevoIdDocente, nuevoIdModulo);
            materiaActualizada.setId(id);
            materiaService.actualizarMateria(materiaActualizada);
        }
    }

    private void eliminarMateria() {
        System.out.println("\n--- ELIMINAR MATERIA ---");
        System.out.print("Ingrese el ID de la materia a eliminar: ");
        int id = scanner.nextInt();

        // Primero mostrar la información de la materia antes de eliminar
        Materia materia = materiaService.buscarMateria(id);
        if (materia != null) {
            System.out.println("\n¿Está seguro de eliminar la siguiente materia?");
            mostrarInformacionMateria(materia);

            System.out.print("\nConfirmar eliminación (s/n): ");
            scanner.nextLine(); // Limpiar buffer
            String confirmacion = scanner.nextLine();

            if (confirmacion.equalsIgnoreCase("s") || confirmacion.equalsIgnoreCase("sí")) {
                materiaService.eliminarMateria(id);
            } else {
                System.out.println("Eliminación cancelada.");
            }
        }
    }

    private void mostrarInformacionMateria(Materia materia) {
        System.out.println("\n--- INFORMACIÓN DE LA MATERIA ---");
        System.out.println("ID: " + materia.getId());
        System.out.println("Nombre: " + materia.getNombre());
        System.out.println("Número de créditos: " + materia.getNumCreditos());
        System.out.println("Duración en días: " + materia.getDuracionDias());
        System.out.println("Horario: " + materia.getHorario());
        System.out.println("ID Docente: " + materia.getIdDocente());
        System.out.println("ID Módulo: " + materia.getIdModulo());
    }
}
