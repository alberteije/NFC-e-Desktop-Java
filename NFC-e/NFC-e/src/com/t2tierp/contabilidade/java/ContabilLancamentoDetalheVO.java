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
package com.t2tierp.contabilidade.java;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.openswing.swing.message.receive.java.ValueObjectImpl;


@Entity
@Table(name = "CONTABIL_LANCAMENTO_DETALHE")
public class ContabilLancamentoDetalheVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "HISTORICO")
    private String historico;
    @Column(name = "VALOR")
    private BigDecimal valor;
    @Column(name = "TIPO")
    private String tipo;
    @JoinColumn(name = "ID_CONTABIL_LANCAMENTO_CAB", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ContabilLancamentoCabecalhoVO contabilLancamentoCabecalho;
    @JoinColumn(name = "ID_CONTABIL_HISTORICO", referencedColumnName = "ID")
    @ManyToOne
    private ContabilHistoricoVO contabilHistorico;
    @JoinColumn(name = "ID_CONTABIL_CONTA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ContabilContaVO contabilConta;

    public ContabilLancamentoDetalheVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ContabilLancamentoCabecalhoVO getContabilLancamentoCabecalho() {
        return contabilLancamentoCabecalho;
    }

    public void setContabilLancamentoCabecalho(ContabilLancamentoCabecalhoVO contabilLancamentoCabecalho) {
        this.contabilLancamentoCabecalho = contabilLancamentoCabecalho;
    }

    public ContabilHistoricoVO getContabilHistorico() {
        return contabilHistorico;
    }

    public void setContabilHistorico(ContabilHistoricoVO contabilHistorico) {
        this.contabilHistorico = contabilHistorico;
    }

    public ContabilContaVO getContabilConta() {
        return contabilConta;
    }

    public void setContabilConta(ContabilContaVO contabilConta) {
        this.contabilConta = contabilConta;
    }


    @Override
    public String toString() {
        return "com.t2tierp.contabilidade.java.ContabilLancamentoDetalheVO[id=" + id + "]";
    }

}
