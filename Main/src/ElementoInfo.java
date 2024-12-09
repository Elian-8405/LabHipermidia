public class ElementoInfo {
    private final String id;
    private final String titulo;
    private int ocorrencias;
    private boolean apareceNoTitulo;

    public ElementoInfo(String id, String titulo) {
        this.id = id;
        this.titulo = titulo;

    }

    public void incrementarOcorrencias() {
        this.ocorrencias++;
    }

    public void setApareceNoTitulo(boolean apareceNoTitulo) {
        this.apareceNoTitulo = apareceNoTitulo;
    }

    public double calcularRelevancia(int totalPalavras) {
        double relevancia = (double) ocorrencias / totalPalavras;
        if (apareceNoTitulo) {
            relevancia += 1.0;
        }
        return relevancia;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getOcorrencias() {
        return ocorrencias;
    }

    public boolean isApareceNoTitulo() {
        return apareceNoTitulo;
    }
}

