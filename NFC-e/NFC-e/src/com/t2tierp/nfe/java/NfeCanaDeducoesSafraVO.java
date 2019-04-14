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
@Table(name = "NFE_CANA_DEDUCOES_SAFRA")
public class NfeCanaDeducoesSafraVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "DECRICAO")
    private String decricao;
    @Column(name = "VALOR_DEDUCAO")
    private BigDecimal valorDeducao;
    @Column(name = "VALOR_FORNECIMENTO")
    private BigDecimal valorFornecimento;
    @Column(name = "VALOR_TOTAL_DEDUCAO")
    private BigDecimal valorTotalDeducao;
    @Column(name = "VALOR_LIQUIDO_FORNECIMENTO")
    private BigDecimal valorLiquidoFornecimento;
    @JoinColumn(name = "ID_NFE_CANA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private NfeCanaVO nfeCana;

    public NfeCanaDeducoesSafraVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDecricao() {
        return decricao;
    }

    public void setDecricao(String decricao) {
        this.decricao = decricao;
    }

    public BigDecimal getValorDeducao() {
        return valorDeducao;
    }

    public void setValorDeducao(BigDecimal valorDeducao) {
        this.valorDeducao = valorDeducao;
    }

    public BigDecimal getValorFornecimento() {
        return valorFornecimento;
    }

    public void setValorFornecimento(BigDecimal valorFornecimento) {
        this.valorFornecimento = valorFornecimento;
    }

    public BigDecimal getValorTotalDeducao() {
        return valorTotalDeducao;
    }

    public void setValorTotalDeducao(BigDecimal valorTotalDeducao) {
        this.valorTotalDeducao = valorTotalDeducao;
    }

    public BigDecimal getValorLiquidoFornecimento() {
        return valorLiquidoFornecimento;
    }

    public void setValorLiquidoFornecimento(BigDecimal valorLiquidoFornecimento) {
        this.valorLiquidoFornecimento = valorLiquidoFornecimento;
    }

    public NfeCanaVO getNfeCana() {
        return nfeCana;
    }

    public void setNfeCana(NfeCanaVO nfeCana) {
        this.nfeCana = nfeCana;
    }


    @Override
    public String toString() {
        return "com.t2tierp.nfe.java.NfeCanaDeducoesSafraVO[id=" + id + "]";
    }

}
