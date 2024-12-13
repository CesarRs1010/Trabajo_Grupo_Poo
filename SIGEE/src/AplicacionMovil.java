import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

//La clase se actualiza para integrar la persistencia de datos con la base de datos y funcionalidades avanzadas.
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
            System.out.println("\nMenú Principal:");
            System.out.println("1) Registrar entrada de moto");
            System.out.println("2) Generar reporte del estacionamiento");
            System.out.println("3) Consultar historial");
            System.out.println("4) Salir");
            opcion = obtenerEntradaInt();

            switch (opcion) {
                case 1:
                    ejecutarOpciones(datosSimulacion);
                    break;
                case 2:
                    mostrarReporteAplicacionMovil(datosSimulacion.getAdmin());
                    break;
                case 3:
                    consultarHistorial();
                    break;
                case 4:
                    System.out.println("Gracias por usar la aplicación. ¡Hasta luego!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 4);
    }

    private void consultarHistorial() {
        System.out.println("\nConsultar historial:");
        System.out.println("1) Por tipo de evento");
        System.out.println("2) Por rango de fechas");
        System.out.println("3) Por espacio");
        int opcion = obtenerEntradaInt();
        try {
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el tipo de evento (Ocupado/Liberado): ");
                    String evento = scanner.nextLine();
                    mostrarHistorial(baseDatos.consultarHistorialPorEvento(evento));
                    break;
                case 2:
                    System.out.print("Ingrese fecha de inicio (YYYY-MM-DD HH:MM:SS): ");
                    Timestamp inicio = Timestamp.valueOf(scanner.nextLine());
                    System.out.print("Ingrese fecha de fin (YYYY-MM-DD HH:MM:SS): ");
                    Timestamp fin = Timestamp.valueOf(scanner.nextLine());
                    mostrarHistorial(baseDatos.consultarHistorialPorFecha(inicio, fin));
                    break;
                case 3:
                    System.out.print("Ingrese el ID del espacio: ");
                    String idEspacio = scanner.nextLine();
                    mostrarHistorial(baseDatos.consultarHistorialPorEspacio(idEspacio));
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } catch (Exception e) {
            System.out.println("Error al consultar historial: " + e.getMessage());
        }
    }

    private void mostrarHistorial(ResultSet historial) {
        try {
            System.out.println("\n[HISTORIAL]");
            while (historial != null && historial.next()) {
                System.out.println("ID: " + historial.getInt("ID") +
                                   ", Placa: " + historial.getString("Placa") +
                                   ", Espacio: " + historial.getString("Espacio") +
                                   ", Evento: " + historial.getString("Evento") +
                                   ", FechaHora: " + historial.getTimestamp("FechaHora"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    }

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
