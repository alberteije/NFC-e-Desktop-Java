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
@Table(name = "NFE_DETALHE_IMPOSTO_IPI")
public class NfeDetalheImpostoIpiVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "ENQUADRAMENTO_IPI")
    private String enquadramentoIpi;
    @Column(name = "CNPJ_PRODUTOR")
    private String cnpjProdutor;
    @Column(name = "CODIGO_SELO_IPI")
    private String codigoSeloIpi;
    @Column(name = "QUANTIDADE_SELO_IPI")
    private Integer quantidadeSeloIpi;
    @Column(name = "ENQUADRAMENTO_LEGAL_IPI")
    private String enquadramentoLegalIpi;
    @Column(name = "CST_IPI")
    private String cstIpi;
    @Column(name = "VALOR_BASE_CALCULO_IPI")
    private BigDecimal valorBaseCalculoIpi;
    @Column(name = "ALIQUOTA_IPI")
    private BigDecimal aliquotaIpi;
    @Column(name = "QUANTIDADE_UNIDADE_TRIBUTAVEL")
    private BigDecimal quantidadeUnidadeTributavel;
    @Column(name = "VALOR_UNIDADE_TRIBUTAVEL")
    private BigDecimal valorUnidadeTributavel;
    @Column(name = "VALOR_IPI")
    private BigDecimal valorIpi;
    @JoinColumn(name = "ID_NFE_DETALHE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private NfeDetalheVO nfeDetalhe;

    public NfeDetalheImpostoIpiVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnquadramentoIpi() {
        return enquadramentoIpi;
    }

    public void setEnquadramentoIpi(String enquadramentoIpi) {
        this.enquadramentoIpi = enquadramentoIpi;
    }

    public String getCnpjProdutor() {
        return cnpjProdutor;
    }

    public void setCnpjProdutor(String cnpjProdutor) {
        this.cnpjProdutor = cnpjProdutor;
    }

    public String getCodigoSeloIpi() {
        return codigoSeloIpi;
    }

    public void setCodigoSeloIpi(String codigoSeloIpi) {
        this.codigoSeloIpi = codigoSeloIpi;
    }

    public Integer getQuantidadeSeloIpi() {
        return quantidadeSeloIpi;
    }

    public void setQuantidadeSeloIpi(Integer quantidadeSeloIpi) {
        this.quantidadeSeloIpi = quantidadeSeloIpi;
    }

    public String getEnquadramentoLegalIpi() {
        return enquadramentoLegalIpi;
    }

    public void setEnquadramentoLegalIpi(String enquadramentoLegalIpi) {
        this.enquadramentoLegalIpi = enquadramentoLegalIpi;
    }

    public String getCstIpi() {
        return cstIpi;
    }

    public void setCstIpi(String cstIpi) {
        this.cstIpi = cstIpi;
    }

    public BigDecimal getValorBaseCalculoIpi() {
        return valorBaseCalculoIpi;
    }

    public void setValorBaseCalculoIpi(BigDecimal valorBaseCalculoIpi) {
        this.valorBaseCalculoIpi = valorBaseCalculoIpi;
    }

    public BigDecimal getAliquotaIpi() {
        return aliquotaIpi;
    }

    public void setAliquotaIpi(BigDecimal aliquotaIpi) {
        this.aliquotaIpi = aliquotaIpi;
    }

    public BigDecimal getQuantidadeUnidadeTributavel() {
        return quantidadeUnidadeTributavel;
    }

    public void setQuantidadeUnidadeTributavel(BigDecimal quantidadeUnidadeTributavel) {
        this.quantidadeUnidadeTributavel = quantidadeUnidadeTributavel;
    }

    public BigDecimal getValorUnidadeTributavel() {
        return valorUnidadeTributavel;
    }

    public void setValorUnidadeTributavel(BigDecimal valorUnidadeTributavel) {
        this.valorUnidadeTributavel = valorUnidadeTributavel;
    }

    public BigDecimal getValorIpi() {
        return valorIpi;
    }

    public void setValorIpi(BigDecimal valorIpi) {
        this.valorIpi = valorIpi;
    }

    public NfeDetalheVO getNfeDetalhe() {
        return nfeDetalhe;
    }

    public void setNfeDetalhe(NfeDetalheVO nfeDetalhe) {
        this.nfeDetalhe = nfeDetalhe;
    }


    @Override
    public String toString() {
        return "com.t2tierp.nfe.java.NfeDetalheImpostoIpiVO[id=" + id + "]";
    }

}
