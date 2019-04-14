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
package com.t2ti.nfce.view;

import com.t2ti.nfce.controller.ClienteController;
import com.t2ti.pafecf.infra.Biblioteca;
import com.t2ti.nfce.infra.SessaoUsuario;
import com.t2tierp.cadastros.java.ClienteVO;
import com.t2tierp.nfce.java.ViewNfceClienteVO;
import com.t2tierp.nfe.java.NfeDestinatarioVO;
import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class IdentificaCliente extends javax.swing.JDialog {

    private ViewNfceClienteVO cliente;
    public boolean cancelado = false;

    public IdentificaCliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        try {
            int rgb = Integer.valueOf(SessaoUsuario.getConfiguracao().getCorJanelasInternas());

            panelPrincipal.setBackground(new Color(rgb));
            panelComponentes.setBackground(new Color(rgb));
            panelDados.setBackground(new Color(rgb));
            panelBotoes.setBackground(new Color(rgb));
        } catch (Exception e) {
        }

        CancelaAction cancelaAction = new CancelaAction();
        botaoCancela.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancelaAction");
        botaoCancela.getActionMap().put("cancelaAction", cancelaAction);

        ConfirmaAction confirmaAction = new ConfirmaAction();
        botaoConfirma.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), "confirmaAction");
        botaoConfirma.getActionMap().put("confirmaAction", confirmaAction);

        LocalizaAction localizaAction = new LocalizaAction();
        botaoConfirma.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0), "localizaAction");
        botaoConfirma.getActionMap().put("localizaAction", localizaAction);

        //troca TAB por ENTER
        HashSet conj = new HashSet(this.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        this.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        this.setPreferredSize(new Dimension(488, 200));
        
        try {
            Image bi = ImageIO.read(new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "body.jpg"));
            jLabel5.setText("");
            jLabel5.setIcon(new ImageIcon(bi.getScaledInstance(jLabel5.size().width, jLabel5.size().height, Image.SCALE_SMOOTH)));          
        } catch (IOException e) {
            e.printStackTrace();
        }        

        this.pack();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panelPrincipal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelComponentes = new javax.swing.JPanel();
        panelDados = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        editCpfCnpj = new javax.swing.JFormattedTextField();
        editNome = new javax.swing.JTextField();
        editEndereco = new javax.swing.JTextField();
        panelBotoes = new javax.swing.JPanel();
        botaoConfirma = new javax.swing.JButton();
        botaoCancela = new javax.swing.JButton();
        botaoLocaliza = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Identificação do Cliente");
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(new java.awt.CardLayout());

        panelPrincipal.setLayout(new java.awt.GridBagLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/telas/telaHomem09.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        panelPrincipal.add(jLabel1, gridBagConstraints);

        panelComponentes.setLayout(new java.awt.GridBagLayout());

        panelDados.setBackground(new Color(255,255,255,0));
        panelDados.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("CPF/CNPJ:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        panelDados.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Nome:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        panelDados.add(jLabel3, gridBagConstraints);

        jLabel4.setText("Endereço:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        panelDados.add(jLabel4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelDados.add(editCpfCnpj, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelDados.add(editNome, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelDados.add(editEndereco, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        panelComponentes.add(panelDados, gridBagConstraints);

        panelBotoes.setBackground(new Color(255,255,255,0));
        panelBotoes.setLayout(new java.awt.GridBagLayout());

        botaoConfirma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgBotoes/botaoConfirmar.png"))); // NOI18N
        botaoConfirma.setText("Confirma (F12)");
        botaoConfirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoConfirmaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 10);
        panelBotoes.add(botaoConfirma, gridBagConstraints);

        botaoCancela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgBotoes/botaoCancelar.png"))); // NOI18N
        botaoCancela.setText("Cancela (ESC)");
        botaoCancela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 5);
        panelBotoes.add(botaoCancela, gridBagConstraints);

        botaoLocaliza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgBotoes/botaoLocalizar.png"))); // NOI18N
        botaoLocaliza.setText("Localiza (F11)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 5);
        panelBotoes.add(botaoLocaliza, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 10);
        panelComponentes.add(panelBotoes, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        panelPrincipal.add(panelComponentes, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/layout/body.jpg"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        panelPrincipal.add(jPanel1, gridBagConstraints);

        getContentPane().add(panelPrincipal, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoConfirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoConfirmaActionPerformed
        confirma();
}//GEN-LAST:event_botaoConfirmaActionPerformed

    private void botaoCancelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelaActionPerformed
        cancelado = true;
        dispose();
}//GEN-LAST:event_botaoCancelaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancela;
    private javax.swing.JButton botaoConfirma;
    private javax.swing.JButton botaoLocaliza;
    private javax.swing.JFormattedTextField editCpfCnpj;
    private javax.swing.JTextField editEndereco;
    private javax.swing.JTextField editNome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelBotoes;
    private javax.swing.JPanel panelComponentes;
    private javax.swing.JPanel panelDados;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables

    private class ConfirmaAction extends AbstractAction {

        public ConfirmaAction() {
        }

        public void actionPerformed(ActionEvent e) {
            confirma();
        }
    }

    private class CancelaAction extends AbstractAction {

        public CancelaAction() {
        }

        public void actionPerformed(ActionEvent e) {
            cancelado = true;
            dispose();
        }
    }

    private class LocalizaAction extends AbstractAction {

        public LocalizaAction() {
        }

        public void actionPerformed(ActionEvent e) {
            localiza();
        }
    }

    private boolean validaCpfCnpj() {
        if (!editCpfCnpj.getText().trim().equals("")) {
            if (editCpfCnpj.getText().trim().length() != 11 && editCpfCnpj.getText().trim().length() != 14) {
                return false;
            } else {
                if (Biblioteca.validaCpfCnpj(editCpfCnpj.getText().trim())) {
                    localizaClienteNoBanco();
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    private void localizaClienteNoBanco() {
        try {
            ClienteController clienteController = new ClienteController();
            cliente = clienteController.getBean(ViewNfceClienteVO.class, "cpf", editCpfCnpj.getText());
            if (cliente != null) {
                editNome.setText(cliente.getNome());
                editEndereco.setText(cliente.getLogradouro());
            } else {
                cliente = new ViewNfceClienteVO();
                cliente.setCpf(editCpfCnpj.getText().trim());
                cliente.setNome(editNome.getText().trim());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar os dados do cliente.", "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void confirma() {
        if (!validaCpfCnpj()) {
            JOptionPane.showMessageDialog(this, "CPF ou CNPJ Inválido.", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
            editCpfCnpj.requestFocus();
        } else {
            if (cliente.getId() != null) {
                ClienteVO c = new ClienteVO();
                c.setId(cliente.getId());
                SessaoUsuario.vendaAtual.setCliente(c);

                preencheDadosDestinatario();
            } else {
                SessaoUsuario.vendaAtual.setCliente(null);

                preencheDadosDestinatario();
            }
            dispose();
        }
    }

    private void preencheDadosDestinatario() {
        NfeDestinatarioVO destinatario = new NfeDestinatarioVO();
        destinatario.setNfeCabecalho(SessaoUsuario.vendaAtual);
        destinatario.setNome(cliente.getNome());
        destinatario.setCpfCnpj(cliente.getCpf());

        SessaoUsuario.vendaAtual.setDestinatario(destinatario);
    }

    private void localiza() {
        ImportaCliente janelaImportaCliente = new ImportaCliente(this, true);
        janelaImportaCliente.setLocationRelativeTo(null);
        janelaImportaCliente.setVisible(true);
        if (!janelaImportaCliente.cancelado) {
            cliente = janelaImportaCliente.getCliente();
            if (cliente != null) {
                if (cliente.getCpf() == null) {
                    JOptionPane.showMessageDialog(this, "Cliente armazenado no banco de dados sem CPF.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
                    editCpfCnpj.setText("");
                    editNome.setText("");
                    editEndereco.setText("");
                    editCpfCnpj.requestFocus();
                    cliente = null;
                } else {
                    editCpfCnpj.setText(cliente.getCpf());
                    editNome.setText(cliente.getNome());
                    editEndereco.setText(cliente.getLogradouro());
                }
            }
        }
    }
}
