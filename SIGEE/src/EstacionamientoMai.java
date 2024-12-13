public class EstacionamientoMai {
    public static void main(String[] args) {
        // Crear la instancia de DatosSimulacion, que ahora carga los datos desde la base de datos
        DatosSimulacion datosSimulacion = new DatosSimulacion();

        // Crear e iniciar la aplicación móvil
        AplicacionMovil app = new AplicacionMovil();
        app.iniciarAplicacion(datosSimulacion);
    }
}
