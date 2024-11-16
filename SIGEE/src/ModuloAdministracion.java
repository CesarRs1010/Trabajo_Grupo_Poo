public class ModuloAdministracion {
    private final int espaciosTotales;
    private int espaciosOcupados;

    public ModuloAdministracion(int espaciosTotales) {
        this.espaciosTotales = espaciosTotales;
        this.espaciosOcupados = 0;
    }

    public void incrementarEspaciosOcupados() {
        if (espaciosOcupados < espaciosTotales) {
            espaciosOcupados++;
        }
    }

    public void decrementarEspaciosOcupados() {
        if (espaciosOcupados > 0) {
            espaciosOcupados--;
        }
    }

    public void generarReporte() {
        System.out.println("Total de espacios: " + espaciosTotales);
        System.out.println("Espacios ocupados: " + espaciosOcupados);
        System.out.println("Espacios libres: " + (espaciosTotales - espaciosOcupados));
    }
}
