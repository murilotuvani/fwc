package br.com.jcomputacao.fwc.view.desktop;

import br.com.jcomputacao.dao.DaoException;
import br.com.jcomputacao.fwc.dao.RelRelatorioDao;
import br.com.jcomputacao.fwc.dao.RelRelatorioGraficoDao;
import br.com.jcomputacao.fwc.dao.RelRelatorioTabelaDao;
import br.com.jcomputacao.fwc.model.RelElemento;
import br.com.jcomputacao.fwc.model.RelRelatorio;
import br.com.jcomputacao.fwc.model.RelRelatorioGrafico;
import br.com.jcomputacao.fwc.model.RelRelatorioTabela;
import br.com.jcomputacao.util.TimeUtil;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * 29/09/2011 22:24:43
 * @author Murilo
 */
public class RelatorioController implements ActionListener {
    private final JDesktopPane desktop;
    private final JFrame pai;
    private RelatorioPanel panel = new RelatorioPanel();
    private RelRelatorio relatorio;
    private List<RelRelatorioTabela> tabelas;
    private List<RelRelatorioGrafico> graficos;
    
    RelatorioController (JDesktopPane desktop, JFrame pai) { 
        this.desktop = desktop;
        this.pai     = pai;
        
        panel.bExportar.addActionListener(this);
        JInternalFrame iFrame = new JInternalFrame("Relatório", true, true);
        iFrame.setContentPane(panel);
        desktop.add(iFrame);
        iFrame.setSize(desktop.getSize());
        iFrame.setVisible(true);
        iFrame.toFront();
    }

    void setRelatorio(Long codigo) {
        try {
            RelRelatorioDao dao = new RelRelatorioDao();
            relatorio = dao.buscar(codigo);
            exibirRelatorio();
            RelRelatorioTabelaDao rrtd = new RelRelatorioTabelaDao();
            this.tabelas = rrtd.listar("\nWHERE COD_RELATORIO="+codigo+" ORDER BY NUM_ORDEM");
            RelRelatorioGraficoDao rrgd = new RelRelatorioGraficoDao();
            this.graficos = rrgd.listar("\nWHERE COD_RELATORIO="+codigo+" ORDER BY NUM_ORDEM");
            exibirElementos();
        } catch (DaoException ex) {
            Logger.getLogger(RelatorioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void exibirElementos() {
        List<RelElemento> es = new ArrayList<RelElemento>();
        es.addAll(graficos);
        es.addAll(tabelas);
        Collections.sort(es);
        
        while (panel.tabbedPane.getTabCount() > 0) {
            panel.tabbedPane.remove(0);
        }
        
        for(RelElemento e:es) {
            RelatorioElementoPanel rep = new RelatorioElementoPanel();
            RelatorioElementoController rec = new RelatorioElementoController(rep,e, desktop);
            rep.textAnalise.setText(e.getDesAnalise());
            panel.tabbedPane.add(e.getTitulo()+" ["+e.getCodElemento()+"]", rep);
            
            if(e instanceof RelRelatorioGrafico) {
                Icon icon = new ImageIcon(((RelRelatorioGrafico)e).getBloConteudo());
                rep.label.setIcon(icon);
                rep.label.setVisible(true);
                rep.remove(rep.scrollPaneTable);
                rep.editorPane.setVisible(false);
                rep.editorPane.setText("");
            } else {
                rep.label.setIcon(null);
                rep.label.setVisible(false);
                rep.remove(rep.scrollPaneImagem);
                rep.editorPane.setVisible(true);
                String html = ((RelRelatorioTabela)e).getDesConteudo();
                rep.editorPane.setText(html);
                rep.label.setText(((RelRelatorioTabela)e).getDesConteudo());
            }
            
        }
    }

    private void exibirRelatorio() {
        panel.tCodigo.setText(Long.toString(relatorio.getCodRelatorio()));
        panel.tCriacao.setText(TimeUtil.getShortDateForUi(relatorio.getDatCadastro()));
        panel.tClienteCodigo.setText(Long.toString(relatorio.getCodCliente()));
        panel.tClienteNome.setText(relatorio.getNomCliente());
        panel.tInicio.setText(TimeUtil.getShortDateForUi(relatorio.getDatInicio()));
        panel.tFim.setText(TimeUtil.getShortDateForUi(relatorio.getDatFim()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Qual o diretório de destino do arquivo PDF?");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        JTextArea textArea = new JTextArea();
        int cont = 0;
        if (fc.showSaveDialog(pai) == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();    
            JDialog dialog = new JDialog(pai, "Console de exportacao");
            JScrollPane sp = new JScrollPane(textArea);
            dialog.setLayout(new BorderLayout());
            dialog.add(sp, BorderLayout.CENTER);
            dialog.setSize(400, 300);
            dialog.setVisible(true);
            dialog.toFront();
            try {
                RelatorioExportacao exportacao = new RelatorioExportacao(file, relatorio, textArea);
                exportacao.execute();
//            } catch (DaoException ex) {
//                Logger.getLogger(RelatorioPesquisaController.class.getName()).log(Level.SEVERE, "Erro", ex);
//                JOptionPane.showMessageDialog(pai, "Erro ao tentar exportar o(s) arquivo(s)"
//                        , "Erro", JOptionPane.ERROR_MESSAGE);
            } finally {
                String text = textArea.getText();
                if (text == null) {
                    text = "";
                }
                text += "Exportação concluída\nForam exportados " + cont + " relatórios";
                textArea.setText(text);
            }
        }
    }
    
}
