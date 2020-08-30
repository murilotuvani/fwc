package br.com.jcomputacao.fwc.view.desktop;

import br.com.jcomputacao.dao.DaoException;
import br.com.jcomputacao.fwc.business.BusinessException;
import br.com.jcomputacao.fwc.business.RelatorioLogic;
import br.com.jcomputacao.fwc.dao.ClienteDao;
import br.com.jcomputacao.fwc.dao.MonServidorDao;
import br.com.jcomputacao.fwc.dao.RelRelatorioDao;
import br.com.jcomputacao.fwc.dao.RelRelatorioGraficoDao;
import br.com.jcomputacao.fwc.dao.RelRelatorioServidorDao;
import br.com.jcomputacao.fwc.dao.RelRelatorioTabelaDao;
import br.com.jcomputacao.fwc.dao.ReportData;
import br.com.jcomputacao.fwc.dao.ReportElement;
import br.com.jcomputacao.fwc.dao.ReportGraph;
import br.com.jcomputacao.fwc.dao.ReportTable;
import br.com.jcomputacao.fwc.model.Cliente;
import br.com.jcomputacao.fwc.model.MonServidor;
import br.com.jcomputacao.fwc.model.RelRelatorio;
import br.com.jcomputacao.fwc.model.RelRelatorioGrafico;
import br.com.jcomputacao.fwc.model.RelRelatorioServidor;
import br.com.jcomputacao.fwc.model.RelRelatorioTabela;
import br.com.jcomputacao.util.TimeUtil;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
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
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * 15/09/2011 21:04:10
 * @author Murilo
 */
public class RelatorioGeracaoController implements ActionListener, MouseListener {

    private RelatorioGeracaoPanel panel = new RelatorioGeracaoPanel();
    private ClienteDao clienteDao = new ClienteDao();
    private MonServidorDao servidorDao = new MonServidorDao();
    private final Map<Cliente, DefaultComboBoxModel> mapaServidores = new HashMap<Cliente, DefaultComboBoxModel>();
    private final JFrame pai;
    private final JDesktopPane desktop;
    private List<RelRelatorio> relatorios = new ArrayList<RelRelatorio>();

