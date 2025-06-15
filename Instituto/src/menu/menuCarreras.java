package menu;

import Servicio.CarreraService;
import model.Carrera;

import java.sql.Connection;
import java.util.Scanner;

public class menuCarreras {
    private final CarreraService carreraService;

    // Constructor que recibe la conexión y crea el servicio
    public menuCarreras(Connection connection) {
        this.carreraService = new CarreraService(connection);
    }

    // Ya no es static
    public void mostrarMenu() {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n----- 📚 GESTIÓN DE CARRERAS -----");
            System.out.println("1. Crear nueva carrera");
            System.out.println("2. Buscar carrera por ID");
            System.out.println("3. Actualizar carrera");
            System.out.println("4. Eliminar carrera");
            System.out.println("5. Listar todas las carreras");
            System.out.println("0. Volver al menú principal");
            System.out.print("Elige una opción: ");

            opcion = obtenerOpcionValida(sc, 0, 5);

            switch (opcion) {
                case 1 -> crearCarrera(sc);
                case 2 -> buscarCarrera(sc);
                case 3 -> actualizarCarrera(sc);
                case 4 -> eliminarCarrera(sc);
                case 0 -> System.out.println("↩️ Volviendo al menú principal...");
            }
        } while (opcion != 0);
    }

    private void crearCarrera(Scanner sc) {
        System.out.println("\n--- ➕ CREAR NUEVA CARRERA ---");

        System.out.print("Nombre de la carrera: ");
        String nombre = sc.nextLine();

        int numModulos = obtenerEnteroValido(sc, "Número de módulos: ", "Debe ingresar un número válido");

        int tiempoDuracion = obtenerEnteroValido(sc, "Duración en meses: ", "Debe ingresar un número válido");

        Carrera nuevaCarrera = new Carrera(nombre, numModulos, tiempoDuracion);
        carreraService.crearCarrera(nuevaCarrera);
        System.out.println("✅ Carrera creada exitosamente.");
    }

    private void buscarCarrera(Scanner sc) {
        System.out.println("\n--- 🔍 BUSCAR CARRERA POR ID ---");
        int id = obtenerEnteroValido(sc, "Ingrese el ID de la carrera: ", "ID inválido");

        Carrera carrera = carreraService.leerCarrera(id);
        if (carrera != null) {
            System.out.println("\n📋 DETALLES DE LA CARRERA:");
            System.out.println("ID: " + carrera.getId());
            System.out.println("Nombre: " + carrera.getNombre());
            System.out.println("Número de módulos: " + carrera.getNumModulo());
            System.out.println("Duración (meses): " + carrera.getTiempoDuracion());
        } else {
            System.out.println("❌ No se encontró ninguna carrera con ese ID.");
        }
    }

    private void actualizarCarrera(Scanner sc) {
        System.out.println("\n--- ✏️ ACTUALIZAR CARRERA ---");
        int id = obtenerEnteroValido(sc, "Ingrese el ID de la carrera a actualizar: ", "ID inválido");

        Carrera carreraExistente = carreraService.leerCarrera(id);
        if (carreraExistente == null) {
            System.out.println("❌ No existe una carrera con ID: " + id);
            return;
        }

        System.out.println("\nDatos actuales:");
        System.out.println("Nombre: " + carreraExistente.getNombre());
        System.out.println("Módulos: " + carreraExistente.getNumModulo());
        System.out.println("Duración: " + carreraExistente.getTiempoDuracion());

        System.out.println("\nIngrese los nuevos datos (deje en blanco para mantener el valor actual):");

        System.out.print("Nuevo nombre: ");
        String nuevoNombre = sc.nextLine();

        System.out.print("Nuevo número de módulos (actual: " + carreraExistente.getNumModulo() + "): ");
        String nroModulosStr = sc.nextLine();

        System.out.print("Nueva duración en meses (actual: " + carreraExistente.getTiempoDuracion() + "): ");
        String duracionStr = sc.nextLine();

        if (!nuevoNombre.isEmpty()) {
            carreraExistente.setNombre(nuevoNombre);
        }
        if (!nroModulosStr.isEmpty()) {
            carreraExistente.setNumModulo(Integer.parseInt(nroModulosStr));
        }
        if (!duracionStr.isEmpty()) {
            carreraExistente.setTiempoDuracion(Integer.parseInt(duracionStr));
        }

        carreraService.actualizarCarrera(carreraExistente);
        System.out.println("✅ Carrera actualizada correctamente.");
    }

    private void eliminarCarrera(Scanner sc) {
        System.out.println("\n--- 🗑️ ELIMINAR CARRERA ---");
        int id = obtenerEnteroValido(sc, "Ingrese el ID de la carrera a eliminar: ", "ID inválido");

        Carrera carrera = carreraService.leerCarrera(id);
        if (carrera == null) {
            System.out.println("❌ No existe una carrera con ese ID.");
            return;
        }

        System.out.print("¿Está seguro que desea eliminar esta carrera? (S/N): ");
        String confirmacion = sc.nextLine();

        if (confirmacion.equalsIgnoreCase("S")) {
            carreraService.eliminarCarrera(id);
            System.out.println("✅ Carrera eliminada correctamente.");
        } else {
            System.out.println("❌ Operación cancelada.");
        }
    }


    private int obtenerOpcionValida(Scanner sc, int min, int max) {
        int opcion;
        while (true) {
            try {
                opcion = Integer.parseInt(sc.nextLine());
                if (opcion >= min && opcion <= max) {
                    return opcion;
                }
                System.out.printf("Ingrese un número entre %d y %d: ", min, max);
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Ingrese un número: ");
            }
        }
    }

    private int obtenerEnteroValido(Scanner sc, String mensaje, String errorMsg) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(errorMsg);
            }
        }
    }
}


