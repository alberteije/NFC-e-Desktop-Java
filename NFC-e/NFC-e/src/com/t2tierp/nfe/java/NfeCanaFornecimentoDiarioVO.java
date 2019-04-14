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
@Table(name = "NFE_CANA_FORNECIMENTO_DIARIO")
public class NfeCanaFornecimentoDiarioVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "DIA")
    private String dia;
    @Column(name = "QUANTIDADE")
    private BigDecimal quantidade;
    @Column(name = "QUANTIDADE_TOTAL_MES")
    private BigDecimal quantidadeTotalMes;
    @Column(name = "QUANTIDADE_TOTAL_ANTERIOR")
    private BigDecimal quantidadeTotalAnterior;
    @Column(name = "QUANTIDADE_TOTAL_GERAL")
    private BigDecimal quantidadeTotalGeral;
    @JoinColumn(name = "ID_NFE_CANA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private NfeCanaVO nfeCana;

    public NfeCanaFornecimentoDiarioVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getQuantidadeTotalMes() {
        return quantidadeTotalMes;
    }

    public void setQuantidadeTotalMes(BigDecimal quantidadeTotalMes) {
        this.quantidadeTotalMes = quantidadeTotalMes;
    }

    public BigDecimal getQuantidadeTotalAnterior() {
        return quantidadeTotalAnterior;
    }

    public void setQuantidadeTotalAnterior(BigDecimal quantidadeTotalAnterior) {
        this.quantidadeTotalAnterior = quantidadeTotalAnterior;
    }

    public BigDecimal getQuantidadeTotalGeral() {
        return quantidadeTotalGeral;
    }

    public void setQuantidadeTotalGeral(BigDecimal quantidadeTotalGeral) {
        this.quantidadeTotalGeral = quantidadeTotalGeral;
    }

    public NfeCanaVO getNfeCana() {
        return nfeCana;
    }

    public void setNfeCana(NfeCanaVO nfeCana) {
        this.nfeCana = nfeCana;
    }


    @Override
    public String toString() {
        return "com.t2tierp.nfe.java.NfeCanaFornecimentoDiarioVO[id=" + id + "]";
    }

}
