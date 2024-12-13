import java.sql.Timestamp;

public class Moto {
    private final String placa;  // Placa única de la moto
    private final String espacio; // Espacio asignado a la moto
    private final Timestamp tiempoEntrada; // Tiempo de entrada al estacionamiento

    public Moto(String placa, String espacio, Timestamp tiempoEntrada) {
        this.placa = placa;
        this.espacio = espacio;
        this.tiempoEntrada = tiempoEntrada;
    }

    public String getPlaca() {
        return placa;
    }

    public String getEspacio() {
        return espacio;
    }

    public Timestamp getTiempoEntrada() {
        return tiempoEntrada;
    }

    @Override
    public String toString() {
        return "Moto{" +
                "placa='" + placa + '\'' +
                ", espacio='" + espacio + '\'' +
                ", tiempoEntrada=" + tiempoEntrada +
                '}';
    }
}
