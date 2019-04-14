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
package com.t2tierp.cadastros.java;

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
@Table(name = "PESSOA_FISICA")
public class PessoaFisicaVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CPF")
    private String cpf;
    @Column(name = "RG")
    private String rg;
    @Column(name = "ORGAO_RG")
    private String orgaoRg;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_EMISSAO_RG")
    private Date dataEmissaoRg;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_NASCIMENTO")
    private Date dataNascimento;
    @Column(name = "SEXO")
    private String sexo;
    @Column(name = "NATURALIDADE")
    private String naturalidade;
    @Column(name = "NACIONALIDADE")
    private String nacionalidade;
    @Column(name = "RACA")
    private String raca;
    @Column(name = "TIPO_SANGUE")
    private String tipoSangue;
    @Column(name = "CNH_NUMERO")
    private String cnhNumero;
    @Column(name = "CNH_CATEGORIA")
    private String cnhCategoria;
    @Temporal(TemporalType.DATE)
    @Column(name = "CNH_VENCIMENTO")
    private Date cnhVencimento;
    @Column(name = "TITULO_ELEITORAL_NUMERO")
    private String tituloEleitoralNumero;
    @Column(name = "TITULO_ELEITORAL_ZONA")
    private Integer tituloEleitoralZona;
    @Column(name = "TITULO_ELEITORAL_SECAO")
    private Integer tituloEleitoralSecao;
    @Column(name = "RESERVISTA_NUMERO")
    private String reservistaNumero;
    @Column(name = "RESERVISTA_CATEGORIA")
    private Integer reservistaCategoria;
    @Column(name = "NOME_MAE")
    private String nomeMae;
    @Column(name = "NOME_PAI")
    private String nomePai;
    @JoinColumn(name = "ID_ESTADO_CIVIL", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private EstadoCivilVO estadoCivil;
    @JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PessoaVO pessoa;

    public PessoaFisicaVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getOrgaoRg() {
        return orgaoRg;
    }

    public void setOrgaoRg(String orgaoRg) {
        this.orgaoRg = orgaoRg;
    }

    public Date getDataEmissaoRg() {
        return dataEmissaoRg;
    }

    public void setDataEmissaoRg(Date dataEmissaoRg) {
        this.dataEmissaoRg = dataEmissaoRg;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getTipoSangue() {
        return tipoSangue;
    }

    public void setTipoSangue(String tipoSangue) {
        this.tipoSangue = tipoSangue;
    }

    public String getCnhNumero() {
        return cnhNumero;
    }

    public void setCnhNumero(String cnhNumero) {
        this.cnhNumero = cnhNumero;
    }

    public String getCnhCategoria() {
        return cnhCategoria;
    }

    public void setCnhCategoria(String cnhCategoria) {
        this.cnhCategoria = cnhCategoria;
    }

    public Date getCnhVencimento() {
        return cnhVencimento;
    }

    public void setCnhVencimento(Date cnhVencimento) {
        this.cnhVencimento = cnhVencimento;
    }

    public String getTituloEleitoralNumero() {
        return tituloEleitoralNumero;
    }

    public void setTituloEleitoralNumero(String tituloEleitoralNumero) {
        this.tituloEleitoralNumero = tituloEleitoralNumero;
    }

    public Integer getTituloEleitoralZona() {
        return tituloEleitoralZona;
    }

    public void setTituloEleitoralZona(Integer tituloEleitoralZona) {
        this.tituloEleitoralZona = tituloEleitoralZona;
    }

    public Integer getTituloEleitoralSecao() {
        return tituloEleitoralSecao;
    }

    public void setTituloEleitoralSecao(Integer tituloEleitoralSecao) {
        this.tituloEleitoralSecao = tituloEleitoralSecao;
    }

    public String getReservistaNumero() {
        return reservistaNumero;
    }

    public void setReservistaNumero(String reservistaNumero) {
        this.reservistaNumero = reservistaNumero;
    }

    public Integer getReservistaCategoria() {
        return reservistaCategoria;
    }

    public void setReservistaCategoria(Integer reservistaCategoria) {
        this.reservistaCategoria = reservistaCategoria;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public EstadoCivilVO getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivilVO estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public PessoaVO getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaVO pessoa) {
        this.pessoa = pessoa;
    }


    @Override
    public String toString() {
        return "com.t2tierp.cadastros.java.PessoaFisicaVO[id=" + id + "]";
    }

}
