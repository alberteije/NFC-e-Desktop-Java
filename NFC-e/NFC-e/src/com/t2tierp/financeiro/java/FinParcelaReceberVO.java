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
package com.t2tierp.financeiro.java;

import com.t2ti.pafecf.infra.ColunaGrid;
import com.t2tierp.cadastros.java.ContaCaixaVO;
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
@Table(name = "FIN_PARCELA_RECEBER")
public class FinParcelaReceberVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @ColunaGrid(nome = "Nr. Parcela")
    @Column(name = "NUMERO_PARCELA")
    private Integer numeroParcela;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_EMISSAO")
    private Date dataEmissao;
    @ColunaGrid(nome = "Data Vencimento")
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_VENCIMENTO")
    private Date dataVencimento;
    @Temporal(TemporalType.DATE)
    @Column(name = "DESCONTO_ATE")
    private Date descontoAte;
    @ColunaGrid(nome = "Valor")
    @Column(name = "VALOR")
    private BigDecimal valor;
    @Column(name = "TAXA_JURO")
    private BigDecimal taxaJuro;
    @Column(name = "TAXA_MULTA")
    private BigDecimal taxaMulta;
    @Column(name = "TAXA_DESCONTO")
    private BigDecimal taxaDesconto;
    @Column(name = "VALOR_JURO")
    private BigDecimal valorJuro;
    @Column(name = "VALOR_MULTA")
    private BigDecimal valorMulta;
    @Column(name = "VALOR_DESCONTO")
    private BigDecimal valorDesconto;
    @Column(name = "EMITIU_BOLETO")
    private String emitiuBoleto;
    @Column(name = "BOLETO_NOSSO_NUMERO")
    private String boletoNossoNumero;
    @JoinColumn(name = "ID_FIN_LANCAMENTO_RECEBER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private FinLancamentoReceberVO finLancamentoReceber;
    @JoinColumn(name = "ID_FIN_STATUS_PARCELA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private FinStatusParcelaVO finStatusParcela;
    @JoinColumn(name = "ID_CONTA_CAIXA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ContaCaixaVO contaCaixa;

    public FinParcelaReceberVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(Integer numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDescontoAte() {
        return descontoAte;
    }

    public void setDescontoAte(Date descontoAte) {
        this.descontoAte = descontoAte;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getTaxaJuro() {
        return taxaJuro;
    }

    public void setTaxaJuro(BigDecimal taxaJuro) {
        this.taxaJuro = taxaJuro;
    }

    public BigDecimal getTaxaMulta() {
        return taxaMulta;
    }

    public void setTaxaMulta(BigDecimal taxaMulta) {
        this.taxaMulta = taxaMulta;
    }

    public BigDecimal getTaxaDesconto() {
        return taxaDesconto;
    }

    public void setTaxaDesconto(BigDecimal taxaDesconto) {
        this.taxaDesconto = taxaDesconto;
    }

    public BigDecimal getValorJuro() {
        return valorJuro;
    }

    public void setValorJuro(BigDecimal valorJuro) {
        this.valorJuro = valorJuro;
    }

    public BigDecimal getValorMulta() {
        return valorMulta;
    }

    public void setValorMulta(BigDecimal valorMulta) {
        this.valorMulta = valorMulta;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public String getEmitiuBoleto() {
        return emitiuBoleto;
    }

    public void setEmitiuBoleto(String emitiuBoleto) {
        this.emitiuBoleto = emitiuBoleto;
    }

    public String getBoletoNossoNumero() {
        return boletoNossoNumero;
    }

    public void setBoletoNossoNumero(String boletoNossoNumero) {
        this.boletoNossoNumero = boletoNossoNumero;
    }

    public FinLancamentoReceberVO getFinLancamentoReceber() {
        return finLancamentoReceber;
    }

    public void setFinLancamentoReceber(FinLancamentoReceberVO finLancamentoReceber) {
        this.finLancamentoReceber = finLancamentoReceber;
    }

    public FinStatusParcelaVO getFinStatusParcela() {
        return finStatusParcela;
    }

    public void setFinStatusParcela(FinStatusParcelaVO finStatusParcela) {
        this.finStatusParcela = finStatusParcela;
    }

    public ContaCaixaVO getContaCaixa() {
        return contaCaixa;
    }

    public void setContaCaixa(ContaCaixaVO contaCaixa) {
        this.contaCaixa = contaCaixa;
    }


    @Override
    public String toString() {
        return "com.t2tierp.financeiro.java.FinParcelaReceberVO[id=" + id + "]";
    }

}
