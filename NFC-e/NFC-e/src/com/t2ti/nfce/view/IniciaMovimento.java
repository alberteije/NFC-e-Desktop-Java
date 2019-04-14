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
import com.t2ti.pafecf.infra.Biblioteca;
import com.t2ti.pafecf.infra.MonetarioDocument;
import com.t2ti.pafecf.infra.TableModelGenerico;
import com.t2tierp.nfce.java.NfceMovimentoVO;
import com.t2tierp.nfce.java.NfceOperadorVO;
import com.t2tierp.nfce.java.NfceSuprimentoVO;
import com.t2tierp.nfce.java.NfceTurnoVO;
import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.view.JasperViewer;

public class IniciaMovimento extends javax.swing.JDialog {

    private TableModelGenerico<NfceTurnoVO> tableModel;
    private StringBuilder linhasRelatorio;

    public IniciaMovimento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        linhasRelatorio = new StringBuilder();

        try {
            int rgb = Integer.valueOf(SessaoUsuario.getConfiguracao().getCorJanelasInternas());
            panelPrincipal.setBackground(new Color(rgb));
            panelAbertura.setBackground(new Color(rgb));
            panelBotoes.setBackground(new Color(rgb));
            panelComponentes.setBackground(new Color(rgb));
            panelGerente.setBackground(new Color(rgb));
            panelOperador.setBackground(new Color(rgb));
            panelTurno.setBackground(new Color(rgb));
        } catch (Exception e) {
        }

        editSuprimento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        editSuprimento.setDocument(new MonetarioDocument());
        editSuprimento.setText("0");

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
        gridTurno.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        this.setPreferredSize(new Dimension(635, 340));
        
