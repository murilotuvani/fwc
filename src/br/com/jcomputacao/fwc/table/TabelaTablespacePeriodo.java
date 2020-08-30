package br.com.jcomputacao.fwc.table;

import br.com.jcomputacao.fwc.model.MonBancoDados;
import br.com.jcomputacao.fwc.model.MonTablespace;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 27/06/2011 17:45:15
 * @author Murilo
 */
public class TabelaTablespacePeriodo extends Tabela {
    //TODO Criar configuracao para o tamanho da fonte nas tabelas
    private Map<String, TabelaTablespaceLinha> linhas = new TreeMap<String, TabelaTablespaceLinha>();
    
    public TabelaTablespacePeriodo(List<MonTablespace> lista, MonBancoDados banco) {
        setTitulo("Tablespaces em "+banco.getNomBancoDados());
        java.util.Date inicio = null;
        java.util.Date fim    = null;
        
        for (MonTablespace atual : lista) {
            if (inicio == null || inicio.after(atual.getDatColeta())) {
                inicio = atual.getDatColeta();
            }
            if (fim == null || fim.before(atual.getDatColeta())) {
                fim = atual.getDatColeta();
            }
            if (linhas.containsKey(atual.getNomTablespace())) {
                TabelaTablespaceLinha ttl = linhas.get(atual.getNomTablespace());
                if (ttl.inicio.compareTo(atual) > 0) {
                    ttl.inicio = atual;                   
                }
                if (atual.compareTo(ttl.fim) > 0) {
                    ttl.fim = atual;
                }
            } else {
                TabelaTablespaceLinha ttl = new TabelaTablespaceLinha();
                ttl.tableSpace = atual.getNomTablespace();
                ttl.inicio = atual;
                ttl.fim    = atual;
                linhas.put(atual.getNomTablespace(), ttl);
            }
        }

        long escala = 1024*1024;
        DateFormat df   = DateFormat.getDateInstance(DateFormat.SHORT);
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);
        NumberFormat pf = NumberFormat.getPercentInstance();
        pf.setMinimumFractionDigits(2);
        pf.setMaximumFractionDigits(2);
        
        StringBuilder sb = new StringBuilder("<table>");
        sb.append("<tr><td align=\"center\" width=\"36%\">Tablespace</td>");
        sb.append("<td align=\"center\">Tamanho em ");
        if (inicio != null) {
            sb.append(df.format(inicio));
        }
        sb.append("</td><td align=\"center\">Tamanho em ");
        if (fim != null) {
            sb.append(df.format(fim));
        }
        sb.append("</td><td align=\"center\">Crescimento MBytes</td>");
        sb.append("<td align=\"center\">Crescimento em %</td></tr>");
        
        double totalUtilizadoInicio = 0;
        double totalUtilizadoFim    = 0;
        for (TabelaTablespaceLinha linha : linhas.values()) {
            double utilizadoInicio         = linha.inicio.getValAlocado()-linha.inicio.getValLivre();
            double utilizadoFim            = linha.fim.getValAlocado()-linha.fim.getValLivre();
            double crescimento             = (utilizadoFim-utilizadoInicio);
            double crescimentoPorcentagem  = crescimento/utilizadoInicio;
            
            totalUtilizadoInicio += utilizadoInicio;
            totalUtilizadoFim    += utilizadoFim;
            
            sb.append("<tr><td>");
            sb.append(linha.inicio.getNomTablespace());
            sb.append("</td><td align=\"right\">");
            sb.append(nf.format(utilizadoInicio/escala));
            sb.append("</td><td align=\"right\">");
            sb.append(nf.format(utilizadoFim/escala));
            sb.append("</td><td align=\"right\">");
            sb.append(nf.format(crescimento/escala));
            sb.append("</td><td align=\"right\">");
            sb.append(pf.format(crescimentoPorcentagem));
            sb.append("</td></tr>");
        }
        sb.append("<tr><td>Total</td><td align=\"right\">");
        sb.append(nf.format(totalUtilizadoInicio / escala));
        sb.append("</td><td align=\"right\">");
        sb.append(nf.format(totalUtilizadoFim / escala));
        sb.append("</td><td align=\"right\">");
        totalUtilizadoFim -= totalUtilizadoInicio;
        sb.append(nf.format(totalUtilizadoFim / escala));
        sb.append("</td><td align=\"right\">");
        sb.append(pf.format(totalUtilizadoFim/totalUtilizadoInicio));
        sb.append("</td></tr>");
        sb.append("</table>");
        setConteudo(sb.toString());
    }
    
    private class TabelaTablespaceLinha {
        String tableSpace;
        MonTablespace inicio;
        MonTablespace fim;
    }
}
