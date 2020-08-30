/*
 * RelatorioElementoPanel.java
 *
 * Created on 29/09/2011, 22:31:02
 */
package br.com.jcomputacao.fwc.view.desktop;

/**
 *
 * @author Murilo
 */
public class RelatorioElementoPanel extends javax.swing.JPanel {

    public RelatorioElementoPanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        scrollPaneImagem = new javax.swing.JScrollPane();
        label = new javax.swing.JLabel();
        scrollPaneAnalise = new javax.swing.JScrollPane();
        textAnalise = new javax.swing.JTextArea();
        scrollPaneTable = new javax.swing.JScrollPane();
        editorPane = new javax.swing.JEditorPane();
        bAlterar = new javax.swing.JButton();
        status = new javax.swing.JTextField();

        setLayout(new java.awt.GridBagLayout());

        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        scrollPaneImagem.setViewportView(label);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 3.0;
        add(scrollPaneImagem, gridBagConstraints);

        textAnalise.setColumns(20);
        textAnalise.setRows(5);
        scrollPaneAnalise.setViewportView(textAnalise);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 5.0;
        add(scrollPaneAnalise, gridBagConstraints);

        editorPane.setContentType("text/html");
        editorPane.setEditable(false);
        scrollPaneTable.setViewportView(editorPane);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 5.0;
        add(scrollPaneTable, gridBagConstraints);

        bAlterar.setText("Alterar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        add(bAlterar, gridBagConstraints);

        status.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(status, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton bAlterar;
    javax.swing.JEditorPane editorPane;
    javax.swing.JLabel label;
    javax.swing.JScrollPane scrollPaneAnalise;
    javax.swing.JScrollPane scrollPaneImagem;
    javax.swing.JScrollPane scrollPaneTable;
    javax.swing.JTextField status;
    javax.swing.JTextArea textAnalise;
    // End of variables declaration//GEN-END:variables
}
