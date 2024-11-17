public class AplicacionMovil {
    public void asignarEspacio(Estacionamiento espacio) {
        if (!espacio.isOcupado()) {
            espacio.ocuparEspacio();
            System.out.println("Espacio " + espacio.getIdEspacio() + " asignado correctamente.");
        } else {
            System.out.println("Espacio " + espacio.getIdEspacio() + " ya está ocupado, selecciona otro.");
        }
    }
}
