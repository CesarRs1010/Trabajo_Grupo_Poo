public class SeñalizacionVisual {
    public void mostrarEstado(Estacionamiento espacio) {
        if (espacio.isOcupado()) {
            System.out.println("Luz Roja: Espacio " + espacio.idEspacio + " está ocupado.");
        } else {
            System.out.println("Luz Verde: Espacio " + espacio.idEspacio + " está disponible.");
        }
    }

    public void mostrarDetalleEstado(Estacionamiento espacio) {
        if (espacio.isOcupado()) {
            System.out.println("[DETALLE] Espacio " + espacio.idEspacio + " está ocupado. Luz Roja encendida. Tiempo de ocupación estimado: " + obtenerTiempoOcupacion(espacio) + " minutos.");
        } else {
            System.out.println("[DETALLE] Espacio " + espacio.idEspacio + " está disponible. Luz Verde encendida. Última liberación hace: " + obtenerTiempoDesdeUltimaLiberacion(espacio) + " minutos.");
        }
    }

    private int obtenerTiempoOcupacion(Estacionamiento espacio) {
        // Simulación del cálculo del tiempo de ocupación.
        return 15; // Retorna un valor de ejemplo.
    }

    private int obtenerTiempoDesdeUltimaLiberacion(Estacionamiento espacio) {
        // Simulación del cálculo del tiempo desde la última liberación.
        return 10; // Retorna un valor de ejemplo.
    }

    public void mostrarEstadisticas(Estacionamiento espacio, ModuloAdministracion admin) {
        System.out.println("[ESTADÍSTICAS] Estado del estacionamiento:");
        mostrarDetalleEstado(espacio);
        admin.generarReporte();
    }
}
