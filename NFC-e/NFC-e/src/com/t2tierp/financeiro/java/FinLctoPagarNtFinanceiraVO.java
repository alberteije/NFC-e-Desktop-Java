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

import com.t2tierp.contabilidade.java.ContabilLancamentoDetalheVO;
import com.t2tierp.contabilidade.java.NaturezaFinanceiraVO;
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
@Table(name = "FIN_LCTO_PAGAR_NT_FINANCEIRA")
public class FinLctoPagarNtFinanceiraVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_INCLUSAO")
    private Date dataInclusao;
    @Column(name = "VALOR")
    private BigDecimal valor;
    @Column(name = "PERCENTUAL")
    private BigDecimal percentual;
    @JoinColumn(name = "ID_FIN_LANCAMENTO_PAGAR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private FinLancamentoPagarVO finLancamentoPagar;
    @JoinColumn(name = "ID_NATUREZA_FINANCEIRA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private NaturezaFinanceiraVO naturezaFinanceira;
    @JoinColumn(name = "ID_CONTABIL_LANCAMENTO_DET", referencedColumnName = "ID")
    @ManyToOne
    private ContabilLancamentoDetalheVO contabilLancamentoDetalhe;

    public FinLctoPagarNtFinanceiraVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getPercentual() {
        return percentual;
    }

    public void setPercentual(BigDecimal percentual) {
        this.percentual = percentual;
    }

    public FinLancamentoPagarVO getFinLancamentoPagar() {
        return finLancamentoPagar;
    }

    public void setFinLancamentoPagar(FinLancamentoPagarVO finLancamentoPagar) {
        this.finLancamentoPagar = finLancamentoPagar;
    }

    public NaturezaFinanceiraVO getNaturezaFinanceira() {
        return naturezaFinanceira;
    }

    public void setNaturezaFinanceira(NaturezaFinanceiraVO naturezaFinanceira) {
        this.naturezaFinanceira = naturezaFinanceira;
    }

    public ContabilLancamentoDetalheVO getContabilLancamentoDetalhe() {
        return contabilLancamentoDetalhe;
    }

    public void setContabilLancamentoDetalhe(ContabilLancamentoDetalheVO contabilLancamentoDetalhe) {
        this.contabilLancamentoDetalhe = contabilLancamentoDetalhe;
    }


    @Override
    public String toString() {
        return "com.t2tierp.financeiro.java.FinLctoPagarNtFinanceiraVO[id=" + id + "]";
    }

}