    RelatorioGeracaoController(JDesktopPane desktop, JFrame pai) {
        JInternalFrame iFrame = new JInternalFrame("Gerar relatório(s)", true, true);
        this.pai = pai;
        this.desktop = desktop;
        
        iFrame.setContentPane(panel);
        desktop.add(iFrame);
        iFrame.pack();
        iFrame.setVisible(true);
        iFrame.toFront();
        try {
            List<Cliente> clientes = clienteDao.listar();
            Collections.sort(clientes);
            clientes.add(0, null);
            DefaultComboBoxModel dcb = new DefaultComboBoxModel(clientes.toArray(new Cliente[]{}));
            panel.cbCliente.setModel(dcb);
            List<MonServidor> servidores = servidorDao.listar();
            for (Cliente cliente : clientes) {
                MonServidor[] servs = servidoresDoCliente(servidores, cliente);
                DefaultComboBoxModel dcbs = new DefaultComboBoxModel(servs);
                mapaServidores.put(cliente, dcbs);
            }
        } catch (DaoException ex) {
            Logger.getLogger(RelatorioGeracaoController.class.getName()).log(Level.SEVERE, "Erro", ex);
            JOptionPane.showMessageDialog(pai, "Erro ao tentar acessar o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
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
        if (e.getSource() == panel.ckTodosClientes) {
            panel.ckTodosServidores.setEnabled(!panel.ckTodosClientes.isSelected());
            panel.cbServidor.setEnabled(!panel.ckTodosClientes.isSelected());
            panel.cbCliente.setEnabled(!panel.ckTodosClientes.isSelected());
        } else if (e.getSource() == panel.ckTodosServidores) {
            panel.cbServidor.setEnabled(!panel.ckTodosServidores.isSelected());
        } else if (e.getSource() == panel.cbCliente) {
            Object selected = panel.cbCliente.getSelectedItem();
            if (selected != null && selected instanceof Cliente) {
                Cliente c = (Cliente) selected;
                panel.cbServidor.setModel(mapaServidores.get(c));
            }
        } else if (e.getSource() == panel.bGerarRelatorio) {
            gerarRelatorios();
        } else if (e.getSource() == panel.bPreverGeracao) {
            preverGeracao();
        }

        if (panel.cbCliente.getSelectedItem() != null || panel.ckTodosClientes.isSelected()) {
            panel.bPreverGeracao.setEnabled(true);
        }

    }

    private void initListeners() {
        panel.cbCliente.addActionListener(this);
        panel.ckTodosClientes.addActionListener(this);
        panel.ckTodosServidores.addActionListener(this);
        panel.bGerarRelatorio.addActionListener(this);
        panel.bPreverGeracao.addActionListener(this);
        panel.table.addMouseListener(this);
    }

    private void gerarRelatorios() {
        Date inicio = panel.dInicio.getDate();
        Date fim    = panel.dFim.getDate();
        if (inicio == null || fim == null || inicio.after(fim)) {
            JOptionPane.showMessageDialog(pai, 
                    "A data de início e fim precisam ser especificadas\n"
                    + "e o início precisa ser menor que o fim",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        relatorios.clear();
        exibeRelatorios();
        
        if (panel.ckTodosClientes.isSelected()) {
            try {
                boolean serial = !Boolean.parseBoolean(System.getProperty("geracao.paralela", "false"));
                WaitterThread wt = new WaitterThread();
                Thread awt = new Thread(wt);
                List<Cliente> clientes = clienteDao.listar("\nWHERE COD_CLIENTE IN (SELECT COD_CLIENTE FROM MON_SERVIDOR WHERE FLG_ATIVO='S' GROUP BY COD_CLIENTE)");
                for (Cliente cliente : clientes) {
                    System.out.println("Gerando relatorio para o cliente " + cliente.getNomCliente());
                    RelatorioGeracao rg = new RelatorioGeracao(cliente, inicio, fim);
                    Thread t = new Thread(rg);
                    wt.add(t);
                    t.start();
                    if(serial) {
                        try {
                            t.join();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(RelatorioGeracaoController.class.getName()).log(Level.SEVERE, "Erro ao tentar gerar o relatorio", ex);
                        }
                    }
                }
                awt.start();
            } catch (DaoException ex) {
                Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, 
                        "Erro ao tentar buscar informações sobre os clientes"
                        , "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            Object selectedItem = panel.cbCliente.getSelectedItem();
            if(selectedItem instanceof Cliente) {
                Cliente cliente = (Cliente) selectedItem;
                RelatorioGeracao rg;
                if(panel.ckTodosServidores.isSelected()) {
                    rg = new RelatorioGeracao(cliente, inicio, fim);
                } else {
                    selectedItem = panel.cbServidor.getSelectedItem();
                    if (selectedItem!=null && selectedItem instanceof MonServidor) {
                        MonServidor servidor = (MonServidor) selectedItem;
                        rg = new RelatorioGeracao(cliente, servidor, inicio, fim);
                    } else {
                        rg = new RelatorioGeracao(cliente, inicio, fim);
                    }
                    
                }
                
                Thread t = new Thread(rg);
                t.start();
                while(t.isAlive()) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(RelatorioGeracaoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                JOptionPane.showMessageDialog(null, "Relatório criado", "Informação", JOptionPane.INFORMATION_MESSAGE);
                
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um cliente", "Aviso", JOptionPane.WARNING_MESSAGE);
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
    
    private class RelatorioGeracao implements Runnable {
        private final Cliente     cliente;
        private final MonServidor servidor;
        private final Date inicio;
        private final Date fim;
        
        private RelatorioGeracao(Cliente c, Date inicio, Date fim) {
            this.cliente = c;
            this.inicio  = inicio;
            this.fim     = fim;
            this.servidor = null;
        }
        
        private RelatorioGeracao(Cliente c, MonServidor servidor, Date inicio, Date fim) {
            this.cliente  = c;
            this.inicio   = inicio;
            this.fim      = fim;
            this.servidor = servidor;
        }

        @Override
        public void run() {
            try {
                RelatorioLogic rl;
                if(servidor==null) {
                    rl = new RelatorioLogic(cliente, inicio, fim);
                } else {
                    rl = new RelatorioLogic(cliente, inicio, fim, servidor);
                }
                
                ReportData rd = rl.execute();

                RelRelatorio rel = new RelRelatorio();
                rel.setCodCliente(cliente.getCodCliente());
                rel.setNomCliente(cliente.getNomCliente());
                rel.setDatCadastro(new java.util.Date());
                rel.setDatInicio(inicio);
                rel.setDatFim(fim);
                RelRelatorioDao rdao = new RelRelatorioDao();
                rel = rdao.salvar(rel);
                long codigo = rel.getCodRelatorio();
                List<MonServidor> ss = rd.getServidores();
                for (MonServidor s : ss) {
                    salvaRelatorioServidor(codigo, s.getCodServidor());
                }

                List<ReportElement> elements = new ArrayList<ReportElement>(rd.getReportElements().values());
                Collections.sort(elements);

                for (ReportElement element : elements) {
                    String text = element.getAnalise();                    
                    if(element instanceof ReportGraph) {
                        ReportGraph graph = (ReportGraph) element;
                        RelRelatorioGrafico rrg = new RelRelatorioGrafico();
                        rrg.setTitulo(graph.getTitulo());
                        rrg.setNumOrdem(graph.getOrdem());
                        rrg.setCodRelatorio(codigo);
                        rrg.setDesAnalise(text);
                        rrg.setBloConteudo(graph.getContent());
                        RelRelatorioGraficoDao dao = new RelRelatorioGraficoDao();
                        dao.salvar(rrg);
                    } else if (element instanceof ReportTable) {
                        ReportTable table = (ReportTable) element;
                        RelRelatorioTabela rrg = new RelRelatorioTabela();
                        rrg.setTitulo(table.getTitulo());
                        rrg.setNumOrdem(table.getOrdem());
                        rrg.setCodRelatorio(codigo);
                        rrg.setDesAnalise(text);
                        rrg.setDesConteudo(table.getContent());
                        RelRelatorioTabelaDao dao = new RelRelatorioTabelaDao();
                        dao.salvar(rrg);
                    }
                }
                relatorios.add(rel);
                exibeRelatorios();
            } catch (DaoException ex) {
                Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, "Erro de banco de dados", ex);
                JOptionPane.showMessageDialog(pai, 
                        "Erro ao tentar geraro o relatório para "+cliente.getNomCliente()
                        , "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (BusinessException ex) {
                Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, "Erro de lógica de negógio", ex);
                JOptionPane.showMessageDialog(pai, 
                        "Erro ao tentar geraro o relatório para "+cliente.getNomCliente()
                        , "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, "Erro de IO", ex);
                JOptionPane.showMessageDialog(pai, 
                        "Erro ao tentar geraro o relatório para "+cliente.getNomCliente()
                        , "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void salvaRelatorioServidor(long codRelatorio, long codServidor) throws DaoException {
            RelRelatorioServidor rrs = new RelRelatorioServidor();
            rrs.setCodRelatorio(codRelatorio);
            rrs.setCodServidor(codServidor);
            RelRelatorioServidorDao dao = new RelRelatorioServidorDao();
            dao.salvar(rrs);
        }
    }

    private void preverGeracao() {
        relatorios.clear();
        if (panel.ckTodosClientes.isSelected()) {
            //comeca no 1 porque o primeiro eh nulo
            for (int i = 1; i < panel.cbCliente.getModel().getSize(); i++) {
                Object item = panel.cbCliente.getModel().getElementAt(i);
                adicionaClienteAosRelatorios(item);
            }
        } else {
            Object item = panel.cbCliente.getSelectedItem();
            adicionaClienteAosRelatorios(item);
        }

        exibeRelatorios();
        panel.bGerarRelatorio.setEnabled(!relatorios.isEmpty());
    }

    private void adicionaClienteAosRelatorios(Object item) {
        if (item != null && item instanceof Cliente) {
            Cliente cliente = (Cliente) item;
            RelRelatorio rr = new RelRelatorio();
            rr.setCodCliente(cliente.getCodCliente());
            rr.setNomCliente(cliente.getNomCliente());
            rr.setDatInicio(panel.dInicio.getDate());
            rr.setDatFim(panel.dFim.getDate());
            relatorios.add(rr);
        }
    }

    private void exibeRelatorios() {
        DefaultTableModel model = (DefaultTableModel) panel.table.getModel();

        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }

        for (RelRelatorio r : relatorios) {
            Object buf[] = new Object[model.getColumnCount()];

            int i = 0;
            buf[i++] = new Long(r.getCodRelatorio());
            buf[i++] = r.getNomCliente();
            buf[i++] = TimeUtil.getShortDateForUi(r.getDatInicio());
            buf[i++] = TimeUtil.getShortDateForUi(r.getDatFim());
            buf[i++] = Boolean.FALSE;
            model.addRow(buf);
        }
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(panel.table.getModel());
        panel.table.setRowSorter(sorter);
    }
}
