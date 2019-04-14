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
@Table(name = "NFE_IMPORTACAO_DETALHE")
public class NfeImportacaoDetalheVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NUMERO_ADICAO")
    private Integer numeroAdicao;
    @Column(name = "NUMERO_SEQUENCIAL")
    private Integer numeroSequencial;
    @Column(name = "CODIGO_FABRICANTE_ESTRANGEIRO")
    private String codigoFabricanteEstrangeiro;
    @Column(name = "VALOR_DESCONTO")
    private BigDecimal valorDesconto;
    @Column(name = "DRAWBACK")
    private Integer drawback;
    @JoinColumn(name = "ID_NFE_DECLARACAO_IMPORTACAO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private NfeDeclaracaoImportacaoVO nfeDeclaracaoImportacao;

    public NfeImportacaoDetalheVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroAdicao() {
        return numeroAdicao;
    }

    public void setNumeroAdicao(Integer numeroAdicao) {
        this.numeroAdicao = numeroAdicao;
    }

    public Integer getNumeroSequencial() {
        return numeroSequencial;
    }

    public void setNumeroSequencial(Integer numeroSequencial) {
        this.numeroSequencial = numeroSequencial;
    }

    public String getCodigoFabricanteEstrangeiro() {
        return codigoFabricanteEstrangeiro;
    }

    public void setCodigoFabricanteEstrangeiro(String codigoFabricanteEstrangeiro) {
        this.codigoFabricanteEstrangeiro = codigoFabricanteEstrangeiro;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public Integer getDrawback() {
        return drawback;
    }

    public void setDrawback(Integer drawback) {
        this.drawback = drawback;
    }

    public NfeDeclaracaoImportacaoVO getNfeDeclaracaoImportacao() {
        return nfeDeclaracaoImportacao;
    }

    public void setNfeDeclaracaoImportacao(NfeDeclaracaoImportacaoVO nfeDeclaracaoImportacao) {
        this.nfeDeclaracaoImportacao = nfeDeclaracaoImportacao;
    }


    @Override
    public String toString() {
        return "com.t2tierp.nfe.java.NfeImportacaoDetalheVO[id=" + id + "]";
    }

}
