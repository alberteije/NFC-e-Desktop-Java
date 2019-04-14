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

import com.t2ti.pafecf.infra.ColunaGrid;
import com.t2tierp.tributacao.java.TributGrupoTributarioVO;
import com.t2tierp.tributacao.java.TributIcmsCustomCabVO;
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
@Table(name = "PRODUTO")
public class ProdutoVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @ColunaGrid(nome = "GTIN")
    @Column(name = "GTIN")
    private String gtin;
    @Column(name = "CODIGO_INTERNO")
    private String codigoInterno;
    @Column(name = "NCM")
    private String ncm;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "DESCRICAO")
    private String descricao;
    @ColunaGrid(nome = "Nome")
    @Column(name = "DESCRICAO_PDV")
    private String descricaoPdv;
    @Column(name = "VALOR_COMPRA")
    private BigDecimal valorCompra;
    @ColunaGrid(nome = "Valor")
    @Column(name = "VALOR_VENDA")
    private BigDecimal valorVenda;
    @Column(name = "PRECO_VENDA_MINIMO")
    private BigDecimal precoVendaMinimo;
    @Column(name = "PRECO_SUGERIDO")
    private BigDecimal precoSugerido;
    @Column(name = "CUSTO_MEDIO_LIQUIDO")
    private BigDecimal custoMedioLiquido;
    @Column(name = "PRECO_LUCRO_ZERO")
    private BigDecimal precoLucroZero;
    @Column(name = "PRECO_LUCRO_MINIMO")
    private BigDecimal precoLucroMinimo;
    @Column(name = "PRECO_LUCRO_MAXIMO")
    private BigDecimal precoLucroMaximo;
    @Column(name = "MARKUP")
    private BigDecimal markup;
    @Column(name = "QUANTIDADE_ESTOQUE")
    private BigDecimal quantidadeEstoque;
    @Column(name = "QUANTIDADE_ESTOQUE_ANTERIOR")
    private BigDecimal quantidadeEstoqueAnterior;
    @Column(name = "ESTOQUE_MINIMO")
    private BigDecimal estoqueMinimo;
    @Column(name = "ESTOQUE_MAXIMO")
    private BigDecimal estoqueMaximo;
    @Column(name = "ESTOQUE_IDEAL")
    private BigDecimal estoqueIdeal;
    @Column(name = "EXCLUIDO")
    private String excluido;
    @Column(name = "INATIVO")
    private String inativo;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_CADASTRO")
    private Date dataCadastro;
    @Column(name = "FOTO_PRODUTO")
    private String fotoProduto;
    @Column(name = "EX_TIPI")
    private String exTipi;
    @Column(name = "CODIGO_LST")
    private String codigoLst;
    @Column(name = "CLASSE_ABC")
    private String classeAbc;
    @Column(name = "IAT")
    private String iat;
    @Column(name = "IPPT")
    private String ippt;
    @Column(name = "TIPO_ITEM_SPED")
    private String tipoItemSped;
    @Column(name = "PESO")
    private BigDecimal peso;
    @Column(name = "PORCENTO_COMISSAO")
    private BigDecimal porcentoComissao;
    @Column(name = "PONTO_PEDIDO")
    private BigDecimal pontoPedido;
    @Column(name = "LOTE_ECONOMICO_COMPRA")
    private BigDecimal loteEconomicoCompra;
    @Column(name = "ALIQUOTA_ICMS_PAF")
    private BigDecimal aliquotaIcmsPaf;
    @Column(name = "ALIQUOTA_ISSQN_PAF")
    private BigDecimal aliquotaIssqnPaf;
    @Column(name = "TOTALIZADOR_PARCIAL")
    private String totalizadorParcial;
    @Column(name = "CODIGO_BALANCA")
    private Integer codigoBalanca;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_ALTERACAO")
    private Date dataAlteracao;
    @Column(name = "TIPO")
    private String tipo;
    @JoinColumn(name = "ID_UNIDADE_PRODUTO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private UnidadeProdutoVO unidadeProduto;
    @JoinColumn(name = "ID_SUB_GRUPO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ProdutoSubGrupoVO produtoSubGrupo;
    @JoinColumn(name = "ID_MARCA_PRODUTO", referencedColumnName = "ID")
    @ManyToOne
    private ProdutoMarcaVO produtoMarca;
    @JoinColumn(name = "ID_GRUPO_TRIBUTARIO", referencedColumnName = "ID")
    @ManyToOne
    private TributGrupoTributarioVO tributGrupoTributario;
    @JoinColumn(name = "ID_ALMOXARIFADO", referencedColumnName = "ID")
    @ManyToOne
    private AlmoxarifadoVO almoxarifado;
    @JoinColumn(name = "ID_TRIBUT_ICMS_CUSTOM_CAB", referencedColumnName = "ID")
    @ManyToOne
    private TributIcmsCustomCabVO tributIcmsCustomCab;

    public ProdutoVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGtin() {
        return gtin;
    }

    public void setGtin(String gtin) {
        this.gtin = gtin;
    }

    public String getCodigoInterno() {
        return codigoInterno;
    }

    public void setCodigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
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

    public String getDescricaoPdv() {
        return descricaoPdv;
    }

    public void setDescricaoPdv(String descricaoPdv) {
        this.descricaoPdv = descricaoPdv;
    }

    public BigDecimal getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(BigDecimal valorCompra) {
        this.valorCompra = valorCompra;
    }

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(BigDecimal valorVenda) {
        this.valorVenda = valorVenda;
    }

    public BigDecimal getPrecoVendaMinimo() {
        return precoVendaMinimo;
    }

    public void setPrecoVendaMinimo(BigDecimal precoVendaMinimo) {
        this.precoVendaMinimo = precoVendaMinimo;
    }

    public BigDecimal getPrecoSugerido() {
        return precoSugerido;
    }

    public void setPrecoSugerido(BigDecimal precoSugerido) {
        this.precoSugerido = precoSugerido;
    }

    public BigDecimal getCustoMedioLiquido() {
        return custoMedioLiquido;
    }

    public void setCustoMedioLiquido(BigDecimal custoMedioLiquido) {
        this.custoMedioLiquido = custoMedioLiquido;
    }

    public BigDecimal getPrecoLucroZero() {
        return precoLucroZero;
    }

    public void setPrecoLucroZero(BigDecimal precoLucroZero) {
        this.precoLucroZero = precoLucroZero;
    }

    public BigDecimal getPrecoLucroMinimo() {
        return precoLucroMinimo;
    }

    public void setPrecoLucroMinimo(BigDecimal precoLucroMinimo) {
        this.precoLucroMinimo = precoLucroMinimo;
    }

    public BigDecimal getPrecoLucroMaximo() {
        return precoLucroMaximo;
    }

    public void setPrecoLucroMaximo(BigDecimal precoLucroMaximo) {
        this.precoLucroMaximo = precoLucroMaximo;
    }

    public BigDecimal getMarkup() {
        return markup;
    }

    public void setMarkup(BigDecimal markup) {
        this.markup = markup;
    }

    public BigDecimal getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(BigDecimal quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public BigDecimal getQuantidadeEstoqueAnterior() {
        return quantidadeEstoqueAnterior;
    }

    public void setQuantidadeEstoqueAnterior(BigDecimal quantidadeEstoqueAnterior) {
        this.quantidadeEstoqueAnterior = quantidadeEstoqueAnterior;
    }

    public BigDecimal getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(BigDecimal estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public BigDecimal getEstoqueMaximo() {
        return estoqueMaximo;
    }

    public void setEstoqueMaximo(BigDecimal estoqueMaximo) {
        this.estoqueMaximo = estoqueMaximo;
    }

    public BigDecimal getEstoqueIdeal() {
        return estoqueIdeal;
    }

    public void setEstoqueIdeal(BigDecimal estoqueIdeal) {
        this.estoqueIdeal = estoqueIdeal;
    }

    public String getExcluido() {
        return excluido;
    }

    public void setExcluido(String excluido) {
        this.excluido = excluido;
    }

    public String getInativo() {
        return inativo;
    }

    public void setInativo(String inativo) {
        this.inativo = inativo;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getFotoProduto() {
        return fotoProduto;
    }

    public void setFotoProduto(String fotoProduto) {
        this.fotoProduto = fotoProduto;
    }

    public String getExTipi() {
        return exTipi;
    }

    public void setExTipi(String exTipi) {
        this.exTipi = exTipi;
    }

    public String getCodigoLst() {
        return codigoLst;
    }

    public void setCodigoLst(String codigoLst) {
        this.codigoLst = codigoLst;
    }

    public String getClasseAbc() {
        return classeAbc;
    }

    public void setClasseAbc(String classeAbc) {
        this.classeAbc = classeAbc;
    }

    public String getIat() {
        return iat;
    }

    public void setIat(String iat) {
        this.iat = iat;
    }

    public String getIppt() {
        return ippt;
    }

    public void setIppt(String ippt) {
        this.ippt = ippt;
    }

    public String getTipoItemSped() {
        return tipoItemSped;
    }

    public void setTipoItemSped(String tipoItemSped) {
        this.tipoItemSped = tipoItemSped;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public BigDecimal getPorcentoComissao() {
        return porcentoComissao;
    }

    public void setPorcentoComissao(BigDecimal porcentoComissao) {
        this.porcentoComissao = porcentoComissao;
    }

    public BigDecimal getPontoPedido() {
        return pontoPedido;
    }

    public void setPontoPedido(BigDecimal pontoPedido) {
        this.pontoPedido = pontoPedido;
    }

    public BigDecimal getLoteEconomicoCompra() {
        return loteEconomicoCompra;
    }

    public void setLoteEconomicoCompra(BigDecimal loteEconomicoCompra) {
        this.loteEconomicoCompra = loteEconomicoCompra;
    }

    public BigDecimal getAliquotaIcmsPaf() {
        return aliquotaIcmsPaf;
    }

    public void setAliquotaIcmsPaf(BigDecimal aliquotaIcmsPaf) {
        this.aliquotaIcmsPaf = aliquotaIcmsPaf;
    }

    public BigDecimal getAliquotaIssqnPaf() {
        return aliquotaIssqnPaf;
    }

    public void setAliquotaIssqnPaf(BigDecimal aliquotaIssqnPaf) {
        this.aliquotaIssqnPaf = aliquotaIssqnPaf;
    }

    public String getTotalizadorParcial() {
        return totalizadorParcial;
    }

    public void setTotalizadorParcial(String totalizadorParcial) {
        this.totalizadorParcial = totalizadorParcial;
    }

    public Integer getCodigoBalanca() {
        return codigoBalanca;
    }

    public void setCodigoBalanca(Integer codigoBalanca) {
        this.codigoBalanca = codigoBalanca;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public UnidadeProdutoVO getUnidadeProduto() {
        return unidadeProduto;
    }

    public void setUnidadeProduto(UnidadeProdutoVO unidadeProduto) {
        this.unidadeProduto = unidadeProduto;
    }

    public ProdutoSubGrupoVO getProdutoSubGrupo() {
        return produtoSubGrupo;
    }

    public void setProdutoSubGrupo(ProdutoSubGrupoVO produtoSubGrupo) {
        this.produtoSubGrupo = produtoSubGrupo;
    }

    public ProdutoMarcaVO getProdutoMarca() {
        return produtoMarca;
    }

    public void setProdutoMarca(ProdutoMarcaVO produtoMarca) {
        this.produtoMarca = produtoMarca;
    }

    public TributGrupoTributarioVO getTributGrupoTributario() {
        return tributGrupoTributario;
    }

    public void setTributGrupoTributario(TributGrupoTributarioVO tributGrupoTributario) {
        this.tributGrupoTributario = tributGrupoTributario;
    }

    public AlmoxarifadoVO getAlmoxarifado() {
        return almoxarifado;
    }

    public void setAlmoxarifado(AlmoxarifadoVO almoxarifado) {
        this.almoxarifado = almoxarifado;
    }

    public TributIcmsCustomCabVO getTributIcmsCustomCab() {
        return tributIcmsCustomCab;
    }

    public void setTributIcmsCustomCab(TributIcmsCustomCabVO tributIcmsCustomCab) {
        this.tributIcmsCustomCab = tributIcmsCustomCab;
    }


    @Override
    public String toString() {
        return "com.t2tierp.cadastros.java.ProdutoVO[id=" + id + "]";
    }

}
