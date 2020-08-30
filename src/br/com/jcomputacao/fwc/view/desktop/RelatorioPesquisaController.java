package br.com.jcomputacao.fwc.view.desktop;

import br.com.jcomputacao.dao.DaoException;
import br.com.jcomputacao.fwc.dao.ClienteDao;
import br.com.jcomputacao.fwc.dao.MonServidorDao;
import br.com.jcomputacao.fwc.dao.RelRelatorioDao;
import br.com.jcomputacao.fwc.model.Cliente;
import br.com.jcomputacao.fwc.model.MonServidor;
import br.com.jcomputacao.fwc.model.RelRelatorio;
import br.com.jcomputacao.util.TimeUtil;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
/**
 * 16/09/2011 17:15:31
 * @author Murilo
 */
public class RelatorioPesquisaController implements ActionListener, MouseListener {

    private RelatorioPesquisaPanel panel = new RelatorioPesquisaPanel();
    private final Map<Cliente, DefaultComboBoxModel> mapaServidores = new HashMap<Cliente, DefaultComboBoxModel>();
    private final JFrame pai;
    private final JDesktopPane desktop;

    RelatorioPesquisaController(JDesktopPane desktop, JFrame pai) {
        this.desktop = desktop;
        this.pai     = pai;
        JInternalFrame iFrame = new JInternalFrame("Consulta de  relatório(s)", true, true);
        
        iFrame.setContentPane(panel);
        desktop.add(iFrame);
        iFrame.pack();
        iFrame.setVisible(true);
        iFrame.toFront();
        try {
            ClienteDao clienteDao = new ClienteDao();
            List<Cliente> clientes = clienteDao.listar();
            Collections.sort(clientes);
            clientes.add(0, null);
            DefaultComboBoxModel dcb = new DefaultComboBoxModel(clientes.toArray(new Cliente[]{}));
            panel.cbCliente.setModel(dcb);
            MonServidorDao servidorDao = new MonServidorDao();
            List<MonServidor> servidores = servidorDao.listar();
            for (Cliente cliente : clientes) {
                MonServidor[] servs = servidoresDoCliente(servidores, cliente);
                DefaultComboBoxModel dcbs = new DefaultComboBoxModel(servs);
                mapaServidores.put(cliente, dcbs);
            }
        } catch (DaoException ex) {
            Logger.getLogger(RelatorioGeracaoController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            initListeners();
        }
    }

    private MonServidor[] servidoresDoCliente(List<MonServidor> servidores, Cliente cliente) {
        List<MonServidor> servidoresDoCliente = new ArrayList<MonServidor>();
        if (cliente != null) {
            for (MonServidor serv : servidores) {
                if (serv.getCodCliente() == cliente.getCodCliente()) {
                    servidoresDoCliente.add(serv);
                }
            }
            Collections.sort(servidoresDoCliente);
            servidoresDoCliente.add(0, null);
        }
        return servidoresDoCliente.toArray(new MonServidor[]{});
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == panel.bConsultarRelatorios) {
            consultarRelatorios();
        } else if(e.getSource() == panel.bExportar) {
            exportarRelatorios();
        } else if(e.getSource() == panel.bMarcarTodos) {
            marcarTodos();
        } else if(e.getSource() == panel.bInverterSelecao) {
            inverterSelecao();
        } else if(e.getSource() == panel.bExcluir) {
            excluirSelecao();
        }

    }

    private void initListeners() {
        panel.bConsultarRelatorios.addActionListener(this);
        panel.bExportar.addActionListener(this);
        panel.bMarcarTodos.addActionListener(this);
        panel.bInverterSelecao.addActionListener(this);
        panel.bExcluir.addActionListener(this);
        panel.table.addMouseListener(this);
    }

    private void consultarRelatorios() {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        Object item = panel.cbCliente.getSelectedItem();
        if (item != null && item instanceof Cliente) {
            if (first) {
                first = false;
                sb.append(" WHERE ");
            } else {
                sb.append(" AND ");
            }
            sb.append(" R.COD_CLIENTE=");
            Cliente c = (Cliente) item;
            sb.append(c.getCodCliente());
        }

//        item = panel.cbServidor.getSelectedItem();
//        if (item != null && item instanceof MonServidor) {
//            if (first) {
//                first = false;
//                sb.append(" WHERE ");
//            } else {
//                sb.append(" AND ");
//            }
//            sb.append(" R.COD_CLIENTE=");
//            Cliente c = (Cliente) item;
//            sb.append(c.getCodCliente());
//        }
        //R.DAT_INICIO,R.DAT_FIM
        Date date = panel.dInicio.getDate();
        if (date != null) {
            if (first) {
                first = false;
                sb.append(" WHERE ");
            } else {
                sb.append(" AND ");
            }
            
            sb.append(" R.DAT_CADASTRO>=TO_DATE('");
            sb.append(TimeUtil.getShortDateForBd(date));
            sb.append("','YYYY-MM-DD')");
            
        }
        
        date = panel.dFim.getDate();
        if (date != null) {
            if (first) {
                first = false;
                sb.append(" WHERE ");
            } else {
                sb.append(" AND ");
            }
            sb.append(" R.DAT_CADASTRO>=TO_DATE('");
            sb.append(TimeUtil.getShortDateForBd(date));
            sb.append("','YYYY-MM-DD')");
        }

        executarConsultaEExibir(sb.toString());
    }

