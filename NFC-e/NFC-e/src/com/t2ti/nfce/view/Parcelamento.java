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
import com.t2ti.nfce.controller.NfceControllerGenerico;
import com.t2ti.nfce.infra.SessaoUsuario;
import com.t2ti.pafecf.infra.Biblioteca;
import com.t2ti.pafecf.infra.TableModelGenerico;
import com.t2tierp.cadastros.java.ClienteVO;
import com.t2tierp.cadastros.java.ContaCaixaVO;
import com.t2tierp.financeiro.java.FinDocumentoOrigemVO;
import com.t2tierp.financeiro.java.FinLancamentoReceberVO;
import com.t2tierp.financeiro.java.FinParcelaReceberVO;
import com.t2tierp.financeiro.java.FinStatusParcelaVO;
import com.t2tierp.nfce.java.ViewNfceClienteVO;
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
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class Parcelamento extends javax.swing.JDialog {

    boolean cancelado = false;
    private TableModelGenerico<FinParcelaReceberVO> tableModel;
    private BigDecimal valorParcelar;
    private Calendar primeiroVencimento;
    private ClienteVO cliente;

    public Parcelamento(java.awt.Frame parent, boolean modal, ClienteVO cliente, BigDecimal valorVenda, BigDecimal valorRecebido, BigDecimal valorParcelar, BigDecimal valorDesconto, BigDecimal valorAcrescimo) {
        super(parent, modal);
        initComponents();
        configuraTela();

        SpinnerModel spinnerModel = new SpinnerNumberModel(Double.valueOf(1), //valor inicial
                Double.valueOf(1), //valor minimo
                Double.valueOf(SessaoUsuario.getConfiguracao().getQuantidadeMaximaParcela()), // valor maximo
                Double.valueOf(1) //incrementa/decrementa em
        );
        spinnerQtdeParcela.setModel(spinnerModel);

        this.cliente = cliente;
        this.valorParcelar = valorParcelar;
        primeiroVencimento = Calendar.getInstance();
        primeiroVencimento.add(Calendar.DAY_OF_MONTH, 30);

        editValorVenda.setText(Biblioteca.formatoDecimal("V", valorVenda.doubleValue()));
        editValorRecebido.setText(Biblioteca.formatoDecimal("V", valorRecebido.doubleValue()));
        editDescontoAcrescimo.setText(Biblioteca.formatoDecimal("V", Biblioteca.subtrai(valorAcrescimo, valorDesconto).doubleValue()));
        editValorParcelar.setText(Biblioteca.formatoDecimal("V", valorParcelar.doubleValue()));
        editVencimento.setText(Biblioteca.formataData(primeiroVencimento.getTime()));

        if (cliente != null) {
            try {
                ViewNfceClienteVO viewCliente = new ClienteController().getBean(cliente.getId(), ViewNfceClienteVO.class);
                editNomeCliente.setText(viewCliente.getNome());
                editCpfCnpj.setText(viewCliente.getCpf());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        geraParcelas(1);
    }

    private void configuraTela() {
        try {
            int rgb = Integer.valueOf(SessaoUsuario.getConfiguracao().getCorJanelasInternas());

            panelPrincipal.setBackground(new Color(rgb));
            panelComponentes.setBackground(new Color(rgb));
            panelGrid.setBackground(new Color(rgb));
            panelCliente.setBackground(new Color(rgb));
            panelBotoes.setBackground(new Color(rgb));
        } catch (Exception ex) {
        }

        try {
            MaskFormatter mascara = new MaskFormatter("##/##/####");
            DefaultFormatterFactory formatter = new DefaultFormatterFactory(mascara);
            editVencimento.setFormatterFactory(formatter);
        } catch (ParseException ex) {
            //ex.printStackTrace();
        }

        configuraGridFinParcelaReceber();

        CancelaAction cancelaAction = new CancelaAction();
        botaoCancela.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancelaAction");
        botaoCancela.getActionMap().put("cancelaAction", cancelaAction);

        ConfirmaAction confirmaAction = new ConfirmaAction();
        botaoConfirma.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), "confirmaAction");
        botaoConfirma.getActionMap().put("confirmaAction", confirmaAction);

        LocalizaAction localizaAction = new LocalizaAction();
        botaoConfirma.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "localizaAction");
        botaoConfirma.getActionMap().put("localizaAction", localizaAction);

        //troca TAB por ENTER
        HashSet conj = new HashSet(this.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        this.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
        gridNfeCabecalho.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        spinnerQtdeParcela.requestFocus();

        this.setPreferredSize(new Dimension(650, 450));
        
        try {
            Image bi = ImageIO.read(new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "body.jpg"));
            jLabel10.setText("");
            jLabel10.setIcon(new ImageIcon(bi.getScaledInstance(jLabel10.size().width, jLabel10.size().height, Image.SCALE_SMOOTH)));          
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
        panelGrid = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        gridNfeCabecalho = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        editVencimento = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        spinnerQtdeParcela = new javax.swing.JSpinner();
        editValorParcelar = new javax.swing.JTextField();
        editDescontoAcrescimo = new javax.swing.JTextField();
        editValorRecebido = new javax.swing.JTextField();
        editValorVenda = new javax.swing.JTextField();
        panelCliente = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        botaoLocaliza = new javax.swing.JButton();
        editCpfCnpj = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        editNomeCliente = new javax.swing.JTextField();
        panelBotoes = new javax.swing.JPanel();
        botaoConfirma = new javax.swing.JButton();
        botaoCancela = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Parcelamento");
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(new java.awt.CardLayout());

        panelPrincipal.setLayout(new java.awt.GridBagLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/telas/telaDinheiro05.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        panelPrincipal.add(jLabel1, gridBagConstraints);

        panelComponentes.setLayout(new java.awt.GridBagLayout());

        panelGrid.setBackground(new Color(255,255,255,0));
        panelGrid.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do Parcelamento"));
        panelGrid.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setBorder(null);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setMinimumSize(new java.awt.Dimension(452, 200));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 200));

        gridNfeCabecalho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(gridNfeCabecalho);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelGrid.add(jScrollPane1, gridBagConstraints);

        jLabel3.setText("Nº de Parcelas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        panelGrid.add(jLabel3, gridBagConstraints);

        jLabel4.setText("Valor Recebido:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        panelGrid.add(jLabel4, gridBagConstraints);

        jLabel5.setText("Valor da Venda:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        panelGrid.add(jLabel5, gridBagConstraints);

        jLabel6.setText("1º Vencimento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        panelGrid.add(jLabel6, gridBagConstraints);

        editVencimento.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelGrid.add(editVencimento, gridBagConstraints);

        jLabel7.setText("Desconto/Acréscimo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        panelGrid.add(jLabel7, gridBagConstraints);

        jLabel8.setText("Valor a Parcelar:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        panelGrid.add(jLabel8, gridBagConstraints);

        spinnerQtdeParcela.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerQtdeParcelaStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelGrid.add(spinnerQtdeParcela, gridBagConstraints);

        editValorParcelar.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelGrid.add(editValorParcelar, gridBagConstraints);

        editDescontoAcrescimo.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelGrid.add(editDescontoAcrescimo, gridBagConstraints);

        editValorRecebido.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelGrid.add(editValorRecebido, gridBagConstraints);

        editValorVenda.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelGrid.add(editValorVenda, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        panelComponentes.add(panelGrid, gridBagConstraints);

        panelCliente.setBackground(new Color(255,255,255,0));
        panelCliente.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do Cliente"));
        panelCliente.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Nome:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        panelCliente.add(jLabel2, gridBagConstraints);

        botaoLocaliza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgBotoes/botaoLocalizar.png"))); // NOI18N
        botaoLocaliza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLocalizaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelCliente.add(botaoLocaliza, gridBagConstraints);

        editCpfCnpj.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelCliente.add(editCpfCnpj, gridBagConstraints);

        jLabel9.setText("CPF/CNPJ:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        panelCliente.add(jLabel9, gridBagConstraints);

        editNomeCliente.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelCliente.add(editNomeCliente, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        panelComponentes.add(panelCliente, gridBagConstraints);

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

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/layout/body.jpg"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel10, gridBagConstraints);

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

    private void botaoLocalizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLocalizaActionPerformed
        localiza();
    }//GEN-LAST:event_botaoLocalizaActionPerformed

    private void spinnerQtdeParcelaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerQtdeParcelaStateChanged
        geraParcelas(((Double) (((JSpinner) evt.getSource()).getValue())).intValue());
    }//GEN-LAST:event_spinnerQtdeParcelaStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancela;
    private javax.swing.JButton botaoConfirma;
    private javax.swing.JButton botaoLocaliza;
    private javax.swing.JTextField editCpfCnpj;
    private javax.swing.JTextField editDescontoAcrescimo;
    private javax.swing.JTextField editNomeCliente;
    private javax.swing.JTextField editValorParcelar;
    private javax.swing.JTextField editValorRecebido;
    private javax.swing.JTextField editValorVenda;
    private javax.swing.JFormattedTextField editVencimento;
    private javax.swing.JTable gridNfeCabecalho;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelBotoes;
    private javax.swing.JPanel panelCliente;
    private javax.swing.JPanel panelComponentes;
    private javax.swing.JPanel panelGrid;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JSpinner spinnerQtdeParcela;
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

    private void configuraGridFinParcelaReceber() {
        tableModel = new TableModelGenerico<>(new FinParcelaReceberVO());
        gridNfeCabecalho.setModel(tableModel);
        gridNfeCabecalho.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void localiza() {
        ImportaCliente janelaImportaCliente = new ImportaCliente(this, true);
        janelaImportaCliente.setLocationRelativeTo(null);
        janelaImportaCliente.setVisible(true);
        if (!janelaImportaCliente.cancelado) {
            ViewNfceClienteVO viewCliente = janelaImportaCliente.getCliente();
            if (viewCliente != null) {
                editNomeCliente.setText(viewCliente.getNome());
                editCpfCnpj.setText(viewCliente.getCpf());
                cliente = new ClienteVO();
                cliente.setId(viewCliente.getId());
            }
        }
    }

    private void geraParcelas(int qtdeParcelas) {
        tableModel.limpaRegistros();
        BigDecimal valorCadaParcela = Biblioteca.divide(valorParcelar, BigDecimal.valueOf(qtdeParcelas));
        BigDecimal resto = Biblioteca.multiplica(valorCadaParcela, BigDecimal.valueOf(qtdeParcelas));
        resto = Biblioteca.subtrai(valorParcelar, resto);

        FinParcelaReceberVO parcelaReceber;
        Calendar proximoVencimento = Calendar.getInstance();
        proximoVencimento.setTime(primeiroVencimento.getTime());
        for (int i = 0; i < qtdeParcelas; i++) {
            parcelaReceber = new FinParcelaReceberVO();
            parcelaReceber.setNumeroParcela(i + 1);
            if (i == 0) {
                parcelaReceber.setValor(Biblioteca.soma(valorCadaParcela, resto));
            } else {
                parcelaReceber.setValor(valorCadaParcela);
                proximoVencimento.add(Calendar.DAY_OF_MONTH, 30);
            }
            parcelaReceber.setDataVencimento(proximoVencimento.getTime());

            tableModel.adicionaRegistro(parcelaReceber);
        }
    }

    private void confirma() {
        if (cliente == null) {
            JOptionPane.showMessageDialog(this, "É necessário selecionar um cliente.", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                FinDocumentoOrigemVO documentoOrigem = new FinDocumentoOrigemVO();
                documentoOrigem.setId(32);

                ContaCaixaVO contaCaixa = new ContaCaixaVO();
                contaCaixa.setId(2);

                FinStatusParcelaVO statusParcela = new FinStatusParcelaVO();
                statusParcela.setId(1);
                
                String identificador = "E" + SessaoUsuario.getConfiguracao().getEmpresa().getId()
                        + "X" + SessaoUsuario.getConfiguracao().getNfceCaixa().getId()
                        + "V" + SessaoUsuario.vendaAtual.getId()
                        + "C" + SessaoUsuario.vendaAtual.getNumero()
                        + "Q" + tableModel.getRowCount();

                FinLancamentoReceberVO lancamentoReceber = new FinLancamentoReceberVO();
                lancamentoReceber.setFinDocumentoOrigem(documentoOrigem);
                lancamentoReceber.setCliente(cliente);
                lancamentoReceber.setQuantidadeParcela(tableModel.getRowCount());
                lancamentoReceber.setValorTotal(valorParcelar);
                lancamentoReceber.setValorAReceber(valorParcelar);
                lancamentoReceber.setDataLancamento(new Date());
                lancamentoReceber.setNumeroDocumento(identificador);
                lancamentoReceber.setPrimeiroVencimento(primeiroVencimento.getTime());
                lancamentoReceber.setCodigoModuloLcto("NFC");
                lancamentoReceber.setListaFinParcelaReceber(tableModel.getValues());

                for (FinParcelaReceberVO p : lancamentoReceber.getListaFinParcelaReceber()) {
                    p.setFinLancamentoReceber(lancamentoReceber);
                    p.setFinStatusParcela(statusParcela);
                    p.setContaCaixa(contaCaixa);
                }
                
                new NfceControllerGenerico<FinLancamentoReceberVO>().salvar(lancamentoReceber);
                dispose();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao gravar parcelamento.\n" + e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
