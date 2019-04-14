/*
 * The MIT License
 * 
 * Copyright: Copyright (C) 2014 T2Ti.COM
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * 
 * The author may be contacted at: t2ti.com@gmail.com
 *
 * @author Claudio de Barros (T2Ti.com)
 * @version 2.0
 */
package com.t2ti.nfce.infra;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Properties;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class SelecionaCertificado extends javax.swing.JDialog {

    private boolean cancelado;

    /**
     * Creates new form SelecionaCertificado
     */
    public SelecionaCertificado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(450, 200);
        setLocationRelativeTo(null);
        cancelado = false;
    }

    private void selecionaCertificado() {
        FileFilter filter = new FileFilter() {

            @Override
            public boolean accept(File f) {
                String arquivo = f.getName().toLowerCase();
                return f.isDirectory()
                        || arquivo.endsWith(".p12")
                        || arquivo.endsWith(".pfx");
            }

            @Override
            public String getDescription() {
                return "*.p12;*.pfx";
            }
        };

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        fileChooser.showOpenDialog(this);
        File file = fileChooser.getSelectedFile();

        if (file != null) {
            txtCaminhoCertificado.setText(file.getAbsolutePath());
        }
    }

    public void confirma() {
        if (txtCaminhoCertificado.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Selecione o arquivo do certificado!", "Informação do Sistema", JOptionPane.WARNING_MESSAGE);
        } else if (txtSenha.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "Informe a senha do certificado!", "Informação do Sistema", JOptionPane.WARNING_MESSAGE);
            txtSenha.requestFocus();
        } else {
            try {
                KeyStore certificado = KeyStore.getInstance("PKCS12");
                certificado.load(new FileInputStream(txtCaminhoCertificado.getText()), txtSenha.getPassword());
                certificado.aliases().nextElement();

                Properties arquivoConfiguracao = new Properties();
                arquivoConfiguracao.load(new FileInputStream(new File(NfceConstantes.ARQUIVO_DADOS_CERTIFICADO)));
                arquivoConfiguracao.setProperty("caminhoArquivo", txtCaminhoCertificado.getText());
                arquivoConfiguracao.setProperty("senha", new String(txtSenha.getPassword()));

                FileOutputStream out = new FileOutputStream(new File(NfceConstantes.ARQUIVO_DADOS_CERTIFICADO));
                arquivoConfiguracao.store(out, "Dados do certificado");
                out.close();
                
                this.dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro ao carregar os dados do certificado A1.\n" + e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void carregaDadosCertificado() throws Exception {
        try {
            Properties arquivoConfiguracao = new Properties();
            arquivoConfiguracao.load(new FileInputStream(new File(NfceConstantes.ARQUIVO_DADOS_CERTIFICADO)));
            SessaoUsuario.certificado = KeyStore.getInstance("PKCS12");
            SessaoUsuario.senhaCertificado = arquivoConfiguracao.getProperty("senha").toCharArray();
            SessaoUsuario.certificado.load(new FileInputStream(arquivoConfiguracao.getProperty("caminhoArquivo")), SessaoUsuario.senhaCertificado);
            SessaoUsuario.certificado.aliases().nextElement();
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            SessaoUsuario.certificado = null;
            SessaoUsuario.senhaCertificado = null;
            throw e;
        }
    }    
    
    public boolean isCancelado() {
        return cancelado;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        txtCaminhoCertificado = new javax.swing.JTextField();
        btnSelecionaCertificado = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Selecionar Certificado Digital");
        setModal(true);
        setPreferredSize(new java.awt.Dimension(450, 200));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Caminho do Certificado:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        getContentPane().add(jLabel1, gridBagConstraints);

        txtCaminhoCertificado.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        getContentPane().add(txtCaminhoCertificado, gridBagConstraints);

        btnSelecionaCertificado.setText("...");
        btnSelecionaCertificado.setToolTipText("Selecionar Certificado");
        btnSelecionaCertificado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionaCertificadoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        getContentPane().add(btnSelecionaCertificado, gridBagConstraints);

        jLabel2.setText("Senha:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        getContentPane().add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        getContentPane().add(txtSenha, gridBagConstraints);

        jButton1.setText("OK");
        jButton1.setPreferredSize(new java.awt.Dimension(75, 23));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSelecionaCertificadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionaCertificadoActionPerformed
        selecionaCertificado();
    }//GEN-LAST:event_btnSelecionaCertificadoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        confirma();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        cancelado = true;
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSelecionaCertificado;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtCaminhoCertificado;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}
