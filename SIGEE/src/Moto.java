import java.sql.Timestamp;

public class Moto {
    private final String placa; // Placa única de la moto.
    private final String espacio; // Identificador del espacio asignado.
    private final Timestamp tiempoEntrada; // Tiempo de entrada al estacionamiento.

    // Constructor para inicializar una instancia de Moto.
    public Moto(String placa, String espacio, Timestamp tiempoEntrada) {
        this.placa = placa;
        this.espacio = espacio;
        this.tiempoEntrada = tiempoEntrada;
    }

    // Getters para acceder a las propiedades de la moto.
    public String getPlaca() {
        return placa;
    }

    public String getEspacio() {
        return espacio;
    }

    public Timestamp getTiempoEntrada() {
        return tiempoEntrada;
    }

    // Método toString para representar la información de la moto como texto.
    @Override
    public String toString() {
        return "Moto{" +
                "placa='" + placa + '\'' +
                ", espacio='" + espacio + '\'' +
                ", tiempoEntrada=" + tiempoEntrada +
                '}';
    }
}