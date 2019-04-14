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
package com.t2tierp.nfce.java;

import com.t2tierp.cadastros.java.EmpresaVO;
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
@Table(name = "NFCE_CONFIGURACAO")
public class NfceConfiguracaoVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "MENSAGEM_CUPOM")
    private String mensagemCupom;
    @Column(name = "TITULO_TELA_CAIXA")
    private String tituloTelaCaixa;
    @Column(name = "CAMINHO_IMAGENS_PRODUTOS")
    private String caminhoImagensProdutos;
    @Column(name = "CAMINHO_IMAGENS_MARKETING")
    private String caminhoImagensMarketing;
    @Column(name = "CAMINHO_IMAGENS_LAYOUT")
    private String caminhoImagensLayout;
    @Column(name = "COR_JANELAS_INTERNAS")
    private String corJanelasInternas;
    @Column(name = "MARKETING_ATIVO")
    private String marketingAtivo;
    @Column(name = "CFOP")
    private Integer cfop;
    @Column(name = "DECIMAIS_QUANTIDADE")
    private Integer decimaisQuantidade;
    @Column(name = "DECIMAIS_VALOR")
    private Integer decimaisValor;
    @Column(name = "QUANTIDADE_MAXIMA_PARCELA")
    private Integer quantidadeMaximaParcela;
    @Column(name = "IMPRIME_PARCELA")
    private String imprimeParcela;
    @JoinColumn(name = "ID_NFCE_RESOLUCAO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private NfceResolucaoVO nfceResolucao;
    @JoinColumn(name = "ID_NFCE_CAIXA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private NfceCaixaVO nfceCaixa;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private EmpresaVO empresa;

    public NfceConfiguracaoVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMensagemCupom() {
        return mensagemCupom;
    }

    public void setMensagemCupom(String mensagemCupom) {
        this.mensagemCupom = mensagemCupom;
    }

    public String getTituloTelaCaixa() {
        return tituloTelaCaixa;
    }

    public void setTituloTelaCaixa(String tituloTelaCaixa) {
        this.tituloTelaCaixa = tituloTelaCaixa;
    }

    public String getCaminhoImagensProdutos() {
        return caminhoImagensProdutos;
    }

    public void setCaminhoImagensProdutos(String caminhoImagensProdutos) {
        this.caminhoImagensProdutos = caminhoImagensProdutos;
    }

    public String getCaminhoImagensMarketing() {
        return caminhoImagensMarketing;
    }

    public void setCaminhoImagensMarketing(String caminhoImagensMarketing) {
        this.caminhoImagensMarketing = caminhoImagensMarketing;
    }

    public String getCaminhoImagensLayout() {
        return caminhoImagensLayout;
    }

    public void setCaminhoImagensLayout(String caminhoImagensLayout) {
        this.caminhoImagensLayout = caminhoImagensLayout;
    }

    public String getCorJanelasInternas() {
        return corJanelasInternas;
    }

    public void setCorJanelasInternas(String corJanelasInternas) {
        this.corJanelasInternas = corJanelasInternas;
    }

    public String getMarketingAtivo() {
        return marketingAtivo;
    }

    public void setMarketingAtivo(String marketingAtivo) {
        this.marketingAtivo = marketingAtivo;
    }

    public Integer getCfop() {
        return cfop;
    }

    public void setCfop(Integer cfop) {
        this.cfop = cfop;
    }

    public Integer getDecimaisQuantidade() {
        return decimaisQuantidade;
    }

    public void setDecimaisQuantidade(Integer decimaisQuantidade) {
        this.decimaisQuantidade = decimaisQuantidade;
    }

    public Integer getDecimaisValor() {
        return decimaisValor;
    }

    public void setDecimaisValor(Integer decimaisValor) {
        this.decimaisValor = decimaisValor;
    }

    public Integer getQuantidadeMaximaParcela() {
        return quantidadeMaximaParcela;
    }

    public void setQuantidadeMaximaParcela(Integer quantidadeMaximaParcela) {
        this.quantidadeMaximaParcela = quantidadeMaximaParcela;
    }

    public String getImprimeParcela() {
        return imprimeParcela;
    }

    public void setImprimeParcela(String imprimeParcela) {
        this.imprimeParcela = imprimeParcela;
    }

    public NfceResolucaoVO getNfceResolucao() {
        return nfceResolucao;
    }

    public void setNfceResolucao(NfceResolucaoVO nfceResolucao) {
        this.nfceResolucao = nfceResolucao;
    }

    public NfceCaixaVO getNfceCaixa() {
        return nfceCaixa;
    }

    public void setNfceCaixa(NfceCaixaVO nfceCaixa) {
        this.nfceCaixa = nfceCaixa;
    }

    public EmpresaVO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaVO empresa) {
        this.empresa = empresa;
    }


    @Override
    public String toString() {
        return "com.t2tierp.nfce.java.NfceConfiguracaoVO[id=" + id + "]";
    }

}
