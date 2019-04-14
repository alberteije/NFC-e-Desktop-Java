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

import com.t2tierp.cadastros.java.TransportadoraVO;
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
@Table(name = "NFE_TRANSPORTE")
public class NfeTransporteVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "MODALIDADE_FRETE")
    private Integer modalidadeFrete;
    @Column(name = "CPF_CNPJ")
    private String cpfCnpj;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "INSCRICAO_ESTADUAL")
    private String inscricaoEstadual;
    @Column(name = "EMPRESA_ENDERECO")
    private String empresaEndereco;
    @Column(name = "NOME_MUNICIPIO")
    private String nomeMunicipio;
    @Column(name = "UF")
    private String uf;
    @Column(name = "VALOR_SERVICO")
    private BigDecimal valorServico;
    @Column(name = "VALOR_BC_RETENCAO_ICMS")
    private BigDecimal valorBcRetencaoIcms;
    @Column(name = "ALIQUOTA_RETENCAO_ICMS")
    private BigDecimal aliquotaRetencaoIcms;
    @Column(name = "VALOR_ICMS_RETIDO")
    private BigDecimal valorIcmsRetido;
    @Column(name = "CFOP")
    private Integer cfop;
    @Column(name = "MUNICIPIO")
    private Integer municipio;
    @Column(name = "PLACA_VEICULO")
    private String placaVeiculo;
    @Column(name = "UF_VEICULO")
    private String ufVeiculo;
    @Column(name = "RNTC_VEICULO")
    private String rntcVeiculo;
    @Column(name = "VAGAO")
    private String vagao;
    @Column(name = "BALSA")
    private String balsa;
    @JoinColumn(name = "ID_NFE_CABECALHO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private NfeCabecalhoVO nfeCabecalho;
    @JoinColumn(name = "ID_TRANSPORTADORA", referencedColumnName = "ID")
    @ManyToOne
    private TransportadoraVO transportadora;

    public NfeTransporteVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModalidadeFrete() {
        return modalidadeFrete;
    }

    public void setModalidadeFrete(Integer modalidadeFrete) {
        this.modalidadeFrete = modalidadeFrete;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getEmpresaEndereco() {
        return empresaEndereco;
    }

    public void setEmpresaEndereco(String empresaEndereco) {
        this.empresaEndereco = empresaEndereco;
    }

    public String getNomeMunicipio() {
        return nomeMunicipio;
    }

    public void setNomeMunicipio(String nomeMunicipio) {
        this.nomeMunicipio = nomeMunicipio;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public BigDecimal getValorServico() {
        return valorServico;
    }

    public void setValorServico(BigDecimal valorServico) {
        this.valorServico = valorServico;
    }

    public BigDecimal getValorBcRetencaoIcms() {
        return valorBcRetencaoIcms;
    }

    public void setValorBcRetencaoIcms(BigDecimal valorBcRetencaoIcms) {
        this.valorBcRetencaoIcms = valorBcRetencaoIcms;
    }

    public BigDecimal getAliquotaRetencaoIcms() {
        return aliquotaRetencaoIcms;
    }

    public void setAliquotaRetencaoIcms(BigDecimal aliquotaRetencaoIcms) {
        this.aliquotaRetencaoIcms = aliquotaRetencaoIcms;
    }

    public BigDecimal getValorIcmsRetido() {
        return valorIcmsRetido;
    }

    public void setValorIcmsRetido(BigDecimal valorIcmsRetido) {
        this.valorIcmsRetido = valorIcmsRetido;
    }

    public Integer getCfop() {
        return cfop;
    }

    public void setCfop(Integer cfop) {
        this.cfop = cfop;
    }

    public Integer getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Integer municipio) {
        this.municipio = municipio;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public String getUfVeiculo() {
        return ufVeiculo;
    }

    public void setUfVeiculo(String ufVeiculo) {
        this.ufVeiculo = ufVeiculo;
    }

    public String getRntcVeiculo() {
        return rntcVeiculo;
    }

    public void setRntcVeiculo(String rntcVeiculo) {
        this.rntcVeiculo = rntcVeiculo;
    }

    public String getVagao() {
        return vagao;
    }

    public void setVagao(String vagao) {
        this.vagao = vagao;
    }

    public String getBalsa() {
        return balsa;
    }

    public void setBalsa(String balsa) {
        this.balsa = balsa;
    }

    public NfeCabecalhoVO getNfeCabecalho() {
        return nfeCabecalho;
    }

    public void setNfeCabecalho(NfeCabecalhoVO nfeCabecalho) {
        this.nfeCabecalho = nfeCabecalho;
    }

    public TransportadoraVO getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(TransportadoraVO transportadora) {
        this.transportadora = transportadora;
    }


    @Override
    public String toString() {
        return "com.t2tierp.nfe.java.NfeTransporteVO[id=" + id + "]";
    }

}
