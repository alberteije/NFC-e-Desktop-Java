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
package com.t2tierp.vendas.java;

import com.t2tierp.cadastros.java.EmpresaVO;
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
@Table(name = "CONDICOES_PAGAMENTO")
public class CondicoesPagamentoVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "DESCRICAO")
    private String descricao;
    @Column(name = "FATURAMENTO_MINIMO")
    private BigDecimal faturamentoMinimo;
    @Column(name = "FATURAMENTO_MAXIMO")
    private BigDecimal faturamentoMaximo;
    @Column(name = "INDICE_CORRECAO")
    private BigDecimal indiceCorrecao;
    @Column(name = "DIAS_TOLERANCIA")
    private Integer diasTolerancia;
    @Column(name = "VALOR_TOLERANCIA")
    private BigDecimal valorTolerancia;
    @Column(name = "PRAZO_MEDIO")
    private Integer prazoMedio;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private EmpresaVO empresa;

    public CondicoesPagamentoVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getFaturamentoMinimo() {
        return faturamentoMinimo;
    }

    public void setFaturamentoMinimo(BigDecimal faturamentoMinimo) {
        this.faturamentoMinimo = faturamentoMinimo;
    }

    public BigDecimal getFaturamentoMaximo() {
        return faturamentoMaximo;
    }

    public void setFaturamentoMaximo(BigDecimal faturamentoMaximo) {
        this.faturamentoMaximo = faturamentoMaximo;
    }

    public BigDecimal getIndiceCorrecao() {
        return indiceCorrecao;
    }

    public void setIndiceCorrecao(BigDecimal indiceCorrecao) {
        this.indiceCorrecao = indiceCorrecao;
    }

    public Integer getDiasTolerancia() {
        return diasTolerancia;
    }

    public void setDiasTolerancia(Integer diasTolerancia) {
        this.diasTolerancia = diasTolerancia;
    }

    public BigDecimal getValorTolerancia() {
        return valorTolerancia;
    }

    public void setValorTolerancia(BigDecimal valorTolerancia) {
        this.valorTolerancia = valorTolerancia;
    }

    public Integer getPrazoMedio() {
        return prazoMedio;
    }

    public void setPrazoMedio(Integer prazoMedio) {
        this.prazoMedio = prazoMedio;
    }

    public EmpresaVO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaVO empresa) {
        this.empresa = empresa;
    }


    @Override
    public String toString() {
        return "com.t2tierp.vendas.java.CondicoesPagamentoVO[id=" + id + "]";
    }

}
