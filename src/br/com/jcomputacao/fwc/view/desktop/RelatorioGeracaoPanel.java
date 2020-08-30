/*
 * RelatorioGeracaoPanel.java
 *
 * Created on 15/09/2011, 21:02:29
 */
package br.com.jcomputacao.fwc.view.desktop;

import javax.swing.table.TableColumn;

/**
 *
 * @author Murilo
 */
public class RelatorioGeracaoPanel extends javax.swing.JPanel {

    public RelatorioGeracaoPanel() {
        initComponents();
        TableColumn col = table.getColumnModel().getColumn(1);
        col.setPreferredWidth(240);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lInicio = new javax.swing.JLabel();
        lFim = new javax.swing.JLabel();
        lCliente = new javax.swing.JLabel();
        lServidor = new javax.swing.JLabel();
        ckTodosClientes = new javax.swing.JCheckBox();
        dInicio = new com.toedter.calendar.JDateChooser();
        dFim = new com.toedter.calendar.JDateChooser();
        cbCliente = new javax.swing.JComboBox();
        cbServidor = new javax.swing.JComboBox();
        ckTodosServidores = new javax.swing.JCheckBox();
        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        bPanel = new javax.swing.JPanel();
        bPreverGeracao = new javax.swing.JButton();
        bGerarRelatorio = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        lInicio.setText("Início");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 0);
        add(lInicio, gridBagConstraints);

        lFim.setText("Fim");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 0);
        add(lFim, gridBagConstraints);

        lCliente.setText("Cliente");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 0);
        add(lCliente, gridBagConstraints);

        lServidor.setText("Servidor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 0);
        add(lServidor, gridBagConstraints);

        ckTodosClientes.setText("Todos clientes ativos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 4);
        add(ckTodosClientes, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 0);
        add(dInicio, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 4);
        add(dFim, gridBagConstraints);

        cbCliente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 0);
        add(cbCliente, gridBagConstraints);

        cbServidor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 0);
        add(cbServidor, gridBagConstraints);

        ckTodosServidores.setText("Todos servidores");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 4);
        add(ckTodosServidores, gridBagConstraints);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Cliente", "Inicio", "Fim", "Gerado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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

        bPreverGeracao.setText("Prever geração");
        bPreverGeracao.setEnabled(false);
        bPanel.add(bPreverGeracao);

        bGerarRelatorio.setText("Gerar relatório(s)");
        bGerarRelatorio.setEnabled(false);
        bPanel.add(bGerarRelatorio);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 4;
        add(bPanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton bGerarRelatorio;
    private javax.swing.JPanel bPanel;
    javax.swing.JButton bPreverGeracao;
    javax.swing.JComboBox cbCliente;
    javax.swing.JComboBox cbServidor;
    javax.swing.JCheckBox ckTodosClientes;
    javax.swing.JCheckBox ckTodosServidores;
    com.toedter.calendar.JDateChooser dFim;
    com.toedter.calendar.JDateChooser dInicio;
    private javax.swing.JLabel lCliente;
    private javax.swing.JLabel lFim;
    private javax.swing.JLabel lInicio;
    private javax.swing.JLabel lServidor;
    private javax.swing.JScrollPane scrollPane;
    javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
