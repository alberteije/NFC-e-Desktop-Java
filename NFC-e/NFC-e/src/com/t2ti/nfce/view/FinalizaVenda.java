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

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import com.t2ti.nfce.controller.NfceControllerGenerico;
import com.t2ti.nfce.controller.VendaController;
import com.t2ti.nfce.infra.GeraXmlEnvio;
import com.t2ti.nfce.infra.NfceConstantes;
import com.t2ti.pafecf.infra.Biblioteca;
import com.t2ti.pafecf.infra.MonetarioDocument;
import com.t2ti.nfce.infra.SessaoUsuario;
import com.t2ti.nfce.infra.ValidaXmlNfe;
import com.t2ti.pafecf.infra.TableModelGenerico;
import com.t2tierp.nfe.java.NfeFormaPagamentoVO;
import com.t2tierp.nfe.servidor.EnviaNfe;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.util.JRXmlUtils;
import org.apache.commons.codec.digest.DigestUtils;

public class FinalizaVenda extends javax.swing.JDialog {

    private BigDecimal totalVenda;
    private BigDecimal desconto;
    private BigDecimal acrescimo;
    private BigDecimal totalReceber;
    private BigDecimal troco;
    private BigDecimal totalRecebido;
    private TableModelGenerico<NfeFormaPagamentoVO> tableModelValores;
    private String caminhoArquivo;
    private String mensagem;

    public FinalizaVenda(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        totalVenda = new BigDecimal(0);
        desconto = new BigDecimal(0);
        acrescimo = new BigDecimal(0);
        totalReceber = new BigDecimal(0);
        troco = new BigDecimal(0);
        totalRecebido = new BigDecimal(0);

        caminhoArquivo = NfceConstantes.DIRETORIO_ARQUIVOS_XML + SessaoUsuario.vendaAtual.getChaveAcesso() + SessaoUsuario.vendaAtual.getDigitoChaveAcesso();

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        editValorPago.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        editValorPago.setDocument(new MonetarioDocument());

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

        FinalizaAction finalizaAction = new FinalizaAction();
        /*
        labelMensagens.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "finalizaAction");
        labelMensagens.getActionMap().put("finalizaAction", finalizaAction);

        labelMensagens.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "finalizaAction");
*/
        totalVenda = SessaoUsuario.vendaAtual.getValorTotalProdutos();
        desconto = SessaoUsuario.vendaAtual.getValorDesconto();
        acrescimo = SessaoUsuario.vendaAtual.getValorDespesasAcessorias();
        totalReceber = Biblioteca.soma(totalVenda, acrescimo);
        totalReceber = Biblioteca.subtrai(totalReceber, desconto);
        for (NfeFormaPagamentoVO p : SessaoUsuario.vendaAtual.getListaNfeFormaPagamento()) {
            totalRecebido = Biblioteca.soma(totalRecebido, p.getValor());
        }
        troco = SessaoUsuario.vendaAtual.getTroco();

        editValorPago.setText("0");

        setPreferredSize(new Dimension(760, 350));

        try {
            Image bi = ImageIO.read(new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "body.jpg"));
            jLabel8.setText("");
            jLabel8.setIcon(new ImageIcon(bi.getScaledInstance(jLabel8.size().width, jLabel8.size().height, Image.SCALE_SMOOTH)));          
        } catch (IOException e) {
            e.printStackTrace();
        } 

        this.pack();

        try {
            procedimentosEnvio();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        jProgressBar1.setIndeterminate(true);
    }

