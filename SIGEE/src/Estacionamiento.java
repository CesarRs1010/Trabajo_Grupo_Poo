public class EstacionamientoMai {
    public static void main(String[] args) {
        DatosSimulacion datosSimulacion = new DatosSimulacion(10);  // Crear la simulación con 10 espacios
        AplicacionMovil app = new AplicacionMovil();
        app.iniciarAplicacion(datosSimulacion);
    }
}