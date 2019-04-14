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
@Table(name = "NFE_CONFIGURACAO")
public class NfeConfiguracaoVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CERTIFICADO_DIGITAL_SERIE")
    private String certificadoDigitalSerie;
    @Column(name = "CERTIFICADO_DIGITAL_CAMINHO")
    private String certificadoDigitalCaminho;
    @Column(name = "CERTIFICADO_DIGITAL_SENHA")
    private String certificadoDigitalSenha;
    @Column(name = "TIPO_EMISSAO")
    private Integer tipoEmissao;
    @Column(name = "FORMATO_IMPRESSAO_DANFE")
    private Integer formatoImpressaoDanfe;
    @Column(name = "PROCESSO_EMISSAO")
    private Integer processoEmissao;
    @Column(name = "VERSAO_PROCESSO_EMISSAO")
    private String versaoProcessoEmissao;
    @Column(name = "CAMINHO_LOGOMARCA")
    private String caminhoLogomarca;
    @Column(name = "SALVAR_XML")
    private String salvarXml;
    @Column(name = "CAMINHO_SALVAR_XML")
    private String caminhoSalvarXml;
    @Column(name = "CAMINHO_SCHEMAS")
    private String caminhoSchemas;
    @Column(name = "CAMINHO_ARQUIVO_DANFE")
    private String caminhoArquivoDanfe;
    @Column(name = "CAMINHO_SALVAR_PDF")
    private String caminhoSalvarPdf;
    @Column(name = "WEBSERVICE_UF")
    private String webserviceUf;
    @Column(name = "WEBSERVICE_AMBIENTE")
    private Integer webserviceAmbiente;
    @Column(name = "WEBSERVICE_PROXY_HOST")
    private String webserviceProxyHost;
    @Column(name = "WEBSERVICE_PROXY_PORTA")
    private Integer webserviceProxyPorta;
    @Column(name = "WEBSERVICE_PROXY_USUARIO")
    private String webserviceProxyUsuario;
    @Column(name = "WEBSERVICE_PROXY_SENHA")
    private String webserviceProxySenha;
    @Column(name = "WEBSERVICE_VISUALIZAR")
    private String webserviceVisualizar;
    @Column(name = "EMAIL_SERVIDOR_SMTP")
    private String emailServidorSmtp;
    @Column(name = "EMAIL_PORTA")
    private Integer emailPorta;
    @Column(name = "EMAIL_USUARIO")
    private String emailUsuario;
    @Column(name = "EMAIL_SENHA")
    private String emailSenha;
    @Column(name = "EMAIL_ASSUNTO")
    private String emailAssunto;
    @Column(name = "EMAIL_AUTENTICA_SSL")
    private String emailAutenticaSsl;
    @Column(name = "EMAIL_TEXTO")
    private String emailTexto;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private EmpresaVO empresa;

    public NfeConfiguracaoVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCertificadoDigitalSerie() {
        return certificadoDigitalSerie;
    }

    public void setCertificadoDigitalSerie(String certificadoDigitalSerie) {
        this.certificadoDigitalSerie = certificadoDigitalSerie;
    }

    public String getCertificadoDigitalCaminho() {
        return certificadoDigitalCaminho;
    }

    public void setCertificadoDigitalCaminho(String certificadoDigitalCaminho) {
        this.certificadoDigitalCaminho = certificadoDigitalCaminho;
    }

    public String getCertificadoDigitalSenha() {
        return certificadoDigitalSenha;
    }

    public void setCertificadoDigitalSenha(String certificadoDigitalSenha) {
        this.certificadoDigitalSenha = certificadoDigitalSenha;
    }

    public Integer getTipoEmissao() {
        return tipoEmissao;
    }

    public void setTipoEmissao(Integer tipoEmissao) {
        this.tipoEmissao = tipoEmissao;
    }

    public Integer getFormatoImpressaoDanfe() {
        return formatoImpressaoDanfe;
    }

    public void setFormatoImpressaoDanfe(Integer formatoImpressaoDanfe) {
        this.formatoImpressaoDanfe = formatoImpressaoDanfe;
    }

    public Integer getProcessoEmissao() {
        return processoEmissao;
    }

    public void setProcessoEmissao(Integer processoEmissao) {
        this.processoEmissao = processoEmissao;
    }

    public String getVersaoProcessoEmissao() {
        return versaoProcessoEmissao;
    }

    public void setVersaoProcessoEmissao(String versaoProcessoEmissao) {
        this.versaoProcessoEmissao = versaoProcessoEmissao;
    }

    public String getCaminhoLogomarca() {
        return caminhoLogomarca;
    }

    public void setCaminhoLogomarca(String caminhoLogomarca) {
        this.caminhoLogomarca = caminhoLogomarca;
    }

    public String getSalvarXml() {
        return salvarXml;
    }

    public void setSalvarXml(String salvarXml) {
        this.salvarXml = salvarXml;
    }

    public String getCaminhoSalvarXml() {
        return caminhoSalvarXml;
    }

    public void setCaminhoSalvarXml(String caminhoSalvarXml) {
        this.caminhoSalvarXml = caminhoSalvarXml;
    }

    public String getCaminhoSchemas() {
        return caminhoSchemas;
    }

    public void setCaminhoSchemas(String caminhoSchemas) {
        this.caminhoSchemas = caminhoSchemas;
    }

    public String getCaminhoArquivoDanfe() {
        return caminhoArquivoDanfe;
    }

    public void setCaminhoArquivoDanfe(String caminhoArquivoDanfe) {
        this.caminhoArquivoDanfe = caminhoArquivoDanfe;
    }

    public String getCaminhoSalvarPdf() {
        return caminhoSalvarPdf;
    }

    public void setCaminhoSalvarPdf(String caminhoSalvarPdf) {
        this.caminhoSalvarPdf = caminhoSalvarPdf;
    }

    public String getWebserviceUf() {
        return webserviceUf;
    }

    public void setWebserviceUf(String webserviceUf) {
        this.webserviceUf = webserviceUf;
    }

    public Integer getWebserviceAmbiente() {
        return webserviceAmbiente;
    }

    public void setWebserviceAmbiente(Integer webserviceAmbiente) {
        this.webserviceAmbiente = webserviceAmbiente;
    }

    public String getWebserviceProxyHost() {
        return webserviceProxyHost;
    }

    public void setWebserviceProxyHost(String webserviceProxyHost) {
        this.webserviceProxyHost = webserviceProxyHost;
    }

    public Integer getWebserviceProxyPorta() {
        return webserviceProxyPorta;
    }

    public void setWebserviceProxyPorta(Integer webserviceProxyPorta) {
        this.webserviceProxyPorta = webserviceProxyPorta;
    }

    public String getWebserviceProxyUsuario() {
        return webserviceProxyUsuario;
    }

    public void setWebserviceProxyUsuario(String webserviceProxyUsuario) {
        this.webserviceProxyUsuario = webserviceProxyUsuario;
    }

    public String getWebserviceProxySenha() {
        return webserviceProxySenha;
    }

    public void setWebserviceProxySenha(String webserviceProxySenha) {
        this.webserviceProxySenha = webserviceProxySenha;
    }

    public String getWebserviceVisualizar() {
        return webserviceVisualizar;
    }

    public void setWebserviceVisualizar(String webserviceVisualizar) {
        this.webserviceVisualizar = webserviceVisualizar;
    }

    public String getEmailServidorSmtp() {
        return emailServidorSmtp;
    }

    public void setEmailServidorSmtp(String emailServidorSmtp) {
        this.emailServidorSmtp = emailServidorSmtp;
    }

    public Integer getEmailPorta() {
        return emailPorta;
    }

    public void setEmailPorta(Integer emailPorta) {
        this.emailPorta = emailPorta;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getEmailSenha() {
        return emailSenha;
    }

    public void setEmailSenha(String emailSenha) {
        this.emailSenha = emailSenha;
    }

    public String getEmailAssunto() {
        return emailAssunto;
    }

    public void setEmailAssunto(String emailAssunto) {
        this.emailAssunto = emailAssunto;
    }

    public String getEmailAutenticaSsl() {
        return emailAutenticaSsl;
    }

    public void setEmailAutenticaSsl(String emailAutenticaSsl) {
        this.emailAutenticaSsl = emailAutenticaSsl;
    }

    public String getEmailTexto() {
        return emailTexto;
    }

    public void setEmailTexto(String emailTexto) {
        this.emailTexto = emailTexto;
    }

    public EmpresaVO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaVO empresa) {
        this.empresa = empresa;
    }


    @Override
    public String toString() {
        return "com.t2tierp.nfe.java.NfeConfiguracaoVO[id=" + id + "]";
    }

}
