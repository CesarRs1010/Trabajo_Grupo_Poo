public class EstacionamientoMai {
    public static void main(String[] args) throws Exception {
        SensorOcupacion sensor = new SensorOcupacion();
        AplicacionMovil app = new AplicacionMovil();
        EspacioMoto espacio1 = new EspacioMoto("M1");
        ModuloAdministracion admin = new ModuloAdministracion(10);
        SeñalizacionVisual señal = new SeñalizacionVisual();

        // Probar la asignacion de espacio
        sensor.detectarOcupacion();
        app.asignarEspacio(espacio1);
        señal.mostrarEstado(espacio1);
        señal.mostrarDetalleEstado(espacio1); // Mostrar detalles adicionales del estado del espacio

        // Parte para la liberacion de espacio
        sensor.detectarLibreracion();
        espacio1.liberarEspacio();
        señal.mostrarEstado(espacio1);
        señal.mostrarDetalleEstado(espacio1); // Mostrar detalles adicionales del estado del espacio

        // Parte en la que se va a generar un reporte
        admin.incrementarEspaciosOcupados();
        admin.generarReporte();

        // Mostrar estadísticas completas al final
        señal.mostrarEstadisticas(espacio1, admin);
    }
}
