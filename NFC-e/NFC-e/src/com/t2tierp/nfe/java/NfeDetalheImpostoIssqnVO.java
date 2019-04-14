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
@Table(name = "NFE_DETALHE_IMPOSTO_ISSQN")
public class NfeDetalheImpostoIssqnVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "BASE_CALCULO_ISSQN")
    private BigDecimal baseCalculoIssqn;
    @Column(name = "ALIQUOTA_ISSQN")
    private BigDecimal aliquotaIssqn;
    @Column(name = "VALOR_ISSQN")
    private BigDecimal valorIssqn;
    @Column(name = "MUNICIPIO_ISSQN")
    private Integer municipioIssqn;
    @Column(name = "ITEM_LISTA_SERVICOS")
    private Integer itemListaServicos;
    @Column(name = "VALOR_DEDUCAO")
    private BigDecimal valorDeducao;
    @Column(name = "VALOR_OUTRAS_RETENCOES")
    private BigDecimal valorOutrasRetencoes;
    @Column(name = "VALOR_DESCONTO_INCONDICIONADO")
    private BigDecimal valorDescontoIncondicionado;
    @Column(name = "VALOR_DESCONTO_CONDICIONADO")
    private BigDecimal valorDescontoCondicionado;
    @Column(name = "VALOR_RETENCAO_ISS")
    private BigDecimal valorRetencaoIss;
    @Column(name = "INDICADOR_EXIGIBILIDADE_ISS")
    private Integer indicadorExigibilidadeIss;
    @Column(name = "CODIGO_SERVICO")
    private String codigoServico;
    @Column(name = "MUNICIPIO_INCIDENCIA")
    private Integer municipioIncidencia;
    @Column(name = "PAIS_SEVICO_PRESTADO")
    private Integer paisSevicoPrestado;
    @Column(name = "NUMERO_PROCESSO")
    private String numeroProcesso;
    @Column(name = "INDICADOR_INCENTIVO_FISCAL")
    private Integer indicadorIncentivoFiscal;
    @JoinColumn(name = "ID_NFE_DETALHE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private NfeDetalheVO nfeDetalhe;

    public NfeDetalheImpostoIssqnVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getBaseCalculoIssqn() {
        return baseCalculoIssqn;
    }

    public void setBaseCalculoIssqn(BigDecimal baseCalculoIssqn) {
        this.baseCalculoIssqn = baseCalculoIssqn;
    }

    public BigDecimal getAliquotaIssqn() {
        return aliquotaIssqn;
    }

    public void setAliquotaIssqn(BigDecimal aliquotaIssqn) {
        this.aliquotaIssqn = aliquotaIssqn;
    }

    public BigDecimal getValorIssqn() {
        return valorIssqn;
    }

    public void setValorIssqn(BigDecimal valorIssqn) {
        this.valorIssqn = valorIssqn;
    }

    public Integer getMunicipioIssqn() {
        return municipioIssqn;
    }

    public void setMunicipioIssqn(Integer municipioIssqn) {
        this.municipioIssqn = municipioIssqn;
    }

    public Integer getItemListaServicos() {
        return itemListaServicos;
    }

    public void setItemListaServicos(Integer itemListaServicos) {
        this.itemListaServicos = itemListaServicos;
    }

    public BigDecimal getValorDeducao() {
        return valorDeducao;
    }

    public void setValorDeducao(BigDecimal valorDeducao) {
        this.valorDeducao = valorDeducao;
    }

    public BigDecimal getValorOutrasRetencoes() {
        return valorOutrasRetencoes;
    }

    public void setValorOutrasRetencoes(BigDecimal valorOutrasRetencoes) {
        this.valorOutrasRetencoes = valorOutrasRetencoes;
    }

    public BigDecimal getValorDescontoIncondicionado() {
        return valorDescontoIncondicionado;
    }

    public void setValorDescontoIncondicionado(BigDecimal valorDescontoIncondicionado) {
        this.valorDescontoIncondicionado = valorDescontoIncondicionado;
    }

    public BigDecimal getValorDescontoCondicionado() {
        return valorDescontoCondicionado;
    }

    public void setValorDescontoCondicionado(BigDecimal valorDescontoCondicionado) {
        this.valorDescontoCondicionado = valorDescontoCondicionado;
    }

    public BigDecimal getValorRetencaoIss() {
        return valorRetencaoIss;
    }

    public void setValorRetencaoIss(BigDecimal valorRetencaoIss) {
        this.valorRetencaoIss = valorRetencaoIss;
    }

    public Integer getIndicadorExigibilidadeIss() {
        return indicadorExigibilidadeIss;
    }

    public void setIndicadorExigibilidadeIss(Integer indicadorExigibilidadeIss) {
        this.indicadorExigibilidadeIss = indicadorExigibilidadeIss;
    }

    public String getCodigoServico() {
        return codigoServico;
    }

    public void setCodigoServico(String codigoServico) {
        this.codigoServico = codigoServico;
    }

    public Integer getMunicipioIncidencia() {
        return municipioIncidencia;
    }

    public void setMunicipioIncidencia(Integer municipioIncidencia) {
        this.municipioIncidencia = municipioIncidencia;
    }

    public Integer getPaisSevicoPrestado() {
        return paisSevicoPrestado;
    }

    public void setPaisSevicoPrestado(Integer paisSevicoPrestado) {
        this.paisSevicoPrestado = paisSevicoPrestado;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public Integer getIndicadorIncentivoFiscal() {
        return indicadorIncentivoFiscal;
    }

    public void setIndicadorIncentivoFiscal(Integer indicadorIncentivoFiscal) {
        this.indicadorIncentivoFiscal = indicadorIncentivoFiscal;
    }

    public NfeDetalheVO getNfeDetalhe() {
        return nfeDetalhe;
    }

    public void setNfeDetalhe(NfeDetalheVO nfeDetalhe) {
        this.nfeDetalhe = nfeDetalhe;
    }


    @Override
    public String toString() {
        return "com.t2tierp.nfe.java.NfeDetalheImpostoIssqnVO[id=" + id + "]";
    }

}
