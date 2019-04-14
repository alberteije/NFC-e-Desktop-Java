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

import com.t2tierp.tributacao.java.TributOperacaoFiscalVO;
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
@Table(name = "CLIENTE")
public class ClienteVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Temporal(TemporalType.DATE)
    @Column(name = "DESDE")
    private Date desde;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_CADASTRO")
    private Date dataCadastro;
    @Column(name = "OBSERVACAO")
    private String observacao;
    @Column(name = "CONTA_TOMADOR")
    private String contaTomador;
    @Column(name = "GERA_FINANCEIRO")
    private String geraFinanceiro;
    @Column(name = "INDICADOR_PRECO")
    private String indicadorPreco;
    @Column(name = "PORCENTO_DESCONTO")
    private BigDecimal porcentoDesconto;
    @Column(name = "FORMA_DESCONTO")
    private String formaDesconto;
    @Column(name = "LIMITE_CREDITO")
    private BigDecimal limiteCredito;
    @Column(name = "TIPO_FRETE")
    private String tipoFrete;
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
    @JoinColumn(name = "ID_OPERACAO_FISCAL", referencedColumnName = "ID")
    @ManyToOne
    private TributOperacaoFiscalVO tributOperacaoFiscal;

    public ClienteVO() {
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

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getContaTomador() {
        return contaTomador;
    }

    public void setContaTomador(String contaTomador) {
        this.contaTomador = contaTomador;
    }

    public String getGeraFinanceiro() {
        return geraFinanceiro;
    }

    public void setGeraFinanceiro(String geraFinanceiro) {
        this.geraFinanceiro = geraFinanceiro;
    }

    public String getIndicadorPreco() {
        return indicadorPreco;
    }

    public void setIndicadorPreco(String indicadorPreco) {
        this.indicadorPreco = indicadorPreco;
    }

    public BigDecimal getPorcentoDesconto() {
        return porcentoDesconto;
    }

    public void setPorcentoDesconto(BigDecimal porcentoDesconto) {
        this.porcentoDesconto = porcentoDesconto;
    }

    public String getFormaDesconto() {
        return formaDesconto;
    }

    public void setFormaDesconto(String formaDesconto) {
        this.formaDesconto = formaDesconto;
    }

    public BigDecimal getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(BigDecimal limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public String getTipoFrete() {
        return tipoFrete;
    }

    public void setTipoFrete(String tipoFrete) {
        this.tipoFrete = tipoFrete;
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

    public TributOperacaoFiscalVO getTributOperacaoFiscal() {
        return tributOperacaoFiscal;
    }

    public void setTributOperacaoFiscal(TributOperacaoFiscalVO tributOperacaoFiscal) {
        this.tributOperacaoFiscal = tributOperacaoFiscal;
    }


    @Override
    public String toString() {
        return "com.t2tierp.cadastros.java.ClienteVO[id=" + id + "]";
    }

}
