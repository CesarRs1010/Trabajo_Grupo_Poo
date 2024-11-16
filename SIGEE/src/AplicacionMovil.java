public class AplicacionMovil {
    public void asignarEspacio(Estacionamiento espacio) {
        if (!espacio.isOcupado()) {
            espacio.ocuparEspacio();
            System.out.println("Espacio " + espacio.idEspacio + " asignado correctamente.");
        } else {
            System.out.println("Espacio " + espacio.idEspacio + " ya está ocupado, selecciona otro.");
        }
    }
}
