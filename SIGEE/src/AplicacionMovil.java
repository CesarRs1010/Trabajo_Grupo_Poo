import java.util.List;
import java.util.Scanner;

public class AplicacionMovil {
    private final Scanner scanner = new Scanner(System.in);
    private final SensorOcupacion sensor = new SensorOcupacion();

    public void iniciarAplicacion(DatosSimulacion datosSimulacion) {
        System.out.println("\n******************* Bienvenidos a la cochera UTP *******************");
        mostrarMenuOpciones(datosSimulacion);
    }

    // Agregado método para mostrar menú
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
        System.out.println("\nElija una opción:\n1) Asignar Espacio\n2) Liberar Espacio\n3) Generar Reporte");
        int opcion = obtenerEntradaInt();
    
        switch (opcion) {
            case 1:
                System.out.print("Ingrese el número de placa de la moto: ");
                String placa = scanner.next();
                asignarEspacio(datosSimulacion.getEspacios(), placa, datosSimulacion.getAdmin());
                break;
            case 2:
                System.out.print("Ingrese el ID del espacio a liberar: ");
                String idEspacio = scanner.next();
                liberarEspacio(datosSimulacion.getEspacios(), idEspacio);
                break;
            case 3:
                datosSimulacion.getAdmin().generarReporte();
                break;
            default:
                System.out.println("Opción no válida. Inténtalo de nuevo.");
                break;
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
