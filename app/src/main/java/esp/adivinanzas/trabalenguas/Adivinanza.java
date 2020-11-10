package esp.adivinanzas.trabalenguas;

public class Adivinanza {
    private String section;
    private String adivinanza;
    private String solucion;

    public Adivinanza(String section, String adivinanza, String solucion) {
        this.section = section;
        this.adivinanza = adivinanza;
        this.solucion = solucion;
    }

    public Adivinanza(){

    }

    public String getSection() {
        return section;
    }

    public String getAdivinanza() {
        return adivinanza;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setAdivinanza(String adivinanza) {
        this.adivinanza = adivinanza;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }
}


