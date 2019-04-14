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

import com.t2ti.nfce.controller.NfceControllerGenerico;
import com.t2ti.nfce.infra.SessaoUsuario;
import com.t2tierp.nfce.java.NfceMovimentoVO;
import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class MovimentoAberto extends javax.swing.JDialog {

    public MovimentoAberto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        try {
            int rgb = Integer.valueOf(SessaoUsuario.getConfiguracao().getCorJanelasInternas());
            panelPrincipal.setBackground(new Color(rgb));
            panelBotoes.setBackground(new Color(rgb));
            panelComponentes.setBackground(new Color(rgb));
            panelDadosMovimento.setBackground(new Color(rgb));
            panelOperador.setBackground(new Color(rgb));
        } catch (Exception e) {
        }

        labelTurno.setText(SessaoUsuario.movimento.getNfceTurno().getDescricao());
        labelTerminal.setText(SessaoUsuario.movimento.getNfceCaixa().getNome());
        labelOperador.setText(SessaoUsuario.movimento.getNfceOperador().getLogin());
        labelDataInicio.setText(new SimpleDateFormat("dd/MM/yyyy").format(SessaoUsuario.movimento.getDataAbertura()));
        labelHoraInicio.setText(SessaoUsuario.movimento.getHoraAbertura());

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

        //this.setPreferredSize(new Dimension(556, 350));
        this.setPreferredSize(new Dimension(450, 250));
        
        try {
            Image bi = ImageIO.read(new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "body.jpg"));
            jLabel6.setText("");
            jLabel6.setIcon(new ImageIcon(bi.getScaledInstance(jLabel6.size().width, jLabel6.size().height, Image.SCALE_SMOOTH)));          
        } catch (IOException e) {
            e.printStackTrace();
        }        
        
        this.pack();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelPrincipal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelComponentes = new javax.swing.JPanel();
        panelDadosMovimento = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        labelTurno = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        labelTerminal = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        labelDataInicio = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        labelOperador = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        labelHoraInicio = new javax.swing.JLabel();
        panelBotoes = new javax.swing.JPanel();
        botaoConfirma = new javax.swing.JButton();
        botaoCancela = new javax.swing.JButton();
        panelOperador = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        editSenhaOperador = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Acessa Movimento de Caixa");
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(new java.awt.CardLayout());

        panelPrincipal.setLayout(new java.awt.GridBagLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/telas/telaMonitor04.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        panelPrincipal.add(jLabel1, gridBagConstraints);

        panelComponentes.setLayout(new java.awt.GridBagLayout());

        panelDadosMovimento.setBackground(new Color(255,255,255,0));
        panelDadosMovimento.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do movimento aberto:"));
        panelDadosMovimento.setLayout(new java.awt.GridBagLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Turno:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        panelDadosMovimento.add(jLabel4, gridBagConstraints);

        labelTurno.setText("jLabel6");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        panelDadosMovimento.add(labelTurno, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Terminal:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        panelDadosMovimento.add(jLabel7, gridBagConstraints);

        labelTerminal.setText("jLabel8");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        panelDadosMovimento.add(labelTerminal, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Data Início:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        panelDadosMovimento.add(jLabel9, gridBagConstraints);

        labelDataInicio.setText("jLabel10");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        panelDadosMovimento.add(labelDataInicio, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Operador:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        panelDadosMovimento.add(jLabel11, gridBagConstraints);

        labelOperador.setText("jLabel12");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        panelDadosMovimento.add(labelOperador, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Hora Início:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        panelDadosMovimento.add(jLabel12, gridBagConstraints);

        labelHoraInicio.setText("jLabel12");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        panelDadosMovimento.add(labelHoraInicio, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        panelComponentes.add(panelDadosMovimento, gridBagConstraints);
        panelDadosMovimento.getAccessibleContext().setAccessibleName("");

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

        panelOperador.setLayout(new java.awt.GridBagLayout());

        jLabel5.setText("Senha Operador:");
        panelOperador.add(jLabel5, new java.awt.GridBagConstraints());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelOperador.add(editSenhaOperador, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        panelComponentes.add(panelOperador, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        panelPrincipal.add(panelComponentes, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/layout/body.jpg"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel6, gridBagConstraints);

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
        cancela();
}//GEN-LAST:event_botaoCancelaActionPerformed

    private void botaoConfirmaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_botaoConfirmaFocusLost
        confirma();
    }//GEN-LAST:event_botaoConfirmaFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancela;
    private javax.swing.JButton botaoConfirma;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPasswordField editSenhaOperador;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelDataInicio;
    private javax.swing.JLabel labelHoraInicio;
    private javax.swing.JLabel labelOperador;
    private javax.swing.JLabel labelTerminal;
    private javax.swing.JLabel labelTurno;
    private javax.swing.JPanel panelBotoes;
    private javax.swing.JPanel panelComponentes;
    private javax.swing.JPanel panelDadosMovimento;
    private javax.swing.JPanel panelOperador;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables

    private class ConfirmaAction extends AbstractAction {

        public ConfirmaAction() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            confirma();
        }
    }

    private class CancelaAction extends AbstractAction {

        public CancelaAction() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            cancela();
        }
    }

    private void confirma() {
        try {
            SessaoUsuario.autenticaUsuario(labelOperador.getText(), new String(editSenhaOperador.getPassword()));
            if (SessaoUsuario.usuario != null) {
                if (SessaoUsuario.movimento.getStatusMovimento().equals("T")) {
                    NfceControllerGenerico<NfceMovimentoVO> movimentoController = new NfceControllerGenerico<>();
                    SessaoUsuario.movimento.setStatusMovimento("A");
                    movimentoController.atualizar(SessaoUsuario.movimento);
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Operador: dados incorretos!", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao abrir o movimento\n." + ex.getMessage(), "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cancela() {
        System.exit(0);
    }
}
