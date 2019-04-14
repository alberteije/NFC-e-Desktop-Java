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
@Table(name = "COLABORADOR")
public class ColaboradorVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "MATRICULA")
    private String matricula;
    @Column(name = "FOTO_34")
    private String foto34;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_CADASTRO")
    private Date dataCadastro;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_ADMISSAO")
    private Date dataAdmissao;
    @Temporal(TemporalType.DATE)
    @Column(name = "VENCIMENTO_FERIAS")
    private Date vencimentoFerias;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_TRANSFERENCIA")
    private Date dataTransferencia;
    @Column(name = "FGTS_OPTANTE")
    private String fgtsOptante;
    @Temporal(TemporalType.DATE)
    @Column(name = "FGTS_DATA_OPCAO")
    private Date fgtsDataOpcao;
    @Column(name = "FGTS_CONTA")
    private Integer fgtsConta;
    @Column(name = "PAGAMENTO_FORMA")
    private String pagamentoForma;
    @Column(name = "PAGAMENTO_BANCO")
    private String pagamentoBanco;
    @Column(name = "PAGAMENTO_AGENCIA")
    private String pagamentoAgencia;
    @Column(name = "PAGAMENTO_AGENCIA_DIGITO")
    private String pagamentoAgenciaDigito;
    @Column(name = "PAGAMENTO_CONTA")
    private String pagamentoConta;
    @Column(name = "PAGAMENTO_CONTA_DIGITO")
    private String pagamentoContaDigito;
    @Temporal(TemporalType.DATE)
    @Column(name = "EXAME_MEDICO_ULTIMO")
    private Date exameMedicoUltimo;
    @Temporal(TemporalType.DATE)
    @Column(name = "EXAME_MEDICO_VENCIMENTO")
    private Date exameMedicoVencimento;
    @Temporal(TemporalType.DATE)
    @Column(name = "PIS_DATA_CADASTRO")
    private Date pisDataCadastro;
    @Column(name = "PIS_NUMERO")
    private String pisNumero;
    @Column(name = "PIS_BANCO")
    private String pisBanco;
    @Column(name = "PIS_AGENCIA")
    private String pisAgencia;
    @Column(name = "PIS_AGENCIA_DIGITO")
    private String pisAgenciaDigito;
    @Column(name = "CTPS_NUMERO")
    private String ctpsNumero;
    @Column(name = "CTPS_SERIE")
    private String ctpsSerie;
    @Temporal(TemporalType.DATE)
    @Column(name = "CTPS_DATA_EXPEDICAO")
    private Date ctpsDataExpedicao;
    @Column(name = "CTPS_UF")
    private String ctpsUf;
    @Column(name = "DESCONTO_PLANO_SAUDE")
    private String descontoPlanoSaude;
    @Column(name = "SAI_NA_RAIS")
    private String saiNaRais;
    @Column(name = "CATEGORIA_SEFIP")
    private String categoriaSefip;
    @Column(name = "OBSERVACAO")
    private String observacao;
    @Column(name = "OCORRENCIA_SEFIP")
    private Integer ocorrenciaSefip;
    @Column(name = "CODIGO_ADMISSAO_CAGED")
    private Integer codigoAdmissaoCaged;
    @Column(name = "CODIGO_DEMISSAO_CAGED")
    private Integer codigoDemissaoCaged;
    @Column(name = "CODIGO_DEMISSAO_SEFIP")
    private Integer codigoDemissaoSefip;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_DEMISSAO")
    private Date dataDemissao;
    @Column(name = "CODIGO_TURMA_PONTO")
    private String codigoTurmaPonto;
    @Column(name = "CAGED_APRENDIZ")
    private String cagedAprendiz;
    @Column(name = "CAGED_DEFICIENCIA")
    private String cagedDeficiencia;
    @Column(name = "CLASSIFICACAO_CONTABIL_CONTA")
    private String classificacaoContabilConta;
    @JoinColumn(name = "ID_SETOR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private SetorVO setor;
    @JoinColumn(name = "ID_CARGO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CargoVO cargo;
    @JoinColumn(name = "ID_TIPO_COLABORADOR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TipoColaboradorVO tipoColaborador;
    @JoinColumn(name = "ID_NIVEL_FORMACAO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private NivelFormacaoVO nivelFormacao;
    @JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PessoaVO pessoa;
    @JoinColumn(name = "ID_SITUACAO_COLABORADOR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private SituacaoColaboradorVO situacaoColaborador;
    @JoinColumn(name = "ID_TIPO_ADMISSAO", referencedColumnName = "ID")
    @ManyToOne
    private TipoAdmissaoVO tipoAdmissao;
    @JoinColumn(name = "ID_SINDICATO", referencedColumnName = "ID")
    @ManyToOne
    private SindicatoVO sindicato;

    public ColaboradorVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getFoto34() {
        return foto34;
    }

    public void setFoto34(String foto34) {
        this.foto34 = foto34;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public Date getVencimentoFerias() {
        return vencimentoFerias;
    }

    public void setVencimentoFerias(Date vencimentoFerias) {
        this.vencimentoFerias = vencimentoFerias;
    }

    public Date getDataTransferencia() {
        return dataTransferencia;
    }

    public void setDataTransferencia(Date dataTransferencia) {
        this.dataTransferencia = dataTransferencia;
    }

    public String getFgtsOptante() {
        return fgtsOptante;
    }

    public void setFgtsOptante(String fgtsOptante) {
        this.fgtsOptante = fgtsOptante;
    }

    public Date getFgtsDataOpcao() {
        return fgtsDataOpcao;
    }

    public void setFgtsDataOpcao(Date fgtsDataOpcao) {
        this.fgtsDataOpcao = fgtsDataOpcao;
    }

    public Integer getFgtsConta() {
        return fgtsConta;
    }

    public void setFgtsConta(Integer fgtsConta) {
        this.fgtsConta = fgtsConta;
    }

    public String getPagamentoForma() {
        return pagamentoForma;
    }

    public void setPagamentoForma(String pagamentoForma) {
        this.pagamentoForma = pagamentoForma;
    }

    public String getPagamentoBanco() {
        return pagamentoBanco;
    }

    public void setPagamentoBanco(String pagamentoBanco) {
        this.pagamentoBanco = pagamentoBanco;
    }

    public String getPagamentoAgencia() {
        return pagamentoAgencia;
    }

    public void setPagamentoAgencia(String pagamentoAgencia) {
        this.pagamentoAgencia = pagamentoAgencia;
    }

    public String getPagamentoAgenciaDigito() {
        return pagamentoAgenciaDigito;
    }

    public void setPagamentoAgenciaDigito(String pagamentoAgenciaDigito) {
        this.pagamentoAgenciaDigito = pagamentoAgenciaDigito;
    }

    public String getPagamentoConta() {
        return pagamentoConta;
    }

    public void setPagamentoConta(String pagamentoConta) {
        this.pagamentoConta = pagamentoConta;
    }

    public String getPagamentoContaDigito() {
        return pagamentoContaDigito;
    }

    public void setPagamentoContaDigito(String pagamentoContaDigito) {
        this.pagamentoContaDigito = pagamentoContaDigito;
    }

    public Date getExameMedicoUltimo() {
        return exameMedicoUltimo;
    }

    public void setExameMedicoUltimo(Date exameMedicoUltimo) {
        this.exameMedicoUltimo = exameMedicoUltimo;
    }

    public Date getExameMedicoVencimento() {
        return exameMedicoVencimento;
    }

    public void setExameMedicoVencimento(Date exameMedicoVencimento) {
        this.exameMedicoVencimento = exameMedicoVencimento;
    }

    public Date getPisDataCadastro() {
        return pisDataCadastro;
    }

    public void setPisDataCadastro(Date pisDataCadastro) {
        this.pisDataCadastro = pisDataCadastro;
    }

    public String getPisNumero() {
        return pisNumero;
    }

    public void setPisNumero(String pisNumero) {
        this.pisNumero = pisNumero;
    }

    public String getPisBanco() {
        return pisBanco;
    }

    public void setPisBanco(String pisBanco) {
        this.pisBanco = pisBanco;
    }

    public String getPisAgencia() {
        return pisAgencia;
    }

    public void setPisAgencia(String pisAgencia) {
        this.pisAgencia = pisAgencia;
    }

    public String getPisAgenciaDigito() {
        return pisAgenciaDigito;
    }

    public void setPisAgenciaDigito(String pisAgenciaDigito) {
        this.pisAgenciaDigito = pisAgenciaDigito;
    }

    public String getCtpsNumero() {
        return ctpsNumero;
    }

    public void setCtpsNumero(String ctpsNumero) {
        this.ctpsNumero = ctpsNumero;
    }

    public String getCtpsSerie() {
        return ctpsSerie;
    }

    public void setCtpsSerie(String ctpsSerie) {
        this.ctpsSerie = ctpsSerie;
    }

    public Date getCtpsDataExpedicao() {
        return ctpsDataExpedicao;
    }

    public void setCtpsDataExpedicao(Date ctpsDataExpedicao) {
        this.ctpsDataExpedicao = ctpsDataExpedicao;
    }

    public String getCtpsUf() {
        return ctpsUf;
    }

    public void setCtpsUf(String ctpsUf) {
        this.ctpsUf = ctpsUf;
    }

    public String getDescontoPlanoSaude() {
        return descontoPlanoSaude;
    }

    public void setDescontoPlanoSaude(String descontoPlanoSaude) {
        this.descontoPlanoSaude = descontoPlanoSaude;
    }

    public String getSaiNaRais() {
        return saiNaRais;
    }

    public void setSaiNaRais(String saiNaRais) {
        this.saiNaRais = saiNaRais;
    }

    public String getCategoriaSefip() {
        return categoriaSefip;
    }

    public void setCategoriaSefip(String categoriaSefip) {
        this.categoriaSefip = categoriaSefip;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Integer getOcorrenciaSefip() {
        return ocorrenciaSefip;
    }

    public void setOcorrenciaSefip(Integer ocorrenciaSefip) {
        this.ocorrenciaSefip = ocorrenciaSefip;
    }

    public Integer getCodigoAdmissaoCaged() {
        return codigoAdmissaoCaged;
    }

    public void setCodigoAdmissaoCaged(Integer codigoAdmissaoCaged) {
        this.codigoAdmissaoCaged = codigoAdmissaoCaged;
    }

    public Integer getCodigoDemissaoCaged() {
        return codigoDemissaoCaged;
    }

    public void setCodigoDemissaoCaged(Integer codigoDemissaoCaged) {
        this.codigoDemissaoCaged = codigoDemissaoCaged;
    }

    public Integer getCodigoDemissaoSefip() {
        return codigoDemissaoSefip;
    }

    public void setCodigoDemissaoSefip(Integer codigoDemissaoSefip) {
        this.codigoDemissaoSefip = codigoDemissaoSefip;
    }

    public Date getDataDemissao() {
        return dataDemissao;
    }

    public void setDataDemissao(Date dataDemissao) {
        this.dataDemissao = dataDemissao;
    }

    public String getCodigoTurmaPonto() {
        return codigoTurmaPonto;
    }

    public void setCodigoTurmaPonto(String codigoTurmaPonto) {
        this.codigoTurmaPonto = codigoTurmaPonto;
    }

    public String getCagedAprendiz() {
        return cagedAprendiz;
    }

    public void setCagedAprendiz(String cagedAprendiz) {
        this.cagedAprendiz = cagedAprendiz;
    }

    public String getCagedDeficiencia() {
        return cagedDeficiencia;
    }

    public void setCagedDeficiencia(String cagedDeficiencia) {
        this.cagedDeficiencia = cagedDeficiencia;
    }

    public String getClassificacaoContabilConta() {
        return classificacaoContabilConta;
    }

    public void setClassificacaoContabilConta(String classificacaoContabilConta) {
        this.classificacaoContabilConta = classificacaoContabilConta;
    }

    public SetorVO getSetor() {
        return setor;
    }

    public void setSetor(SetorVO setor) {
        this.setor = setor;
    }

    public CargoVO getCargo() {
        return cargo;
    }

    public void setCargo(CargoVO cargo) {
        this.cargo = cargo;
    }

    public TipoColaboradorVO getTipoColaborador() {
        return tipoColaborador;
    }

    public void setTipoColaborador(TipoColaboradorVO tipoColaborador) {
        this.tipoColaborador = tipoColaborador;
    }

    public NivelFormacaoVO getNivelFormacao() {
        return nivelFormacao;
    }

    public void setNivelFormacao(NivelFormacaoVO nivelFormacao) {
        this.nivelFormacao = nivelFormacao;
    }

    public PessoaVO getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaVO pessoa) {
        this.pessoa = pessoa;
    }

    public SituacaoColaboradorVO getSituacaoColaborador() {
        return situacaoColaborador;
    }

    public void setSituacaoColaborador(SituacaoColaboradorVO situacaoColaborador) {
        this.situacaoColaborador = situacaoColaborador;
    }

    public TipoAdmissaoVO getTipoAdmissao() {
        return tipoAdmissao;
    }

    public void setTipoAdmissao(TipoAdmissaoVO tipoAdmissao) {
        this.tipoAdmissao = tipoAdmissao;
    }

    public SindicatoVO getSindicato() {
        return sindicato;
    }

    public void setSindicato(SindicatoVO sindicato) {
        this.sindicato = sindicato;
    }


    @Override
    public String toString() {
        return "com.t2tierp.cadastros.java.ColaboradorVO[id=" + id + "]";
    }

}
