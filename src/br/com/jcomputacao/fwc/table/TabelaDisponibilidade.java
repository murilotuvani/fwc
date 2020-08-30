package br.com.jcomputacao.fwc.table;

import br.com.jcomputacao.fwc.model.MonBancoDados;
import br.com.jcomputacao.fwc.model.MonDisponibilidade;
import br.com.jcomputacao.fwc.model.MonServidor;
import br.com.jcomputacao.util.TimeUtil;
import java.util.Collections;
import java.util.List;

/**
 * 28/06/2011 21:42:09
 * @author Murilo
 */
public class TabelaDisponibilidade extends Tabela {

    public TabelaDisponibilidade(List<MonDisponibilidade> eventos, MonServidor s, MonBancoDados banco) {
        StringBuilder sb = new StringBuilder("<table><tr><td colspan=\"2\">Dispobilidade do servidor ");
        sb.append(s.getNomServidor());
        sb.append("</td></tr>");
//        List<MonBancoDados> bancos = new ArrayList<MonBancoDados>(sbancos);
        
        Collections.sort(eventos);

            sb.append("<tr><td colspan=\"2\">Dispobilidade da instância ");
            sb.append(banco.getNomBancoDados());
            sb.append("</td></tr>");

//            List<MonDisponibilidade> eventosBanco = eventosDoBanco(eventos, banco);
//            for (MonDisponibilidade ev : eventosBanco) {
            for (MonDisponibilidade ev : eventos) {
                sb.append("<tr><td>");
                sb.append(TimeUtil.getShortDateTime(ev.getDatEvento()));
                sb.append("</td><td>");
                sb.append(ev.getDesEvento());
                sb.append("</td></tr>");
            }
        sb.append("</table>");
        setTitulo("Disponibilidade do banco de dados "+banco.getNomBancoDados()+" do servidor "+s.getNomServidor());
        setConteudo(sb.toString());

    }

//    private List<MonDisponibilidade> eventosDoBanco(List<MonDisponibilidade> eventos, MonBancoDados banco) {
//        List<MonDisponibilidade> edb = new ArrayList<MonDisponibilidade>();
//        for (MonDisponibilidade ev : eventos) {
//            if (ev.getCodBancoDados() == banco.getCodBancoDados()) {
//                edb.add(ev);
//            }
//        }
//        Collections.sort(edb);
//        return edb;
//    }
}
