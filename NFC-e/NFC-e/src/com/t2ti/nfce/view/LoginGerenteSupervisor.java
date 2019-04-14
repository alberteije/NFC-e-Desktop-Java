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

import com.t2ti.nfce.infra.SessaoUsuario;
import com.t2tierp.nfce.java.NfceOperadorVO;
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

public class LoginGerenteSupervisor extends javax.swing.JDialog {

    private boolean cancelado = false;

    public LoginGerenteSupervisor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        try {
            int rgb = Integer.valueOf(SessaoUsuario.getConfiguracao().getCorJanelasInternas());
            panelPrincipal.setBackground(new Color(rgb));
            panelBotoes.setBackground(new Color(rgb));
            panelComponentes.setBackground(new Color(rgb));
            panelGerente.setBackground(new Color(rgb));
        } catch (Exception ex) {
        }

        CancelaAction cancelaAction = new CancelaAction();
        botaoCancela.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancelaAction");
        botaoCancela.getActionMap().put("cancelaAction", cancelaAction);

        ConfirmaAction confirmaAction = new ConfirmaAction();
        botaoConfirma.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), "confirmaAction");
        botaoConfirma.getActionMap().put("confirmaAction", confirmaAction);

        //troca TAB por ENTER
        HashSet conj = new HashSet(this.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        this.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        this.setPreferredSize(new Dimension(370, 180));
        
        try {
            Image bi = ImageIO.read(new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "body.jpg"));
            jLabel4.setText("");
            jLabel4.setIcon(new ImageIcon(bi.getScaledInstance(jLabel4.size().width, jLabel4.size().height, Image.SCALE_SMOOTH)));          
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
        panelGerente = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        editLoginGerente = new javax.swing.JTextField();
        editSenhaGerente = new javax.swing.JPasswordField();
        panelBotoes = new javax.swing.JPanel();
        botaoConfirma = new javax.swing.JButton();
        botaoCancela = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Login Gerente/Supervisor");
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(new java.awt.CardLayout());

        panelPrincipal.setLayout(new java.awt.GridBagLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/telas/telaUsuarioSenha01.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        panelPrincipal.add(jLabel1, gridBagConstraints);

        panelComponentes.setLayout(new java.awt.GridBagLayout());

        panelGerente.setBackground(new Color(255,255,255,0));
        panelGerente.setBorder(javax.swing.BorderFactory.createTitledBorder("Login Gerente/Supervisor"));
        panelGerente.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Login:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        panelGerente.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Senha:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        panelGerente.add(jLabel3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelGerente.add(editLoginGerente, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelGerente.add(editSenhaGerente, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        panelComponentes.add(panelGerente, gridBagConstraints);

        panelBotoes.setBackground(new Color(255,255,255,0));
        panelBotoes.setLayout(new java.awt.GridBagLayout());

        botaoConfirma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgBotoes/botaoConfirmar.png"))); // NOI18N
        botaoConfirma.setText("Confirma (F12)");
        botaoConfirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoConfirmaActionPerformed(evt);
            }
        });
        botaoConfirma.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                botaoConfirmaFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
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
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 5);
        panelBotoes.add(botaoCancela, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
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

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/layout/body.jpg"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        panelPrincipal.add(jPanel1, gridBagConstraints);

        getContentPane().add(panelPrincipal, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoConfirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoConfirmaActionPerformed
        dispose();
}//GEN-LAST:event_botaoConfirmaActionPerformed

    private void botaoCancelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelaActionPerformed
        dispose();
}//GEN-LAST:event_botaoCancelaActionPerformed

    private void botaoConfirmaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_botaoConfirmaFocusLost
        cancelado = false;
        dispose();
    }//GEN-LAST:event_botaoConfirmaFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancela;
    private javax.swing.JButton botaoConfirma;
    private javax.swing.JTextField editLoginGerente;
    private javax.swing.JPasswordField editSenhaGerente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelBotoes;
    private javax.swing.JPanel panelComponentes;
    private javax.swing.JPanel panelGerente;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables

    private class ConfirmaAction extends AbstractAction {

        public ConfirmaAction() {
        }

        public void actionPerformed(ActionEvent e) {
            cancelado = false;
            dispose();
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

    public void setLogin(String login, String senha) {
        editLoginGerente.setText(login);
        editSenhaGerente.setText(senha);
    }

    public boolean loginGerente(boolean mostraMensagem) {
        if (cancelado) {
            return false;
        }
        NfceOperadorVO gerente = SessaoUsuario.autenticaGerenteSupervisor(editLoginGerente.getText(), new String(editSenhaGerente.getPassword()));
        if (gerente == null) {
            if (mostraMensagem) {
                JOptionPane.showMessageDialog(this, "Gerente ou Supervisor: dados incorretos ou nível de acesso não permitido.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            //verifica nivel de acesso do gerente
            if (gerente.getNivelAutorizacao().equals("G")) {
                return true;
            } else {
                if (mostraMensagem) {
                    JOptionPane.showMessageDialog(rootPane, "Gerente: nível de acesso incorreto.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        return false;
    }

    public boolean loginSupervisor(boolean mostraMensagem) {
        if (cancelado) {
            return false;
        }
        NfceOperadorVO supervisor = SessaoUsuario.autenticaGerenteSupervisor(editLoginGerente.getText(), new String(editSenhaGerente.getPassword()));
        if (supervisor == null) {
            if (mostraMensagem) {
                JOptionPane.showMessageDialog(rootPane, "Supervisor: dados incorretos.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            //verifica nivel de acesso do supervisor
            if (supervisor.getNivelAutorizacao().equals("S")) {
                return true;
            } else {
                if (mostraMensagem) {
                    JOptionPane.showMessageDialog(rootPane, "Supervisor: nível de acesso incorreto.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        return false;
    }

    public boolean loginGerenteSupervisor() {
        if (cancelado) {
            return false;
        }
        if (loginGerente(false) || loginSupervisor(false)) {
            return true;
        }
        JOptionPane.showMessageDialog(this, "Dados incorretos ou usuário com nível de acesso incorreto!", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
        return false;
    }
}
