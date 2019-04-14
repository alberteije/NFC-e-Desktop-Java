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
import com.t2ti.pafecf.infra.Biblioteca;
import com.t2ti.pafecf.infra.MonetarioDocument;
import com.t2ti.nfce.infra.SessaoUsuario;
import com.t2ti.pafecf.infra.TableModelGenerico;
import com.t2tierp.nfce.java.NfceTipoPagamentoVO;
import com.t2tierp.nfe.java.NfeFormaPagamentoVO;
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
import java.util.HashSet;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;

public class EfetuaPagamento extends javax.swing.JDialog {

    public boolean pagamentoOK;
    private BigDecimal totalVenda;
    private BigDecimal desconto;
    private BigDecimal acrescimo;
    private BigDecimal totalReceber;
    private BigDecimal troco;
    private BigDecimal totalRecebido;
    private BigDecimal saldoRestante;
    private TableModelGenerico<NfeFormaPagamentoVO> tableModelValores;
    private final String[] opcoesResposta = {"Sim", "Não"};

    public EfetuaPagamento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        totalVenda = new BigDecimal(0);
        desconto = new BigDecimal(0);
        acrescimo = new BigDecimal(0);
        totalReceber = new BigDecimal(0);
        troco = new BigDecimal(0);
        totalRecebido = new BigDecimal(0);
        saldoRestante = new BigDecimal(0);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        editValorPago.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        editValorPago.setDocument(new MonetarioDocument());

        pagamentoOK = false;
        SessaoUsuario.vendaAtual.getListaNfeFormaPagamento().clear();

        try {
            int rgb = Integer.valueOf(SessaoUsuario.getConfiguracao().getCorJanelasInternas());

            panelPrincipal.setBackground(new Color(rgb));
            panelComponentes.setBackground(new Color(rgb));
            panelDados.setBackground(new Color(rgb));
            panelBotoes.setBackground(new Color(rgb));
            panelValores.setBackground(new Color(rgb));
            jPanel1.setBackground(new Color(rgb));
            panelResumoVenda.setBackground(new Color(rgb));
            panelTotalReceber.setBackground(new Color(rgb));
            panelTotalRecebido.setBackground(new Color(rgb));
            panelTotalVenda.setBackground(new Color(rgb));
            panelDesconto.setBackground(new Color(rgb));
            panelTroco.setBackground(new Color(rgb));
            panelTroco1.setBackground(new Color(rgb));
            panelAcrescimo.setBackground(new Color(rgb));
        } catch (Exception e) {
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

        //guarda valores para calculo
        totalVenda = SessaoUsuario.vendaAtual.getValorTotalProdutos();
        desconto = SessaoUsuario.vendaAtual.getValorDesconto();
        acrescimo = SessaoUsuario.vendaAtual.getValorDespesasAcessorias();
        totalReceber = Biblioteca.soma(totalVenda, acrescimo);
        totalReceber = Biblioteca.subtrai(totalReceber, desconto);
        saldoRestante = totalReceber;

        editValorPago.setText(Biblioteca.formatoDecimal("V", totalReceber.doubleValue()).replaceAll(",", ""));

        atualizaLabelsValores();

        telaPadrao();

        //foco no primeiro valor da grid
        cboTiposPagamento.requestFocus();

        setPreferredSize(new Dimension(760, 350));
        
        try {
            Image bi = ImageIO.read(new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "body.jpg"));
            jLabel8.setText("");
            jLabel8.setIcon(new ImageIcon(bi.getScaledInstance(jLabel8.size().width, jLabel8.size().height, Image.SCALE_SMOOTH)));          
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
        this.pack();
    }

