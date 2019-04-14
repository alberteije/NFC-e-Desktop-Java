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
package com.t2tierp.vendas.java;

import com.t2tierp.cadastros.java.ClienteVO;
import com.t2tierp.cadastros.java.TransportadoraVO;
import com.t2tierp.cadastros.java.VendedorVO;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.openswing.swing.message.receive.java.ValueObjectImpl;


@Entity
@Table(name = "VENDA_CABECALHO")
public class VendaCabecalhoVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_VENDA")
    private Date dataVenda;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_SAIDA")
    private Date dataSaida;
    @Column(name = "HORA_SAIDA")
    private String horaSaida;
    @Column(name = "NUMERO_FATURA")
    private Integer numeroFatura;
    @Column(name = "LOCAL_ENTREGA")
    private String localEntrega;
    @Column(name = "LOCAL_COBRANCA")
    private String localCobranca;
    @Column(name = "VALOR_SUBTOTAL")
    private BigDecimal valorSubtotal;
    @Column(name = "TAXA_COMISSAO")
    private BigDecimal taxaComissao;
    @Column(name = "VALOR_COMISSAO")
    private BigDecimal valorComissao;
    @Column(name = "TAXA_DESCONTO")
    private BigDecimal taxaDesconto;
    @Column(name = "VALOR_DESCONTO")
    private BigDecimal valorDesconto;
    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;
    @Column(name = "TIPO_FRETE")
    private String tipoFrete;
    @Column(name = "FORMA_PAGAMENTO")
    private String formaPagamento;
    @Column(name = "VALOR_FRETE")
    private BigDecimal valorFrete;
    @Column(name = "VALOR_SEGURO")
    private BigDecimal valorSeguro;
    @Column(name = "OBSERVACAO")
    private String observacao;
    @Column(name = "SITUACAO")
    private String situacao;
    @JoinColumn(name = "ID_VENDA_ORCAMENTO_CABECALHO", referencedColumnName = "ID")
    @ManyToOne
    private VendaOrcamentoCabecalhoVO vendaOrcamentoCabecalho;
    @JoinColumn(name = "ID_VENDA_CONDICOES_PAGAMENTO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CondicoesPagamentoVO condicoesPagamento;
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ClienteVO cliente;
    @JoinColumn(name = "ID_VENDEDOR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private VendedorVO vendedor;
    @JoinColumn(name = "ID_TIPO_NOTA_FISCAL", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private NotaFiscalTipoVO notaFiscalTipo;
    @JoinColumn(name = "ID_TRANSPORTADORA", referencedColumnName = "ID")
    @ManyToOne
    private TransportadoraVO transportadora;
    @JoinColumn(name = "ID_VENDA_ROMANEIO_ENTREGA", referencedColumnName = "ID")
    @ManyToOne
    private VendaRomaneioEntregaVO vendaRomaneioEntrega;

    public VendaCabecalhoVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(String horaSaida) {
        this.horaSaida = horaSaida;
    }

    public Integer getNumeroFatura() {
        return numeroFatura;
    }

    public void setNumeroFatura(Integer numeroFatura) {
        this.numeroFatura = numeroFatura;
    }

    public String getLocalEntrega() {
        return localEntrega;
    }

    public void setLocalEntrega(String localEntrega) {
        this.localEntrega = localEntrega;
    }

    public String getLocalCobranca() {
        return localCobranca;
    }

    public void setLocalCobranca(String localCobranca) {
        this.localCobranca = localCobranca;
    }

    public BigDecimal getValorSubtotal() {
        return valorSubtotal;
    }

    public void setValorSubtotal(BigDecimal valorSubtotal) {
        this.valorSubtotal = valorSubtotal;
    }

    public BigDecimal getTaxaComissao() {
        return taxaComissao;
    }

    public void setTaxaComissao(BigDecimal taxaComissao) {
        this.taxaComissao = taxaComissao;
    }

    public BigDecimal getValorComissao() {
        return valorComissao;
    }

    public void setValorComissao(BigDecimal valorComissao) {
        this.valorComissao = valorComissao;
    }

    public BigDecimal getTaxaDesconto() {
        return taxaDesconto;
    }

    public void setTaxaDesconto(BigDecimal taxaDesconto) {
        this.taxaDesconto = taxaDesconto;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getTipoFrete() {
        return tipoFrete;
    }

    public void setTipoFrete(String tipoFrete) {
        this.tipoFrete = tipoFrete;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public VendaOrcamentoCabecalhoVO getVendaOrcamentoCabecalho() {
        return vendaOrcamentoCabecalho;
    }

    public void setVendaOrcamentoCabecalho(VendaOrcamentoCabecalhoVO vendaOrcamentoCabecalho) {
        this.vendaOrcamentoCabecalho = vendaOrcamentoCabecalho;
    }

    public CondicoesPagamentoVO getCondicoesPagamento() {
        return condicoesPagamento;
    }

    public void setCondicoesPagamento(CondicoesPagamentoVO condicoesPagamento) {
        this.condicoesPagamento = condicoesPagamento;
    }

    public ClienteVO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteVO cliente) {
        this.cliente = cliente;
    }

    public VendedorVO getVendedor() {
        return vendedor;
    }

    public void setVendedor(VendedorVO vendedor) {
        this.vendedor = vendedor;
    }

    public NotaFiscalTipoVO getNotaFiscalTipo() {
        return notaFiscalTipo;
    }

    public void setNotaFiscalTipo(NotaFiscalTipoVO notaFiscalTipo) {
        this.notaFiscalTipo = notaFiscalTipo;
    }

    public TransportadoraVO getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(TransportadoraVO transportadora) {
        this.transportadora = transportadora;
    }

    public VendaRomaneioEntregaVO getVendaRomaneioEntrega() {
        return vendaRomaneioEntrega;
    }

    public void setVendaRomaneioEntrega(VendaRomaneioEntregaVO vendaRomaneioEntrega) {
        this.vendaRomaneioEntrega = vendaRomaneioEntrega;
    }


    @Override
    public String toString() {
        return "com.t2tierp.vendas.java.VendaCabecalhoVO[id=" + id + "]";
    }

}