    private void atualizaLabelsValores() {
        //formata valores para exibição
        labelTotalVenda.setText(Biblioteca.formatoDecimal("V", totalVenda.doubleValue()));
        labelAcrescimo.setText(Biblioteca.formatoDecimal("V", acrescimo.doubleValue()));
        labelDesconto.setText(Biblioteca.formatoDecimal("V", desconto.doubleValue()));
        labelTotalReceber.setText(Biblioteca.formatoDecimal("V", totalReceber.doubleValue()));
        labelTotalRecebido.setText(Biblioteca.formatoDecimal("V", totalRecebido.doubleValue()));
        labelRestante.setText(Biblioteca.formatoDecimal("V", 0));
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

        tableModelValores.setValues(SessaoUsuario.vendaAtual.getListaNfeFormaPagamento());
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
        jProgressBar1 = new javax.swing.JProgressBar();
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
        panelValores.setLayout(new java.awt.GridLayout(1, 0));

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

        editValorPago.setEditable(false);
        editValorPago.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        panelBotoes.add(jProgressBar1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 20;
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboTiposPagamento;
    private javax.swing.JFormattedTextField editValorPago;
    private javax.swing.JTable gridValores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
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

    private class FinalizaAction extends AbstractAction {

        public FinalizaAction() {
        }

        public void actionPerformed(ActionEvent e) {
            finaliza();
        }
    }

    private void procedimentosEnvio() throws Exception {
        SwingWorker swingWorker = new SwingWorker() {

            @Override
            protected Object doInBackground() throws Exception {
                try {
                    atualizaLabelsValores();

                    telaPadrao();

                    mensagem = "Salvando forma de pagamento...";
                    /*
                    labelMensagens.setText("");
                    labelMensagens.setText("Salvando forma de pagamento...");
                    labelMensagens.updateUI();
                            */
                    NfceControllerGenerico<NfeFormaPagamentoVO> controllerPagamento = new NfceControllerGenerico<>();
                    for (NfeFormaPagamentoVO p : tableModelValores.getValues()) {
                        controllerPagamento.salvar(p);
                    }

                    mensagem = "Gerando o XML...";
                    /*
                    labelMensagens.setText("");
                    labelMensagens.setText("Gerando o XML...");
                    labelMensagens.updateUI();
                            */
                    String xmlEnvio = new GeraXmlEnvio().gerarXmlEnvio(SessaoUsuario.getConfiguracao().getEmpresa(),
                            SessaoUsuario.vendaAtual,
                            SessaoUsuario.certificado.aliases().nextElement(),
                            SessaoUsuario.certificado,
                            SessaoUsuario.senhaCertificado);

                    mensagem = "Validando o XML...";
                    /*
                    labelMensagens.setText("");
                    labelMensagens.setText("Validando o XML...");
                    labelMensagens.updateUI();
                            */
                    System.out.println(xmlEnvio);
                    try {
                        new ValidaXmlNfe().validaXmlEnvio(xmlEnvio);
                    } catch (Exception e) {
                        throw new Exception("Erro na validação do XML\n" + e.getMessage());
                    }

                    mensagem = "Enviando a NFC-e...";
                    /*
                    labelMensagens.setText("");
                    labelMensagens.setText("Enviando a NFC-e...");
                    labelMensagens.updateUI();
                            */
                    EnviaNfe envia = new EnviaNfe();
                    Map mapResposta = envia.enviaNfe(xmlEnvio,
                            SessaoUsuario.certificado.aliases().nextElement(),
                            SessaoUsuario.certificado,
                            SessaoUsuario.senhaCertificado,
                            SessaoUsuario.vendaAtual.getUfEmitente().toString(),
                            String.valueOf(SessaoUsuario.vendaAtual.getAmbiente()));

                    Boolean autorizado = (Boolean) mapResposta.get("autorizado");

                    if (autorizado) {
                        mensagem = "Salvando as informações...";
                        /*
                        labelMensagens.setText("");
                        labelMensagens.setText("Salvando as informações...");
                            */
                        String xmlProc = (String) mapResposta.get("xmlProc");
                        salvaArquivos(xmlProc);

                        mensagem = "Imprimindo o DANFE...";
                        /*
                        labelMensagens.setText("");
                        labelMensagens.setText("Imprimindo o DANFE...");
                                */
                        try {
                            imprimeDanfe(mapResposta.get("digVal").toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Erro ao imprimir o DANFE.\n" + e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    mensagem = "Finalizado.";
                    /*
                    labelMensagens.setText("");
                    labelMensagens.setText("Finalizado.");
                                */
                    JOptionPane.showMessageDialog(null, mapResposta.get("resposta"), "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao finalizar a venda.\n" + e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
                }
                return null;
            }

            @Override
            protected void done() {
                dispose();
            }
        };
        swingWorker.execute();
    }

    private void salvaArquivos(String xml) throws Exception {
        SessaoUsuario.vendaAtual.setStatusNota(NfceConstantes.SN_AUTORIZADA);

        new VendaController().atualizar(SessaoUsuario.vendaAtual);

        //salva o xml
        OutputStream outXml = new FileOutputStream(new File(caminhoArquivo + "-nfeproc.xml"));
        outXml.write(xml.getBytes());
        outXml.close();
    }

    private void imprimeDanfe(String digestValue) throws Exception {
        Map map = new HashMap();
        map.put("QR_CODE", geraQrCode(digestValue));
        //http://www.nfe.se.gov.br/portal/portalNoticias.jsp?jsp=barra-menu/documentos/tabelaEnderecoConsulta.htm
        map.put("ENDERECO_SEFAZ", "https://www.sefaz.rs.gov.br/NFCE/NFCE-COM.aspx");
        map.put("SUBREPORT_DIR", this.getClass().getResource("/relatorios/").toString());
        map.put("XML_DATA_DOCUMENT", JRXmlUtils.parse(caminhoArquivo + "-nfeproc.xml"));

        JRXmlDataSource jrXmlDataSource = new JRXmlDataSource(caminhoArquivo + "-nfeproc.xml", "/nfeProc/NFe/infNFe/det");
        JasperPrint jp = JasperFillManager.fillReport(this.getClass().getResourceAsStream("/relatorios/danfeNfce.jasper"), map, jrXmlDataSource);
        JasperPrintManager.printReport(jp, false);
    }

    private void finaliza() {
        dispose();
    }

    private BufferedImage geraQrCode(String digestValue) {
        try {
            DecimalFormatSymbols simboloDecimal = DecimalFormatSymbols.getInstance();
            simboloDecimal.setDecimalSeparator('.');
            DecimalFormat formatoValor = new DecimalFormat("0.00", simboloDecimal);

            String endereco = "https://www.sefaz.rs.gov.br/NFCE/NFCE-COM.aspx?";
            //parametros
            String chNFe = "chNFe=" + SessaoUsuario.vendaAtual.getChaveAcesso() + SessaoUsuario.vendaAtual.getDigitoChaveAcesso();
            String nVersao = "nVersao=100";
            String tpAmb = "tpAmb=" + SessaoUsuario.vendaAtual.getAmbiente();
            String cDest = SessaoUsuario.vendaAtual.getDestinatario() != null ? "cDest=" + SessaoUsuario.vendaAtual.getDestinatario().getCpfCnpj() : null;
            String dhEmi = "dhEmi=" + DigestUtils.sha1Hex(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(SessaoUsuario.vendaAtual.getDataHoraEmissao()));
            String vNF = "vNF=" + formatoValor.format(SessaoUsuario.vendaAtual.getValorTotalProdutos());
            String vICMS = "vICMS=" + formatoValor.format(SessaoUsuario.vendaAtual.getValorIcms());
            String digVal = "digVal=" + DigestUtils.sha1Hex(digestValue);
            String cIdToken = "cIdToken=" + SessaoUsuario.idToken;

            String parametros = chNFe
                    + "&" + nVersao
                    + "&" + tpAmb
                    + (cDest == null ? "" : "&" + cDest)
                    + "&" + dhEmi
                    + "&" + vNF
                    + "&" + vICMS
                    + "&" + digVal
                    + "&" + cIdToken + SessaoUsuario.valorToken;

            String cHashQRCode = "cHashQRCode=" + DigestUtils.sha1Hex(parametros);

            parametros = chNFe
                    + "&" + nVersao
                    + "&" + tpAmb
                    + (cDest == null ? "" : "&" + cDest)
                    + "&" + dhEmi
                    + "&" + vNF
                    + "&" + vICMS
                    + "&" + digVal
                    + "&" + cIdToken
                    + "&" + cHashQRCode;

            BufferedImage image = MatrixToImageWriter.toBufferedImage(new QRCodeWriter().encode(endereco + parametros, BarcodeFormat.QR_CODE, 200, 200));

            return image;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    private void iniciaThread() {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    while (true) {
                        
                        //labelMensagens.setText(mensagem);
                        //labelMensagens.updateUI();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        t.start();
    }    

}
