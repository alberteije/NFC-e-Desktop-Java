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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.openswing.swing.message.receive.java.ValueObjectImpl;


@Entity
@Table(name = "PLANO_CONTA_REF_SPED")
public class PlanoContaRefSpedVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "COD_CTA_REF")
    private String codCtaRef;
    @Column(name = "DESCRICAO")
    private String descricao;
    @Column(name = "ORIENTACOES")
    private String orientacoes;
    @Temporal(TemporalType.DATE)
    @Column(name = "INICIO_VALIDADE")
    private Date inicioValidade;
    @Temporal(TemporalType.DATE)
    @Column(name = "FIM_VALIDADE")
    private Date fimValidade;
    @Column(name = "TIPO")
    private String tipo;

    public PlanoContaRefSpedVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodCtaRef() {
        return codCtaRef;
    }

    public void setCodCtaRef(String codCtaRef) {
        this.codCtaRef = codCtaRef;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getOrientacoes() {
        return orientacoes;
    }

    public void setOrientacoes(String orientacoes) {
        this.orientacoes = orientacoes;
    }

    public Date getInicioValidade() {
        return inicioValidade;
    }

    public void setInicioValidade(Date inicioValidade) {
        this.inicioValidade = inicioValidade;
    }

    public Date getFimValidade() {
        return fimValidade;
    }

    public void setFimValidade(Date fimValidade) {
        this.fimValidade = fimValidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    @Override
    public String toString() {
        return "com.t2tierp.contabilidade.java.PlanoContaRefSpedVO[id=" + id + "]";
    }

}
