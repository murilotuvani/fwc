package br.com.jcomputacao.fwc.view.desktop;

import br.com.jcomputacao.util.WindowUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 03/10/2011, 00:20:21
 * @author Murilo
 */
public class LoginDialog extends javax.swing.JDialog {

    public LoginDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        aplicaDadosSalvos();
        setSize(400, 210);
        WindowUtil.centraliza(this);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lUsuario = new javax.swing.JLabel();
        lSenha = new javax.swing.JLabel();
        lHost = new javax.swing.JLabel();
        lInstancia = new javax.swing.JLabel();
        tUsuario = new javax.swing.JTextField();
        tHost = new javax.swing.JTextField();
        tInstancia = new javax.swing.JTextField();
        bLogin = new javax.swing.JButton();
        pSenha = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        lUsuario.setText("Usuário");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 0);
        getContentPane().add(lUsuario, gridBagConstraints);

        lSenha.setText("Senha");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 0);
        getContentPane().add(lSenha, gridBagConstraints);

        lHost.setText("Host/Servidor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 0);
        getContentPane().add(lHost, gridBagConstraints);

        lInstancia.setText("Instância");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 0);
        getContentPane().add(lInstancia, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 4);
        getContentPane().add(tUsuario, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 4);
        getContentPane().add(tHost, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 4);
        getContentPane().add(tInstancia, gridBagConstraints);

        bLogin.setText("Login");
        bLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLoginActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        getContentPane().add(bLogin, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 4);
        getContentPane().add(pSenha, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLoginActionPerformed
        char[] senha = pSenha.getPassword();
        StringBuilder valor = new StringBuilder();
        valor.append(senha);
        valor.toString();
        
        String user     = tUsuario.getText();
        String host     = tHost.getText();
        String instance = tInstancia.getText();

        System.setProperty("sql.host", host);
        System.setProperty("sql.instance", instance);
        System.setProperty("sql.user", user);
        System.setProperty("sql.passwd", valor.toString());
        
        salvaDados();   
        this.dispose();
    }//GEN-LAST:event_bLoginActionPerformed

    private void aplicaDadosSalvos() {
        File f = new File("fwc.xml");
        if(f.exists()) {
            InputStream is = null;
            try {
                Properties p = new Properties();
                is = new FileInputStream(f);
                p.loadFromXML(is);
                
                String aux = p.getProperty("sql.host", "localhost");
                tHost.setText(aux);
                aux = p.getProperty("sql.instance", "XE");
                tInstancia.setText(aux);
                aux = p.getProperty("sql.user");
                tUsuario.setText(aux);
            } catch (IOException ex) {
                Logger.getLogger(LoginDialog.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    is.close();
                } catch (IOException ex) {
                    Logger.getLogger(LoginDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void salvaDados() {
        String user     = tUsuario.getText();
        String host     = tHost.getText();
        String instance = tInstancia.getText();
        
        Properties p = new Properties();
        p.setProperty("sql.host", host);
        p.setProperty("sql.instance", instance);
        p.setProperty("sql.user", user);
        File f = new File("fwc.xml");
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(f);
            p.storeToXML(os, "Comentarios para que?");
            os.close();
        } catch (IOException ex) {
            Logger.getLogger(LoginDialog.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                os.close();
            } catch (IOException ex) {
                Logger.getLogger(LoginDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bLogin;
    private javax.swing.JLabel lHost;
    private javax.swing.JLabel lInstancia;
    private javax.swing.JLabel lSenha;
    private javax.swing.JLabel lUsuario;
    private javax.swing.JPasswordField pSenha;
    private javax.swing.JTextField tHost;
    private javax.swing.JTextField tInstancia;
    private javax.swing.JTextField tUsuario;
    // End of variables declaration//GEN-END:variables


}
