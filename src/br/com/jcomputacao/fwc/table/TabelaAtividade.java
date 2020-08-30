package br.com.jcomputacao.fwc.table;

import br.com.jcomputacao.dao.DaoException;
import br.com.jcomputacao.fwc.dao.TipoAtividadeDao;
import br.com.jcomputacao.fwc.model.Atividade;
import br.com.jcomputacao.fwc.model.TipoAtividade;
import br.com.jcomputacao.util.TimeUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 28/06/2011 21:40:49
 * @author Murilo
 */
public class TabelaAtividade extends Tabela {
    private Map<Long,TipoAtividade> tipoAtividadeMap = new HashMap<Long,TipoAtividade>();
    
    public TabelaAtividade(List<Atividade> lista, String nomeServidor) {
        setTitulo("Intervenções referente ao servidor "+nomeServidor);
        StringBuilder out = new StringBuilder();
        if (lista.isEmpty()) {
            out.append("<p>Não houveram intervenções para este servidor no período</p>");
        } else {
            if(tipoAtividadeMap.isEmpty()) {
                try {
                    carregarTipoAtividadeMap();
                } catch (DaoException ex) {
                    Logger.getLogger(TabelaAtividade.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            out.append("<table><tr><td>DBA</td><td>Tipo</td><td>Início</td>");
            out.append("<td>Fim</td><td width=\"22%\">Motivo</td><td width=\"30%\">Ação</td></tr>");
            
            for(Atividade a:lista) {
                out.append("<tr><td>");
                out.append(a.getNomUsuario());
                out.append("</td><td>");
                TipoAtividade ta = tipoAtividadeMap.get(a.getCodTipoAtividade());
                if(ta!=null) {
                    out.append(ta.getNomTipoAtividade());
                } else {
                    out.append(a.getCodTipoAtividade());
                }
                out.append("</td><td>");
                out.append(TimeUtil.getShortDateTimeForUi(a.getDatInicio()));
                out.append("</td><td>");
                out.append(TimeUtil.getShortDateTimeForUi(a.getDatFim()));
                out.append("</td><td>");
                out.append(a.getDesMotivo());
                out.append("</td><td>");
                out.append(a.getDesAcaoTomada());
                out.append("</td></tr>");
            }
            out.append("</table>");
        }
        setConteudo(out.toString());
    }
    
    private void carregarTipoAtividadeMap() throws DaoException {
        TipoAtividadeDao tad = new TipoAtividadeDao();
        List<TipoAtividade> lista = tad.listar();
        for (TipoAtividade ta : lista) {
            tipoAtividadeMap.put(ta.getCodTipoAtividade(), ta);
        }
    }
    
}
