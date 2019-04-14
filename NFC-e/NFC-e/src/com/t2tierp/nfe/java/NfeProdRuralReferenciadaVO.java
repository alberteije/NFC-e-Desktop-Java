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
@Table(name = "NFE_PROD_RURAL_REFERENCIADA")
public class NfeProdRuralReferenciadaVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CODIGO_UF")
    private Integer codigoUf;
    @Column(name = "ANO_MES")
    private String anoMes;
    @Column(name = "CNPJ")
    private String cnpj;
    @Column(name = "CPF")
    private String cpf;
    @Column(name = "INSCRICAO_ESTADUAL")
    private String inscricaoEstadual;
    @Column(name = "MODELO")
    private String modelo;
    @Column(name = "SERIE")
    private String serie;
    @Column(name = "NUMERO_NF")
    private Integer numeroNf;
    @JoinColumn(name = "ID_NFE_CABECALHO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private NfeCabecalhoVO nfeCabecalho;

    public NfeProdRuralReferenciadaVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigoUf() {
        return codigoUf;
    }

    public void setCodigoUf(Integer codigoUf) {
        this.codigoUf = codigoUf;
    }

    public String getAnoMes() {
        return anoMes;
    }

    public void setAnoMes(String anoMes) {
        this.anoMes = anoMes;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Integer getNumeroNf() {
        return numeroNf;
    }

    public void setNumeroNf(Integer numeroNf) {
        this.numeroNf = numeroNf;
    }

    public NfeCabecalhoVO getNfeCabecalho() {
        return nfeCabecalho;
    }

    public void setNfeCabecalho(NfeCabecalhoVO nfeCabecalho) {
        this.nfeCabecalho = nfeCabecalho;
    }


    @Override
    public String toString() {
        return "com.t2tierp.nfe.java.NfeProdRuralReferenciadaVO[id=" + id + "]";
    }

}
