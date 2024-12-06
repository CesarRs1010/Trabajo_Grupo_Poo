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
