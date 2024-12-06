public abstract class Estacionamiento {
    private final String idEspacio;
    protected boolean ocupado;

    public Estacionamiento(String idEspacio) {
        this.idEspacio = idEspacio;
        this.ocupado = false;
    }

    public abstract void ocuparEspacio();
    public abstract void liberarEspacio();

    public boolean isOcupado() {
        return ocupado;
    }

    public String getIdEspacio() {
        return idEspacio;
    }

    // Método para obtener el tiempo desde la última liberación del espacio
    public int obtenerTiempoDesdeUltimaLiberacion() {
        System.out.println("Calculando el tiempo desde la última liberación para el espacio: " + idEspacio);
        return 10; // Retorna un valor de ejemplo
    }
}
