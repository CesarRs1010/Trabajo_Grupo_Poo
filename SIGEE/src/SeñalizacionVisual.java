public class SeñalizacionVisual {
    public void mostrarEstado(Estacionamiento espacio) {
        if (espacio.isOcupado()) {
            System.out.println("Luz Roja: Espacio " + espacio.idEspacio + " está ocupado.");
        } else {
            System.out.println("Luz Verde: Espacio " + espacio.idEspacio + " está disponible.");
        }
    }
}
