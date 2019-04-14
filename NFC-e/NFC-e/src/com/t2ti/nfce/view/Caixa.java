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
import com.t2ti.nfce.controller.ProdutoController;
import com.t2ti.nfce.controller.VendaController;
import com.t2ti.nfce.infra.NfceConstantes;
import com.t2ti.nfce.infra.SelecionaCertificado;
import com.t2ti.pafecf.infra.Biblioteca;
import com.t2ti.pafecf.infra.Constantes;
import com.t2ti.nfce.infra.SessaoUsuario;
import com.t2ti.pafecf.infra.Tipos;
import com.t2ti.pafecf.view.DescontoAcrescimo;
import com.t2ti.pafecf.view.ValorInteiro;
import com.t2ti.pafecf.vo.DavCabecalhoVO;
import com.t2ti.pafecf.vo.DavDetalheVO;
import com.t2tierp.cadastros.java.VendedorVO;
import com.t2tierp.nfce.java.NfceMovimentoVO;
import com.t2tierp.nfce.java.NfcePosicaoComponentesVO;
import com.t2tierp.nfce.java.NfceSangriaVO;
import com.t2tierp.nfce.java.NfceSuprimentoVO;
import com.t2tierp.nfe.java.NfeCabecalhoVO;
import com.t2tierp.nfe.java.NfeDetalheImpostoIcmsVO;
import com.t2tierp.nfe.java.NfeDetalheVO;
import com.t2tierp.nfe.java.NfeFormaPagamentoVO;
import com.t2tierp.nfe.java.NfeNumeroVO;
import java.awt.AWTKeyStroke;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

public class Caixa extends javax.swing.JFrame implements MouseListener, Tipos {

//****************************************************************************//
// Variáveis de instância                                                     //
//****************************************************************************//
    private int itemCupom = 0;
    private BigDecimal subTotal = BigDecimal.ZERO;
    private BigDecimal totalGeral = BigDecimal.ZERO;
    private BigDecimal valorIcms = BigDecimal.ZERO;
    private BigDecimal desconto = BigDecimal.ZERO;
    private BigDecimal acrescimo = BigDecimal.ZERO;
    private BigDecimal descontoAcrescimo = BigDecimal.ZERO;
    private DefaultListModel modelMenuPrincipal;
    private DefaultListModel modelMenuOperacoes;
    private DefaultListModel modelMenuFiscal;
    private DefaultListModel modelBobina;
    private DefaultListModel modelSubMenuGerente;
    private DefaultListModel modelSubMenuSupervisor;
    private VendaController vendaController;
    private NfeDetalheVO vendaDetalhe;
    private Thread bannerRotativo;
    private final String[] opcoesResposta = {"Sim", "Não"};
    private DavCabecalhoVO dav;

//****************************************************************************//
// Construtor                                                                 //
//****************************************************************************//
    public Caixa() {
        initComponents();
        pack();
        setLocationRelativeTo(null);
        panelMenuPrincipal.setVisible(false);
        panelMenuOperacoes.setVisible(false);
        panelMenuFiscal.setVisible(false);
        panelSubMenu.setVisible(false);
    }

    private void executaProcedimentosConfiguracao() {
        Constantes.DECIMAIS_QUANTIDADE = SessaoUsuario.getConfiguracao().getDecimaisQuantidade();
        Constantes.DECIMAIS_VALOR = SessaoUsuario.getConfiguracao().getDecimaisValor();

        vendaController = new VendaController();
        configuraLayout();
        verificaMovimento();

        telaPadrao();

        if (SessaoUsuario.movimento != null) {
            labelCaixa.setText("Terminal: " + SessaoUsuario.movimento.getNfceCaixa().getNome());
            labelOperador.setText("Operador: " + SessaoUsuario.movimento.getNfceOperador().getLogin());
        } else {
            labelCaixa.setText("");
            labelOperador.setText("");
            SessaoUsuario.statusCaixa = SC_SOMENTE_CONSULTA;
        }

        try {
            SelecionaCertificado.carregaDadosCertificado();
        } catch (Exception ex) {
            selecionaCertificado();
        }
    }

    private void selecionaCertificado() {
        try {
            SessaoUsuario.selecionaCertificado();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar os dados do certificado.\n" + e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void configuraLayout() {
        ImageIcon imagemTela = null;
        try {
            File file = new File(SessaoUsuario.getConfiguracao().getCaminhoImagensLayout() + SessaoUsuario.getConfiguracao().getNfceResolucao().getImagemTela());
            if (file.exists()) {
                imagemTela = new ImageIcon(SessaoUsuario.getConfiguracao().getCaminhoImagensLayout() + SessaoUsuario.getConfiguracao().getNfceResolucao().getImagemTela());
                labelImagemTela.setIcon(imagemTela);
            }
        } catch (Exception e) {

        }

        labelCliente.setText("");
        labelTitulo.setText(SessaoUsuario.getConfiguracao().getTituloTelaCaixa());
        modelBobina = new DefaultListModel();
        bobina.setModel(modelBobina);

        setResolucao(this);

        this.setPreferredSize(new Dimension(SessaoUsuario.getConfiguracao().getNfceResolucao().getLargura(), SessaoUsuario.getConfiguracao().getNfceResolucao().getAltura()));
        containerPrincipal.setPreferredSize(new Dimension(SessaoUsuario.getConfiguracao().getNfceResolucao().getLargura(), SessaoUsuario.getConfiguracao().getNfceResolucao().getAltura()));
        labelImagemTela.setBounds(0, 0, SessaoUsuario.getConfiguracao().getNfceResolucao().getLargura(), SessaoUsuario.getConfiguracao().getNfceResolucao().getAltura());

        panelF1.addMouseListener(this);
        panelF2.addMouseListener(this);
        panelF3.addMouseListener(this);
        panelF4.addMouseListener(this);
        panelF5.addMouseListener(this);
        panelF6.addMouseListener(this);
        panelF7.addMouseListener(this);
        panelF8.addMouseListener(this);
        panelF9.addMouseListener(this);
        panelF10.addMouseListener(this);
        panelF11.addMouseListener(this);
        panelF12.addMouseListener(this);

        F1Action f1Action = new F1Action();
        panelF1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "F1Action");
        panelF1.getActionMap().put("F1Action", f1Action);

        F2Action f2Action = new F2Action();
        panelF2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "F2Action");
        panelF2.getActionMap().put("F2Action", f2Action);

