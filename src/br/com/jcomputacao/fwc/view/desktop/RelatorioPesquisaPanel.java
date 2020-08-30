/*
 * RelatorioPesquisaPanel.java
 *
 * Created on 15/09/2011, 17:51:39
 */
package br.com.jcomputacao.fwc.view.desktop;

import javax.swing.table.TableColumn;

/**
 *
 * @author Murilo
 */
public class RelatorioPesquisaPanel extends javax.swing.JPanel {

    public RelatorioPesquisaPanel() {
        initComponents();
        TableColumn col = table.getColumnModel().getColumn(1);
        col.setPreferredWidth(240);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lInicio = new javax.swing.JLabel();
        dInicio = new com.toedter.calendar.JDateChooser();
        lFim = new javax.swing.JLabel();
        dFim = new com.toedter.calendar.JDateChooser();
        lCliente = new javax.swing.JLabel();
        cbCliente = new javax.swing.JComboBox();
        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        bPanel = new javax.swing.JPanel();
        bConsultarRelatorios = new javax.swing.JButton();
        bExportar = new javax.swing.JButton();
        bMarcarTodos = new javax.swing.JButton();
        bInverterSelecao = new javax.swing.JButton();
        bExcluir = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        lInicio.setText("Início");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 0);
        add(lInicio, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 0);
        add(dInicio, gridBagConstraints);

        lFim.setText("Fim");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 0);
        add(lFim, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 4);
        add(dFim, gridBagConstraints);

        lCliente.setText("Cliente");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 0);
        add(lCliente, gridBagConstraints);

        cbCliente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 4);
        add(cbCliente, gridBagConstraints);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Cliente", "Inicio", "Fim", "Selecionado", "Gerado em"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        scrollPane.setViewportView(table);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 5.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        add(scrollPane, gridBagConstraints);

        bConsultarRelatorios.setText("Consultar relatórios");
        bPanel.add(bConsultarRelatorios);

        bExportar.setText("Exportar para PDF");
        bPanel.add(bExportar);

        bMarcarTodos.setText("Marcar todos");
        bPanel.add(bMarcarTodos);

        bInverterSelecao.setText("Inverter seleção");
        bPanel.add(bInverterSelecao);

        bExcluir.setText("Excluir");
        bPanel.add(bExcluir);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 4);
        add(bPanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton bConsultarRelatorios;
    javax.swing.JButton bExcluir;
    javax.swing.JButton bExportar;
    javax.swing.JButton bInverterSelecao;
    javax.swing.JButton bMarcarTodos;
    private javax.swing.JPanel bPanel;
    javax.swing.JComboBox cbCliente;
    com.toedter.calendar.JDateChooser dFim;
    com.toedter.calendar.JDateChooser dInicio;
    private javax.swing.JLabel lCliente;
    private javax.swing.JLabel lFim;
    private javax.swing.JLabel lInicio;
    private javax.swing.JScrollPane scrollPane;
    javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
