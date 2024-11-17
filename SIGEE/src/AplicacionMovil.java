public class AplicacionMovil {
    public void asignarEspacio(Estacionamiento espacio) {
        if (!espacio.isOcupado()) {
            espacio.ocuparEspacio();
            System.out.println("Espacio " + espacio.getIdEspacio() + " asignado correctamente.");
        } else {
            System.out.println("Espacio " + espacio.getIdEspacio() + " ya está ocupado, selecciona otro.");
        }
    }

    public static int obtenerTiempoDesdeUltimaLiberacion(Estacionamiento espacio) {
        System.out.println("Calculando el tiempo desde la última liberación para el espacio: " + espacio.getIdEspacio());
        // Simulación del cálculo del tiempo desde la última liberación.
        return 10; // Retorna un valor de ejemplo.
    }

    public void mostrarReporteAplicacionMovil(Estacionamiento espacio, ModuloAdministracion admin) {
        System.out.println("[REPORTE DE APLICACIÓN MÓVIL] Estado del estacionamiento:");
        System.out.println("[DETALLE] Espacio " + espacio.getIdEspacio() + " está disponible. Última liberación hace: " + obtenerTiempoDesdeUltimaLiberacion(espacio) + " minutos.");
        admin.generarReporte();
    }
}