        F3Action f3Action = new F3Action();
        panelF3.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), "F3Action");
        panelF3.getActionMap().put("F3Action", f3Action);

        F4Action f4Action = new F4Action();
        panelF4.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), "F4Action");
        panelF4.getActionMap().put("F4Action", f4Action);

        F5Action f5Action = new F5Action();
        panelF5.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), "F5Action");
        panelF5.getActionMap().put("F5Action", f5Action);

        F6Action f6Action = new F6Action();
        panelF6.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0), "F6Action");
        panelF6.getActionMap().put("F6Action", f6Action);

        F7Action f7Action = new F7Action();
        panelF7.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0), "F7Action");
        panelF7.getActionMap().put("F7Action", f7Action);

        F8Action f8Action = new F8Action();
        panelF8.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0), "F8Action");
        panelF8.getActionMap().put("F8Action", f8Action);

        F9Action f9Action = new F9Action();
        panelF9.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0), "F9Action");
        panelF9.getActionMap().put("F9Action", f9Action);

        F10Action f10Action = new F10Action();
        panelF10.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), "F10Action");
        panelF10.getActionMap().put("F10Action", f10Action);

        F11Action f11Action = new F11Action();
        panelF11.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0), "F11Action");
        panelF11.getActionMap().put("F11Action", f11Action);

        F12Action f12Action = new F12Action();
        panelF12.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), "F12Action");
        panelF12.getActionMap().put("F12Action", f12Action);

        ESCAction escAction = new ESCAction();
        containerPrincipal.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESCAction");
        containerPrincipal.getActionMap().put("ESCAction", escAction);

        EnterAction enterAction = new EnterAction();
        containerPrincipal.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "EnterAction");
        containerPrincipal.getActionMap().put("EnterAction", enterAction);

        SetaAcimaAction setaAcimaAction = new SetaAcimaAction();
        containerPrincipal.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "SetaAcimaAction");
        containerPrincipal.getActionMap().put("SetaAcimaAction", setaAcimaAction);

        SetaAbaixoAction setaAbaixoAction = new SetaAbaixoAction();
        containerPrincipal.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "SetaAbaixoAction");
        containerPrincipal.getActionMap().put("SetaAbaixoAction", setaAbaixoAction);

        //troca TAB por ENTER para os edits de código e quantidade
        HashSet conj = new HashSet(this.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        editCodigo.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
        editQuantidade.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        definirMenuPrincipal();
        definirMenuOperacoes();
        definirSubMenuGerente();
        definirSubMenuSupervisor();

        this.pack();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        containerPrincipal = new javax.swing.JLayeredPane();
        labelTitulo = new javax.swing.JLabel();
        labelOperador = new javax.swing.JLabel();
        labelCaixa = new javax.swing.JLabel();
        panelBotoes = new javax.swing.JPanel();
        panelF1 = new javax.swing.JPanel();
        labelF1 = new javax.swing.JLabel();
        panelF2 = new javax.swing.JPanel();
        labelF2 = new javax.swing.JLabel();
        panelF3 = new javax.swing.JPanel();
        labelF3 = new javax.swing.JLabel();
        panelF4 = new javax.swing.JPanel();
        labelF4 = new javax.swing.JLabel();
        panelF5 = new javax.swing.JPanel();
        labelF5 = new javax.swing.JLabel();
        panelF6 = new javax.swing.JPanel();
        labelF6 = new javax.swing.JLabel();
        panelF7 = new javax.swing.JPanel();
        labelF7 = new javax.swing.JLabel();
        panelF8 = new javax.swing.JPanel();
        labelF8 = new javax.swing.JLabel();
        panelF9 = new javax.swing.JPanel();
        labelF9 = new javax.swing.JLabel();
        panelF10 = new javax.swing.JPanel();
        labelF10 = new javax.swing.JLabel();
        panelF11 = new javax.swing.JPanel();
        labelF11 = new javax.swing.JLabel();
        panelF12 = new javax.swing.JPanel();
        labelF12 = new javax.swing.JLabel();
        panelSubMenu = new javax.swing.JPanel();
        panelCard = new javax.swing.JPanel();
        panelSubMenuGerente = new javax.swing.JScrollPane();
        listaSubMenuGerente = new javax.swing.JList();
        panelSubMenuSupervisor = new javax.swing.JScrollPane();
        listaSubMenuSupervisor = new javax.swing.JList();
        jLabel10 = new javax.swing.JLabel();
        panelMenuPrincipal = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaMenuPrincipal = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        panelMenuOperacoes = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        listaMenuOperacoes = new javax.swing.JList();
        jLabel5 = new javax.swing.JLabel();
        labelCliente = new javax.swing.JLabel();
        panelMenuFiscal = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        listaMenuFiscal = new javax.swing.JList();
        jLabel7 = new javax.swing.JLabel();
        panelBobina = new javax.swing.JScrollPane();
        bobina = new javax.swing.JList();
        editUnitario = new javax.swing.JFormattedTextField();
        editCodigo = new javax.swing.JFormattedTextField();
        editQuantidade = new javax.swing.JFormattedTextField();
        editSubTotal = new javax.swing.JFormattedTextField();
        editTotalItem = new javax.swing.JFormattedTextField();
        labelImagemProduto = new javax.swing.JLabel();
        labelDescricaoProduto = new javax.swing.JLabel();
        labelTotalGeral = new javax.swing.JLabel();
        labelMensagens = new javax.swing.JLabel();
        labelDescontoAcrescimo = new javax.swing.JLabel();
        labelImagemTela = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        containerPrincipal.setPreferredSize(new java.awt.Dimension(1024, 738));

        labelTitulo.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        labelTitulo.setForeground(new java.awt.Color(255, 255, 255));
        labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitulo.setText("T2TiPDV - www.t2ti.com - (99) 9999.9999");
        labelTitulo.setFocusable(false);
        labelTitulo.setName("labelTitulo"); // NOI18N
        labelTitulo.setRequestFocusEnabled(false);
        containerPrincipal.add(labelTitulo);
        labelTitulo.setBounds(300, 10, 710, 20);

        labelOperador.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelOperador.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelOperador.setText("Operador:");
        containerPrincipal.add(labelOperador);
        labelOperador.setBounds(754, 60, 230, 14);

        labelCaixa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelCaixa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelCaixa.setText("Caixa:");
        containerPrincipal.add(labelCaixa);
        labelCaixa.setBounds(754, 80, 230, 14);

        panelBotoes.setBackground(new Color(255,255,255,0));
        panelBotoes.setMinimumSize(new java.awt.Dimension(950, 56));
        panelBotoes.setName("panelBotoes"); // NOI18N
        panelBotoes.setPreferredSize(new java.awt.Dimension(950, 56));
        panelBotoes.setLayout(new java.awt.GridBagLayout());

        panelF1.setBackground(new Color(255,255,255,0));
        panelF1.setToolTipText("");
        panelF1.setName("panelF1"); // NOI18N
        panelF1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        labelF1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgBotoes/21botaoCliente.png"))); // NOI18N
        labelF1.setText("F1 - Identifica Cliente");
        labelF1.setName("labelF1"); // NOI18N
        panelF1.add(labelF1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        panelBotoes.add(panelF1, gridBagConstraints);

        panelF2.setBackground(new Color(255,255,255,0));
        panelF2.setName("panelF2"); // NOI18N
        panelF2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        labelF2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgBotoes/21botaoMenuPrincipal.png"))); // NOI18N
        labelF2.setText("F2 - Menu Principal");
        labelF2.setName("labelF2"); // NOI18N
        panelF2.add(labelF2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        panelBotoes.add(panelF2, gridBagConstraints);

        panelF3.setBackground(new Color(255,255,255,0));
        panelF3.setName("panelF3"); // NOI18N
        panelF3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        labelF3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgBotoes/21botaoMenuOperacoes.png"))); // NOI18N
        labelF3.setText("F3 - Menu Operações");
        labelF3.setName("labelF3"); // NOI18N
        panelF3.add(labelF3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        panelBotoes.add(panelF3, gridBagConstraints);

        panelF4.setBackground(new Color(255,255,255,0));
        panelF4.setName("panelF4"); // NOI18N
        panelF4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        labelF4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgBotoes/21botaoMenuFiscal.png"))); // NOI18N
        labelF4.setText("F4 - Menu Fiscal");
        labelF4.setName("labelF4"); // NOI18N
        panelF4.add(labelF4);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        panelBotoes.add(panelF4, gridBagConstraints);

        panelF5.setBackground(new Color(255,255,255,0));
        panelF5.setName("panelF5"); // NOI18N
        panelF5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        labelF5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgBotoes/21botaoCalculadora.png"))); // NOI18N
        labelF5.setText("F5 - Dados NF");
        labelF5.setName("labelF5"); // NOI18N
        panelF5.add(labelF5);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        panelBotoes.add(panelF5, gridBagConstraints);

        panelF6.setBackground(new Color(255,255,255,0));
        panelF6.setName("panelF6"); // NOI18N
        panelF6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        labelF6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgBotoes/21botaoPesquisa.png"))); // NOI18N
        labelF6.setText("F6 - Pesquisa Produto");
        labelF6.setName("labelF6"); // NOI18N
        panelF6.add(labelF6);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        panelBotoes.add(panelF6, gridBagConstraints);

        panelF7.setBackground(new Color(255,255,255,0));
        panelF7.setName("panelF7"); // NOI18N
        panelF7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        labelF7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgBotoes/21botaoEncerraVenda.png"))); // NOI18N
        labelF7.setText("F7 - Encerra Venda");
        labelF7.setName("labelF7"); // NOI18N
        panelF7.add(labelF7);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        panelBotoes.add(panelF7, gridBagConstraints);

        panelF8.setBackground(new Color(255,255,255,0));
        panelF8.setName("panelF8"); // NOI18N
        panelF8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        labelF8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgBotoes/21botaoCancelaItem.png"))); // NOI18N
        labelF8.setText("F8 - Cancela Item");
        labelF8.setName("labelF8"); // NOI18N
        panelF8.add(labelF8);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        panelBotoes.add(panelF8, gridBagConstraints);

        panelF9.setBackground(new Color(255,255,255,0));
        panelF9.setName("panelF9"); // NOI18N
        panelF9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        labelF9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgBotoes/21botaoCancelaCupom.png"))); // NOI18N
        labelF9.setText("F9 - Cancela Cupom");
        labelF9.setName("labelF9"); // NOI18N
        panelF9.add(labelF9);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        panelBotoes.add(panelF9, gridBagConstraints);

        panelF10.setBackground(new Color(255,255,255,0));
        panelF10.setName("panelF10"); // NOI18N
        panelF10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        labelF10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgBotoes/21botaoDesconto.png"))); // NOI18N
        labelF10.setText("F10 - Desconto/Acréscimo");
        labelF10.setName("labelF10"); // NOI18N
        panelF10.add(labelF10);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        panelBotoes.add(panelF10, gridBagConstraints);

        panelF11.setBackground(new Color(255,255,255,0));
        panelF11.setName("panelF11"); // NOI18N
        panelF11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        labelF11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgBotoes/21botaoGaveta.png"))); // NOI18N
        labelF11.setText("F11 - Identifica Vendedor");
        labelF11.setName("labelF11"); // NOI18N
        panelF11.add(labelF11);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        panelBotoes.add(panelF11, gridBagConstraints);

        panelF12.setBackground(new Color(255,255,255,0));
        panelF12.setName("panelF12"); // NOI18N
        panelF12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        labelF12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgBotoes/21botaoSair.png"))); // NOI18N
        labelF12.setText("F12 - Sai do Caixa");
        labelF12.setName("labelF12"); // NOI18N
        panelF12.add(labelF12);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        panelBotoes.add(panelF12, gridBagConstraints);

        containerPrincipal.add(panelBotoes);
        panelBotoes.setBounds(38, 705, 950, 56);

        panelSubMenu.setBackground(new Color(255,255,255,0));
        panelSubMenu.setName("panelSubMenu"); // NOI18N
        panelSubMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelCard.setLayout(new java.awt.CardLayout());

        panelSubMenuGerente.setBorder(null);
        panelSubMenuGerente.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        listaSubMenuGerente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        listaSubMenuGerente.setForeground(new java.awt.Color(153, 0, 0));
        listaSubMenuGerente.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        panelSubMenuGerente.setViewportView(listaSubMenuGerente);

        panelCard.add(panelSubMenuGerente, "cardGerente");

        panelSubMenuSupervisor.setBorder(null);
        panelSubMenuSupervisor.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        listaSubMenuSupervisor.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        listaSubMenuSupervisor.setForeground(new java.awt.Color(153, 0, 0));
        listaSubMenuSupervisor.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        panelSubMenuSupervisor.setViewportView(listaSubMenuSupervisor);

        panelCard.add(panelSubMenuSupervisor, "cardSupervisor");

        panelSubMenu.add(panelCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 22, 450, 180));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/layout/SubMenu.png"))); // NOI18N
        panelSubMenu.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        containerPrincipal.add(panelSubMenu);
        panelSubMenu.setBounds(10, 280, 467, 212);

        panelMenuPrincipal.setBackground(new Color(255,255,255,0));
        panelMenuPrincipal.setName("panelMenuPrincipal"); // NOI18N
        panelMenuPrincipal.setPreferredSize(new java.awt.Dimension(213, 200));
        panelMenuPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Menu Principal");
        panelMenuPrincipal.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 8, 190, -1));

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        listaMenuPrincipal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        listaMenuPrincipal.setForeground(new java.awt.Color(102, 102, 102));
        listaMenuPrincipal.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        listaMenuPrincipal.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaMenuPrincipal.setAutoscrolls(false);
        jScrollPane2.setViewportView(listaMenuPrincipal);

        panelMenuPrincipal.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 35, 200, 160));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/layout/Menu.png"))); // NOI18N
        panelMenuPrincipal.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        containerPrincipal.add(panelMenuPrincipal);
        panelMenuPrincipal.setBounds(685, 40, 213, 200);

        panelMenuOperacoes.setBackground(new Color(255,255,255,0));
        panelMenuOperacoes.setName("panelMenuOperacoes"); // NOI18N
        panelMenuOperacoes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Menu Operações");
        panelMenuOperacoes.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 8, 190, -1));

        jScrollPane4.setBorder(null);
        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        listaMenuOperacoes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        listaMenuOperacoes.setForeground(new java.awt.Color(102, 102, 102));
        listaMenuOperacoes.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        listaMenuOperacoes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaMenuOperacoes.setAutoscrolls(false);
        jScrollPane4.setViewportView(listaMenuOperacoes);

        panelMenuOperacoes.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 35, 200, 160));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/layout/Menu.png"))); // NOI18N
        panelMenuOperacoes.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        containerPrincipal.add(panelMenuOperacoes);
        panelMenuOperacoes.setBounds(715, 40, 213, 200);

        labelCliente.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        labelCliente.setForeground(new java.awt.Color(51, 153, 255));
        labelCliente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelCliente.setText("nomeCliente: 111.111.111-11");
        labelCliente.setFocusable(false);
        labelCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelCliente.setName("labelCliente"); // NOI18N
        labelCliente.setPreferredSize(new java.awt.Dimension(772, 20));
        containerPrincipal.add(labelCliente);
        labelCliente.setBounds(45, 580, 400, 20);
        labelCliente.getAccessibleContext().setAccessibleName("");

        panelMenuFiscal.setBackground(new Color(255,255,255,0));
        panelMenuFiscal.setName("panelMenuFiscal"); // NOI18N
        panelMenuFiscal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Menu Fiscal");
        panelMenuFiscal.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 8, 190, -1));

        jScrollPane5.setBorder(null);
        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        listaMenuFiscal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        listaMenuFiscal.setForeground(new java.awt.Color(102, 102, 102));
        listaMenuFiscal.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        listaMenuFiscal.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaMenuFiscal.setAutoscrolls(false);
        listaMenuFiscal.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane5.setViewportView(listaMenuFiscal);

        panelMenuFiscal.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 35, 200, 160));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/layout/Menu.png"))); // NOI18N
        panelMenuFiscal.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        containerPrincipal.add(panelMenuFiscal);
        panelMenuFiscal.setBounds(745, 40, 213, 200);

        panelBobina.setBorder(null);
        panelBobina.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelBobina.setName("panelBobina"); // NOI18N

        bobina.setBackground(new java.awt.Color(255, 253, 228));
        bobina.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        bobina.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        bobina.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        bobina.setFocusable(false);
        bobina.setName("Bobina"); // NOI18N
        panelBobina.setViewportView(bobina);

        containerPrincipal.add(panelBobina);
        panelBobina.setBounds(40, 240, 405, 360);

        editUnitario.setEditable(false);
        editUnitario.setBorder(null);
        editUnitario.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        editUnitario.setText("0,00");
        editUnitario.setFocusable(false);
        editUnitario.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        editUnitario.setName("editUnitario"); // NOI18N
        containerPrincipal.add(editUnitario);
        editUnitario.setBounds(490, 462, 200, 30);

        editCodigo.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        editCodigo.setBorder(null);
        editCodigo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        editCodigo.setText("0");
        editCodigo.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        editCodigo.setName("editCodigo"); // NOI18N
        editCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                editCodigoFocusLost(evt);
            }
        });
        containerPrincipal.add(editCodigo);
        editCodigo.setBounds(490, 262, 200, 30);

        editQuantidade.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        editQuantidade.setBorder(null);
        editQuantidade.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        editQuantidade.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        editQuantidade.setName("editQuantidade"); // NOI18N
        editQuantidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                editQuantidadeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                editQuantidadeFocusLost(evt);
            }
        });
        containerPrincipal.add(editQuantidade);
        editQuantidade.setBounds(490, 362, 200, 30);

        editSubTotal.setEditable(false);
        editSubTotal.setBorder(null);
        editSubTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        editSubTotal.setText("0,00");
        editSubTotal.setFocusable(false);
        editSubTotal.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        editSubTotal.setName("editSubTotal"); // NOI18N
        containerPrincipal.add(editSubTotal);
        editSubTotal.setBounds(730, 562, 250, 30);

        editTotalItem.setEditable(false);
        editTotalItem.setBorder(null);
        editTotalItem.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        editTotalItem.setText("0,00");
        editTotalItem.setFocusable(false);
        editTotalItem.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        editTotalItem.setName("editTotalItem"); // NOI18N
        containerPrincipal.add(editTotalItem);
        editTotalItem.setBounds(490, 562, 200, 30);

        labelImagemProduto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelImagemProduto.setFocusable(false);
        labelImagemProduto.setName("imageProduto"); // NOI18N
        containerPrincipal.add(labelImagemProduto);
        labelImagemProduto.setBounds(730, 250, 250, 250);

        labelDescricaoProduto.setFont(new java.awt.Font("Verdana", 1, 48)); // NOI18N
        labelDescricaoProduto.setForeground(new java.awt.Color(255, 255, 255));
        labelDescricaoProduto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDescricaoProduto.setText("Produto para venda");
        labelDescricaoProduto.setName("labelDescricaoProduto"); // NOI18N
        containerPrincipal.add(labelDescricaoProduto);
        labelDescricaoProduto.setBounds(40, 110, 945, 83);

        labelTotalGeral.setFont(new java.awt.Font("Verdana", 1, 27)); // NOI18N
        labelTotalGeral.setForeground(new java.awt.Color(255, 255, 255));
        labelTotalGeral.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTotalGeral.setText("2.785.565,44");
        labelTotalGeral.setFocusable(false);
        labelTotalGeral.setName("labelTotalGeral"); // NOI18N
        containerPrincipal.add(labelTotalGeral);
        labelTotalGeral.setBounds(40, 652, 400, 40);

        labelMensagens.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        labelMensagens.setForeground(new java.awt.Color(255, 255, 0));
        labelMensagens.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMensagens.setText("<html>Mensagem grande mensagem grande mensagem grande mensagem grande</html>");
        labelMensagens.setFocusable(false);
        labelMensagens.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelMensagens.setName("labelMensagens"); // NOI18N
        labelMensagens.setPreferredSize(new java.awt.Dimension(772, 20));
        containerPrincipal.add(labelMensagens);
        labelMensagens.setBounds(485, 650, 500, 45);

        labelDescontoAcrescimo.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        labelDescontoAcrescimo.setForeground(new java.awt.Color(51, 153, 255));
        labelDescontoAcrescimo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelDescontoAcrescimo.setText("Acréscimo R$ 0,00");
        labelDescontoAcrescimo.setFocusable(false);
        labelDescontoAcrescimo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelDescontoAcrescimo.setName("labelDescontoAcrescimo"); // NOI18N
        labelDescontoAcrescimo.setPreferredSize(new java.awt.Dimension(772, 20));
        containerPrincipal.add(labelDescontoAcrescimo);
        labelDescontoAcrescimo.setBounds(200, 620, 250, 20);

        labelImagemTela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/layout/Tela1024x768.jpg"))); // NOI18N
        labelImagemTela.setFocusable(false);
        labelImagemTela.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        labelImagemTela.setName("labelImagemTela"); // NOI18N
        labelImagemTela.setRequestFocusEnabled(false);
        containerPrincipal.add(labelImagemTela);
        labelImagemTela.setBounds(0, 0, 1024, 768);

        getContentPane().add(containerPrincipal);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_editCodigoFocusLost
        if (!evt.isTemporary()) {
            if (!editCodigo.getText().trim().isEmpty()) {
                iniciaVendaDeItens();
            }
        }
    }//GEN-LAST:event_editCodigoFocusLost

    private void editQuantidadeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_editQuantidadeFocusLost
        if (!evt.isTemporary()) {
            try {
                String strQuantidade = editQuantidade.getText();
                strQuantidade = strQuantidade.replace(",", ".");
                double qtde = Double.valueOf(strQuantidade);
                editQuantidade.setText(String.valueOf(qtde));
                if ((qtde <= 0 || qtde > 999)) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Quantidade inválida.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
                editQuantidade.setText("1");
            }
        }
    }//GEN-LAST:event_editQuantidadeFocusLost

    private void editQuantidadeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_editQuantidadeFocusGained
        if (!evt.isTemporary()) {
            editQuantidade.selectAll();
        }
    }//GEN-LAST:event_editQuantidadeFocusGained

