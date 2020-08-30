/*
 * MDI.java
 *
 * Created on 01/09/2011, 17:33:24
 */
package br.com.jcomputacao.fwc.view.desktop;

import br.com.jcomputacao.dao.ConnectionFactory;
import br.com.jcomputacao.fwc.chart.GraficoTipo;
import br.com.jcomputacao.util.TimeUtil;
import java.awt.Dimension;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 *
 * @author Murilo
 */
public class MDI extends javax.swing.JFrame {

    public MDI() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktop = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        menuRelatorios = new javax.swing.JMenu();
        menuRelatorioGerar = new javax.swing.JMenuItem();
        menuRelatorioConsultarRelatoriosGerados = new javax.swing.JMenuItem();
        menuConfiguracoes = new javax.swing.JMenu();
        menuItemConfigGraficoDisco = new javax.swing.JMenuItem();
        menuItemConfigGraficoCpu = new javax.swing.JMenuItem();
        menuItemConfigGraficoMemoria = new javax.swing.JMenuItem();
        menuItemConfigGraficoTablespacePeriodo = new javax.swing.JMenuItem();
        menuItemConfigGraficoTablespaceFinal = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FWC :: Gerador de Relatórios");
        getContentPane().add(desktop, java.awt.BorderLayout.CENTER);

        menuRelatorios.setText("Relatórios");

        menuRelatorioGerar.setText("Gerar relatório(s)");
        menuRelatorioGerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRelatorioGerarActionPerformed(evt);
            }
        });
        menuRelatorios.add(menuRelatorioGerar);

        menuRelatorioConsultarRelatoriosGerados.setText("Consultar relatórios gerados");
        menuRelatorioConsultarRelatoriosGerados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRelatorioConsultarRelatoriosGeradosActionPerformed(evt);
            }
        });
        menuRelatorios.add(menuRelatorioConsultarRelatoriosGerados);

        menuBar.add(menuRelatorios);

        menuConfiguracoes.setText("Configurações");

        menuItemConfigGraficoDisco.setText("Configurações do gráfico de utilização de disco");
        menuItemConfigGraficoDisco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemConfigGraficoDiscoActionPerformed(evt);
            }
        });
        menuConfiguracoes.add(menuItemConfigGraficoDisco);

        menuItemConfigGraficoCpu.setText("Configurações do gráfico de utilização de cpu");
        menuItemConfigGraficoCpu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemConfigGraficoCpuActionPerformed(evt);
            }
        });
        menuConfiguracoes.add(menuItemConfigGraficoCpu);

        menuItemConfigGraficoMemoria.setText("Configurações do gráfico de utilização de memória");
        menuItemConfigGraficoMemoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemConfigGraficoMemoriaActionPerformed(evt);
            }
        });
        menuConfiguracoes.add(menuItemConfigGraficoMemoria);

        menuItemConfigGraficoTablespacePeriodo.setText("Configurações do gráfico de tablespace por período");
        menuItemConfigGraficoTablespacePeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemConfigGraficoTablespacePeriodoActionPerformed(evt);
            }
        });
        menuConfiguracoes.add(menuItemConfigGraficoTablespacePeriodo);

        menuItemConfigGraficoTablespaceFinal.setText("Configurações do gráfico de status final de tablespaces");
        menuItemConfigGraficoTablespaceFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemConfigGraficoTablespaceFinalActionPerformed(evt);
            }
        });
        menuConfiguracoes.add(menuItemConfigGraficoTablespaceFinal);

        menuBar.add(menuConfiguracoes);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuRelatorioGerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRelatorioGerarActionPerformed
        new RelatorioGeracaoController(desktop, this);
    }//GEN-LAST:event_menuRelatorioGerarActionPerformed

    private void menuRelatorioConsultarRelatoriosGeradosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRelatorioConsultarRelatoriosGeradosActionPerformed
        new RelatorioPesquisaController(desktop, this);
    }//GEN-LAST:event_menuRelatorioConsultarRelatoriosGeradosActionPerformed

    private void menuItemConfigGraficoTablespaceFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemConfigGraficoTablespaceFinalActionPerformed
        new ConfiguracaoController(GraficoTipo.TABLESPACE_FINAL, desktop, this);
    }//GEN-LAST:event_menuItemConfigGraficoTablespaceFinalActionPerformed

    private void menuItemConfigGraficoTablespacePeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemConfigGraficoTablespacePeriodoActionPerformed
        new ConfiguracaoController(GraficoTipo.TABLESPACE_PERIODO, desktop, this);
    }//GEN-LAST:event_menuItemConfigGraficoTablespacePeriodoActionPerformed

    private void menuItemConfigGraficoMemoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemConfigGraficoMemoriaActionPerformed
        new ConfiguracaoController(GraficoTipo.MEMORIA, desktop, this);
    }//GEN-LAST:event_menuItemConfigGraficoMemoriaActionPerformed

    private void menuItemConfigGraficoCpuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemConfigGraficoCpuActionPerformed
        new ConfiguracaoController(GraficoTipo.CPU, desktop, this);
    }//GEN-LAST:event_menuItemConfigGraficoCpuActionPerformed

    private void menuItemConfigGraficoDiscoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemConfigGraficoDiscoActionPerformed
        new ConfiguracaoController(GraficoTipo.DISCO, desktop, this);
    }//GEN-LAST:event_menuItemConfigGraficoDiscoActionPerformed

    public static void main(String args[]) {
        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception ex) {
                    Logger.getLogger(MDI.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
        }
        
        LoginDialog ld = new LoginDialog(null, true);
        URL resource = ld.getClass().getResource("/logo.png");
        ImageIcon aIcon = null;
        if(resource!=null) {
            aIcon = new javax.swing.ImageIcon(resource);
            ld.setIconImage(aIcon.getImage());
        }
        final ImageIcon mainIcon = aIcon;
        ld.setVisible(true);
        
        System.setProperty("fwc.report.test", "true");        
        try {
            Connection conn = ConnectionFactory.getConnection();
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "SELECT TO_CHAR(SYSDATE,'DD/MM/YYYY HH24:MI:SS') FROM DUAL";
                query = "SELECT SYSDATE FROM DUAL";
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    Date login = rs.getTimestamp(1);
                    System.out.println("Logado em " + TimeUtil.getShortDateForUi(login));
                }
                stmt.close();
                ConnectionFactory.devolver(conn);
            } else {
                JOptionPane.showMessageDialog(null
                    , "Não conseguiu se conectar ao banco de dados"
                    , "Erro"
                    , JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null
                    , "Não conseguiu se conectar ao banco de dados\n"+ex.getMessage()
                    , "Erro"
                    , JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                MDI mdi = new MDI();
                mdi.setVisible(true);
                mdi.setMinimumSize(new Dimension(800, 580));
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuConfiguracoes;
    private javax.swing.JMenuItem menuItemConfigGraficoCpu;
    private javax.swing.JMenuItem menuItemConfigGraficoDisco;
    private javax.swing.JMenuItem menuItemConfigGraficoMemoria;
    private javax.swing.JMenuItem menuItemConfigGraficoTablespaceFinal;
    private javax.swing.JMenuItem menuItemConfigGraficoTablespacePeriodo;
    private javax.swing.JMenuItem menuRelatorioConsultarRelatoriosGerados;
    private javax.swing.JMenuItem menuRelatorioGerar;
    private javax.swing.JMenu menuRelatorios;
    // End of variables declaration//GEN-END:variables
}
