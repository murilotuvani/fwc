package br.com.jcomputacao.fwc.dao;

import br.com.jcomputacao.fwc.model.MonServidor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 24/05/2011 14:53:39
 * @author Murilo
 */
public class ReportData {
    private static int idCount;
    private final int id;
    private Map<Integer, ReportElement> elements = new HashMap<Integer, ReportElement>();
    private List<MonServidor> servidores = new ArrayList<MonServidor>();
    
    public ReportData() {
        this.id = ++idCount;
    }

    public int getId() {
        return id;
    }
    
    public Map<Integer, ReportElement> getReportElements() {
        return elements;
    }

    public ReportElement addReportElement(ReportElement element) {
        element.setOrdem(this.elements.size());
        return this.elements.put(element.getId(), element);
    }

    public void addServidor(MonServidor s) {
        this.servidores.add(s);
    }

    public List<MonServidor> getServidores() {
        return this.servidores;
    }
}
