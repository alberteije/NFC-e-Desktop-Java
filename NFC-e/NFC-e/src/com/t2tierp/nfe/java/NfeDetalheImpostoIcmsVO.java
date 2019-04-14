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
@Table(name = "NFE_DETALHE_IMPOSTO_ICMS")
public class NfeDetalheImpostoIcmsVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "ORIGEM_MERCADORIA")
    private Integer origemMercadoria;
    @Column(name = "CST_ICMS")
    private String cstIcms;
    @Column(name = "CSOSN")
    private String csosn;
    @Column(name = "MODALIDADE_BC_ICMS")
    private Integer modalidadeBcIcms;
    @Column(name = "TAXA_REDUCAO_BC_ICMS")
    private BigDecimal taxaReducaoBcIcms;
    @Column(name = "BASE_CALCULO_ICMS")
    private BigDecimal baseCalculoIcms;
    @Column(name = "ALIQUOTA_ICMS")
    private BigDecimal aliquotaIcms;
    @Column(name = "VALOR_ICMS")
    private BigDecimal valorIcms;
    @Column(name = "VALOR_ICMS_OPERACAO")
    private BigDecimal valorIcmsOperacao;
    @Column(name = "PERCENTUAL_DIFERIMENTO")
    private BigDecimal percentualDiferimento;
    @Column(name = "VALOR_ICMS_DIFERIDO")
    private BigDecimal valorIcmsDiferido;
    @Column(name = "MOTIVO_DESONERACAO_ICMS")
    private Integer motivoDesoneracaoIcms;
    @Column(name = "VALOR_ICMS_DESONERADO")
    private BigDecimal valorIcmsDesonerado;
    @Column(name = "MODALIDADE_BC_ICMS_ST")
    private Integer modalidadeBcIcmsSt;
    @Column(name = "PERCENTUAL_MVA_ICMS_ST")
    private BigDecimal percentualMvaIcmsSt;
    @Column(name = "PERCENTUAL_REDUCAO_BC_ICMS_ST")
    private BigDecimal percentualReducaoBcIcmsSt;
    @Column(name = "VALOR_BASE_CALCULO_ICMS_ST")
    private BigDecimal valorBaseCalculoIcmsSt;
    @Column(name = "ALIQUOTA_ICMS_ST")
    private BigDecimal aliquotaIcmsSt;
    @Column(name = "VALOR_ICMS_ST")
    private BigDecimal valorIcmsSt;
    @Column(name = "VALOR_BC_ICMS_ST_RETIDO")
    private BigDecimal valorBcIcmsStRetido;
    @Column(name = "VALOR_ICMS_ST_RETIDO")
    private BigDecimal valorIcmsStRetido;
    @Column(name = "VALOR_BC_ICMS_ST_DESTINO")
    private BigDecimal valorBcIcmsStDestino;
    @Column(name = "VALOR_ICMS_ST_DESTINO")
    private BigDecimal valorIcmsStDestino;
    @Column(name = "ALIQUOTA_CREDITO_ICMS_SN")
    private BigDecimal aliquotaCreditoIcmsSn;
    @Column(name = "VALOR_CREDITO_ICMS_SN")
    private BigDecimal valorCreditoIcmsSn;
    @Column(name = "PERCENTUAL_BC_OPERACAO_PROPRIA")
    private BigDecimal percentualBcOperacaoPropria;
    @Column(name = "UF_ST")
    private String ufSt;
    @JoinColumn(name = "ID_NFE_DETALHE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private NfeDetalheVO nfeDetalhe;

    public NfeDetalheImpostoIcmsVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrigemMercadoria() {
        return origemMercadoria;
    }

    public void setOrigemMercadoria(Integer origemMercadoria) {
        this.origemMercadoria = origemMercadoria;
    }

    public String getCstIcms() {
        return cstIcms;
    }

    public void setCstIcms(String cstIcms) {
        this.cstIcms = cstIcms;
    }

    public String getCsosn() {
        return csosn;
    }

    public void setCsosn(String csosn) {
        this.csosn = csosn;
    }

    public Integer getModalidadeBcIcms() {
        return modalidadeBcIcms;
    }

    public void setModalidadeBcIcms(Integer modalidadeBcIcms) {
        this.modalidadeBcIcms = modalidadeBcIcms;
    }

    public BigDecimal getTaxaReducaoBcIcms() {
        return taxaReducaoBcIcms;
    }

    public void setTaxaReducaoBcIcms(BigDecimal taxaReducaoBcIcms) {
        this.taxaReducaoBcIcms = taxaReducaoBcIcms;
    }

    public BigDecimal getBaseCalculoIcms() {
        return baseCalculoIcms;
    }

    public void setBaseCalculoIcms(BigDecimal baseCalculoIcms) {
        this.baseCalculoIcms = baseCalculoIcms;
    }

    public BigDecimal getAliquotaIcms() {
        return aliquotaIcms;
    }

    public void setAliquotaIcms(BigDecimal aliquotaIcms) {
        this.aliquotaIcms = aliquotaIcms;
    }

    public BigDecimal getValorIcms() {
        return valorIcms;
    }

    public void setValorIcms(BigDecimal valorIcms) {
        this.valorIcms = valorIcms;
    }

    public BigDecimal getValorIcmsOperacao() {
        return valorIcmsOperacao;
    }

    public void setValorIcmsOperacao(BigDecimal valorIcmsOperacao) {
        this.valorIcmsOperacao = valorIcmsOperacao;
    }

    public BigDecimal getPercentualDiferimento() {
        return percentualDiferimento;
    }

    public void setPercentualDiferimento(BigDecimal percentualDiferimento) {
        this.percentualDiferimento = percentualDiferimento;
    }

    public BigDecimal getValorIcmsDiferido() {
        return valorIcmsDiferido;
    }

    public void setValorIcmsDiferido(BigDecimal valorIcmsDiferido) {
        this.valorIcmsDiferido = valorIcmsDiferido;
    }

    public Integer getMotivoDesoneracaoIcms() {
        return motivoDesoneracaoIcms;
    }

    public void setMotivoDesoneracaoIcms(Integer motivoDesoneracaoIcms) {
        this.motivoDesoneracaoIcms = motivoDesoneracaoIcms;
    }

    public BigDecimal getValorIcmsDesonerado() {
        return valorIcmsDesonerado;
    }

    public void setValorIcmsDesonerado(BigDecimal valorIcmsDesonerado) {
        this.valorIcmsDesonerado = valorIcmsDesonerado;
    }

    public Integer getModalidadeBcIcmsSt() {
        return modalidadeBcIcmsSt;
    }

    public void setModalidadeBcIcmsSt(Integer modalidadeBcIcmsSt) {
        this.modalidadeBcIcmsSt = modalidadeBcIcmsSt;
    }

    public BigDecimal getPercentualMvaIcmsSt() {
        return percentualMvaIcmsSt;
    }

    public void setPercentualMvaIcmsSt(BigDecimal percentualMvaIcmsSt) {
        this.percentualMvaIcmsSt = percentualMvaIcmsSt;
    }

    public BigDecimal getPercentualReducaoBcIcmsSt() {
        return percentualReducaoBcIcmsSt;
    }

    public void setPercentualReducaoBcIcmsSt(BigDecimal percentualReducaoBcIcmsSt) {
        this.percentualReducaoBcIcmsSt = percentualReducaoBcIcmsSt;
    }

    public BigDecimal getValorBaseCalculoIcmsSt() {
        return valorBaseCalculoIcmsSt;
    }

    public void setValorBaseCalculoIcmsSt(BigDecimal valorBaseCalculoIcmsSt) {
        this.valorBaseCalculoIcmsSt = valorBaseCalculoIcmsSt;
    }

    public BigDecimal getAliquotaIcmsSt() {
        return aliquotaIcmsSt;
    }

    public void setAliquotaIcmsSt(BigDecimal aliquotaIcmsSt) {
        this.aliquotaIcmsSt = aliquotaIcmsSt;
    }

    public BigDecimal getValorIcmsSt() {
        return valorIcmsSt;
    }

    public void setValorIcmsSt(BigDecimal valorIcmsSt) {
        this.valorIcmsSt = valorIcmsSt;
    }

    public BigDecimal getValorBcIcmsStRetido() {
        return valorBcIcmsStRetido;
    }

    public void setValorBcIcmsStRetido(BigDecimal valorBcIcmsStRetido) {
        this.valorBcIcmsStRetido = valorBcIcmsStRetido;
    }

    public BigDecimal getValorIcmsStRetido() {
        return valorIcmsStRetido;
    }

    public void setValorIcmsStRetido(BigDecimal valorIcmsStRetido) {
        this.valorIcmsStRetido = valorIcmsStRetido;
    }

    public BigDecimal getValorBcIcmsStDestino() {
        return valorBcIcmsStDestino;
    }

    public void setValorBcIcmsStDestino(BigDecimal valorBcIcmsStDestino) {
        this.valorBcIcmsStDestino = valorBcIcmsStDestino;
    }

    public BigDecimal getValorIcmsStDestino() {
        return valorIcmsStDestino;
    }

    public void setValorIcmsStDestino(BigDecimal valorIcmsStDestino) {
        this.valorIcmsStDestino = valorIcmsStDestino;
    }

    public BigDecimal getAliquotaCreditoIcmsSn() {
        return aliquotaCreditoIcmsSn;
    }

    public void setAliquotaCreditoIcmsSn(BigDecimal aliquotaCreditoIcmsSn) {
        this.aliquotaCreditoIcmsSn = aliquotaCreditoIcmsSn;
    }

    public BigDecimal getValorCreditoIcmsSn() {
        return valorCreditoIcmsSn;
    }

    public void setValorCreditoIcmsSn(BigDecimal valorCreditoIcmsSn) {
        this.valorCreditoIcmsSn = valorCreditoIcmsSn;
    }

    public BigDecimal getPercentualBcOperacaoPropria() {
        return percentualBcOperacaoPropria;
    }

    public void setPercentualBcOperacaoPropria(BigDecimal percentualBcOperacaoPropria) {
        this.percentualBcOperacaoPropria = percentualBcOperacaoPropria;
    }

    public String getUfSt() {
        return ufSt;
    }

    public void setUfSt(String ufSt) {
        this.ufSt = ufSt;
    }

    public NfeDetalheVO getNfeDetalhe() {
        return nfeDetalhe;
    }

    public void setNfeDetalhe(NfeDetalheVO nfeDetalhe) {
        this.nfeDetalhe = nfeDetalhe;
    }


    @Override
    public String toString() {
        return "com.t2tierp.nfe.java.NfeDetalheImpostoIcmsVO[id=" + id + "]";
    }

}