    private void executarConsultaEExibir(String where) {
        try {
            RelRelatorioDao rdao = new RelRelatorioDao();
            List<RelRelatorio> rs = rdao.listar(where);
            DefaultTableModel model = (DefaultTableModel) panel.table.getModel();

            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }

            for (RelRelatorio r : rs) {
                Object buf[] = new Object[model.getColumnCount()];
                
                int i = 0;
                buf[i++] = new Long(r.getCodRelatorio());
                buf[i++] = r.getNomCliente();
                buf[i++] = TimeUtil.getShortDateForUi(r.getDatInicio());
                buf[i++] = TimeUtil.getShortDateForUi(r.getDatFim());
                buf[i++] = Boolean.FALSE;
                buf[i++] = TimeUtil.getShortDateForUi(r.getDatCadastro());
                model.addRow(buf);
            }
            
            RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(panel.table.getModel());
            panel.table.setRowSorter(sorter);
        } catch (DaoException ex) {
            Logger.getLogger(RelatorioPesquisaController.class.getName()).log(Level.SEVERE, "Erro", ex);
            JOptionPane.showMessageDialog(pai, "Erro ao tentar acessar o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void exportarRelatorios() {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Qual o diretório de destino dos arquivos PDF?");
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
            DefaultTableModel model = (DefaultTableModel) panel.table.getModel();
            List<RelatorioExportacao> es = new ArrayList<RelatorioExportacao>();
            try {
                for (int i = 0; i < model.getRowCount(); i++) {
                    Boolean b = (Boolean) panel.table.getValueAt(i, 4);
                    if (b) {
                        cont++;
                        Long codigo = (Long) panel.table.getValueAt(i, 0);
                        RelRelatorioDao rdao = new RelRelatorioDao();
                        RelRelatorio r = rdao.buscar(codigo);
                        RelatorioExportacao e = new RelatorioExportacao(file, r, textArea);
                        e.execute();
                        es.add(e);
                    }
                }
            } catch (DaoException ex) {
                Logger.getLogger(RelatorioPesquisaController.class.getName()).log(Level.SEVERE, "Erro", ex);
                JOptionPane.showMessageDialog(pai, "Erro ao tentar exportar o(s) arquivo(s)"
                        , "Erro", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    boolean exportando = true;
                    while(exportando) {
                        exportando = false;
                        for(RelatorioExportacao e:es) {
                            if(!e.isCancelled() && !e.isDone()) {
                                exportando = true;
                            }
                        }
                        Thread.sleep(100);
                    }
                    
                    String text = textArea.getText();
                    if (text == null) {
                        text = "";
                    }
                    text += "Exportação concluída\nForam exportados " + cont + " relatórios";
                    textArea.setText(text);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RelatorioPesquisaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void marcarTodos() {
        DefaultTableModel model = (DefaultTableModel) panel.table.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            panel.table.setValueAt(Boolean.TRUE, i, 4);
        }
    }

    private void inverterSelecao() {
        DefaultTableModel model = (DefaultTableModel) panel.table.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            Boolean bool = (Boolean)panel.table.getValueAt(i, 4);
            panel.table.setValueAt(!bool, i, 4);
        }
    }
    
    private void excluirSelecao() {
        int op = JOptionPane.showInternalConfirmDialog(desktop, 
                "Tem certeza que deseja excluir os relatórios selecionados?",
                "Exclusão", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        if(JOptionPane.OK_OPTION == op) {
            DefaultTableModel model = (DefaultTableModel) panel.table.getModel();
            try {
                for (int i = 0; i < model.getRowCount(); i++) {
                    Boolean b = (Boolean) panel.table.getValueAt(i, 4);
                    if (b) {
                        Long codigo = (Long) panel.table.getValueAt(i, 0);
                        RelRelatorioDao rdao = new RelRelatorioDao();
                        rdao.delete(codigo);
                    }
                }
                JOptionPane.showInternalMessageDialog(desktop, "Registros excluídos!");
            } catch (DaoException ex) {
                Logger.getLogger(RelatorioPesquisaController.class.getName()).log(Level.SEVERE, "Erro", ex);
                JOptionPane.showMessageDialog(pai, "Erro ao tentar excluir os registros"
                        , "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if (e.getClickCount() == 2) {
            int row = panel.table.getSelectedRow();
            if (row > -1) {
                Long codigo = (Long)panel.table.getValueAt(row, 0);
                System.out.println("Código : " + codigo);
                RelatorioController rc = new RelatorioController(desktop, pai);
                rc.setRelatorio(codigo);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}
