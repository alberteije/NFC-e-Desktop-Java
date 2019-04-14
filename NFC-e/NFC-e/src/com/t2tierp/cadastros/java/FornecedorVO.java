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
@Table(name = "FORNECEDOR")
public class FornecedorVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Temporal(TemporalType.DATE)
    @Column(name = "DESDE")
    private Date desde;
    @Column(name = "OPTANTE_SIMPLES_NACIONAL")
    private String optanteSimplesNacional;
    @Column(name = "LOCALIZACAO")
    private String localizacao;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_CADASTRO")
    private Date dataCadastro;
    @Column(name = "SOFRE_RETENCAO")
    private String sofreRetencao;
    @Column(name = "CHEQUE_NOMINAL_A")
    private String chequeNominalA;
    @Column(name = "OBSERVACAO")
    private String observacao;
    @Column(name = "CONTA_REMETENTE")
    private String contaRemetente;
    @Column(name = "PRAZO_MEDIO_ENTREGA")
    private Integer prazoMedioEntrega;
    @Column(name = "GERA_FATURAMENTO")
    private String geraFaturamento;
    @Column(name = "NUM_DIAS_PRIMEIRO_VENCIMENTO")
    private Integer numDiasPrimeiroVencimento;
    @Column(name = "NUM_DIAS_INTERVALO")
    private Integer numDiasIntervalo;
    @Column(name = "QUANTIDADE_PARCELAS")
    private Integer quantidadeParcelas;
    @Column(name = "CLASSIFICACAO_CONTABIL_CONTA")
    private String classificacaoContabilConta;
    @JoinColumn(name = "ID_SITUACAO_FOR_CLI", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private SituacaoForCliVO situacaoForCli;
    @JoinColumn(name = "ID_ATIVIDADE_FOR_CLI", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private AtividadeForCliVO atividadeForCli;
    @JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PessoaVO pessoa;

    public FornecedorVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public String getOptanteSimplesNacional() {
        return optanteSimplesNacional;
    }

    public void setOptanteSimplesNacional(String optanteSimplesNacional) {
        this.optanteSimplesNacional = optanteSimplesNacional;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getSofreRetencao() {
        return sofreRetencao;
    }

    public void setSofreRetencao(String sofreRetencao) {
        this.sofreRetencao = sofreRetencao;
    }

    public String getChequeNominalA() {
        return chequeNominalA;
    }

    public void setChequeNominalA(String chequeNominalA) {
        this.chequeNominalA = chequeNominalA;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getContaRemetente() {
        return contaRemetente;
    }

    public void setContaRemetente(String contaRemetente) {
        this.contaRemetente = contaRemetente;
    }

    public Integer getPrazoMedioEntrega() {
        return prazoMedioEntrega;
    }

    public void setPrazoMedioEntrega(Integer prazoMedioEntrega) {
        this.prazoMedioEntrega = prazoMedioEntrega;
    }

    public String getGeraFaturamento() {
        return geraFaturamento;
    }

    public void setGeraFaturamento(String geraFaturamento) {
        this.geraFaturamento = geraFaturamento;
    }

    public Integer getNumDiasPrimeiroVencimento() {
        return numDiasPrimeiroVencimento;
    }

    public void setNumDiasPrimeiroVencimento(Integer numDiasPrimeiroVencimento) {
        this.numDiasPrimeiroVencimento = numDiasPrimeiroVencimento;
    }

    public Integer getNumDiasIntervalo() {
        return numDiasIntervalo;
    }

    public void setNumDiasIntervalo(Integer numDiasIntervalo) {
        this.numDiasIntervalo = numDiasIntervalo;
    }

    public Integer getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public void setQuantidadeParcelas(Integer quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }

    public String getClassificacaoContabilConta() {
        return classificacaoContabilConta;
    }

    public void setClassificacaoContabilConta(String classificacaoContabilConta) {
        this.classificacaoContabilConta = classificacaoContabilConta;
    }

    public SituacaoForCliVO getSituacaoForCli() {
        return situacaoForCli;
    }

    public void setSituacaoForCli(SituacaoForCliVO situacaoForCli) {
        this.situacaoForCli = situacaoForCli;
    }

    public AtividadeForCliVO getAtividadeForCli() {
        return atividadeForCli;
    }

    public void setAtividadeForCli(AtividadeForCliVO atividadeForCli) {
        this.atividadeForCli = atividadeForCli;
    }

    public PessoaVO getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaVO pessoa) {
        this.pessoa = pessoa;
    }


    @Override
    public String toString() {
        return "com.t2tierp.cadastros.java.FornecedorVO[id=" + id + "]";
    }

}
