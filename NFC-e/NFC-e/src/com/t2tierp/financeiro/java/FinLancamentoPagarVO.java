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
package com.t2tierp.financeiro.java;

import com.t2tierp.cadastros.java.FornecedorVO;
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
@Table(name = "FIN_LANCAMENTO_PAGAR")
public class FinLancamentoPagarVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "PAGAMENTO_COMPARTILHADO")
    private String pagamentoCompartilhado;
    @Column(name = "QUANTIDADE_PARCELA")
    private Integer quantidadeParcela;
    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;
    @Column(name = "VALOR_A_PAGAR")
    private BigDecimal valorAPagar;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_LANCAMENTO")
    private Date dataLancamento;
    @Column(name = "NUMERO_DOCUMENTO")
    private String numeroDocumento;
    @Column(name = "IMAGEM_DOCUMENTO")
    private String imagemDocumento;
    @Temporal(TemporalType.DATE)
    @Column(name = "PRIMEIRO_VENCIMENTO")
    private Date primeiroVencimento;
    @Column(name = "CODIGO_MODULO_LCTO")
    private String codigoModuloLcto;
    @Column(name = "INTERVALO_ENTRE_PARCELAS")
    private Integer intervaloEntreParcelas;
    @JoinColumn(name = "ID_FIN_DOCUMENTO_ORIGEM", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private FinDocumentoOrigemVO finDocumentoOrigem;
    @JoinColumn(name = "ID_FORNECEDOR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private FornecedorVO fornecedor;

    public FinLancamentoPagarVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPagamentoCompartilhado() {
        return pagamentoCompartilhado;
    }

    public void setPagamentoCompartilhado(String pagamentoCompartilhado) {
        this.pagamentoCompartilhado = pagamentoCompartilhado;
    }

    public Integer getQuantidadeParcela() {
        return quantidadeParcela;
    }

    public void setQuantidadeParcela(Integer quantidadeParcela) {
        this.quantidadeParcela = quantidadeParcela;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorAPagar() {
        return valorAPagar;
    }

    public void setValorAPagar(BigDecimal valorAPagar) {
        this.valorAPagar = valorAPagar;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getImagemDocumento() {
        return imagemDocumento;
    }

    public void setImagemDocumento(String imagemDocumento) {
        this.imagemDocumento = imagemDocumento;
    }

    public Date getPrimeiroVencimento() {
        return primeiroVencimento;
    }

    public void setPrimeiroVencimento(Date primeiroVencimento) {
        this.primeiroVencimento = primeiroVencimento;
    }

    public String getCodigoModuloLcto() {
        return codigoModuloLcto;
    }

    public void setCodigoModuloLcto(String codigoModuloLcto) {
        this.codigoModuloLcto = codigoModuloLcto;
    }

    public Integer getIntervaloEntreParcelas() {
        return intervaloEntreParcelas;
    }

    public void setIntervaloEntreParcelas(Integer intervaloEntreParcelas) {
        this.intervaloEntreParcelas = intervaloEntreParcelas;
    }

    public FinDocumentoOrigemVO getFinDocumentoOrigem() {
        return finDocumentoOrigem;
    }

    public void setFinDocumentoOrigem(FinDocumentoOrigemVO finDocumentoOrigem) {
        this.finDocumentoOrigem = finDocumentoOrigem;
    }

    public FornecedorVO getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(FornecedorVO fornecedor) {
        this.fornecedor = fornecedor;
    }


    @Override
    public String toString() {
        return "com.t2tierp.financeiro.java.FinLancamentoPagarVO[id=" + id + "]";
    }

}
