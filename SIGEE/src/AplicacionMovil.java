import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

//La clase se actualiza para integrar la persistencia de datos con la base de datos.
public class AplicacionMovil {
    private final Scanner scanner = new Scanner(System.in);
    private final SensorOcupacion sensor = new SensorOcupacion();
    private final BaseDatos baseDatos = new BaseDatos(); //Nueva instancia para interactuar con la base de datos.

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
        int opcion;
        do {
            System.out.println("\nIngresar a la aplicación:\n1) Ingresar\n2) Salir");
            opcion = obtenerEntradaInt();

            if (opcion == 1) {
                ejecutarOpciones(datosSimulacion);
            } else if (opcion != 2) {
                System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 2);
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

        //Se actualiza el menú para preguntar sobre la salida de la aplicación.
        System.out.println("\n¿Desea salir de la aplicación?\n1) Sí\n2) No");
        int salirApp = obtenerEntradaInt();
        if (salirApp == 1) {
            mostrarMenuSalir(datosSimulacion);
        }
    }

    private void mostrarMenuSalir(DatosSimulacion datosSimulacion) {
        System.out.println("\n******************** ¿Desea salir de la simulación? ********************");
        System.out.println("1) Sí\n2) No\n3) Iniciar otra simulación\n4) Limpiar datos de simulación");
        int opcion = obtenerEntradaInt();
        switch (opcion) {
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
                mostrarMenuOpciones(datosSimulacion);
                break;
            case 4:
                System.out.println("Limpiando datos de simulación...");
                datosSimulacion.limpiarDatos();
                iniciarAplicacion(datosSimulacion);
                break;
            default:
                System.out.println("Opción no válida. Intente nuevamente.");
                mostrarMenuSalir(datosSimulacion);
        }
    }

    //Método para asignar espacio e integrar persistencia de datos.
    public void asignarEspacio(List<Estacionamiento> espacios, String placa, ModuloAdministracion admin) {
        for (Estacionamiento espacio : espacios) {
            if (!espacio.isOcupado()) {
                espacio.ocuparEspacio();
                System.out.println("Espacio " + espacio.getIdEspacio() + " ha sido asignado a la moto con placa: " + placa);
                admin.incrementarEspaciosOcupados();
                sensor.detectarOcupacion();

                //Actualizar estado en la base de datos y registrar la moto
                baseDatos.actualizarEstadoEspacio(espacio.getIdEspacio(), true);
                baseDatos.registrarMoto(placa, espacio.getIdEspacio(), new Timestamp(new Date().getTime()));

                //Registrar el evento en el historial
                registrarHistorial(placa, espacio.getIdEspacio(), "Ocupado");
                return;
            }
        }
        System.out.println("No hay espacios disponibles.");
    }

    public void mostrarReporteAplicacionMovil(ModuloAdministracion admin) {
        System.out.println("\n[REPORTE DE APLICACIÓN MÓVIL] Estado del estacionamiento:");
        admin.generarReporte();
    }

    //Método para registrar un evento en el historial
    public void registrarHistorial(String placa, String idEspacio, String evento) {
        baseDatos.registrarHistorial(placa, idEspacio, evento);
        System.out.println("Historial actualizado: " + evento + " para el espacio " + idEspacio + (placa != null ? " con placa " + placa : ""));
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
