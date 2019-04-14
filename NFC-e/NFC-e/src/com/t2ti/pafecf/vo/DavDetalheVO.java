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
package com.t2ti.pafecf.vo;

import com.t2ti.pafecf.infra.ColunaGrid;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "DAV_DETALHE")
public class DavDetalheVO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NUMERO_DAV")
    private String numeroDav;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_EMISSAO")
    private Date dataEmissao;
    @ColunaGrid(nome = "Nr. Item")
    @Column(name = "ITEM")
    private Integer item;
    @ColunaGrid(nome = "QTDE")
    @Column(name = "QUANTIDADE")
    private BigDecimal quantidade;
    @ColunaGrid(nome = "Valor Unitário")
    @Column(name = "VALOR_UNITARIO")
    private BigDecimal valorUnitario;
    @ColunaGrid(nome = "Valor Total")
    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;
    @ColunaGrid(nome = "Cancelado")
    @Column(name = "CANCELADO")
    private String cancelado;
    @Column(name = "MESCLA_PRODUTO")
    private String mesclaProduto;
    @Column(name = "GTIN_PRODUTO")
    private String gtinProduto;
    @ColunaGrid(nome = "Nome Produto")
    @Column(name = "NOME_PRODUTO")
    private String nomeProduto;
    @Column(name = "UNIDADE_PRODUTO")
    private String unidadeProduto;
    @Column(name = "TOTALIZADOR_PARCIAL")
    private String totalizadorParcial;
    @Column(name = "LOGSS")
    private String logss;
    @JoinColumn(name = "ID_DAV_CABECALHO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private DavCabecalhoVO davCabecalho;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "davDetalhe", cascade = CascadeType.ALL)
    @OrderBy
    private List<DavDetalheAlteracaoVO> listaDavDetalheAlteracao;

    public DavDetalheVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroDav() {
        return numeroDav;
    }

    public void setNumeroDav(String numeroDav) {
        this.numeroDav = numeroDav;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getCancelado() {
        return cancelado;
    }

    public void setCancelado(String cancelado) {
        this.cancelado = cancelado;
    }

    public String getMesclaProduto() {
        return mesclaProduto;
    }

    public void setMesclaProduto(String mesclaProduto) {
        this.mesclaProduto = mesclaProduto;
    }

    public String getGtinProduto() {
        return gtinProduto;
    }

    public void setGtinProduto(String gtinProduto) {
        this.gtinProduto = gtinProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getUnidadeProduto() {
        return unidadeProduto;
    }

    public void setUnidadeProduto(String unidadeProduto) {
        this.unidadeProduto = unidadeProduto;
    }

    public String getTotalizadorParcial() {
        return totalizadorParcial;
    }

    public void setTotalizadorParcial(String totalizadorParcial) {
        this.totalizadorParcial = totalizadorParcial;
    }

    public String getLogss() {
        return logss;
    }

    public void setLogss(String logss) {
        this.logss = logss;
    }

    public DavCabecalhoVO getDavCabecalho() {
        return davCabecalho;
    }

    public void setDavCabecalho(DavCabecalhoVO davCabecalho) {
        this.davCabecalho = davCabecalho;
    }

    public List<DavDetalheAlteracaoVO> getListaDavDetalheAlteracao() {
        return listaDavDetalheAlteracao;
    }

    public void setListaDavDetalheAlteracao(List<DavDetalheAlteracaoVO> listaDavDetalheAlteracao) {
        this.listaDavDetalheAlteracao = listaDavDetalheAlteracao;
    }

    @Override
    public String toString() {
        return "com.t2tierp.pafecf.java.DavDetalheVO[id=" + id + "]";
    }

}