        try {
            Image bi = ImageIO.read(new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "body.jpg"));
            jLabel8.setText("");
            jLabel8.setIcon(new ImageIcon(bi.getScaledInstance(jLabel8.size().width, jLabel8.size().height, Image.SCALE_SMOOTH)));          
        } catch (IOException e) {
            e.printStackTrace();
        }        

        this.pack();

        configuraGridTurno();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panelPrincipal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelComponentes = new javax.swing.JPanel();
        panelAbertura = new javax.swing.JPanel();
        panelTurno = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        gridTurno = new javax.swing.JTable();
        panelOperador = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        editLoginOperador = new javax.swing.JTextField();
        editSenhaOperador = new javax.swing.JPasswordField();
        panelGerente = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        editLoginGerente = new javax.swing.JTextField();
        editSenhaGerente = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        editSuprimento = new javax.swing.JFormattedTextField();
        panelBotoes = new javax.swing.JPanel();
        botaoConfirma = new javax.swing.JButton();
        botaoCancela = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Inicia Movimento do Caixa");
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

        panelAbertura.setBackground(new Color(255,255,255,0));
        panelAbertura.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados para abertura do movimento:"));
        panelAbertura.setLayout(new java.awt.GridBagLayout());

        panelTurno.setBorder(javax.swing.BorderFactory.createTitledBorder("Turno:"));
        panelTurno.setLayout(new java.awt.GridLayout(1, 0));

        jScrollPane1.setViewportView(gridTurno);

        panelTurno.add(jScrollPane1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 240;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        panelAbertura.add(panelTurno, gridBagConstraints);

        panelOperador.setBackground(new Color(255,255,255,0));
        panelOperador.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do Operador:"));
        panelOperador.setLayout(new java.awt.GridBagLayout());

        jLabel6.setText("Login:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        panelOperador.add(jLabel6, gridBagConstraints);

        jLabel7.setText("Senha:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        panelOperador.add(jLabel7, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        panelOperador.add(editLoginOperador, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelOperador.add(editSenhaOperador, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        panelAbertura.add(panelOperador, gridBagConstraints);

        panelGerente.setBackground(new Color(255,255,255,0));
        panelGerente.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do Gerente:"));
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
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
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
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        panelAbertura.add(panelGerente, gridBagConstraints);

        jLabel5.setText("Fundo de Caixa (Suprimento):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        panelAbertura.add(jLabel5, gridBagConstraints);

        editSuprimento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        editSuprimento.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        panelAbertura.add(editSuprimento, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        panelComponentes.add(panelAbertura, gridBagConstraints);

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

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/layout/body.jpg"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel8, gridBagConstraints);

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancela;
    private javax.swing.JButton botaoConfirma;
    private javax.swing.JTextField editLoginGerente;
    private javax.swing.JTextField editLoginOperador;
    private javax.swing.JPasswordField editSenhaGerente;
    private javax.swing.JPasswordField editSenhaOperador;
    private javax.swing.JFormattedTextField editSuprimento;
    private javax.swing.JTable gridTurno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelAbertura;
    private javax.swing.JPanel panelBotoes;
    private javax.swing.JPanel panelComponentes;
    private javax.swing.JPanel panelGerente;
    private javax.swing.JPanel panelOperador;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelTurno;
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
            cancela();
        }
    }

    private void configuraGridTurno() {
        try {
            NfceControllerGenerico<NfceTurnoVO> turnoController = new NfceControllerGenerico<>();
            List<NfceTurnoVO> listaTurno = turnoController.getBeans(NfceTurnoVO.class);

            tableModel = new TableModelGenerico<>(new NfceTurnoVO());
            gridTurno.setModel(tableModel);
            tableModel.setValues(listaTurno);
            gridTurno.setRowSelectionInterval(0, 0);
            gridTurno.requestFocus();
        } catch (Exception ex) {
        }
    }

    private void confirma() {
        if (gridTurno.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Selecione o Turno.", "Aviso do Sistema", JOptionPane.WARNING_MESSAGE);
            return;
        }
        SessaoUsuario.autenticaUsuario(editLoginOperador.getText(), new String(editSenhaOperador.getPassword()));
        Double valorSuprimento = Double.valueOf(editSuprimento.getText().replace(".", "").replace(",", "."));

        if (SessaoUsuario.usuario != null) {
            NfceOperadorVO gerente = SessaoUsuario.autenticaGerenteSupervisor(editLoginGerente.getText(), new String(editSenhaGerente.getPassword()));
            if (gerente != null) {
                try {
                    NfceControllerGenerico<NfceMovimentoVO> movimentoController = new NfceControllerGenerico<>();
                    NfceMovimentoVO movimento = new NfceMovimentoVO();
                    NfceTurnoVO turno = tableModel.getRow(gridTurno.getSelectedRow());
                    Date dataAtual = new Date();

                    movimento.setNfceTurno(turno);
                    movimento.setEmpresa(SessaoUsuario.getConfiguracao().getEmpresa());
                    movimento.setNfceOperador(SessaoUsuario.usuario);
                    movimento.setNfceCaixa(SessaoUsuario.getConfiguracao().getNfceCaixa());
                    movimento.setIdGerenteSupervisor(gerente.getId());
                    movimento.setDataAbertura(dataAtual);
                    movimento.setHoraAbertura(Biblioteca.formataHora(dataAtual));
                    movimento.setTotalSuprimento(BigDecimal.valueOf(valorSuprimento));
                    movimento.setStatusMovimento("A");
                    movimento.setTotalAcrescimo(BigDecimal.ZERO);
                    movimento.setTotalCancelado(BigDecimal.ZERO);
                    movimento.setTotalDesconto(BigDecimal.ZERO);
                    movimento.setTotalFinal(BigDecimal.ZERO);
                    movimento.setTotalNaoFiscal(BigDecimal.ZERO);
                    movimento.setTotalRecebido(BigDecimal.ZERO);
                    movimento.setTotalSangria(BigDecimal.ZERO);
                    movimento.setTotalTroco(BigDecimal.ZERO);
                    movimento.setTotalVenda(BigDecimal.ZERO);

                    movimentoController.salvar(movimento);

                    SessaoUsuario.movimento = movimento;

                    suprimento(valorSuprimento, dataAtual, movimentoController);
                    
                    imprimeAbertura();

                    JOptionPane.showMessageDialog(this, "Movimento aberto com sucesso.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
                    SessaoUsuario.statusCaixa = SessaoUsuario.SC_ABERTO;

                    dispose();
                } catch (Exception ex) {
                    //ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Ocorreu um erro ao abrir o movimento\n." + ex.getMessage(), "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Gerente ou Supervisor: dados incorretos ou nível de acesso não permitido.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Operador: dados incorretos!", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void cancela() {
        SessaoUsuario.movimento = null;
        SessaoUsuario.statusCaixa = SessaoUsuario.SC_SOMENTE_CONSULTA;
        dispose();
    }

    private void suprimento(double valor, Date dataAtual, NfceControllerGenerico<NfceMovimentoVO> movimentoController) {
        if (valor > 0) {
            try {
                NfceControllerGenerico<NfceSuprimentoVO> suprimentoController = new NfceControllerGenerico<>();
                NfceSuprimentoVO suprimento = new NfceSuprimentoVO();
                suprimento.setNfceMovimento(SessaoUsuario.movimento);
                suprimento.setDataSuprimento(dataAtual);
                suprimento.setObservacao("Abertura do Caixa");
                suprimento.setValor(BigDecimal.valueOf(valor));

                suprimentoController.salvar(suprimento);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Falha ao Registrar o Suprimento de Caixa\n." + ex.getMessage(), "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void imprimeAbertura() {
        try {
            linhasRelatorio.setLength(0);
            append(Biblioteca.repete("=", 48));
            append("               ABERTURA DE CAIXA ");
            append("");
            append("DATA DE ABERTURA: " + Biblioteca.formataData(SessaoUsuario.movimento.getDataAbertura()));
            append("HORA DE ABERTURA: " + SessaoUsuario.movimento.getHoraAbertura());
            append(SessaoUsuario.movimento.getNfceCaixa().getNome() + "  OPERADOR: " + SessaoUsuario.movimento.getNfceOperador().getLogin());
            append("MOVIMENTO: " + SessaoUsuario.movimento.getId());
            append(Biblioteca.repete("=", 48));
            append("");
            append("SUPRIMENTO...: " + Biblioteca.formatoDecimal("V", SessaoUsuario.movimento.getTotalSuprimento().doubleValue()));
            append("");
            append("");
            append("");
            append(" ________________________________________ ");
            append("             VISTO DO CAIXA ");
            append("");
            append("");
            append("");
            append(" ________________________________________ ");
            append("           VISTO DO SUPERVISOR ");

            Map map = new HashMap();
            map.put("CONTEUDO", linhasRelatorio.toString());

            JasperPrint jp = JasperFillManager.fillReport(this.getClass().getResourceAsStream("/relatorios/relatorioMovimento.jasper"), map, new JREmptyDataSource());
            JasperPrintManager.printReport(jp, false);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao imprimir o relatório de abertura de movimento.\n." + ex.getMessage(), "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void append(String texto) {
        linhasRelatorio.append(texto + "\n");
    }

}
