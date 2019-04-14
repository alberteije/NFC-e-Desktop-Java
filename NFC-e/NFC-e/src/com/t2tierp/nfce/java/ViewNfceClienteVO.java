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
package com.t2tierp.nfce.java;

import com.t2ti.pafecf.infra.ColunaGrid;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "VIEW_NFCE_CLIENTE")
public class ViewNfceClienteVO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "id_operacao_fiscal")
    private Integer idOperacaoFiscal;
    @ColunaGrid(nome = "Nome")
    @Column(name = "nome")
    private String nome;
    @Column(name = "email")
    private String email;
    @ColunaGrid(nome = "CPF")
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "rg")
    private String rg;
    @Column(name = "orgao_rg")
    private String orgaoRg;
    @Column(name = "data_emissao_rg")
    @Temporal(TemporalType.DATE)
    private Date dataEmissaoRg;
    @Column(name = "sexo")
    private Character sexo;
    @Column(name = "data_cadastro")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    @Column(name = "id_pessoa")
    private Integer idPessoa;
    @Column(name = "id_atividade_for_cli")
    private Integer idAtividadeForCli;
    @Column(name = "id_situacao_for_cli")
    private Integer idSituacaoForCli;
    @Column(name = "desde")
    @Temporal(TemporalType.DATE)
    private Date desde;
    @Column(name = "logradouro")
    private String logradouro;
    @Column(name = "numero")
    private String numero;
    @Column(name = "complemento")
    private String complemento;
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "cidade")
    private String cidade;
    @Column(name = "cep")
    private String cep;
    @Column(name = "municipio_ibge")
    private Integer municipioIbge;
    @Column(name = "uf")
    private String uf;
    @Column(name = "fone")
    private String fone;

    public ViewNfceClienteVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdOperacaoFiscal() {
        return idOperacaoFiscal;
    }

    public void setIdOperacaoFiscal(Integer idOperacaoFiscal) {
        this.idOperacaoFiscal = idOperacaoFiscal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Integer getIdAtividadeForCli() {
        return idAtividadeForCli;
    }

    public void setIdAtividadeForCli(Integer idAtividadeForCli) {
        this.idAtividadeForCli = idAtividadeForCli;
    }

    public Integer getIdSituacaoForCli() {
        return idSituacaoForCli;
    }

    public void setIdSituacaoForCli(Integer idSituacaoForCli) {
        this.idSituacaoForCli = idSituacaoForCli;
    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getMunicipioIbge() {
        return municipioIbge;
    }

    public void setMunicipioIbge(Integer municipioIbge) {
        this.municipioIbge = municipioIbge;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }
    
}
