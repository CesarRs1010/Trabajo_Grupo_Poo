import java.util.List;
import java.util.Scanner;

public class AplicacionMovil {
    private final Scanner scanner = new Scanner(System.in);
    private final SensorOcupacion sensor = new SensorOcupacion();

    public void iniciarAplicacion(DatosSimulacion datosSimulacion) {
        System.out.println("\n******************* Bienvenidos a la cochera UTP *******************");
        mostrarMenuOpciones(datosSimulacion);
    }

    public String registrarMoto() {
        System.out.print("Número de placa: ");
        String placa = scanner.nextLine();
        while (placa.isEmpty()) {
            System.out.println("La placa no puede estar vacía. Por favor, ingresa un número de placa válido:");
            placa = scanner.nextLine();
        }
        return placa;
    }

    public void mostrarMenuOpciones(DatosSimulacion datosSimulacion) {
        int ingresar;
        do {
            System.out.println("\nIngresar a la aplicación:\n1) Ingresar\n2) Salir");
            ingresar = obtenerEntradaInt();

            if (ingresar == 1) {
                ejecutarOpciones(datosSimulacion);
            } else if (ingresar != 2) {
                System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (ingresar != 2);
    }

    private void ejecutarOpciones(DatosSimulacion datosSimulacion) {
        List<Estacionamiento> espacios = datosSimulacion.getEspacios();
        ModuloAdministracion admin = datosSimulacion.getAdmin();

        String placa = registrarMoto();
        System.out.println("--------- Espacios disponibles por el momento: " + admin.getEspaciosLibres() + " ---------");

        System.out.println("\nTiempo de espera predeterminado: 1 hora aproximadamente.");
        System.out.println("¿Desea cambiar el tiempo de espera? (1) Sí (2) No");
        int cambiarTiempo = obtenerEntradaInt();

        if (cambiarTiempo == 1) {
            solicitarTiempo();
        } else {
            System.out.println("Tiempo asignado: 1 hora.");
        }

        asignarEspacio(espacios, placa, admin);
        mostrarReporteAplicacionMovil(admin);

        // Preguntar si desea salir de la aplicación
        System.out.println("\n¿Desea salir de la aplicación?\n1) Sí\n2) No");
        int salirApp = obtenerEntradaInt();
        if (salirApp == 1) {
            // Preguntar si desea salir de la simulación o repetirla
            System.out.println("\n********************  ¿Desea salir de la simulación? ********************");
            System.out.println("1) Sí\n2) No\n3) Iniciar otra simulación\n4) Limpiar datos de simulación");
            int salirSimulacion = obtenerEntradaInt();
            switch (salirSimulacion) {
                case 1:
                    System.out.println("\n##########################################");
                    System.out.println("******************** Hasta Pronto ********************");
                    System.out.println("##########################################");
                    System.exit(0);
                case 2:
                    System.out.println("Continuando con la aplicación...");
                    mostrarMenuOpciones(datosSimulacion);
                    break;
                case 3:
                    System.out.println("Iniciando otra simulación...");
                    mostrarMenuOpciones(datosSimulacion); // Aquí se mantiene el estado de los espacios
                    break;
                case 4:
                    System.out.println("Limpiando datos de simulación...");
                    datosSimulacion.limpiarDatos(); // Resetear todos los datos de la simulación
                    iniciarAplicacion(datosSimulacion);
                    break;
                default:
                    break;
            }
        }
    }

    public void asignarEspacio(List<Estacionamiento> espacios, String placa, ModuloAdministracion admin) {
        for (Estacionamiento espacio : espacios) {
            if (!espacio.isOcupado()) {
                espacio.ocuparEspacio();
                System.out.println("Espacio " + espacio.getIdEspacio() + " ha sido asignado a la moto con placa: " + placa);
                admin.incrementarEspaciosOcupados();
                sensor.detectarOcupacion();
                return;
            }
        }
        System.out.println("No hay espacios disponibles.");
    }

    public void mostrarReporteAplicacionMovil(ModuloAdministracion admin) {
        System.out.println("\n[REPORTE DE APLICACIÓN MÓVIL] Estado del estacionamiento:");
        admin.generarReporte();
    }

    private void solicitarTiempo() {
        System.out.print("Por favor, ingresa el tiempo requerido en minutos: ");
        int tiempo = obtenerEntradaInt();
        while (tiempo <= 0) {
            System.out.println("El tiempo debe ser mayor que 0. Inténtalo de nuevo:");
            tiempo = obtenerEntradaInt();
        }
        System.out.println("Tiempo asignado: " + tiempo + " minutos.");
    }

    private int obtenerEntradaInt() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, ingresa un número.");
                scanner.next(); // Consumir la entrada no válida
            }
        }
    }
}
