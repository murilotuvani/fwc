package br.com.jcomputacao.fwc.dao;

/**
 * 28/06/2011 09:41:13
 * @author Murilo
 */
public class ReportElement implements Comparable<ReportElement>{
    private static int idCount;
    private final int id;
    private String titulo;
    private int ordem;
    private String analise;
    
    public ReportElement() {
        this.id = ++idCount;
    }

    public int getId() {
        return id;
    }

    public int getOrdem() {
        return ordem;
    }
    
    void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAnalise() {
        return analise;
    }

    public void setAnalise(String analise) {
        this.analise = analise;
    }
    
    @Override
    public int compareTo(ReportElement o) {
        return (this.id-o.id);
    }
}
