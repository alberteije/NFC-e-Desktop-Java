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

import com.t2ti.pafecf.infra.ColunaGrid;
import com.t2tierp.nfce.java.NfceTipoPagamentoVO;
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
@Table(name = "NFE_FORMA_PAGAMENTO")
public class NfeFormaPagamentoVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "FORMA")
    private String forma;
    @Column(name = "CNPJ_OPERADORA_CARTAO")
    private String cnpjOperadoraCartao;
    @Column(name = "BANDEIRA")
    private String bandeira;
    @Column(name = "NUMERO_AUTORIZACAO")
    private String numeroAutorizacao;
    @Column(name = "ESTORNO")
    private String estorno;
    @JoinColumn(name = "ID_NFE_CABECALHO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private NfeCabecalhoVO nfeCabecalho;
    @ColunaGrid(nome = "Tipo Pagamento", metodoGet = "getNfceTipoPagamento.getDescricao")
    @JoinColumn(name = "ID_NFCE_TIPO_PAGAMENTO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private NfceTipoPagamentoVO nfceTipoPagamento;
    @ColunaGrid(nome = "Valor")
    @Column(name = "VALOR")
    private BigDecimal valor;

    public NfeFormaPagamentoVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getCnpjOperadoraCartao() {
        return cnpjOperadoraCartao;
    }

    public void setCnpjOperadoraCartao(String cnpjOperadoraCartao) {
        this.cnpjOperadoraCartao = cnpjOperadoraCartao;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getNumeroAutorizacao() {
        return numeroAutorizacao;
    }

    public void setNumeroAutorizacao(String numeroAutorizacao) {
        this.numeroAutorizacao = numeroAutorizacao;
    }

    public String getEstorno() {
        return estorno;
    }

    public void setEstorno(String estorno) {
        this.estorno = estorno;
    }

    public NfeCabecalhoVO getNfeCabecalho() {
        return nfeCabecalho;
    }

    public void setNfeCabecalho(NfeCabecalhoVO nfeCabecalho) {
        this.nfeCabecalho = nfeCabecalho;
    }

    public NfceTipoPagamentoVO getNfceTipoPagamento() {
        return nfceTipoPagamento;
    }

    public void setNfceTipoPagamento(NfceTipoPagamentoVO nfceTipoPagamento) {
        this.nfceTipoPagamento = nfceTipoPagamento;
    }


    @Override
    public String toString() {
        return "com.t2tierp.nfe.java.NfeFormaPagamentoVO[id=" + id + "]";
    }

}
