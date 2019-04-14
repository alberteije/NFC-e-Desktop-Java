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
@Table(name = "NATUREZA_FINANCEIRA")
public class NaturezaFinanceiraVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CLASSIFICACAO")
    private String classificacao;
    @Column(name = "DESCRICAO")
    private String descricao;
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "APLICACAO")
    private String aplicacao;
    @Column(name = "APARECE_A_PAGAR")
    private String apareceAPagar;
    @Column(name = "APARECE_A_RECEBER")
    private String apareceAReceber;
    @JoinColumn(name = "ID_CONTABIL_CONTA", referencedColumnName = "ID")
    @ManyToOne
    private ContabilContaVO contabilConta;
    @JoinColumn(name = "ID_PLANO_NATUREZA_FINANCEIRA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PlanoNaturezaFinanceiraVO planoNaturezaFinanceira;

    public NaturezaFinanceiraVO() {
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAplicacao() {
        return aplicacao;
    }

    public void setAplicacao(String aplicacao) {
        this.aplicacao = aplicacao;
    }

    public String getApareceAPagar() {
        return apareceAPagar;
    }

    public void setApareceAPagar(String apareceAPagar) {
        this.apareceAPagar = apareceAPagar;
    }

    public String getApareceAReceber() {
        return apareceAReceber;
    }

    public void setApareceAReceber(String apareceAReceber) {
        this.apareceAReceber = apareceAReceber;
    }

    public ContabilContaVO getContabilConta() {
        return contabilConta;
    }

    public void setContabilConta(ContabilContaVO contabilConta) {
        this.contabilConta = contabilConta;
    }

    public PlanoNaturezaFinanceiraVO getPlanoNaturezaFinanceira() {
        return planoNaturezaFinanceira;
    }

    public void setPlanoNaturezaFinanceira(PlanoNaturezaFinanceiraVO planoNaturezaFinanceira) {
        this.planoNaturezaFinanceira = planoNaturezaFinanceira;
    }


    @Override
    public String toString() {
        return "com.t2tierp.contabilidade.java.NaturezaFinanceiraVO[id=" + id + "]";
    }

}
