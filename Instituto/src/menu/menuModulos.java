package menu;

import Servicio.CarreraService;
import Servicio.ModuloService;
import model.Modulo;

import java.sql.Connection;
import java.util.Scanner;

public class menuModulos {
    private final Scanner sc = new Scanner(System.in);
    private final ModuloService moduloService;
    private final CarreraService carreraService;

    public menuModulos(Connection connection) {
        this.moduloService = new ModuloService(connection);
        this.carreraService = new CarreraService(connection);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n----- GESTIÓN DE MÓDULOS -----");
            System.out.println("1. Crear nuevo módulo");
            System.out.println("2. Buscar módulo por ID");
            System.out.println("3. Actualizar módulo");
            System.out.println("4. Eliminar módulo");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            opcion = obtenerOpcionValida(0, 4);

            switch (opcion) {
                case 1 -> crearModulo();
                case 2 -> buscarModulo();
                case 3 -> actualizarModulo();
                case 4 -> eliminarModulo();
                case 0 -> System.out.println("Volviendo al menú principal...");
            }
        } while (opcion != 0);
    }

    private void crearModulo() {
        System.out.println("\n--- NUEVO MÓDULO ---");

        System.out.print("Nombre del módulo: ");
        String nombre = sc.nextLine();

        int numeroMaterias = obtenerEnteroValido("Número de materias: ", "Debe ser un número entero positivo", 1);

        int idCarrera = obtenerEnteroValido("ID de la carrera asociada: ", "ID inválido", 1);

        if (carreraService.leerCarrera(idCarrera) == null) {
            System.out.println("❌ No existe una carrera con ese ID");
            return;
        }

        Modulo nuevoModulo = new Modulo(nombre, numeroMaterias, idCarrera);
        moduloService.crearModulo(nuevoModulo);
    }

    private void buscarModulo() {
        System.out.println("\n--- BUSCAR MÓDULO ---");
        int id = obtenerEnteroValido("ID del módulo: ", "ID inválido", 1);

        Modulo modulo = moduloService.buscarModulo(id);
        if (modulo != null) {
            System.out.println("\n📋 Detalles del módulo:");
            System.out.println("ID: " + modulo.getId());
            System.out.println("Nombre: " + modulo.getNombre());
            System.out.println("Número de materias: " + modulo.getNumeroMateria());
            System.out.println("ID Carrera asociada: " + modulo.getIdCarrera());
        } else {
            System.out.println("❌ No se encontró el módulo con ID: " + id);
        }
    }

    private void actualizarModulo() {
        System.out.println("\n--- ACTUALIZAR MÓDULO ---");
        int id = obtenerEnteroValido("ID del módulo a actualizar: ", "ID inválido", 1);

        Modulo modulo = moduloService.buscarModulo(id);
        if (modulo == null) {
            System.out.println("❌ No existe un módulo con ese ID");
            return;
        }

        System.out.println("\nDatos actuales:");
        System.out.println("Nombre: " + modulo.getNombre());
        System.out.println("Número de materias: " + modulo.getNumeroMateria());
        System.out.println("ID Carrera: " + modulo.getIdCarrera());

        System.out.println("\nIngrese nuevos datos (deje vacío para mantener el valor actual):");

        System.out.print("Nuevo nombre [" + modulo.getNombre() + "]: ");
        String nuevoNombre = sc.nextLine();

        System.out.print("Nuevo número de materias [" + modulo.getNumeroMateria() + "]: ");
        String nuevoNumMateriasStr = sc.nextLine();

        System.out.print("Nuevo ID de carrera [" + modulo.getIdCarrera() + "]: ");
        String nuevoIdCarreraStr = sc.nextLine();

        if (!nuevoNombre.isEmpty()) {
            modulo.setNombre(nuevoNombre);
        }

        if (!nuevoNumMateriasStr.isEmpty()) {
            modulo.setNumeroMateria(Integer.parseInt(nuevoNumMateriasStr));
        }

        if (!nuevoIdCarreraStr.isEmpty()) {
            int nuevoIdCarrera = Integer.parseInt(nuevoIdCarreraStr);
            if (carreraService.leerCarrera(nuevoIdCarrera) != null) {
                modulo.setIdCarrera(nuevoIdCarrera);
            } else {
                System.out.println("⚠️ No se cambió la carrera: ID no existe");
            }
        }

        moduloService.actualizarModulo(modulo);
        System.out.println("✅ Módulo actualizado correctamente");
    }

    private void eliminarModulo() {
        System.out.println("\n--- ELIMINAR MÓDULO ---");
        int id = obtenerEnteroValido("ID del módulo a eliminar: ", "ID inválido", 1);

        System.out.print("¿Está seguro? (S/N): ");
        String confirmacion = sc.nextLine();

        if (confirmacion.equalsIgnoreCase("S")) {
            moduloService.eliminarModulo(id);
            System.out.println("✅ Módulo eliminado correctamente");
        } else {
            System.out.println("❌ Operación cancelada");
        }
    }

    // Métodos auxiliares
    private int obtenerOpcionValida(int min, int max) {
        while (true) {
            try {
                int opcion = Integer.parseInt(sc.nextLine());
                if (opcion >= min && opcion <= max) {
                    return opcion;
                }
                System.out.printf("Ingrese un valor entre %d y %d: ", min, max);
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Ingrese un número: ");
            }
        }
    }

    private int obtenerEnteroValido(String mensaje, String errorMsg, int minValor) {
        while (true) {
            try {
                System.out.print(mensaje);
                int valor = Integer.parseInt(sc.nextLine());
                if (valor >= minValor) {
                    return valor;
                }
                System.out.println("El valor debe ser mayor o igual a " + minValor);
            } catch (NumberFormatException e) {
                System.out.println(errorMsg);
            }
        }
    }
}
