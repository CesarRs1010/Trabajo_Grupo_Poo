public abstract class Estacionamiento {
    private String idEspacio;  // Cambiado de protected a private
    protected boolean ocupado;

    public Estacionamiento(String idEspacio) {
        this.idEspacio = idEspacio;
        this.ocupado = false;
    }

    // Método abstracto que las subclases deben implementar
    public abstract void ocuparEspacio();
    public abstract void liberarEspacio();

    public boolean isOcupado() {
        return ocupado;
    }

    // Getter para idEspacio
    public String getIdEspacio() {
        return idEspacio;
    }
}
