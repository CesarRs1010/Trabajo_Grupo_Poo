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
        // Código para manejar las opciones seleccionadas por el usuario
        
        if (salirApp == 1) {
            System.out.println("\n********************  ¿Desea salir de la simulación? ********************");
            System.out.println("1) Sí\n2) No\n3) Iniciar otra simulación\n4) Limpiar datos de simulación");
            int salirSimulacion = obtenerEntradaInt();
            switch (salirSimulacion) {
                case 4:
                    System.out.println("Limpiando datos de simulación...");
                    datosSimulacion.limpiarDatos();
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
