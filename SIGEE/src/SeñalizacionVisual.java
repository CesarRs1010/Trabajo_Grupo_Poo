import java.util.List;
import java.util.stream.Collectors;

public class SeñalizacionVisual {
    public void mostrarEstado(Estacionamiento espacio) {
        if (espacio.isOcupado()) {
            System.out.println("Luz Roja: Espacio " + espacio.getIdEspacio() + " está ocupado.");
        } else {
            System.out.println("Luz Verde: Espacio " + espacio.getIdEspacio() + " está disponible.");
        }
    }

    public void mostrarDetalleEstado(Estacionamiento espacio) {
        if (espacio.isOcupado()) {
            System.out.println("[DETALLE] Espacio " + espacio.getIdEspacio()
                    + " está ocupado. Luz Roja encendida. Tiempo de ocupación estimado: "
                    + obtenerTiempoOcupacion(espacio) + " minutos.");
        } else {
            System.out.println("[DETALLE] Espacio " + espacio.getIdEspacio()
                    + " está disponible. Luz Verde encendida. Última liberación hace: "
                    + espacio.obtenerTiempoDesdeUltimaLiberacion() + " minutos."); // Llamada corregida
        }
    }

    private int obtenerTiempoOcupacion(Estacionamiento espacio) {
        System.out.println("Calculando el tiempo de ocupación para el espacio: " + espacio.getIdEspacio());
        return 15; // Retorna un valor de ejemplo
    }

    public void mostrarEspaciosDisponibles(List<Estacionamiento> espacios) {
        List<Estacionamiento> espaciosLibres = espacios.stream()
                .filter(espacio -> !espacio.isOcupado())
                .collect(Collectors.toList());

        System.out.println("Espacios disponibles:");
        espaciosLibres.forEach(espacio -> System.out.println("Espacio " + espacio.getIdEspacio() + " está disponible."));
    }
}
