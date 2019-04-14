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
@Table(name = "NFE_CUPOM_FISCAL_REFERENCIADO")
public class NfeCupomFiscalReferenciadoVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "MODELO_DOCUMENTO_FISCAL")
    private String modeloDocumentoFiscal;
    @Column(name = "NUMERO_ORDEM_ECF")
    private Integer numeroOrdemEcf;
    @Column(name = "COO")
    private Integer coo;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_EMISSAO_CUPOM")
    private Date dataEmissaoCupom;
    @Column(name = "NUMERO_CAIXA")
    private Integer numeroCaixa;
    @Column(name = "NUMERO_SERIE_ECF")
    private String numeroSerieEcf;
    @JoinColumn(name = "ID_NFE_CABECALHO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private NfeCabecalhoVO nfeCabecalho;

    public NfeCupomFiscalReferenciadoVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModeloDocumentoFiscal() {
        return modeloDocumentoFiscal;
    }

    public void setModeloDocumentoFiscal(String modeloDocumentoFiscal) {
        this.modeloDocumentoFiscal = modeloDocumentoFiscal;
    }

    public Integer getNumeroOrdemEcf() {
        return numeroOrdemEcf;
    }

    public void setNumeroOrdemEcf(Integer numeroOrdemEcf) {
        this.numeroOrdemEcf = numeroOrdemEcf;
    }

    public Integer getCoo() {
        return coo;
    }

    public void setCoo(Integer coo) {
        this.coo = coo;
    }

    public Date getDataEmissaoCupom() {
        return dataEmissaoCupom;
    }

    public void setDataEmissaoCupom(Date dataEmissaoCupom) {
        this.dataEmissaoCupom = dataEmissaoCupom;
    }

    public Integer getNumeroCaixa() {
        return numeroCaixa;
    }

    public void setNumeroCaixa(Integer numeroCaixa) {
        this.numeroCaixa = numeroCaixa;
    }

    public String getNumeroSerieEcf() {
        return numeroSerieEcf;
    }

    public void setNumeroSerieEcf(String numeroSerieEcf) {
        this.numeroSerieEcf = numeroSerieEcf;
    }

    public NfeCabecalhoVO getNfeCabecalho() {
        return nfeCabecalho;
    }

    public void setNfeCabecalho(NfeCabecalhoVO nfeCabecalho) {
        this.nfeCabecalho = nfeCabecalho;
    }


    @Override
    public String toString() {
        return "com.t2tierp.nfe.java.NfeCupomFiscalReferenciadoVO[id=" + id + "]";
    }

}
