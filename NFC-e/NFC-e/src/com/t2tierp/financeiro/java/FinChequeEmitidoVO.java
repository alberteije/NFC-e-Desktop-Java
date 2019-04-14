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

import com.t2tierp.cadastros.java.ChequeVO;
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
@Table(name = "FIN_CHEQUE_EMITIDO")
public class FinChequeEmitidoVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_EMISSAO")
    private Date dataEmissao;
    @Temporal(TemporalType.DATE)
    @Column(name = "BOM_PARA")
    private Date bomPara;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_COMPENSACAO")
    private Date dataCompensacao;
    @Column(name = "VALOR")
    private BigDecimal valor;
    @Column(name = "NOMINAL_A")
    private String nominalA;
    @JoinColumn(name = "ID_CHEQUE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ChequeVO cheque;

    public FinChequeEmitidoVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Date getBomPara() {
        return bomPara;
    }

    public void setBomPara(Date bomPara) {
        this.bomPara = bomPara;
    }

    public Date getDataCompensacao() {
        return dataCompensacao;
    }

    public void setDataCompensacao(Date dataCompensacao) {
        this.dataCompensacao = dataCompensacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getNominalA() {
        return nominalA;
    }

    public void setNominalA(String nominalA) {
        this.nominalA = nominalA;
    }

    public ChequeVO getCheque() {
        return cheque;
    }

    public void setCheque(ChequeVO cheque) {
        this.cheque = cheque;
    }


    @Override
    public String toString() {
        return "com.t2tierp.financeiro.java.FinChequeEmitidoVO[id=" + id + "]";
    }

}