// ***************************************************************************//
// Metodos principais e de infra                                              //
// ***************************************************************************//
    public static void main(String args[]) {
        //seta o look and feel para o do SO
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Caixa c = new Caixa();
                c.setVisible(true);
                c.executaProcedimentosConfiguracao();
            }
        });

        /* Exercicio: ativar o marketing
         Banner b = new Banner();
         new Thread(b).start();
         */
        /*try {
         Properties arquivoConexao = new Properties();
         arquivoConexao.load(new FileInputStream(new File(System.getProperty("user.dir")
         + System.getProperty("file.separator")
         + "conexao.properties")));
         pathCargaRemoto = arquivoConexao.getProperty("importa.RemoteApp");
         } catch (Exception e) {
         }*/
    }

    private void sair() {
        if (SessaoUsuario.statusCaixa != SC_VENDA_EM_ANDAMENTO) {
            int escolha = JOptionPane.showOptionDialog(null, "Deseja sair do sistema?", "Pergunta do Sistema",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, opcoesResposta, null);
            if (escolha == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Existe uma venda em andamento.", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void verificaMovimento() {
        if (SessaoUsuario.movimento == null) {
            try {
                NfceControllerGenerico<NfceMovimentoVO> controller = new NfceControllerGenerico<>();
                SessaoUsuario.movimento = controller.getBean(NfceMovimentoVO.class, "statusMovimento", "A");
                if (SessaoUsuario.movimento == null) {
                    SessaoUsuario.movimento = controller.getBean(NfceMovimentoVO.class, "statusMovimento", "T");
                }
                if (SessaoUsuario.statusCaixa != SC_SOMENTE_CONSULTA) {
                    if (SessaoUsuario.movimento != null) {
                        MovimentoAberto ma = new MovimentoAberto(this, true);
                        ma.setLocationRelativeTo(null);
                        ma.setVisible(true);
                    } else {
                        IniciaMovimento im = new IniciaMovimento(this, true);
                        im.setLocationRelativeTo(null);
                        im.setVisible(true);
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro ao abrir o movimento!", "Informação do Sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void setResolucao(Container container) {
        List<NfcePosicaoComponentesVO> listaPosicoes = SessaoUsuario.getConfiguracao().getNfceResolucao().getListaNfcePosicaoComponentesVO();
        String nomeComponente;
        String nomeFonte;
        int estiloFonte;

        for (Component componente : container.getComponents()) {
            nomeComponente = componente.getName();
            if (nomeComponente != null) {
                for (NfcePosicaoComponentesVO posicaoComponente : listaPosicoes) {
                    if (posicaoComponente.getNome().equals(nomeComponente)) {
                        ((JComponent) componente).setBounds(posicaoComponente.getEsquerda(), posicaoComponente.getTopo(), posicaoComponente.getLargura(), posicaoComponente.getAltura());
                        if (posicaoComponente.getTamanhoFonte() > 0) {
                            nomeFonte = ((JComponent) componente).getFont().getName();
                            estiloFonte = ((JComponent) componente).getFont().getStyle();
                            ((JComponent) componente).setFont(new Font(nomeFonte, estiloFonte, posicaoComponente.getTamanhoFonte()));
                        }
                        if (componente instanceof JLabel) {
                            ((JLabel) componente).setText(posicaoComponente.getTexto());
                        }
                        break;
                    }
                }
            }
            setResolucao((Container) componente);
        }
    }

    private void recuperarVenda() {
        try {
            ListaNfce listaNfce = new ListaNfce(this, true, NfceConstantes.OP_RECUPERA_VENDA);
            listaNfce.setLocationRelativeTo(null);
            listaNfce.setVisible(true);
            if (!listaNfce.cancelado && listaNfce.getIdNfeCabecalho() != null) {
                SessaoUsuario.vendaAtual = vendaController.getBean(listaNfce.getIdNfeCabecalho(), NfeCabecalhoVO.class);
                if (SessaoUsuario.vendaAtual != null) {
                    imprimeCabecalhoBobina();
                    parametrosIniciaisVenda();
                    SessaoUsuario.statusCaixa = SC_VENDA_RECUPERADA_DAV_PREVENDA;
                    labelMensagens.setText("Venda recuperada em andamento...");

                    for (NfeDetalheVO v : SessaoUsuario.vendaAtual.getListaNfeDetalhe()) {
                        vendaDetalhe = v;
                        compoeItemParaVenda();
                        imprimeItemBobina();
                        subTotal = Biblioteca.soma(subTotal, vendaDetalhe.getValorTotal());
                        totalGeral = Biblioteca.soma(totalGeral, vendaDetalhe.getValorTotal());
                        atualizaTotais();
                    }
                    editCodigo.requestFocus();
                    SessaoUsuario.statusCaixa = SC_VENDA_EM_ANDAMENTO;
                }
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao recuperar a venda.\n" + ex.getMessage(), "Erro do sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void telaPadrao() {
        SessaoUsuario.vendaAtual = null;

        if (SessaoUsuario.movimento == null) {
            labelMensagens.setText("CAIXA FECHADO");
        } else {
            if (SessaoUsuario.movimento.getStatusMovimento().equals("T")) {
                labelMensagens.setText("SAIDA TEMPORÁRIA");
            } else {
                labelMensagens.setText("CAIXA ABERTO");
            }
        }

        editQuantidade.setText("1");
        editCodigo.setText("");
        editUnitario.setText("0.00");
        editTotalItem.setText("0.00");
        editSubTotal.setText("0.00");
        labelTotalGeral.setText("0.00");
        labelDescricaoProduto.setText("");
        labelDescontoAcrescimo.setText("");
        labelCliente.setText("");
        modelBobina.clear();

        if (SessaoUsuario.getConfiguracao().getMarketingAtivo().equals("S")) {
            Banner b = new Banner();
            bannerRotativo = new Thread(b);
            bannerRotativo.start();
        } else {
            setarImagem("padrao.png");
        }

        panelMenuPrincipal.setVisible(false);
        panelMenuOperacoes.setVisible(false);
        panelMenuFiscal.setVisible(false);
        panelSubMenu.setVisible(false);
        SessaoUsuario.menuAberto = NAO;
    }

    private void setarImagem(String nome) {
        try {
            File imagem = new File(SessaoUsuario.getConfiguracao().getCaminhoImagensProdutos() + nome);
            if (imagem.exists()) {
                labelImagemProduto.setIcon(new ImageIcon(imagem.getAbsolutePath()));
            } else {
                labelImagemProduto.setIcon(new ImageIcon(this.getClass().getResource("/images/produto/padrao.png")));
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

// ***************************************************************************//
// Métodos para definição e acionamento dos menus e submenus                  //
// ***************************************************************************//
    private void definirMenuPrincipal() {
        modelMenuPrincipal = new DefaultListModel();
        listaMenuPrincipal.setModel(modelMenuPrincipal);
        modelMenuPrincipal.addElement("Supervisor");
        modelMenuPrincipal.addElement("Gerente");
        modelMenuPrincipal.addElement("Saída Temporária");
    }

    private void definirMenuOperacoes() {
        modelMenuOperacoes = new DefaultListModel();
        listaMenuOperacoes.setModel(modelMenuOperacoes);
        modelMenuOperacoes.addElement("Carrega Orçamento");
        modelMenuOperacoes.addElement("Seleciona Certificado Digital");
        modelMenuOperacoes.addElement("Consulta Status Serviço");
    }

    private void definirSubMenuGerente() {
        modelSubMenuGerente = new DefaultListModel();
        listaSubMenuGerente.setModel(modelSubMenuGerente);
        modelSubMenuGerente.addElement("Iniciar Movimento");
        modelSubMenuGerente.addElement("Encerrar Movimento");
        modelSubMenuGerente.addElement(" ");
        modelSubMenuGerente.addElement("Suprimento");
        modelSubMenuGerente.addElement("Sangria");
    }

    private void definirSubMenuSupervisor() {
        modelSubMenuSupervisor = new DefaultListModel();
        listaSubMenuSupervisor.setModel(modelSubMenuSupervisor);
        modelSubMenuSupervisor.addElement("Iniciar Movimento");
        modelSubMenuSupervisor.addElement("Encerrar Movimento");
        modelSubMenuSupervisor.addElement(" ");
        modelSubMenuSupervisor.addElement("Suprimento");
        modelSubMenuSupervisor.addElement("Sangria");
    }

    private void acionaMenuPrincipal() {
        if (SessaoUsuario.statusCaixa != SC_VENDA_EM_ANDAMENTO) {
            if (SessaoUsuario.menuAberto == NAO) {
                panelMenuPrincipal.setVisible(true);
                listaMenuPrincipal.requestFocus();
                listaMenuPrincipal.setSelectedIndex(0);
                SessaoUsuario.menuAberto = SIM;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Existe uma venda em andamento!", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void acionaMenuOperacoes() {
        if (SessaoUsuario.statusCaixa != SC_VENDA_EM_ANDAMENTO) {
            if (SessaoUsuario.menuAberto == NAO) {
                panelMenuOperacoes.setVisible(true);
                listaMenuOperacoes.requestFocus();
                listaMenuOperacoes.setSelectedIndex(0);
                SessaoUsuario.menuAberto = SIM;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Existe uma venda em andamento!", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void fechaMenuOperacoes() {
        panelMenuOperacoes.setVisible(false);
        labelMensagens.setText("");
        SessaoUsuario.menuAberto = NAO;
    }

    public void fechaMenus() {
        panelSubMenu.setVisible(false);
        panelMenuPrincipal.setVisible(false);
        panelMenuOperacoes.setVisible(false);
        labelMensagens.setText("");
        SessaoUsuario.menuAberto = NAO;
    }

// ***************************************************************************//
// Actions vinculadas ao pressionamento de teclas                             //
// ***************************************************************************//
    private class F1Action extends AbstractAction {

        public F1Action() {
        }

        public void actionPerformed(ActionEvent e) {
            identificaCliente();
        }
    }

    private class F2Action extends AbstractAction {

        public F2Action() {
        }

        public void actionPerformed(ActionEvent e) {
            acionaMenuPrincipal();
        }
    }

    private class F3Action extends AbstractAction {

        public F3Action() {
        }

        public void actionPerformed(ActionEvent e) {
            acionaMenuOperacoes();
        }
    }

    private class F4Action extends AbstractAction {

        public F4Action() {
        }

        public void actionPerformed(ActionEvent e) {
            cancelaInutilizaNota();
        }
    }

    private class F5Action extends AbstractAction {

        public F5Action() {
        }

        public void actionPerformed(ActionEvent e) {
            recuperarVenda();
        }
    }

    private class F6Action extends AbstractAction {

        public F6Action() {
        }

        public void actionPerformed(ActionEvent e) {
            localizaProduto();
        }
    }

    private class F7Action extends AbstractAction {

        public F7Action() {
        }

        public void actionPerformed(ActionEvent e) {
            iniciaEncerramentoVenda();
        }
    }

    private class F8Action extends AbstractAction {

        public F8Action() {
        }

        public void actionPerformed(ActionEvent e) {
            cancelaItem();
        }
    }

    private class F9Action extends AbstractAction {

        public F9Action() {
        }

        public void actionPerformed(ActionEvent e) {
            cancelaVenda();
        }
    }

    private class F10Action extends AbstractAction {

        public F10Action() {
        }

        public void actionPerformed(ActionEvent e) {
            descontoOuAcrescimo();
        }
    }

    private class F11Action extends AbstractAction {

        public F11Action() {
        }

        public void actionPerformed(ActionEvent e) {
            identificaVendedor();
        }
    }

    private class F12Action extends AbstractAction {

        public F12Action() {
        }

        public void actionPerformed(ActionEvent e) {
            sair();
        }
    }

    private class ESCAction extends AbstractAction {

        public ESCAction() {
        }

        public void actionPerformed(ActionEvent e) {
            teclouESC();
        }
    }

    private class EnterAction extends AbstractAction {

        public EnterAction() {
        }

        public void actionPerformed(ActionEvent e) {
            teclouEnter();
        }
    }

    private class SetaAcimaAction extends AbstractAction {

        public SetaAcimaAction() {
        }

        public void actionPerformed(ActionEvent e) {
            teclouSetaAcimaAbaixo();
        }
    }

    private class SetaAbaixoAction extends AbstractAction {

        public SetaAbaixoAction() {
        }

        public void actionPerformed(ActionEvent e) {
            teclouSetaAcimaAbaixo();
        }
    }

// ***************************************************************************//
// Controle do pressionamento das teclas                                      //
// ***************************************************************************//
    private void teclouEnter() {
        //menu principal
        if (this.getFocusOwner() == listaMenuPrincipal) {
            if (listaMenuPrincipal.getSelectedIndex() == 0 || listaMenuPrincipal.getSelectedIndex() == 1) {
                LoginGerenteSupervisor janelaLoginGerenteSupervisor = new LoginGerenteSupervisor(this, true);
                janelaLoginGerenteSupervisor.setLocationRelativeTo(null);
                janelaLoginGerenteSupervisor.setVisible(true);
                //supervisor
                if (listaMenuPrincipal.getSelectedIndex() == 0) {
                    if (janelaLoginGerenteSupervisor.loginSupervisor(true)) {
                        panelSubMenu.setVisible(true);
                        ((CardLayout) panelCard.getLayout()).show(panelCard, "cardSupervisor");
                        listaSubMenuSupervisor.requestFocus();
                        listaSubMenuSupervisor.setSelectedIndex(0);
                    }
                }
                //gerente
                if (listaMenuPrincipal.getSelectedIndex() == 1) {
                    if (janelaLoginGerenteSupervisor.loginGerente(true)) {
                        panelSubMenu.setVisible(true);
                        ((CardLayout) panelCard.getLayout()).show(panelCard, "cardGerente");
                        listaSubMenuGerente.requestFocus();
                        listaSubMenuGerente.setSelectedIndex(0);
                    }
                }
            }
            //saida temporaria
            if (listaMenuPrincipal.getSelectedIndex() == 2) {
                if ((SessaoUsuario.statusCaixa == SC_ABERTO)) {
                    int escolha = JOptionPane.showOptionDialog(this, "Deseja fechar o caixa temporariamente?", "Pergunta do Sistema",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                            null, opcoesResposta, null);
                    if (escolha == JOptionPane.YES_OPTION) {
                        try {
                            NfceControllerGenerico<NfceMovimentoVO> movimentoController = new NfceControllerGenerico<>();
                            SessaoUsuario.movimento.setStatusMovimento("T");
                            SessaoUsuario.usuario = null;
                            movimentoController.atualizar(SessaoUsuario.movimento);

                            telaPadrao();

                            MovimentoAberto ma = new MovimentoAberto(this, true);
                            ma.setLocationRelativeTo(null);
                            ma.setVisible(true);
                            telaPadrao();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(this, "Erro ao registrar a saída temporária.\n" + ex.getMessage(), "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Status do caixa não permite saída temporária.", "Aviso do Sistema", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        //menu principal - submenu supervisor
        if (this.getFocusOwner() == listaSubMenuSupervisor) {
            //inicia movimento
            if (listaSubMenuSupervisor.getSelectedIndex() == 0) {
                iniciaMovimento();
            }
            //encerra movimento
            if (listaSubMenuSupervisor.getSelectedIndex() == 1) {
                encerraMovimento();
            }
            //suprimento
            if (listaSubMenuSupervisor.getSelectedIndex() == 3) {
                suprimento();
            }
            //sangria
            if (listaSubMenuSupervisor.getSelectedIndex() == 4) {
                sangria();
            }

        }
        //menu principal - submenu gerente
        if (this.getFocusOwner() == listaSubMenuGerente) {
            //inicia movimento
            if (listaSubMenuGerente.getSelectedIndex() == 0) {
                iniciaMovimento();
            }
            //encerra movimento
            if (listaSubMenuGerente.getSelectedIndex() == 1) {
                encerraMovimento();
            }
            //suprimento
            if (listaSubMenuGerente.getSelectedIndex() == 3) {
                suprimento();
            }
            //sangria
            if (listaSubMenuGerente.getSelectedIndex() == 4) {
                sangria();
            }
        }
        //menu operacoes
        if (this.getFocusOwner() == listaMenuOperacoes) {
            //carrega dav
            if (listaMenuOperacoes.getSelectedIndex() == 0) {
                if (SessaoUsuario.statusCaixa == SC_ABERTO) {
                    CarregaOrcamento janelaCarregaOrcamento = new CarregaOrcamento(this, true);
                    janelaCarregaOrcamento.setLocationRelativeTo(null);
                    janelaCarregaOrcamento.setVisible(true);
                    if (!janelaCarregaOrcamento.cancelado) {
                        dav = janelaCarregaOrcamento.getOrcamento();
                        if (dav != null) {
                            fechaMenuOperacoes();
                            try {
                                carregaDAV();
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(this, "Erro ao carregar o orçamento.\n" + e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Já existe uma venda em andamento.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            //seleciona certificado digital
            if (listaMenuOperacoes.getSelectedIndex() == 1) {
                if (SessaoUsuario.statusCaixa != SC_VENDA_EM_ANDAMENTO) {
                    selecionaCertificado();
                } else {
                    JOptionPane.showMessageDialog(this, "Existe uma venda em andamento.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            //consulta status serviço
            if (listaMenuOperacoes.getSelectedIndex() == 2) {
                SessaoUsuario.statusServico();
            }

        }
    }

    private void teclouESC() {
        if (this.getFocusOwner() == listaMenuPrincipal) {
            panelMenuPrincipal.setVisible(false);
            panelSubMenu.setVisible(false);
            SessaoUsuario.menuAberto = NAO;
        }
        if (this.getFocusOwner() == listaMenuOperacoes) {
            panelMenuOperacoes.setVisible(false);
            SessaoUsuario.menuAberto = NAO;
        }
        if (this.getFocusOwner() == listaMenuFiscal) {
            panelMenuFiscal.setVisible(false);
            SessaoUsuario.menuAberto = NAO;
        }
        if (this.getFocusOwner() == listaSubMenuGerente
                || this.getFocusOwner() == listaSubMenuSupervisor) {
            listaMenuPrincipal.requestFocus();
            listaMenuPrincipal.setSelectedIndex(0);
            panelSubMenu.setVisible(false);
        }
    }

    private void teclouSetaAcimaAbaixo() {
        String selecionado = "";

        if (this.getFocusOwner() == listaMenuPrincipal) {
            selecionado = (String) modelMenuPrincipal.get(listaMenuPrincipal.getSelectedIndex());
        }

        if (this.getFocusOwner() == listaMenuOperacoes) {
            selecionado = (String) modelMenuOperacoes.get(listaMenuOperacoes.getSelectedIndex());
        }

        if (this.getFocusOwner() == listaMenuFiscal) {
            selecionado = (String) modelMenuFiscal.get(listaMenuFiscal.getSelectedIndex());
        }

        if (this.getFocusOwner() == listaSubMenuGerente) {
            selecionado = (String) modelSubMenuGerente.get(listaSubMenuGerente.getSelectedIndex());
        }

        if (this.getFocusOwner() == listaSubMenuSupervisor) {
            selecionado = (String) modelSubMenuSupervisor.get(listaSubMenuSupervisor.getSelectedIndex());
        }

        labelMensagens.setText(selecionado);
        this.repaint();
    }

// ***************************************************************************//
// Métodos referentes ao Menu Principal e seus SubMenus                       //
// ***************************************************************************//
    private void iniciaMovimento() {
        if (SessaoUsuario.movimento != null) {
            JOptionPane.showMessageDialog(this, "Já existe um movimento aberto.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
        } else {
            IniciaMovimento im = new IniciaMovimento(this, true);
            im.setLocationRelativeTo(null);
            im.setVisible(true);
            telaPadrao();
        }
    }

    private void encerraMovimento() {
        if (SessaoUsuario.movimento == null) {
            JOptionPane.showMessageDialog(this, "Não existe movimento aberto.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
        } else {
            EncerraMovimento em = new EncerraMovimento(this, true);
            em.setLocationRelativeTo(null);
            em.setVisible(true);
            if (em.movimentoEncerrado) {
                SessaoUsuario.movimento = null;
            }
            telaPadrao();
        }
    }

    private void suprimento() {
        if (SessaoUsuario.movimento == null) {
            JOptionPane.showMessageDialog(this, "Não Existe um movimento aberto.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
        } else {
            ValorReal janelaValorReal = new ValorReal(this, true);
            janelaValorReal.setLocationRelativeTo(null);
            janelaValorReal.setTitle("Suprimento");
            janelaValorReal.setVisible(true);
            Double valorSuprimento = janelaValorReal.getValor();
            if (!janelaValorReal.cancelado) {
                if (valorSuprimento > 0) {
                    try {
                        NfceSuprimentoVO suprimento = new NfceSuprimentoVO();
                        suprimento.setNfceMovimento(SessaoUsuario.movimento);
                        suprimento.setDataSuprimento(new Date());
                        suprimento.setObservacao(janelaValorReal.getObservacao().trim());
                        suprimento.setValor(BigDecimal.valueOf(valorSuprimento));
                        new NfceControllerGenerico<NfceSuprimentoVO>().salvar(suprimento);

                        SessaoUsuario.movimento.setTotalSuprimento(Biblioteca.soma(SessaoUsuario.movimento.getTotalSuprimento(), suprimento.getValor()));
                        new NfceControllerGenerico<NfceMovimentoVO>().atualizar(SessaoUsuario.movimento);

                        JOptionPane.showMessageDialog(this, "Suprimento inserido com sucesso.", "Informação do sistema", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Erro no suprimento.\n" + ex.getMessage(), "Erro do sistema", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Valor deve ser maior que zero.", "Informação do sistema", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private void sangria() {
        if (SessaoUsuario.movimento == null) {
            JOptionPane.showMessageDialog(this, "Não Existe um movimento aberto.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
        } else {
            ValorReal janelaValorReal = new ValorReal(this, true);
            janelaValorReal.setLocationRelativeTo(null);
            janelaValorReal.setTitle("Sangria");
            janelaValorReal.setVisible(true);
            Double valorSangria = janelaValorReal.getValor();
            if (!janelaValorReal.cancelado) {
                if (valorSangria > 0) {
                    try {
                        NfceSangriaVO sangria = new NfceSangriaVO();
                        sangria.setNfceMovimento(SessaoUsuario.movimento);
                        sangria.setDataSangria(new Date());
                        sangria.setValor(BigDecimal.valueOf(valorSangria));
                        sangria.setObservacao(janelaValorReal.getObservacao().trim());
                        new NfceControllerGenerico<NfceSangriaVO>().salvar(sangria);

                        SessaoUsuario.movimento.setTotalSangria(Biblioteca.soma(SessaoUsuario.movimento.getTotalSangria(), sangria.getValor()));
                        new NfceControllerGenerico<NfceMovimentoVO>().atualizar(SessaoUsuario.movimento);

                        JOptionPane.showMessageDialog(this, "Sangria realizada com sucesso.", "Informação do sistema", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Erro na sangria.\n" + ex.getMessage(), "Erro do sistema", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Valor deve ser maior que zero.", "Informação do sistema", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private void descontoOuAcrescimo() {
        if (SessaoUsuario.statusCaixa == SC_VENDA_EM_ANDAMENTO) {
            LoginGerenteSupervisor janelaLogin = new LoginGerenteSupervisor(this, true);
            janelaLogin.setLocationRelativeTo(null);
            janelaLogin.setVisible(true);
            if (janelaLogin.loginGerenteSupervisor()) {
                DescontoAcrescimo janela = new DescontoAcrescimo(this, true);
                janela.setLocationRelativeTo(null);
                janela.setVisible(true);
                Double valor = janela.retornaValor();
                if (!janela.cancelado) {
                    int operacao = janela.getOperacao();
                    switch (operacao) {
                        case DESCONTO_VALOR: {
                            descontoValor(BigDecimal.valueOf(valor));
                            break;
                        }
                        case DESCONTO_PERCENTUAL: {
                            descontoTaxa(BigDecimal.valueOf(valor));
                            break;
                        }
                        case ACRESCIMO_VALOR: {
                            acrescimoValor(BigDecimal.valueOf(valor));
                            break;
                        }
                        case ACRESCIMO_PERCENTUAL: {
                            acrescimoTaxa(BigDecimal.valueOf(valor));
                            break;
                        }
                        case CANCELA_DESCONTO_ACRESCIMO: {
                            acrescimo = BigDecimal.ZERO;
                            desconto = BigDecimal.ZERO;
                            atualizaTotais();
                            break;
                        }
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Não existe venda em andamento.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void descontoValor(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            JOptionPane.showMessageDialog(this, "Valor zerado ou negativo. Operação não realizada.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
        } else if (valor.compareTo(SessaoUsuario.vendaAtual.getValorTotalProdutos()) >= 0) {
            JOptionPane.showMessageDialog(this, "Desconto não pode ser superior ou igual ao valor da venda.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
        } else {
            desconto = valor;
            atualizaTotais();
        }
    }

    private void descontoTaxa(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            JOptionPane.showMessageDialog(this, "Valor zerado ou negativo. Operação não realizada.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
        } else if (valor.compareTo(BigDecimal.valueOf(100.0)) >= 0) {
            JOptionPane.showMessageDialog(this, "Desconto não pode ser superior ou igual a 100%.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
        } else {
            desconto = Biblioteca.divide(valor, BigDecimal.valueOf(100));
            desconto = Biblioteca.multiplica(subTotal, desconto);
            atualizaTotais();
        }
    }

    private void acrescimoValor(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            JOptionPane.showMessageDialog(this, "Valor zerado ou negativo. Operação não realizada.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
        } else if (valor.compareTo(SessaoUsuario.vendaAtual.getValorTotalProdutos()) >= 0) {
            JOptionPane.showMessageDialog(this, "Valor do acréscimo não pode ser igual ou superior ao valor da venda.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
        } else {
            acrescimo = valor;
            atualizaTotais();
        }
    }

    private void acrescimoTaxa(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            JOptionPane.showMessageDialog(this, "Valor zerado ou negativo. Operação não realizada.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
        } else if (valor.compareTo(BigDecimal.valueOf(100.0)) >= 0) {
            JOptionPane.showMessageDialog(this, "Acréscimo não pode ser superior ou igual a 100%.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
        } else {
            acrescimo = Biblioteca.divide(valor, BigDecimal.valueOf(100));
            acrescimo = Biblioteca.multiplica(subTotal, acrescimo);
            atualizaTotais();
        }
    }

// ***************************************************************************//
// Métodos referentes ao Menu Operações                                       //
// ***************************************************************************//
    public void carregaDAV() throws Exception {
        if (!dav.getListaDavDetalhe().isEmpty()) {
            iniciaVenda();
            SessaoUsuario.statusCaixa = SC_VENDA_RECUPERADA_DAV_PREVENDA;
            for (DavDetalheVO davDetalhe : dav.getListaDavDetalhe()) {
                vendaDetalhe = new NfeDetalheVO();
                NfeDetalheImpostoIcmsVO impostoIcms = new NfeDetalheImpostoIcmsVO();
                impostoIcms.setNfeDetalhe(vendaDetalhe);
                vendaDetalhe.setNfeDetalheImpostoIcms(impostoIcms);

                consultaProduto(davDetalhe.getGtinProduto());

                vendaDetalhe.setQuantidadeComercial(davDetalhe.getQuantidade());
                vendaDetalhe.setQuantidadeTributavel(davDetalhe.getQuantidade());
                vendaDetalhe.setValorUnitarioComercial(davDetalhe.getValorUnitario());
                vendaDetalhe.setValorUnitarioTributavel(davDetalhe.getValorUnitario());
                vendaDetalhe.setValorBrutoProduto(davDetalhe.getValorTotal());
                vendaDetalhe.setValorSubtotal(davDetalhe.getValorTotal());
                vendaDetalhe.setValorDesconto(BigDecimal.ZERO);
                vendaDetalhe.setValorTotal(davDetalhe.getValorTotal());
                vendaDetalhe.setValorFrete(BigDecimal.ZERO);
                vendaDetalhe.setValorSeguro(BigDecimal.ZERO);
                vendaDetalhe.setValorOutrasDespesas(BigDecimal.ZERO);
                vendaDetalhe.setEntraTotal(1);

                vendeItem();
                subTotal = Biblioteca.soma(subTotal, vendaDetalhe.getValorTotal());
                totalGeral = Biblioteca.soma(totalGeral, vendaDetalhe.getValorTotal());
                valorIcms = Biblioteca.soma(valorIcms, vendaDetalhe.getNfeDetalheImpostoIcms().getValorIcms());
                atualizaTotais();
            }
            editCodigo.requestFocus();
            SessaoUsuario.statusCaixa = SC_VENDA_EM_ANDAMENTO;
        } else {
            JOptionPane.showMessageDialog(this, "DAV sem itens.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
        }
    }

// ***************************************************************************//
// Métodos para controle da venda                                             //
// ***************************************************************************//
    private void localizaProduto() {
        editCodigo.setText("");
        editQuantidade.requestFocus();
        ImportaProduto janelaImportaProduto = new ImportaProduto(this, true);
        janelaImportaProduto.setLocationRelativeTo(null);
        janelaImportaProduto.setVisible(true);
        if (SessaoUsuario.statusCaixa == SC_ABERTO || SessaoUsuario.statusCaixa == SC_VENDA_EM_ANDAMENTO) {
            if (!janelaImportaProduto.cancelado) {
                editCodigo.setText(janelaImportaProduto.getGTIN());
            }
        }
        editCodigo.requestFocus();
    }

    private void identificaCliente() {
        try {
            instanciaVendaAtual();
            IdentificaCliente identificaCliente = new IdentificaCliente(this, true);
            identificaCliente.setLocationRelativeTo(null);
            identificaCliente.setVisible(true);
            if (!identificaCliente.cancelado) {
                if (SessaoUsuario.vendaAtual.getDestinatario() != null) {
                    if (SessaoUsuario.statusCaixa == SC_ABERTO) {
                        iniciaVenda();
                    }
                    labelCliente.setText("Cliente: " + SessaoUsuario.vendaAtual.getDestinatario().getNome() + " - " + SessaoUsuario.vendaAtual.getDestinatario().getCpfCnpj());
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao identificar o cliente.\n" + ex.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void identificaVendedor() {
        try {
            if (SessaoUsuario.statusCaixa == SC_VENDA_EM_ANDAMENTO) {
                ValorInteiro janelaValorInteiro = new ValorInteiro(this, true);
                janelaValorInteiro.setLocationRelativeTo(null);
                janelaValorInteiro.setTitle("Identifica Vendedor");
                janelaValorInteiro.setTextoLabelEntrada("Informe o código do vendedor");
                janelaValorInteiro.setVisible(true);
                Integer valor = janelaValorInteiro.retornaValor();
                NfceControllerGenerico<VendedorVO> vendedorController = new NfceControllerGenerico<>();
                VendedorVO vendedor = vendedorController.getBean(valor, VendedorVO.class);
                if (vendedor != null) {
                    SessaoUsuario.vendaAtual.setVendedor(vendedor);
                    JOptionPane.showMessageDialog(this, "Vendedor: " + vendedor.getColaborador().getPessoa().getNome(), "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Vendedor: código inválido ou inexistente.", "Aviso do Sistema", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Não existe venda em andamento.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao identificar o vendedor.\n" + ex.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void instanciaVendaAtual() throws Exception {
        if (SessaoUsuario.vendaAtual == null) {
            Date dataAtual = new Date();
            SessaoUsuario.vendaAtual = new NfeCabecalhoVO();
            SessaoUsuario.vendaAtual.setListaNfeDetalhe(new ArrayList<NfeDetalheVO>());
            SessaoUsuario.vendaAtual.setListaNfeFormaPagamento(new ArrayList<NfeFormaPagamentoVO>());
            SessaoUsuario.vendaAtual.setUfEmitente(SessaoUsuario.getConfiguracao().getEmpresa().getCodigoIbgeUf());
            SessaoUsuario.vendaAtual.setNaturezaOperacao("VENDA");
            SessaoUsuario.vendaAtual.setCodigoModelo("65");
            SessaoUsuario.vendaAtual.setDataHoraEmissao(dataAtual);
            SessaoUsuario.vendaAtual.setDataHoraEntradaSaida(dataAtual);
            SessaoUsuario.vendaAtual.setTipoOperacao(1);
            SessaoUsuario.vendaAtual.setCodigoMunicipio(SessaoUsuario.getConfiguracao().getEmpresa().getCodigoIbgeCidade());
            SessaoUsuario.vendaAtual.setFormatoImpressaoDanfe(4);//0=Sem geração de DANFE;1=DANFE normal, Retrato;2=DANFE normal, Paisagem;3=DANFE Simplificado;4=DANFE NFC-e;5=DANFE NFC-e em mensagem eletrônica.
            SessaoUsuario.vendaAtual.setTipoEmissao(1);//1=Emissão normal (não em contingência);2=Contingência FS-IA, com impressão do DANFE em formulário de segurança;3=Contingência SCAN (Sistema de Contingência do Ambiente Nacional);4=Contingência DPEC (Declaração Prévia da Emissão em Contingência);5=Contingência FS-DA, com impressão do DANFE em formulário de segurança; 6=Contingência SVC-AN (SEFAZ Virtual de Contingência do AN); 7=Contingência SVC-RS (SEFAZ Virtual de Contingência do RS); 9=Contingência off-line da NFC-e;
            SessaoUsuario.vendaAtual.setEmpresa(SessaoUsuario.getConfiguracao().getEmpresa());
            SessaoUsuario.vendaAtual.setAmbiente(SessaoUsuario.getNfeConfiguracao().getWebserviceAmbiente());
            SessaoUsuario.vendaAtual.setFinalidadeEmissao(1);
            SessaoUsuario.vendaAtual.setProcessoEmissao(SessaoUsuario.getNfeConfiguracao().getProcessoEmissao());
            SessaoUsuario.vendaAtual.setVersaoProcessoEmissao(SessaoUsuario.getNfeConfiguracao().getVersaoProcessoEmissao());
            SessaoUsuario.vendaAtual.setConsumidorPresenca(1);//1=Operação presencial;4=NFC-e em operação com entrega em domicílio;
            SessaoUsuario.vendaAtual.setConsumidorOperacao(1);//0=Não;1=Consumidor final;
            SessaoUsuario.vendaAtual.setLocalDestino(1);//1=Operação interna;2=Operação interestadual;3=Operação com exterior.
            SessaoUsuario.vendaAtual.setStatusNota(NfceConstantes.SN_EM_EDICAO);

            SessaoUsuario.vendaAtual.setNfceMovimento(SessaoUsuario.movimento);
            SessaoUsuario.vendaAtual.setBaseCalculoIcms(BigDecimal.ZERO);
            SessaoUsuario.vendaAtual.setValorIcms(BigDecimal.ZERO);
            SessaoUsuario.vendaAtual.setValorTotalProdutos(BigDecimal.ZERO);
            SessaoUsuario.vendaAtual.setBaseCalculoIcmsSt(BigDecimal.ZERO);
            SessaoUsuario.vendaAtual.setValorIcmsSt(BigDecimal.ZERO);
            SessaoUsuario.vendaAtual.setValorIpi(BigDecimal.ZERO);
            SessaoUsuario.vendaAtual.setValorPis(BigDecimal.ZERO);
            SessaoUsuario.vendaAtual.setValorCofins(BigDecimal.ZERO);
            SessaoUsuario.vendaAtual.setValorFrete(BigDecimal.ZERO);
            SessaoUsuario.vendaAtual.setValorSeguro(BigDecimal.ZERO);
            SessaoUsuario.vendaAtual.setValorDespesasAcessorias(BigDecimal.ZERO);
            SessaoUsuario.vendaAtual.setValorDesconto(BigDecimal.ZERO);
            SessaoUsuario.vendaAtual.setValorTotal(BigDecimal.ZERO);
            SessaoUsuario.vendaAtual.setValorImpostoImportacao(BigDecimal.ZERO);
            SessaoUsuario.vendaAtual.setBaseCalculoIssqn(BigDecimal.ZERO);
            SessaoUsuario.vendaAtual.setValorIssqn(BigDecimal.ZERO);
            SessaoUsuario.vendaAtual.setValorPisIssqn(BigDecimal.ZERO);
            SessaoUsuario.vendaAtual.setValorCofinsIssqn(BigDecimal.ZERO);
            SessaoUsuario.vendaAtual.setValorServicos(BigDecimal.ZERO);
            SessaoUsuario.vendaAtual.setValorRetidoPis(BigDecimal.ZERO);
            SessaoUsuario.vendaAtual.setValorRetidoCofins(BigDecimal.ZERO);
            SessaoUsuario.vendaAtual.setValorRetidoCsll(BigDecimal.ZERO);
            SessaoUsuario.vendaAtual.setBaseCalculoIrrf(BigDecimal.ZERO);
            SessaoUsuario.vendaAtual.setValorRetidoIrrf(BigDecimal.ZERO);
            SessaoUsuario.vendaAtual.setBaseCalculoPrevidencia(BigDecimal.ZERO);
            SessaoUsuario.vendaAtual.setValorRetidoPrevidencia(BigDecimal.ZERO);
            SessaoUsuario.vendaAtual.setValorIcmsDesonerado(BigDecimal.ZERO);
            SessaoUsuario.vendaAtual.setTroco(BigDecimal.ZERO);
            numeroNfe();

            desconto = BigDecimal.ZERO;
            acrescimo = BigDecimal.ZERO;
        }
    }

    private void numeroNfe() throws Exception {
        DecimalFormat formatoNumero = new DecimalFormat("000000000");
        DecimalFormat formatoCodigoNumerico = new DecimalFormat("00000000");
        SimpleDateFormat formatoAno = new SimpleDateFormat("yy");
        SimpleDateFormat formatoMes = new SimpleDateFormat("MM");

        NfceControllerGenerico<NfeNumeroVO> numeroController = new NfceControllerGenerico<>();
        NfeNumeroVO numero = numeroController.getBean(1, NfeNumeroVO.class);
        if (numero == null) {
            numero = new NfeNumeroVO();
            numero.setEmpresa(SessaoUsuario.getConfiguracao().getEmpresa());
            numero.setSerie("001");
            numero.setNumero(1);
        } else {
            numero.setNumero(numero.getNumero() + 1);
        }
        numeroController.atualizar(numero);

        SessaoUsuario.vendaAtual.setNumero(formatoNumero.format(numero.getNumero()));
        SessaoUsuario.vendaAtual.setCodigoNumerico(formatoCodigoNumerico.format(numero.getNumero()));
        SessaoUsuario.vendaAtual.setSerie(numero.getSerie());

        SessaoUsuario.vendaAtual.setChaveAcesso("" + SessaoUsuario.vendaAtual.getEmpresa().getCodigoIbgeUf()
                + formatoAno.format(SessaoUsuario.vendaAtual.getDataHoraEmissao())
                + formatoMes.format(SessaoUsuario.vendaAtual.getDataHoraEmissao())
                + SessaoUsuario.vendaAtual.getEmpresa().getCnpj()
                + SessaoUsuario.vendaAtual.getCodigoModelo()
                + SessaoUsuario.vendaAtual.getSerie()
                + SessaoUsuario.vendaAtual.getNumero()
                + "1"
                + SessaoUsuario.vendaAtual.getCodigoNumerico());
        SessaoUsuario.vendaAtual.setDigitoChaveAcesso(Biblioteca.modulo11(SessaoUsuario.vendaAtual.getChaveAcesso()).toString());
    }

    private void iniciaVenda() {
        try {
            if (SessaoUsuario.movimento == null) {
                JOptionPane.showMessageDialog(this, "Não existe um movimento aberto.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
            } else {
                instanciaVendaAtual();

                imprimeCabecalhoBobina();
                parametrosIniciaisVenda();
                SessaoUsuario.statusCaixa = SC_VENDA_EM_ANDAMENTO;
                labelMensagens.setText("Venda em andamento...");

                vendaController.salvar(SessaoUsuario.vendaAtual);

                editCodigo.requestFocus();
                editCodigo.selectAll();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao iniciar a venda.\n" + ex.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
            SessaoUsuario.statusCaixa = SC_ABERTO;
            telaPadrao();
        }
    }

    private void parametrosIniciaisVenda() {
        setarImagem("padrao.png");
        itemCupom = 0;
        subTotal = BigDecimal.ZERO;
        totalGeral = BigDecimal.ZERO;
        valorIcms = BigDecimal.ZERO;
    }

    private void imprimeCabecalhoBobina() {
        modelBobina.addElement(Biblioteca.repete("-", 48));
        modelBobina.addElement("               **     NFC-e     **               ");
        modelBobina.addElement(Biblioteca.repete("-", 48));
        modelBobina.addElement("ITEM CÓDIGO         DESCRIÇÃO                   ");
        modelBobina.addElement("QTD.     UN      VL.UNIT.(R$) ST     VL.ITEM(R$)");
        modelBobina.addElement(Biblioteca.repete("-", 48));
    }

    private void iniciaVendaDeItens() {
        if (SessaoUsuario.statusCaixa != SC_SOMENTE_CONSULTA
                && SessaoUsuario.menuAberto == NAO) {

            try {
                if (SessaoUsuario.movimento == null) {
                    telaPadrao();
                    iniciaMovimento();
                    return;
                }

                if (SessaoUsuario.statusCaixa == SC_ABERTO) {
                    iniciaVenda();
                }

                if (!editCodigo.getText().trim().isEmpty()) {
                    vendaDetalhe = new NfeDetalheVO();
                    NfeDetalheImpostoIcmsVO impostoIcms = new NfeDetalheImpostoIcmsVO();
                    impostoIcms.setNfeDetalhe(vendaDetalhe);
                    vendaDetalhe.setNfeDetalheImpostoIcms(impostoIcms);

                    if (editCodigo.getText().length() == 13 || editCodigo.getText().length() == 14) {
                        consultaProduto(editCodigo.getText());
                    } else {
                        consultaProduto(editCodigo.getText(), PESQUISA_CODIGO_INTERNO);
                    }

                    if (vendaDetalhe.getProduto() != null) {
                        if (vendaDetalhe.getProduto().getValorVenda().doubleValue() <= 0) {
                            JOptionPane.showMessageDialog(rootPane, "Produto não pode ser vendido com valor zerado ou negativo.", "Informação do Sistema", JOptionPane.WARNING_MESSAGE);
                            vendaDetalhe.setProduto(null);
                            editCodigo.requestFocus();
                            editCodigo.selectAll();
                        } else if (vendaDetalhe.getProduto().getUnidadeProduto().getPodeFracionar().equals("N") && (Double.valueOf(editQuantidade.getText()) % 1) != 0) {
                            JOptionPane.showMessageDialog(rootPane, "Este produto não pode ser vendido em quantidade fracionada.", "Aviso do Sistema", JOptionPane.WARNING_MESSAGE);
                            editUnitario.setText("0,00");
                            editTotalItem.setText("0,00");
                            editQuantidade.setText("1");
                            labelDescricaoProduto.setText("");
                            editCodigo.setText("");
                            editCodigo.requestFocus();
                        } else {
                            editUnitario.setText(Biblioteca.formatoDecimal("V", vendaDetalhe.getProduto().getValorVenda().doubleValue()));
                            labelDescricaoProduto.setText(vendaDetalhe.getProduto().getDescricaoPdv());
                            //carrega imagem do produto
                            setarImagem(editCodigo.getText() + ".jpg");
                            BigDecimal total = Biblioteca.multiplica(vendaDetalhe.getProduto().getValorVenda(), BigDecimal.valueOf(Double.valueOf(editQuantidade.getText())));
                            editTotalItem.setText(Biblioteca.formatoDecimal("V", total.doubleValue()));

                            vendeItem();

                            editTotalItem.setText(vendaDetalhe.getValorTotal().toString());

                            subTotal = Biblioteca.soma(subTotal, vendaDetalhe.getValorTotal());
                            totalGeral = Biblioteca.soma(totalGeral, vendaDetalhe.getValorTotal());
                            valorIcms = Biblioteca.soma(valorIcms, vendaDetalhe.getNfeDetalheImpostoIcms().getValorIcms());
                            atualizaTotais();
                            editCodigo.setText("");
                            editCodigo.requestFocus();
                            editQuantidade.setText("1");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Código não encontrado.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
                        editUnitario.setText("0,00");
                        editTotalItem.setText("0,00");
                        editQuantidade.setText("1");
                        labelDescricaoProduto.setText("");
                        editCodigo.setText("");
                        editCodigo.requestFocus();
                    }
                }
            } catch (Exception e) {
                SessaoUsuario.statusCaixa = SC_ABERTO;
                telaPadrao();
                JOptionPane.showMessageDialog(this, "Erro ao vender o item.\n" + e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void consultaProduto(String pCodigo) {
        consultaProduto(pCodigo, PESQUISA_GTIN);
    }

    private void consultaProduto(String pCodigo, int pTipo) {
        ProdutoController produtoController = new ProdutoController();
        vendaDetalhe.setProduto(produtoController.consultaPorTipo(pCodigo, pTipo));
    }

    private void vendeItem() throws Exception {
        compoeItemParaVenda();

        if (vendaDetalhe.getGtin() == null || vendaDetalhe.getGtin().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Produto com Código ou GTIN não definico!", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
            editUnitario.setText("0,00");
            editTotalItem.setText("0,00");
            editCodigo.requestFocus();
            editCodigo.selectAll();
            itemCupom--;
            vendaDetalhe = null;
            return;
        }

        if (vendaDetalhe.getProduto().getDescricaoPdv() == null || vendaDetalhe.getProduto().getDescricaoPdv().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Produto com Descrição não definico!", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
            editUnitario.setText("0,00");
            editTotalItem.setText("0,00");
            editCodigo.requestFocus();
            editCodigo.selectAll();
            itemCupom--;
            vendaDetalhe = null;
            return;
        }

        if (vendaDetalhe.getProduto().getUnidadeProduto().getSigla() == null || vendaDetalhe.getProduto().getUnidadeProduto().getSigla().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Produto com Unidade não definico!", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
            editUnitario.setText("0,00");
            editTotalItem.setText("0,00");
            editCodigo.requestFocus();
            editCodigo.selectAll();
            itemCupom--;
            vendaDetalhe = null;
            return;
        }

        // Imposto
        vendaDetalhe.getNfeDetalheImpostoIcms().setOrigemMercadoria(0); //nacional
        vendaDetalhe.getNfeDetalheImpostoIcms().setCstIcms("00"); //nacional
        vendaDetalhe.getNfeDetalheImpostoIcms().setModalidadeBcIcms(3); //valor da operação
        vendaDetalhe.getNfeDetalheImpostoIcms().setAliquotaIcms(vendaDetalhe.getProduto().getAliquotaIcmsPaf());
        vendaDetalhe.getNfeDetalheImpostoIcms().setBaseCalculoIcms(vendaDetalhe.getValorTotal());
        BigDecimal icms = Biblioteca.multiplica(vendaDetalhe.getValorTotal(), vendaDetalhe.getProduto().getAliquotaIcmsPaf());
        icms = Biblioteca.divide(icms, BigDecimal.valueOf(100));
        vendaDetalhe.getNfeDetalheImpostoIcms().setValorIcms(icms);

        new NfceControllerGenerico<NfeDetalheVO>().salvar(vendaDetalhe);

        SessaoUsuario.vendaAtual.getListaNfeDetalhe().add(vendaDetalhe);

        imprimeItemBobina();
    }

    private void compoeItemParaVenda() {
        itemCupom++;

        vendaDetalhe.setNumeroItem(itemCupom);
        vendaDetalhe.setCfop(SessaoUsuario.getConfiguracao().getCfop());
        vendaDetalhe.setNfeCabecalho(SessaoUsuario.vendaAtual);
        vendaDetalhe.setCodigoProduto(vendaDetalhe.getProduto().getGtin());
        vendaDetalhe.setGtin(vendaDetalhe.getProduto().getGtin());
        vendaDetalhe.setNomeProduto(vendaDetalhe.getProduto().getNome());
        vendaDetalhe.setNcm(vendaDetalhe.getProduto().getNcm());
        vendaDetalhe.setUnidadeComercial(vendaDetalhe.getProduto().getUnidadeProduto().getSigla());
        vendaDetalhe.setUnidadeTributavel(vendaDetalhe.getProduto().getUnidadeProduto().getSigla());
        vendaDetalhe.setGtinUnidadeTributavel(vendaDetalhe.getProduto().getGtin());

        if (SessaoUsuario.statusCaixa == SC_VENDA_EM_ANDAMENTO) {
            vendaDetalhe.setQuantidadeComercial(BigDecimal.valueOf(Double.valueOf(editQuantidade.getText())));
            vendaDetalhe.setQuantidadeTributavel(BigDecimal.valueOf(Double.valueOf(editQuantidade.getText())));
            vendaDetalhe.setValorUnitarioComercial(vendaDetalhe.getProduto().getValorVenda());
            vendaDetalhe.setValorUnitarioTributavel(vendaDetalhe.getProduto().getValorVenda());
            vendaDetalhe.setValorBrutoProduto(Biblioteca.multiplica(vendaDetalhe.getValorUnitarioComercial(), vendaDetalhe.getQuantidadeComercial()));
            vendaDetalhe.setValorSubtotal(BigDecimal.valueOf(Double.valueOf(editTotalItem.getText().replace(",", "."))));
            vendaDetalhe.setValorTotal(vendaDetalhe.getValorSubtotal());
            // Exercício: implemente o desconto sobre o valor do item de acordo com a sua necessidade
            vendaDetalhe.setValorDesconto(BigDecimal.ZERO);
            vendaDetalhe.setValorTotal(Biblioteca.subtrai(vendaDetalhe.getValorTotal(), vendaDetalhe.getValorDesconto()));
            vendaDetalhe.setValorFrete(BigDecimal.ZERO);
            vendaDetalhe.setValorSeguro(BigDecimal.ZERO);
            vendaDetalhe.setValorOutrasDespesas(BigDecimal.ZERO);
            vendaDetalhe.setEntraTotal(1);
        }
    }

    private void imprimeItemBobina() {
        String quantidade = Biblioteca.formatoDecimal("Q", vendaDetalhe.getQuantidadeComercial().doubleValue());
        String unitario = Biblioteca.formatoDecimal("V", vendaDetalhe.getValorUnitarioComercial().doubleValue());
        String total = Biblioteca.formatoDecimal("V", vendaDetalhe.getValorTotal().doubleValue());
        String descricao = vendaDetalhe.getProduto().getDescricaoPdv();
        String unidadeProduto = vendaDetalhe.getProduto().getUnidadeProduto().getSigla();

        if (descricao.length() > 28) {
            descricao = descricao.substring(0, 28);
        }
        //linha 1 do item
        modelBobina.addElement(
                Biblioteca.repete("0", 3 - String.valueOf(itemCupom).length()) + itemCupom
                + "  "
                + vendaDetalhe.getGtin() + Biblioteca.repete(" ", 14 - vendaDetalhe.getGtin().length())
                + " "
                + descricao);

        //linha 2 do item
        if (unidadeProduto.length() >= 3) {
            unidadeProduto = unidadeProduto.substring(0, 3);
        } else {
            unidadeProduto += Biblioteca.repete(" ", 3 - unidadeProduto.length());
        }
        modelBobina.addElement(
                Biblioteca.repete(" ", 8 - quantidade.length()) + quantidade
                + " "
                + unidadeProduto
                + " x "
                + Biblioteca.repete(" ", 13 - unitario.length()) + unitario
                + "      "
                + Biblioteca.repete(" ", 13 - total.length()) + total);

        bobina.setSelectedIndex(modelBobina.getSize() - 1);
        bobina.ensureIndexIsVisible(modelBobina.getSize() - 1);
    }

    private void cancelaItemBobina(int numeroItem, String descricao) {
        if (descricao.length() > 28) {
            descricao = descricao.substring(0, 28);
        }

        modelBobina.addElement(Biblioteca.repete("*", 48));
        modelBobina.addElement(
                Biblioteca.repete("0", 3 - String.valueOf(numeroItem).length()) + numeroItem
                + "  "
                + vendaDetalhe.getGtin() + Biblioteca.repete(" ", 14 - vendaDetalhe.getGtin().length())
                + "  "
                + descricao);
        modelBobina.addElement("ITEM CANCELADO");
        modelBobina.addElement(Biblioteca.repete("*", 48));
    }

    private void atualizaTotais() {
        SessaoUsuario.vendaAtual.setValorTotalProdutos(subTotal);
        SessaoUsuario.vendaAtual.setValorDesconto(desconto);
        SessaoUsuario.vendaAtual.setValorDespesasAcessorias(acrescimo);

        descontoAcrescimo = Biblioteca.subtrai(acrescimo, desconto);

        SessaoUsuario.vendaAtual.setValorTotal(Biblioteca.soma(totalGeral, descontoAcrescimo));
        SessaoUsuario.vendaAtual.setBaseCalculoIcms(SessaoUsuario.vendaAtual.getValorTotal());
        SessaoUsuario.vendaAtual.setValorIcms(valorIcms);

        if (descontoAcrescimo.compareTo(BigDecimal.ZERO) == -1) {
            labelDescontoAcrescimo.setText("Desconto: R$ " + Biblioteca.formatoDecimal("V", descontoAcrescimo.negate().doubleValue()));
            labelDescontoAcrescimo.setForeground(Color.red);
            SessaoUsuario.vendaAtual.setValorDesconto(descontoAcrescimo.negate());
            SessaoUsuario.vendaAtual.setValorDespesasAcessorias(BigDecimal.ZERO);
        } else if (descontoAcrescimo.compareTo(BigDecimal.ZERO) == 1) {
            labelDescontoAcrescimo.setText("Acréscimo: R$ " + Biblioteca.formatoDecimal("V", descontoAcrescimo.doubleValue()));
            labelDescontoAcrescimo.setForeground(Color.blue);
            SessaoUsuario.vendaAtual.setValorDesconto(BigDecimal.ZERO);
            SessaoUsuario.vendaAtual.setValorDespesasAcessorias(descontoAcrescimo);
        } else {
            labelDescontoAcrescimo.setText("");
            SessaoUsuario.vendaAtual.setValorDesconto(BigDecimal.ZERO);
            SessaoUsuario.vendaAtual.setValorDespesasAcessorias(BigDecimal.ZERO);
            acrescimo = BigDecimal.ZERO;
            desconto = BigDecimal.ZERO;
        }

        editSubTotal.setText(Biblioteca.formatoDecimal("V", subTotal.doubleValue()));
        labelTotalGeral.setText(Biblioteca.formatoDecimal("V", SessaoUsuario.vendaAtual.getValorTotal().doubleValue()));
    }

    private void iniciaEncerramentoVenda() {
        try {
            if (SessaoUsuario.statusCaixa == SC_VENDA_EM_ANDAMENTO) {
                if (!SessaoUsuario.vendaAtual.getListaNfeDetalhe().isEmpty()) {
                    if (SessaoUsuario.vendaAtual.getValorTotal().compareTo(BigDecimal.ZERO) <= 0) {
                        int resposta = JOptionPane.showOptionDialog(this, "Não existem itens para o fechamento da venda.\nDeseja cancelar o cupom atual?", "Pergunta do Sistema",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                                null, opcoesResposta, null);
                        if (resposta == JOptionPane.YES_OPTION) {
                            cancelaVenda();
                        }
                    } else {
                        EfetuaPagamento telaEfetuaPagamento = new EfetuaPagamento(this, true);
                        telaEfetuaPagamento.setLocationRelativeTo(null);
                        telaEfetuaPagamento.setVisible(true);
                        if (telaEfetuaPagamento.pagamentoOK) {
                            concluiEncerramentoVenda();
                            SessaoUsuario.statusCaixa = SC_ABERTO;
                            telaPadrao();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "A venda não contém itens.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Não existe venda em andamento.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao concluir a venda.\n" + e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void concluiEncerramentoVenda() {
        try {
            FinalizaVenda finalizaVenda = new FinalizaVenda(this, true);
            finalizaVenda.setLocationRelativeTo(null);
            finalizaVenda.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao concluir a venda.\n" + ex.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cancelaVenda() {
        if (SessaoUsuario.statusCaixa == SC_VENDA_EM_ANDAMENTO || SessaoUsuario.statusCaixa == SC_VENDA_RECUPERADA_DAV_PREVENDA) {
            LoginGerenteSupervisor janelaLogin = new LoginGerenteSupervisor(this, true);
            janelaLogin.setLocationRelativeTo(null);
            janelaLogin.setVisible(true);
            if (janelaLogin.loginGerenteSupervisor()) {
                int escolha = JOptionPane.showOptionDialog(this, "Deseja cancelar a venda atual?", "Pergunta do Sistema",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, opcoesResposta, null);
                if (escolha == JOptionPane.YES_OPTION) {
                    SessaoUsuario.statusCaixa = SC_ABERTO;
                    telaPadrao();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Não existe venda em andamento.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void cancelaItem() {
        LoginGerenteSupervisor janelaLogin = new LoginGerenteSupervisor(this, true);
        janelaLogin.setLocationRelativeTo(null);
        janelaLogin.setVisible(true);
        if (janelaLogin.loginGerenteSupervisor()) {
            if (SessaoUsuario.statusCaixa == SC_VENDA_EM_ANDAMENTO) {
                ValorInteiro janelaValorInteiro = new ValorInteiro(this, true);
                janelaValorInteiro.setLocationRelativeTo(null);
                janelaValorInteiro.setVisible(true);
                if (!janelaValorInteiro.solicitacaoCancelada) {
                    Integer numeroItem = janelaValorInteiro.retornaValor();
                    if (numeroItem != null && numeroItem > 0) {
                        if (numeroItem > (SessaoUsuario.vendaAtual.getListaNfeDetalhe().size() + 1)) {
                            JOptionPane.showMessageDialog(this, "Nr. de item inválido.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            cancelaItem(numeroItem);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Nr. de item inválido.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Não existe venda em andamento.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void cancelaItem(int numeroItem) {
        if ((numeroItem > 0)) {
            if (numeroItem <= SessaoUsuario.vendaAtual.getListaNfeDetalhe().size()) {
                vendaDetalhe = SessaoUsuario.vendaAtual.getListaNfeDetalhe().get(numeroItem - 1);
                try {
                    new NfceControllerGenerico<NfeDetalheVO>().excluir(vendaDetalhe);

                    cancelaItemBobina(numeroItem, vendaDetalhe.getProduto().getDescricaoPdv());

                    SessaoUsuario.vendaAtual.getListaNfeDetalhe().remove(numeroItem - 1);

                    subTotal = Biblioteca.subtrai(subTotal, vendaDetalhe.getValorTotal());
                    totalGeral = Biblioteca.subtrai(totalGeral, vendaDetalhe.getValorTotal());
                    valorIcms = Biblioteca.subtrai(valorIcms, vendaDetalhe.getNfeDetalheImpostoIcms().getValorIcms());

                    desconto = BigDecimal.ZERO;
                    acrescimo = BigDecimal.ZERO;

                    atualizaTotais();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao cancelar o item.\n" + ex.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "O item solicitado não existe na venda atual.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Número do item inválido", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void cancelaInutilizaNota() {
        try {
            LoginGerenteSupervisor janelaLogin = new LoginGerenteSupervisor(this, true);
            janelaLogin.setLocationRelativeTo(null);
            janelaLogin.setVisible(true);
            if (janelaLogin.loginGerenteSupervisor()) {
                if (SessaoUsuario.statusCaixa == SC_VENDA_EM_ANDAMENTO) {
                    JOptionPane.showMessageDialog(this, "Existe uma venda em andamento.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String operacao[] = {"Cancelamento", "Inutilização"};
                    String resposta = (String) JOptionPane.showInputDialog(this,
                            "Informe a operação a ser realizada.",
                            "Cancela/Inutiliza Nota",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            operacao,
                            operacao[0]);
                    if (resposta != null) {
                        ListaNfce listaNfce;
                        if (resposta.equals(operacao[0])) {
                            listaNfce = new ListaNfce(this, true, NfceConstantes.OP_CANCELA_NFCE);
                        } else {
                            listaNfce = new ListaNfce(this, true, NfceConstantes.OP_INUTILIZA_NUMERO);
                        }
                        listaNfce.setLocationRelativeTo(null);
                        listaNfce.setVisible(true);
                        if (!listaNfce.cancelado && listaNfce.getIdNfeCabecalho() != null) {
                            String justificativa = JOptionPane.showInputDialog(this, "Informe a justificativa para " + resposta);
                            if (justificativa != null) {
                                if (justificativa.trim().equals("")) {
                                    JOptionPane.showMessageDialog(this, "É necessário informar uma justificativa para " + resposta + " da NFC-e.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
                                } else if (justificativa.trim().length() < 15) {
                                    JOptionPane.showMessageDialog(this, "A justificativa deve ter no mínimo 15 caracteres.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
                                } else if (justificativa.trim().length() > 255) {
                                    JOptionPane.showMessageDialog(this, "A justificativa deve ter no máximo 255 caracteres.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    CancelaInutiliza janelaCancelaInutiliza = null;
                                    if (resposta.equals(operacao[0])) {
                                        janelaCancelaInutiliza = new CancelaInutiliza(this, true, NfceConstantes.OP_CANCELA_NFCE, justificativa, listaNfce.getIdNfeCabecalho());
                                    } else {
                                        janelaCancelaInutiliza = new CancelaInutiliza(this, true, NfceConstantes.OP_INUTILIZA_NUMERO, justificativa, listaNfce.getIdNfeCabecalho());
                                    }
                                    janelaCancelaInutiliza.setLocationRelativeTo(null);
                                    janelaCancelaInutiliza.setVisible(true);
                                    telaPadrao();
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "É necessário informar uma justificativa para " + resposta + " da NFC-e.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
                            }

                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao cancelar/inutilizar a NFC-e.\n" + ex.getMessage(), "Erro do sistema", JOptionPane.ERROR_MESSAGE);
        }

    }

//****************************************************************************//
// Aparência e controle dos painéis com as funções do programa - F1 a F12     //
//****************************************************************************//
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == panelF1) {
            identificaCliente();
        }
        if (e.getSource() == panelF2) {
            acionaMenuPrincipal();
        }
        if (e.getSource() == panelF3) {
            acionaMenuOperacoes();
        }
        if (e.getSource() == panelF4) {
            cancelaInutilizaNota();
        }
        if (e.getSource() == panelF5) {
            recuperarVenda();
        }
        if (e.getSource() == panelF6) {
            localizaProduto();
        }
        if (e.getSource() == panelF7) {
            iniciaEncerramentoVenda();
        }
        if (e.getSource() == panelF8) {
            cancelaItem();
        }
        if (e.getSource() == panelF9) {
            cancelaVenda();
        }
        if (e.getSource() == panelF10) {
            descontoOuAcrescimo();
        }
        if (e.getSource() == panelF11) {
            identificaVendedor();
        }
        if (e.getSource() == panelF12) {
            sair();
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == panelF1) {
            panelF1.setBackground(Color.WHITE);
        }
        if (e.getSource() == panelF2) {
            panelF2.setBackground(Color.WHITE);
        }
        if (e.getSource() == panelF3) {
            panelF3.setBackground(Color.WHITE);
        }
        if (e.getSource() == panelF4) {
            panelF4.setBackground(Color.WHITE);
        }
        if (e.getSource() == panelF5) {
            panelF5.setBackground(Color.WHITE);
        }
        if (e.getSource() == panelF6) {
            panelF6.setBackground(Color.WHITE);
        }
        if (e.getSource() == panelF7) {
            panelF7.setBackground(Color.WHITE);
        }
        if (e.getSource() == panelF8) {
            panelF8.setBackground(Color.WHITE);
        }
        if (e.getSource() == panelF9) {
            panelF9.setBackground(Color.WHITE);
        }
        if (e.getSource() == panelF10) {
            panelF10.setBackground(Color.WHITE);
        }
        if (e.getSource() == panelF11) {
            panelF11.setBackground(Color.WHITE);
        }
        if (e.getSource() == panelF12) {
            panelF12.setBackground(Color.WHITE);
        }
        this.repaint();
    }

    public void mouseExited(MouseEvent e) {
        if (e.getSource() == panelF1) {
            panelF1.setBackground(new Color(255, 255, 255, 0));
        }
        if (e.getSource() == panelF2) {
            panelF2.setBackground(new Color(255, 255, 255, 0));
        }
        if (e.getSource() == panelF3) {
            panelF3.setBackground(new Color(255, 255, 255, 0));
        }
        if (e.getSource() == panelF4) {
            panelF4.setBackground(new Color(255, 255, 255, 0));
        }
        if (e.getSource() == panelF5) {
            panelF5.setBackground(new Color(255, 255, 255, 0));
        }
        if (e.getSource() == panelF6) {
            panelF6.setBackground(new Color(255, 255, 255, 0));
        }
        if (e.getSource() == panelF7) {
            panelF7.setBackground(new Color(255, 255, 255, 0));
        }
        if (e.getSource() == panelF8) {
            panelF8.setBackground(new Color(255, 255, 255, 0));
        }
        if (e.getSource() == panelF9) {
            panelF9.setBackground(new Color(255, 255, 255, 0));
        }
        if (e.getSource() == panelF10) {
            panelF10.setBackground(new Color(255, 255, 255, 0));
        }
        if (e.getSource() == panelF11) {
            panelF11.setBackground(new Color(255, 255, 255, 0));
        }
        if (e.getSource() == panelF12) {
            panelF12.setBackground(new Color(255, 255, 255, 0));
        }
        this.repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList bobina;
    private javax.swing.JLayeredPane containerPrincipal;
    private javax.swing.JFormattedTextField editCodigo;
    private javax.swing.JFormattedTextField editQuantidade;
    private javax.swing.JFormattedTextField editSubTotal;
    private javax.swing.JFormattedTextField editTotalItem;
    private javax.swing.JFormattedTextField editUnitario;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel labelCaixa;
    private static javax.swing.JLabel labelCliente;
    private static javax.swing.JLabel labelDescontoAcrescimo;
    private javax.swing.JLabel labelDescricaoProduto;
    private javax.swing.JLabel labelF1;
    private javax.swing.JLabel labelF10;
    private javax.swing.JLabel labelF11;
    private javax.swing.JLabel labelF12;
    private javax.swing.JLabel labelF2;
    private javax.swing.JLabel labelF3;
    private javax.swing.JLabel labelF4;
    private javax.swing.JLabel labelF5;
    private javax.swing.JLabel labelF6;
    private javax.swing.JLabel labelF7;
    private javax.swing.JLabel labelF8;
    private javax.swing.JLabel labelF9;
    public static javax.swing.JLabel labelImagemProduto;
    private javax.swing.JLabel labelImagemTela;
    private static javax.swing.JLabel labelMensagens;
    private javax.swing.JLabel labelOperador;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelTotalGeral;
    private javax.swing.JList listaMenuFiscal;
    private javax.swing.JList listaMenuOperacoes;
    private javax.swing.JList listaMenuPrincipal;
    private javax.swing.JList listaSubMenuGerente;
    private javax.swing.JList listaSubMenuSupervisor;
    private javax.swing.JScrollPane panelBobina;
    private javax.swing.JPanel panelBotoes;
    private javax.swing.JPanel panelCard;
    private javax.swing.JPanel panelF1;
    private javax.swing.JPanel panelF10;
    private javax.swing.JPanel panelF11;
    private javax.swing.JPanel panelF12;
    private javax.swing.JPanel panelF2;
    private javax.swing.JPanel panelF3;
    private javax.swing.JPanel panelF4;
    private javax.swing.JPanel panelF5;
    private javax.swing.JPanel panelF6;
    private javax.swing.JPanel panelF7;
    private javax.swing.JPanel panelF8;
    private javax.swing.JPanel panelF9;
    private javax.swing.JPanel panelMenuFiscal;
    private javax.swing.JPanel panelMenuOperacoes;
    private javax.swing.JPanel panelMenuPrincipal;
    private javax.swing.JPanel panelSubMenu;
    private javax.swing.JScrollPane panelSubMenuGerente;
    private javax.swing.JScrollPane panelSubMenuSupervisor;
    // End of variables declaration//GEN-END:variables
}

class Banner implements Runnable {

    @Override
    public void run() {
        /*if (Caixa.getCaixa() != null) {
         Random aleatorio = new Random();
         int numero = 0;
         try {
         while (true) {
         if (SessaoUsuario.statusCaixa == 0) {
         numero = aleatorio.nextInt(6);

         /*String arquivo = Caixa.configuracao.getCaminhoImagensMarketing() + numero + ".jpg";
         File arquivoImagem = new File(arquivo);

         if (!arquivoImagem.exists()) {
         Thread.sleep(500);
         } else {
         Caixa.labelImagemProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource(arquivo)));
         Thread.sleep(5000);
         }+/
         }
         }
         } catch (Exception e) {
         //Caixa.labelImagemProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource(Caixa.configuracao.getCaminhoImagensProdutos() + "padrao.png")));
         }
         }*/
    }
}
