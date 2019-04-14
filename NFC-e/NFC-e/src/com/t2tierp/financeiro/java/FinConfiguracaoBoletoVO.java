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

import com.t2tierp.cadastros.java.ContaCaixaVO;
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
@Table(name = "FIN_CONFIGURACAO_BOLETO")
public class FinConfiguracaoBoletoVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "INSTRUCAO01")
    private String instrucao01;
    @Column(name = "INSTRUCAO02")
    private String instrucao02;
    @Column(name = "CAMINHO_ARQUIVO_REMESSA")
    private String caminhoArquivoRemessa;
    @Column(name = "CAMINHO_ARQUIVO_RETORNO")
    private String caminhoArquivoRetorno;
    @Column(name = "CAMINHO_ARQUIVO_LOGOTIPO")
    private String caminhoArquivoLogotipo;
    @Column(name = "CAMINHO_ARQUIVO_PDF")
    private String caminhoArquivoPdf;
    @Column(name = "MENSAGEM")
    private String mensagem;
    @Column(name = "LOCAL_PAGAMENTO")
    private String localPagamento;
    @Column(name = "LAYOUT_REMESSA")
    private String layoutRemessa;
    @Column(name = "ACEITE")
    private String aceite;
    @Column(name = "ESPECIE")
    private String especie;
    @Column(name = "CARTEIRA")
    private String carteira;
    @Column(name = "CODIGO_CONVENIO")
    private String codigoConvenio;
    @Column(name = "CODIGO_CEDENTE")
    private String codigoCedente;
    @Column(name = "TAXA_MULTA")
    private BigDecimal taxaMulta;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private EmpresaVO empresa;
    @JoinColumn(name = "ID_CONTA_CAIXA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ContaCaixaVO contaCaixa;

    public FinConfiguracaoBoletoVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInstrucao01() {
        return instrucao01;
    }

    public void setInstrucao01(String instrucao01) {
        this.instrucao01 = instrucao01;
    }

    public String getInstrucao02() {
        return instrucao02;
    }

    public void setInstrucao02(String instrucao02) {
        this.instrucao02 = instrucao02;
    }

    public String getCaminhoArquivoRemessa() {
        return caminhoArquivoRemessa;
    }

    public void setCaminhoArquivoRemessa(String caminhoArquivoRemessa) {
        this.caminhoArquivoRemessa = caminhoArquivoRemessa;
    }

    public String getCaminhoArquivoRetorno() {
        return caminhoArquivoRetorno;
    }

    public void setCaminhoArquivoRetorno(String caminhoArquivoRetorno) {
        this.caminhoArquivoRetorno = caminhoArquivoRetorno;
    }

    public String getCaminhoArquivoLogotipo() {
        return caminhoArquivoLogotipo;
    }

    public void setCaminhoArquivoLogotipo(String caminhoArquivoLogotipo) {
        this.caminhoArquivoLogotipo = caminhoArquivoLogotipo;
    }

    public String getCaminhoArquivoPdf() {
        return caminhoArquivoPdf;
    }

    public void setCaminhoArquivoPdf(String caminhoArquivoPdf) {
        this.caminhoArquivoPdf = caminhoArquivoPdf;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getLocalPagamento() {
        return localPagamento;
    }

    public void setLocalPagamento(String localPagamento) {
        this.localPagamento = localPagamento;
    }

    public String getLayoutRemessa() {
        return layoutRemessa;
    }

    public void setLayoutRemessa(String layoutRemessa) {
        this.layoutRemessa = layoutRemessa;
    }

    public String getAceite() {
        return aceite;
    }

    public void setAceite(String aceite) {
        this.aceite = aceite;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getCarteira() {
        return carteira;
    }

    public void setCarteira(String carteira) {
        this.carteira = carteira;
    }

    public String getCodigoConvenio() {
        return codigoConvenio;
    }

    public void setCodigoConvenio(String codigoConvenio) {
        this.codigoConvenio = codigoConvenio;
    }

    public String getCodigoCedente() {
        return codigoCedente;
    }

    public void setCodigoCedente(String codigoCedente) {
        this.codigoCedente = codigoCedente;
    }

    public BigDecimal getTaxaMulta() {
        return taxaMulta;
    }

    public void setTaxaMulta(BigDecimal taxaMulta) {
        this.taxaMulta = taxaMulta;
    }

    public EmpresaVO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaVO empresa) {
        this.empresa = empresa;
    }

    public ContaCaixaVO getContaCaixa() {
        return contaCaixa;
    }

    public void setContaCaixa(ContaCaixaVO contaCaixa) {
        this.contaCaixa = contaCaixa;
    }


    @Override
    public String toString() {
        return "com.t2tierp.financeiro.java.FinConfiguracaoBoletoVO[id=" + id + "]";
    }

}