    private void atualizaLabelsValores() {
        //formata valores para exibição
        labelTotalVenda.setText(Biblioteca.formatoDecimal("V", totalVenda.doubleValue()));
        labelAcrescimo.setText(Biblioteca.formatoDecimal("V", acrescimo.doubleValue()));
        labelDesconto.setText(Biblioteca.formatoDecimal("V", desconto.doubleValue()));
        labelTotalReceber.setText(Biblioteca.formatoDecimal("V", totalReceber.doubleValue()));
        labelTotalRecebido.setText(Biblioteca.formatoDecimal("V", totalRecebido.doubleValue()));
        if (saldoRestante.compareTo(BigDecimal.ZERO) > 0) {
            labelRestante.setText(Biblioteca.formatoDecimal("V", saldoRestante.doubleValue()));
        } else {
            labelRestante.setText(Biblioteca.formatoDecimal("V", 0));
        }
        labelTroco.setText(Biblioteca.formatoDecimal("V", troco.doubleValue()));
    }

    private void telaPadrao() {
        DefaultComboBoxModel box = new DefaultComboBoxModel();

        for (int i = 0; i < SessaoUsuario.getListaTipoPagamento().size(); i++) {
            box.addElement(SessaoUsuario.getListaTipoPagamento().get(i).getDescricao());
        }

        cboTiposPagamento.setModel(box);

        tableModelValores = new TableModelGenerico<>(new NfeFormaPagamentoVO());
        gridValores.setModel(tableModelValores);
        gridValores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panelPrincipal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelComponentes = new javax.swing.JPanel();
        panelDados = new javax.swing.JPanel();
        panelResumoVenda = new javax.swing.JPanel();
        panelTotalVenda = new javax.swing.JPanel();
        labelDescricaoTotalVenda = new javax.swing.JLabel();
        labelTotalVenda = new javax.swing.JLabel();
        panelDesconto = new javax.swing.JPanel();
        labelDescricaoDesconto = new javax.swing.JLabel();
        labelDesconto = new javax.swing.JLabel();
        panelAcrescimo = new javax.swing.JPanel();
        labelDescricaoAcrescimo = new javax.swing.JLabel();
        labelAcrescimo = new javax.swing.JLabel();
        panelTotalReceber = new javax.swing.JPanel();
        labelDescricaoTotalReceber = new javax.swing.JLabel();
        labelTotalReceber = new javax.swing.JLabel();
        panelTotalRecebido = new javax.swing.JPanel();
        labelDescricaoTotalRecebido = new javax.swing.JLabel();
        labelTotalRecebido = new javax.swing.JLabel();
        panelTroco = new javax.swing.JPanel();
        labelDescricaoTroco = new javax.swing.JLabel();
        labelTroco = new javax.swing.JLabel();
        panelTroco1 = new javax.swing.JPanel();
        labelDescricaoRestante = new javax.swing.JLabel();
        labelRestante = new javax.swing.JLabel();
        panelValores = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        gridValores = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cboTiposPagamento = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        editValorPago = new javax.swing.JFormattedTextField();
        panelBotoes = new javax.swing.JPanel();
        botaoConfirma = new javax.swing.JButton();
        botaoCancela = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Efetua Pagamento para Encerrar Venda");
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(new java.awt.CardLayout());

        panelPrincipal.setPreferredSize(new java.awt.Dimension(590, 326));
        panelPrincipal.setLayout(new java.awt.GridBagLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/telas/telaCarrinho02.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        panelPrincipal.add(jLabel1, gridBagConstraints);

        panelComponentes.setLayout(new java.awt.GridBagLayout());

        panelDados.setLayout(new java.awt.GridBagLayout());

        panelResumoVenda.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumo da Venda:"));
        panelResumoVenda.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        panelResumoVenda.setPreferredSize(new java.awt.Dimension(200, 220));
        panelResumoVenda.setLayout(new java.awt.GridBagLayout());

        panelTotalVenda.setLayout(new java.awt.GridBagLayout());

        labelDescricaoTotalVenda.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelDescricaoTotalVenda.setForeground(new java.awt.Color(0, 0, 255));
        labelDescricaoTotalVenda.setText("Total Venda:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelTotalVenda.add(labelDescricaoTotalVenda, gridBagConstraints);

        labelTotalVenda.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelTotalVenda.setForeground(new java.awt.Color(0, 0, 255));
        labelTotalVenda.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTotalVenda.setText("0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelTotalVenda.add(labelTotalVenda, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelResumoVenda.add(panelTotalVenda, gridBagConstraints);

        panelDesconto.setLayout(new java.awt.GridBagLayout());

        labelDescricaoDesconto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelDescricaoDesconto.setForeground(new java.awt.Color(255, 0, 0));
        labelDescricaoDesconto.setText("Desconto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelDesconto.add(labelDescricaoDesconto, gridBagConstraints);

        labelDesconto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelDesconto.setForeground(new java.awt.Color(255, 0, 0));
        labelDesconto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelDesconto.setText("0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelDesconto.add(labelDesconto, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelResumoVenda.add(panelDesconto, gridBagConstraints);

        panelAcrescimo.setLayout(new java.awt.GridBagLayout());

        labelDescricaoAcrescimo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelDescricaoAcrescimo.setForeground(new java.awt.Color(0, 0, 255));
        labelDescricaoAcrescimo.setText("Acréscimo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelAcrescimo.add(labelDescricaoAcrescimo, gridBagConstraints);

        labelAcrescimo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelAcrescimo.setForeground(new java.awt.Color(0, 0, 255));
        labelAcrescimo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelAcrescimo.setText("0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelAcrescimo.add(labelAcrescimo, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelResumoVenda.add(panelAcrescimo, gridBagConstraints);

        panelTotalReceber.setLayout(new java.awt.GridBagLayout());

        labelDescricaoTotalReceber.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelDescricaoTotalReceber.setForeground(new java.awt.Color(0, 0, 255));
        labelDescricaoTotalReceber.setText("Total a Receber:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelTotalReceber.add(labelDescricaoTotalReceber, gridBagConstraints);

        labelTotalReceber.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelTotalReceber.setForeground(new java.awt.Color(0, 0, 255));
        labelTotalReceber.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTotalReceber.setText("0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelTotalReceber.add(labelTotalReceber, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelResumoVenda.add(panelTotalReceber, gridBagConstraints);

        panelTotalRecebido.setLayout(new java.awt.GridBagLayout());

        labelDescricaoTotalRecebido.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelDescricaoTotalRecebido.setForeground(new java.awt.Color(0, 153, 0));
        labelDescricaoTotalRecebido.setText("Total Recebido:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelTotalRecebido.add(labelDescricaoTotalRecebido, gridBagConstraints);

        labelTotalRecebido.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelTotalRecebido.setForeground(new java.awt.Color(0, 153, 0));
        labelTotalRecebido.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTotalRecebido.setText("0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelTotalRecebido.add(labelTotalRecebido, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelResumoVenda.add(panelTotalRecebido, gridBagConstraints);

        panelTroco.setLayout(new java.awt.GridBagLayout());

        labelDescricaoTroco.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelDescricaoTroco.setForeground(new java.awt.Color(255, 0, 0));
        labelDescricaoTroco.setText("Troco:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelTroco.add(labelDescricaoTroco, gridBagConstraints);

        labelTroco.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelTroco.setForeground(new java.awt.Color(255, 0, 0));
        labelTroco.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTroco.setText("0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelTroco.add(labelTroco, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelResumoVenda.add(panelTroco, gridBagConstraints);

        panelTroco1.setLayout(new java.awt.GridBagLayout());

        labelDescricaoRestante.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelDescricaoRestante.setForeground(new java.awt.Color(0, 0, 204));
        labelDescricaoRestante.setText("Restante:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelTroco1.add(labelDescricaoRestante, gridBagConstraints);

        labelRestante.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelRestante.setForeground(new java.awt.Color(0, 0, 153));
        labelRestante.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelRestante.setText("0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelTroco1.add(labelRestante, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        panelResumoVenda.add(panelTroco1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        panelDados.add(panelResumoVenda, gridBagConstraints);

        panelValores.setBorder(javax.swing.BorderFactory.createTitledBorder("Informe os valores pagos:"));
        panelValores.setMinimumSize(new java.awt.Dimension(216, 220));
        panelValores.setPreferredSize(new java.awt.Dimension(200, 180));
        panelValores.setLayout(new javax.swing.BoxLayout(panelValores, javax.swing.BoxLayout.X_AXIS));

        jScrollPane1.setMinimumSize(new java.awt.Dimension(200, 220));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(200, 220));

        gridValores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(gridValores);

        panelValores.add(jScrollPane1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        panelDados.add(panelValores, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Tipo de Pagamento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        jPanel1.add(jLabel2, gridBagConstraints);

        cboTiposPagamento.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        jPanel1.add(cboTiposPagamento, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Valor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        jPanel1.add(jLabel3, gridBagConstraints);

        editValorPago.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        editValorPago.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                editValorPagoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                editValorPagoFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        jPanel1.add(editValorPago, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        panelDados.add(jPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        panelComponentes.add(panelDados, gridBagConstraints);

        panelBotoes.setBackground(new Color(255,255,255,0));
        panelBotoes.setMinimumSize(new java.awt.Dimension(261, 30));
        panelBotoes.setPreferredSize(new java.awt.Dimension(261, 30));
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
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 10);
        panelComponentes.add(panelBotoes, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        panelPrincipal.add(panelComponentes, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/layout/body.jpg"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jLabel8, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        panelPrincipal.add(jPanel2, gridBagConstraints);

        getContentPane().add(panelPrincipal, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoConfirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoConfirmaActionPerformed
        confirma();
}//GEN-LAST:event_botaoConfirmaActionPerformed

    private void botaoCancelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelaActionPerformed
        cancela();
}//GEN-LAST:event_botaoCancelaActionPerformed

    private void editValorPagoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_editValorPagoFocusLost
        if (!evt.isTemporary()) {
            saiuCampoValor(editValorPago.getText());
        }
    }//GEN-LAST:event_editValorPagoFocusLost

    private void editValorPagoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_editValorPagoFocusGained
        editValorPago.selectAll();
    }//GEN-LAST:event_editValorPagoFocusGained

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancela;
    private javax.swing.JButton botaoConfirma;
    private javax.swing.JComboBox cboTiposPagamento;
    private javax.swing.JFormattedTextField editValorPago;
    private javax.swing.JTable gridValores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelAcrescimo;
    private javax.swing.JLabel labelDesconto;
    private javax.swing.JLabel labelDescricaoAcrescimo;
    private javax.swing.JLabel labelDescricaoDesconto;
    private javax.swing.JLabel labelDescricaoRestante;
    private javax.swing.JLabel labelDescricaoTotalReceber;
    private javax.swing.JLabel labelDescricaoTotalRecebido;
    private javax.swing.JLabel labelDescricaoTotalVenda;
    private javax.swing.JLabel labelDescricaoTroco;
    private javax.swing.JLabel labelRestante;
    private javax.swing.JLabel labelTotalReceber;
    private javax.swing.JLabel labelTotalRecebido;
    private javax.swing.JLabel labelTotalVenda;
    private javax.swing.JLabel labelTroco;
    private javax.swing.JPanel panelAcrescimo;
    private javax.swing.JPanel panelBotoes;
    private javax.swing.JPanel panelComponentes;
    private javax.swing.JPanel panelDados;
    private javax.swing.JPanel panelDesconto;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelResumoVenda;
    private javax.swing.JPanel panelTotalReceber;
    private javax.swing.JPanel panelTotalRecebido;
    private javax.swing.JPanel panelTotalVenda;
    private javax.swing.JPanel panelTroco;
    private javax.swing.JPanel panelTroco1;
    private javax.swing.JPanel panelValores;
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

    private void verificaSaldoRestante() {
        BigDecimal recebidoAteAgora = BigDecimal.ZERO;
        List<NfeFormaPagamentoVO> listaPagamento = tableModelValores.getValues();
        for (NfeFormaPagamentoVO p : listaPagamento) {
            recebidoAteAgora = Biblioteca.soma(recebidoAteAgora, p.getValor());
        }

        saldoRestante = Biblioteca.subtrai(totalReceber, recebidoAteAgora);

        atualizaLabelsValores();
    }

    private void cancela() {
        dispose();
    }

    private void confirma() {
        verificaSaldoRestante();
        if (saldoRestante.compareTo(BigDecimal.ZERO) <= 0) {
            int escolha = JOptionPane.showOptionDialog(null, "Deseja finalizar a venda?", "Pergunta do Sistema",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, opcoesResposta, null);
            if (escolha == JOptionPane.YES_OPTION) {
                finalizaVenda();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Valores informados não são suficientes para finalizar a venda.", "Aviso do Sistema", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void finalizaVenda() {
        try {
            SessaoUsuario.vendaAtual.setValorTotal(totalReceber);
            SessaoUsuario.vendaAtual.setTroco(troco);
            SessaoUsuario.vendaAtual.setListaNfeFormaPagamento(tableModelValores.getValues());
            pagamentoOK = true;
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao finalizar a venda.\n" + ex.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void incluiPagamento(NfceTipoPagamentoVO tipoPagamento, BigDecimal valor) throws Exception {
        NfeFormaPagamentoVO formaPagamento = new NfeFormaPagamentoVO();
        formaPagamento.setNfeCabecalho(SessaoUsuario.vendaAtual);
        formaPagamento.setNfceTipoPagamento(tipoPagamento);
        formaPagamento.setValor(valor);
        formaPagamento.setForma(tipoPagamento.getCodigo());
        formaPagamento.setEstorno("N");

        tableModelValores.adicionaRegistro(formaPagamento);

        totalRecebido = Biblioteca.soma(totalRecebido, valor);
        troco = Biblioteca.subtrai(totalRecebido, totalReceber);
        if (troco.compareTo(BigDecimal.ZERO) == -1) {
            troco = BigDecimal.ZERO;
        }
        verificaSaldoRestante();

        if (saldoRestante.compareTo(BigDecimal.ZERO) <= 0) {
            finalizaVenda();
        }
    }

    private void saiuCampoValor(String valorCampo) {
        NfceTipoPagamentoVO tipoPagamento = SessaoUsuario.getListaTipoPagamento().get(cboTiposPagamento.getSelectedIndex());
        BigDecimal valor = BigDecimal.valueOf(Double.parseDouble(valorCampo.replace(".", "").replace(",", ".")));
        if (valor.compareTo(BigDecimal.ZERO) == 1) {
            verificaSaldoRestante();
            if (saldoRestante.compareTo(BigDecimal.ZERO) <= 0) {
                JOptionPane.showMessageDialog(this, "Todos os valores já foram recebidos. Finalize a venda.", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (tipoPagamento.getGeraParcelas().equals("S")) {
                    Parcelamento parcelamento = new Parcelamento(null, true, SessaoUsuario.vendaAtual.getCliente(), totalVenda, totalRecebido, saldoRestante, desconto, acrescimo);
                    parcelamento.setLocationRelativeTo(null);
                    parcelamento.setVisible(true);
                    if (!parcelamento.cancelado) {
                        try {
                            SessaoUsuario.vendaAtual.setIndicadorFormaPagamento(1); // ['0', '1', '2'][ipVista, ipPrazo, ipOutras]
                            incluiPagamento(tipoPagamento, saldoRestante);
                        } catch (Exception e) {
                            //e.printStackTrace();
                            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    int escolha = JOptionPane.showOptionDialog(null, "Confirma inclusão de pagamento?", "Pergunta do Sistema",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                            null, opcoesResposta, null);
                    if (escolha == JOptionPane.YES_OPTION) {
                        try {
                            SessaoUsuario.vendaAtual.setIndicadorFormaPagamento(1); // ['0', '1', '2'][ipVista, ipPrazo, ipOutras]
                            incluiPagamento(tipoPagamento, valor);
                        } catch (Exception e) {
                            //e.printStackTrace();
                            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
                        }
                        editValorPago.setDocument(new MonetarioDocument());
                        editValorPago.setText(Biblioteca.formatoDecimal("V", saldoRestante.doubleValue()).replaceAll(",", ""));
                    }
                }
            }
            cboTiposPagamento.requestFocus();
        } else {
            editValorPago.requestFocus();
        }
    }
}
