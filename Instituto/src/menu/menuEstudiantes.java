package menu;

import Servicio.EstudianteService;
import model.Estudiante;

import java.sql.Connection;
import java.util.Scanner;

public class menuEstudiantes {
    private EstudianteService estudianteService;
    private Scanner scanner;

    public menuEstudiantes(Connection connection) {
        this.estudianteService = new EstudianteService(connection);
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE ESTUDIANTES ===");
            System.out.println("1. Crear Estudiante");
            System.out.println("2. Buscar Estudiante");
            System.out.println("3. Actualizar Estudiante");
            System.out.println("4. Eliminar Estudiante");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    crearEstudiante();
                    break;
                case 2:
                    buscarEstudiante();
                    break;
                case 3:
                    actualizarEstudiante();
                    break;
                case 4:
                    eliminarEstudiante();
                    break;
                case 5:
                    System.out.println("Saliendo del menú de estudiantes...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 5);
    }

    private void crearEstudiante() {
        System.out.println("\n--- CREAR ESTUDIANTE ---");

        System.out.print("Tipo de identificación (CC/TI/CE/PASAPORTE): ");
        String tipoIdentificacion = scanner.nextLine();

        System.out.print("Número de identificación: ");
        String identificacion = scanner.nextLine();

        System.out.print("Nombres: ");
        String nombres = scanner.nextLine();

        System.out.print("Apellidos: ");
        String apellidos = scanner.nextLine();

        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();

        System.out.print("Estado civil (Soltero/Casado/Divorciado/Viudo): ");
        String estadoCivil = scanner.nextLine();

        System.out.print("Número de cargas familiares: ");
        int cargasFamiliares = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        System.out.print("País: ");
        String pais = scanner.nextLine();

        System.out.print("Ciudad: ");
        String ciudad = scanner.nextLine();

        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();

        System.out.print("¿Trabaja? (s/n): ");
        String trabajaStr = scanner.nextLine();
        boolean trabaja = trabajaStr.equalsIgnoreCase("s") || trabajaStr.equalsIgnoreCase("sí");

        String lugarTrabajo = "";
        if (trabaja) {
            System.out.print("Lugar de trabajo: ");
            lugarTrabajo = scanner.nextLine();
        }

        Estudiante estudiante = new Estudiante(tipoIdentificacion, identificacion, nombres, apellidos,
                direccion, estadoCivil, cargasFamiliares, pais, ciudad,
                telefono, trabaja, lugarTrabajo);
        estudianteService.crearEstudiante(estudiante);
    }

    private void buscarEstudiante() {
        System.out.println("\n--- BUSCAR ESTUDIANTE ---");
        System.out.print("Ingrese el ID del estudiante a buscar: ");
        int id = scanner.nextInt();

        Estudiante estudiante = estudianteService.buscarEstudiante(id);
        if (estudiante != null) {
            mostrarInformacionEstudiante(estudiante);
        }
    }

    private void actualizarEstudiante() {
        System.out.println("\n--- ACTUALIZAR ESTUDIANTE ---");
        System.out.print("Ingrese el ID del estudiante a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        // Primero buscar si existe el estudiante
        Estudiante estudianteExistente = estudianteService.buscarEstudiante(id);
        if (estudianteExistente != null) {
            System.out.println("\nEstudiante actual:");
            mostrarInformacionEstudiante(estudianteExistente);

            System.out.println("\n--- INGRESE LOS NUEVOS DATOS ---");
            System.out.println("(Presione Enter para mantener el valor actual)");

            System.out.print("Tipo de identificación (actual: " + estudianteExistente.getTipoIdentificacion() + "): ");
            String nuevoTipoId = scanner.nextLine();
            if (nuevoTipoId.trim().isEmpty()) {
                nuevoTipoId = estudianteExistente.getTipoIdentificacion();
            }

            System.out.print("Número de identificación (actual: " + estudianteExistente.getIdentificacion() + "): ");
            String nuevaIdentificacion = scanner.nextLine();
            if (nuevaIdentificacion.trim().isEmpty()) {
                nuevaIdentificacion = estudianteExistente.getIdentificacion();
            }

            System.out.print("Nombres (actual: " + estudianteExistente.getNombres() + "): ");
            String nuevosNombres = scanner.nextLine();
            if (nuevosNombres.trim().isEmpty()) {
                nuevosNombres = estudianteExistente.getNombres();
            }

            System.out.print("Apellidos (actual: " + estudianteExistente.getApellidos() + "): ");
            String nuevosApellidos = scanner.nextLine();
            if (nuevosApellidos.trim().isEmpty()) {
                nuevosApellidos = estudianteExistente.getApellidos();
            }

            System.out.print("Dirección (actual: " + estudianteExistente.getDireccion() + "): ");
            String nuevaDireccion = scanner.nextLine();
            if (nuevaDireccion.trim().isEmpty()) {
                nuevaDireccion = estudianteExistente.getDireccion();
            }

            System.out.print("Estado civil (actual: " + estudianteExistente.getEstadoCivil() + "): ");
            String nuevoEstadoCivil = scanner.nextLine();
            if (nuevoEstadoCivil.trim().isEmpty()) {
                nuevoEstadoCivil = estudianteExistente.getEstadoCivil();
            }

            System.out.print("Cargas familiares (actual: " + estudianteExistente.getCargasFamiliares() + "): ");
            String cargasStr = scanner.nextLine();
            int nuevasCargasFamiliares = cargasStr.trim().isEmpty() ?
                    estudianteExistente.getCargasFamiliares() : Integer.parseInt(cargasStr);

            System.out.print("País (actual: " + estudianteExistente.getPais() + "): ");
            String nuevoPais = scanner.nextLine();
            if (nuevoPais.trim().isEmpty()) {
                nuevoPais = estudianteExistente.getPais();
            }

            System.out.print("Ciudad (actual: " + estudianteExistente.getCiudad() + "): ");
            String nuevaCiudad = scanner.nextLine();
            if (nuevaCiudad.trim().isEmpty()) {
                nuevaCiudad = estudianteExistente.getCiudad();
            }

            System.out.print("Teléfono (actual: " + estudianteExistente.getTelefono() + "): ");
            String nuevoTelefono = scanner.nextLine();
            if (nuevoTelefono.trim().isEmpty()) {
                nuevoTelefono = estudianteExistente.getTelefono();
            }

            System.out.print("¿Trabaja? (actual: " + (estudianteExistente.isTrabaja() ? "Sí" : "No") + ") (s/n): ");
            String trabajaStr = scanner.nextLine();
            boolean nuevoTrabaja = estudianteExistente.isTrabaja();
            if (!trabajaStr.trim().isEmpty()) {
                nuevoTrabaja = trabajaStr.equalsIgnoreCase("s") || trabajaStr.equalsIgnoreCase("sí");
            }

            String nuevoLugarTrabajo = estudianteExistente.getLugarTrabajo();
            if (nuevoTrabaja) {
                System.out.print("Lugar de trabajo (actual: " + estudianteExistente.getLugarTrabajo() + "): ");
                String lugarTrabajoStr = scanner.nextLine();
                if (!lugarTrabajoStr.trim().isEmpty()) {
                    nuevoLugarTrabajo = lugarTrabajoStr;
                }
            } else {
                nuevoLugarTrabajo = "";
            }

            Estudiante estudianteActualizado = new Estudiante(nuevoTipoId, nuevaIdentificacion, nuevosNombres,
                    nuevosApellidos, nuevaDireccion, nuevoEstadoCivil,
                    nuevasCargasFamiliares, nuevoPais, nuevaCiudad,
                    nuevoTelefono, nuevoTrabaja, nuevoLugarTrabajo);
            estudianteActualizado.setId(id);
            estudianteService.actualizarEstudiante(estudianteActualizado);
        }
    }

    private void eliminarEstudiante() {
        System.out.println("\n--- ELIMINAR ESTUDIANTE ---");
        System.out.print("Ingrese el ID del estudiante a eliminar: ");
        int id = scanner.nextInt();

        // Primero mostrar la información del estudiante antes de eliminar
        Estudiante estudiante = estudianteService.buscarEstudiante(id);
        if (estudiante != null) {
            System.out.println("\n¿Está seguro de eliminar al siguiente estudiante?");
            mostrarInformacionEstudiante(estudiante);

            System.out.print("\nConfirmar eliminación (s/n): ");
            scanner.nextLine(); // Limpiar buffer
            String confirmacion = scanner.nextLine();

            if (confirmacion.equalsIgnoreCase("s") || confirmacion.equalsIgnoreCase("sí")) {
                estudianteService.eliminarEstudiante(id);
            } else {
                System.out.println("Eliminación cancelada.");
            }
        }
    }

    private void mostrarInformacionEstudiante(Estudiante estudiante) {
        System.out.println("\n--- INFORMACIÓN DEL ESTUDIANTE ---");
        System.out.println("ID: " + estudiante.getId());
        System.out.println("Tipo de Identificación: " + estudiante.getTipoIdentificacion());
        System.out.println("Identificación: " + estudiante.getIdentificacion());
        System.out.println("Nombres: " + estudiante.getNombres());
        System.out.println("Apellidos: " + estudiante.getApellidos());
        System.out.println("Dirección: " + estudiante.getDireccion());
        System.out.println("Estado Civil: " + estudiante.getEstadoCivil());
        System.out.println("Cargas Familiares: " + estudiante.getCargasFamiliares());
        System.out.println("País: " + estudiante.getPais());
        System.out.println("Ciudad: " + estudiante.getCiudad());
        System.out.println("Teléfono: " + estudiante.getTelefono());
        System.out.println("Trabaja: " + (estudiante.isTrabaja() ? "Sí" : "No"));
        if (estudiante.isTrabaja() && estudiante.getLugarTrabajo() != null && !estudiante.getLugarTrabajo().isEmpty()) {
            System.out.println("Lugar de Trabajo: " + estudiante.getLugarTrabajo());
        }
    }
}
