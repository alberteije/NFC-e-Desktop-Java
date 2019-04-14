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
package com.t2tierp.contabilidade.java;

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
@Table(name = "CONTABIL_CONTA")
public class ContabilContaVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CLASSIFICACAO")
    private String classificacao;
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "DESCRICAO")
    private String descricao;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_INCLUSAO")
    private Date dataInclusao;
    @Column(name = "SITUACAO")
    private String situacao;
    @Column(name = "NATUREZA")
    private String natureza;
    @Column(name = "PATRIMONIO_RESULTADO")
    private String patrimonioResultado;
    @Column(name = "LIVRO_CAIXA")
    private String livroCaixa;
    @Column(name = "DFC")
    private String dfc;
    @Column(name = "ORDEM")
    private String ordem;
    @Column(name = "CODIGO_REDUZIDO")
    private String codigoReduzido;
    @Column(name = "CODIGO_EFD")
    private String codigoEfd;
    @JoinColumn(name = "ID_PLANO_CONTA_REF_SPED", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PlanoContaRefSpedVO planoContaRefSped;
    @JoinColumn(name = "ID_CONTABIL_CONTA", referencedColumnName = "ID")
    @ManyToOne
    private ContabilContaVO contabilConta;
    @JoinColumn(name = "ID_PLANO_CONTA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PlanoContaVO planoConta;

    public ContabilContaVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getNatureza() {
        return natureza;
    }

    public void setNatureza(String natureza) {
        this.natureza = natureza;
    }

    public String getPatrimonioResultado() {
        return patrimonioResultado;
    }

    public void setPatrimonioResultado(String patrimonioResultado) {
        this.patrimonioResultado = patrimonioResultado;
    }

    public String getLivroCaixa() {
        return livroCaixa;
    }

    public void setLivroCaixa(String livroCaixa) {
        this.livroCaixa = livroCaixa;
    }

    public String getDfc() {
        return dfc;
    }

    public void setDfc(String dfc) {
        this.dfc = dfc;
    }

    public String getOrdem() {
        return ordem;
    }

    public void setOrdem(String ordem) {
        this.ordem = ordem;
    }

    public String getCodigoReduzido() {
        return codigoReduzido;
    }

    public void setCodigoReduzido(String codigoReduzido) {
        this.codigoReduzido = codigoReduzido;
    }

    public String getCodigoEfd() {
        return codigoEfd;
    }

    public void setCodigoEfd(String codigoEfd) {
        this.codigoEfd = codigoEfd;
    }

    public PlanoContaRefSpedVO getPlanoContaRefSped() {
        return planoContaRefSped;
    }

    public void setPlanoContaRefSped(PlanoContaRefSpedVO planoContaRefSped) {
        this.planoContaRefSped = planoContaRefSped;
    }

    public ContabilContaVO getContabilConta() {
        return contabilConta;
    }

    public void setContabilConta(ContabilContaVO contabilConta) {
        this.contabilConta = contabilConta;
    }

    public PlanoContaVO getPlanoConta() {
        return planoConta;
    }

    public void setPlanoConta(PlanoContaVO planoConta) {
        this.planoConta = planoConta;
    }


    @Override
    public String toString() {
        return "com.t2tierp.contabilidade.java.ContabilContaVO[id=" + id + "]";
    }

}
