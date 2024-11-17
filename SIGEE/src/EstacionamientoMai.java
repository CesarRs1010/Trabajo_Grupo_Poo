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
        admin.incrementarEspaciosOcupados();  // Incrementar espacios ocupados después de asignar
        señal.mostrarEstado(espacio1);
        señal.mostrarDetalleEstado(espacio1);

        // Parte para la liberacion de espacio
        sensor.detectarLibreracion();
        espacio1.liberarEspacio();
        admin.decrementarEspaciosOcupados();  // Decrementar espacios ocupados después de liberar
        señal.mostrarEstado(espacio1);
        señal.mostrarDetalleEstado(espacio1);

        // Parte en la que se va a generar un reporte desde AplicacionMovil
        app.mostrarReporteAplicacionMovil(espacio1, admin);
    }
}
