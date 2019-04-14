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
@Table(name = "FIN_PARCELA_PAGAMENTO")
public class FinParcelaPagamentoVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_PAGAMENTO")
    private Date dataPagamento;
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
    @Column(name = "VALOR_PAGO")
    private BigDecimal valorPago;
    @Column(name = "HISTORICO")
    private String historico;
    @JoinColumn(name = "ID_FIN_PARCELA_PAGAR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private FinParcelaPagarVO finParcelaPagar;
    @JoinColumn(name = "ID_FIN_CHEQUE_EMITIDO", referencedColumnName = "ID")
    @ManyToOne
    private FinChequeEmitidoVO finChequeEmitido;
    @JoinColumn(name = "ID_FIN_TIPO_PAGAMENTO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private FinTipoPagamentoVO finTipoPagamento;
    @JoinColumn(name = "ID_CONTA_CAIXA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ContaCaixaVO contaCaixa;

    public FinParcelaPagamentoVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
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

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public FinParcelaPagarVO getFinParcelaPagar() {
        return finParcelaPagar;
    }

    public void setFinParcelaPagar(FinParcelaPagarVO finParcelaPagar) {
        this.finParcelaPagar = finParcelaPagar;
    }

    public FinChequeEmitidoVO getFinChequeEmitido() {
        return finChequeEmitido;
    }

    public void setFinChequeEmitido(FinChequeEmitidoVO finChequeEmitido) {
        this.finChequeEmitido = finChequeEmitido;
    }

    public FinTipoPagamentoVO getFinTipoPagamento() {
        return finTipoPagamento;
    }

    public void setFinTipoPagamento(FinTipoPagamentoVO finTipoPagamento) {
        this.finTipoPagamento = finTipoPagamento;
    }

    public ContaCaixaVO getContaCaixa() {
        return contaCaixa;
    }

    public void setContaCaixa(ContaCaixaVO contaCaixa) {
        this.contaCaixa = contaCaixa;
    }


    @Override
    public String toString() {
        return "com.t2tierp.financeiro.java.FinParcelaPagamentoVO[id=" + id + "]";
    }

}
