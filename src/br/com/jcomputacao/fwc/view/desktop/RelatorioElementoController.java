package br.com.jcomputacao.fwc.view.desktop;

import br.com.jcomputacao.dao.DaoException;
import br.com.jcomputacao.fwc.dao.RelRelatorioGraficoDao;
import br.com.jcomputacao.fwc.dao.RelRelatorioTabelaDao;
import br.com.jcomputacao.fwc.model.RelElemento;
import br.com.jcomputacao.fwc.model.RelRelatorioGrafico;
import br.com.jcomputacao.fwc.model.RelRelatorioTabela;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

/**
 * 02/10/2011 21:31:53
 * @author Murilo
 */
public class RelatorioElementoController implements ActionListener {
    private final RelElemento elemento;
    private final RelatorioElementoPanel rep;
    private final JDesktopPane desktop;

    RelatorioElementoController(RelatorioElementoPanel rep, RelElemento e, JDesktopPane desktop) {
        this.rep = rep;
        rep.bAlterar.addActionListener(this);
        this.elemento = e;
        this.desktop  = desktop;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String texto = rep.textAnalise.getText();
        if (elemento.getDesAnalise() == null
                || !elemento.getDesAnalise().equals(texto)) {
            elemento.setDesAnalise(texto);
            try {
                if (elemento instanceof RelRelatorioGrafico) {
                    RelRelatorioGraficoDao dao = new RelRelatorioGraficoDao();
                    dao.alterar((RelRelatorioGrafico) elemento);
                    rep.status.setText("Regisro alterado com sucesso");
                }
                if (elemento instanceof RelRelatorioTabela) {
                    RelRelatorioTabelaDao dao = new RelRelatorioTabelaDao();
                    dao.alterar((RelRelatorioTabela) elemento);
                    rep.status.setText("Regisro alterado com sucesso");
                }
            } catch (DaoException ex) {
                Logger.getLogger(MDI.class.getName()).log(Level.SEVERE, 
                        "Erro ao tentar atualizar o cadastro", ex);
                JOptionPane.showInternalMessageDialog(desktop
                        , "Erro", "Ocorreu um erro ao tentar atualizar o registro\n"+ex.getLocalizedMessage()
                        , JOptionPane.ERROR_MESSAGE);
            }
        } else {
            rep.status.setText("Regisro não alterado");
        }
    }
    
}
