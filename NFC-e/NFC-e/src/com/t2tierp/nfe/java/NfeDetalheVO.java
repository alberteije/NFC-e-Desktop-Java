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
package com.t2tierp.nfe.java;

import com.t2tierp.cadastros.java.ProdutoVO;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.openswing.swing.message.receive.java.ValueObjectImpl;


@Entity
@Table(name = "NFE_DETALHE")
public class NfeDetalheVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NUMERO_ITEM")
    private Integer numeroItem;
    @Column(name = "CODIGO_PRODUTO")
    private String codigoProduto;
    @Column(name = "GTIN")
    private String gtin;
    @Column(name = "NOME_PRODUTO")
    private String nomeProduto;
    @Column(name = "NCM")
    private String ncm;
    @Column(name = "NVE")
    private String nve;
    @Column(name = "EX_TIPI")
    private Integer exTipi;
    @Column(name = "CFOP")
    private Integer cfop;
    @Column(name = "UNIDADE_COMERCIAL")
    private String unidadeComercial;
    @Column(name = "QUANTIDADE_COMERCIAL")
    private BigDecimal quantidadeComercial;
    @Column(name = "VALOR_UNITARIO_COMERCIAL")
    private BigDecimal valorUnitarioComercial;
    @Column(name = "VALOR_BRUTO_PRODUTO")
    private BigDecimal valorBrutoProduto;
    @Column(name = "GTIN_UNIDADE_TRIBUTAVEL")
    private String gtinUnidadeTributavel;
    @Column(name = "UNIDADE_TRIBUTAVEL")
    private String unidadeTributavel;
    @Column(name = "QUANTIDADE_TRIBUTAVEL")
    private BigDecimal quantidadeTributavel;
    @Column(name = "VALOR_UNITARIO_TRIBUTAVEL")
    private BigDecimal valorUnitarioTributavel;
    @Column(name = "VALOR_FRETE")
    private BigDecimal valorFrete;
    @Column(name = "VALOR_SEGURO")
    private BigDecimal valorSeguro;
    @Column(name = "VALOR_DESCONTO")
    private BigDecimal valorDesconto;
    @Column(name = "VALOR_OUTRAS_DESPESAS")
    private BigDecimal valorOutrasDespesas;
    @Column(name = "ENTRA_TOTAL")
    private Integer entraTotal;
    @Column(name = "VALOR_SUBTOTAL")
    private BigDecimal valorSubtotal;
    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;
    @Column(name = "NUMERO_PEDIDO_COMPRA")
    private String numeroPedidoCompra;
    @Column(name = "ITEM_PEDIDO_COMPRA")
    private Integer itemPedidoCompra;
    @Column(name = "INFORMACOES_ADICIONAIS")
    private String informacoesAdicionais;
    @Column(name = "NUMERO_FCI")
    private String numeroFci;
    @Column(name = "NUMERO_RECOPI")
    private String numeroRecopi;
    @Column(name = "VALOR_TOTAL_TRIBUTOS")
    private BigDecimal valorTotalTributos;
    @Column(name = "PERCENTUAL_DEVOLVIDO")
    private BigDecimal percentualDevolvido;
    @Column(name = "VALOR_IPI_DEVOLVIDO")
    private BigDecimal valorIpiDevolvido;
    @JoinColumn(name = "ID_NFE_CABECALHO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private NfeCabecalhoVO nfeCabecalho;
    @JoinColumn(name = "ID_PRODUTO", referencedColumnName = "ID")
    @ManyToOne
    private ProdutoVO produto;
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "nfeDetalhe", cascade = CascadeType.ALL)
    private NfeDetalheImpostoIcmsVO nfeDetalheImpostoIcms;

    public NfeDetalheVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroItem() {
        return numeroItem;
    }

    public void setNumeroItem(Integer numeroItem) {
        this.numeroItem = numeroItem;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getGtin() {
        return gtin;
    }

    public void setGtin(String gtin) {
        this.gtin = gtin;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public String getNve() {
        return nve;
    }

    public void setNve(String nve) {
        this.nve = nve;
    }

    public Integer getExTipi() {
        return exTipi;
    }

    public void setExTipi(Integer exTipi) {
        this.exTipi = exTipi;
    }

    public Integer getCfop() {
        return cfop;
    }

    public void setCfop(Integer cfop) {
        this.cfop = cfop;
    }

    public String getUnidadeComercial() {
        return unidadeComercial;
    }

    public void setUnidadeComercial(String unidadeComercial) {
        this.unidadeComercial = unidadeComercial;
    }

    public BigDecimal getQuantidadeComercial() {
        return quantidadeComercial;
    }

    public void setQuantidadeComercial(BigDecimal quantidadeComercial) {
        this.quantidadeComercial = quantidadeComercial;
    }

    public BigDecimal getValorUnitarioComercial() {
        return valorUnitarioComercial;
    }

    public void setValorUnitarioComercial(BigDecimal valorUnitarioComercial) {
        this.valorUnitarioComercial = valorUnitarioComercial;
    }

    public BigDecimal getValorBrutoProduto() {
        return valorBrutoProduto;
    }

    public void setValorBrutoProduto(BigDecimal valorBrutoProduto) {
        this.valorBrutoProduto = valorBrutoProduto;
    }

    public String getGtinUnidadeTributavel() {
        return gtinUnidadeTributavel;
    }

    public void setGtinUnidadeTributavel(String gtinUnidadeTributavel) {
        this.gtinUnidadeTributavel = gtinUnidadeTributavel;
    }

    public String getUnidadeTributavel() {
        return unidadeTributavel;
    }

    public void setUnidadeTributavel(String unidadeTributavel) {
        this.unidadeTributavel = unidadeTributavel;
    }

    public BigDecimal getQuantidadeTributavel() {
        return quantidadeTributavel;
    }

    public void setQuantidadeTributavel(BigDecimal quantidadeTributavel) {
        this.quantidadeTributavel = quantidadeTributavel;
    }

    public BigDecimal getValorUnitarioTributavel() {
        return valorUnitarioTributavel;
    }

    public void setValorUnitarioTributavel(BigDecimal valorUnitarioTributavel) {
        this.valorUnitarioTributavel = valorUnitarioTributavel;
    }

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(BigDecimal valorFrete) {
        this.valorFrete = valorFrete;
    }

    public BigDecimal getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(BigDecimal valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public BigDecimal getValorOutrasDespesas() {
        return valorOutrasDespesas;
    }

    public void setValorOutrasDespesas(BigDecimal valorOutrasDespesas) {
        this.valorOutrasDespesas = valorOutrasDespesas;
    }

    public Integer getEntraTotal() {
        return entraTotal;
    }

    public void setEntraTotal(Integer entraTotal) {
        this.entraTotal = entraTotal;
    }

    public BigDecimal getValorSubtotal() {
        return valorSubtotal;
    }

    public void setValorSubtotal(BigDecimal valorSubtotal) {
        this.valorSubtotal = valorSubtotal;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getNumeroPedidoCompra() {
        return numeroPedidoCompra;
    }

    public void setNumeroPedidoCompra(String numeroPedidoCompra) {
        this.numeroPedidoCompra = numeroPedidoCompra;
    }

    public Integer getItemPedidoCompra() {
        return itemPedidoCompra;
    }

    public void setItemPedidoCompra(Integer itemPedidoCompra) {
        this.itemPedidoCompra = itemPedidoCompra;
    }

    public String getInformacoesAdicionais() {
        return informacoesAdicionais;
    }

    public void setInformacoesAdicionais(String informacoesAdicionais) {
        this.informacoesAdicionais = informacoesAdicionais;
    }

    public String getNumeroFci() {
        return numeroFci;
    }

    public void setNumeroFci(String numeroFci) {
        this.numeroFci = numeroFci;
    }

    public String getNumeroRecopi() {
        return numeroRecopi;
    }

    public void setNumeroRecopi(String numeroRecopi) {
        this.numeroRecopi = numeroRecopi;
    }

    public BigDecimal getValorTotalTributos() {
        return valorTotalTributos;
    }

    public void setValorTotalTributos(BigDecimal valorTotalTributos) {
        this.valorTotalTributos = valorTotalTributos;
    }

    public BigDecimal getPercentualDevolvido() {
        return percentualDevolvido;
    }

    public void setPercentualDevolvido(BigDecimal percentualDevolvido) {
        this.percentualDevolvido = percentualDevolvido;
    }

    public BigDecimal getValorIpiDevolvido() {
        return valorIpiDevolvido;
    }

    public void setValorIpiDevolvido(BigDecimal valorIpiDevolvido) {
        this.valorIpiDevolvido = valorIpiDevolvido;
    }

    public NfeCabecalhoVO getNfeCabecalho() {
        return nfeCabecalho;
    }

    public void setNfeCabecalho(NfeCabecalhoVO nfeCabecalho) {
        this.nfeCabecalho = nfeCabecalho;
    }

    public ProdutoVO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoVO produto) {
        this.produto = produto;
    }

    public NfeDetalheImpostoIcmsVO getNfeDetalheImpostoIcms() {
        return nfeDetalheImpostoIcms;
    }

    public void setNfeDetalheImpostoIcms(NfeDetalheImpostoIcmsVO nfeDetalheImpostoIcms) {
        this.nfeDetalheImpostoIcms = nfeDetalheImpostoIcms;
    }
    
    @Override
    public String toString() {
        return "com.t2tierp.nfe.java.NfeDetalheVO[id=" + id + "]";
    }

}
