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
package com.t2ti.nfce.infra;

import br.inf.portalfiscal.nfe.procevento.TEvento;
import br.inf.portalfiscal.nfe.procevento.TProcEvento;
import br.inf.portalfiscal.www.nfe.wsdl.recepcaoevento.RecepcaoEventoStub;
import com.t2ti.pafecf.infra.Biblioteca;
import com.t2tierp.nfe.servidor.SocketFactoryDinamico;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.commons.httpclient.protocol.Protocol;

public class CancelaNfe {

    public Map cancelaNfe(String alias, KeyStore ks, char[] senha, String codigoUf, String ambiente, String chaveAcesso, String numeroProtocolo, String justificativa, String cnpj) throws Exception {
        String versaoDados = "1.00";
        String url = "";
        if (codigoUf.equals("53")) {
            if (ambiente.equals("1")) {
                url = "https://nfe.sefazvirtual.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx";
            } else if (ambiente.equals("2")) {
                url = "https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/recepcaoevento/recepcaoevento.asmx";
            }
        }
        /* fica a cargo de cada participante definir a url que será utiizada de acordo com o código da UF
         * URLs disponíveis em:
         * Homologação: http://hom.nfe.fazenda.gov.br/PORTAL/WebServices.aspx
         * Produção: http://www.nfe.fazenda.gov.br/portal/WebServices.aspx
         */

        if (url.equals("")) {
            throw new Exception("URL da sefaz não definida para o código de UF = " + codigoUf);
        }

        SimpleDateFormat formatoIso = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        String dataHoraEvento = formatoIso.format(new Date());

        String xmlCanc = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"
                + "<envEvento xmlns=\"http://www.portalfiscal.inf.br/nfe\" versao=\"" + versaoDados + "\">"
                + "<idLote>1</idLote>"
                + "<evento versao=\"" + versaoDados + "\">"
                + "<infEvento Id=\"ID" + "110111" + chaveAcesso + "01\">"
                + "<cOrgao>" + codigoUf + "</cOrgao>"
                + "<tpAmb>" + ambiente + "</tpAmb>"
                + "<CNPJ>" + cnpj + "</CNPJ>"
                + "<chNFe>" + chaveAcesso + "</chNFe>"
                + "<dhEvento>" + dataHoraEvento + "</dhEvento>"
                + "<tpEvento>110111</tpEvento>"
                + "<nSeqEvento>1</nSeqEvento>"
                + "<verEvento>" + versaoDados + "</verEvento>"
                + "<detEvento versao=\"" + versaoDados + "\">"
                + "<descEvento>Cancelamento</descEvento>"
                + "<nProt>" + numeroProtocolo + "</nProt>"
                + "<xJust>" + justificativa + "</xJust>"
                + "</detEvento>"
                + "</infEvento>"
                + "</evento>"
                + "</envEvento>";

        xmlCanc = Biblioteca.assinaXML(xmlCanc, alias, ks, senha, "#ID110111" + chaveAcesso + "01", "evento", "infEvento", "Id");
        
        X509Certificate certificate = (X509Certificate) ks.getCertificate(alias);
        PrivateKey privatekey = (PrivateKey) ks.getKey(alias, senha);
        SocketFactoryDinamico socketFactory = new SocketFactoryDinamico(certificate, privatekey);
        //arquivo que contém a cadeia de certificados do serviço a ser consumido
        socketFactory.setFileCacerts(this.getClass().getResourceAsStream("/br/inf/portalfiscal/nfe/jssecacerts"));

        //define o protocolo a ser utilizado na conexão
        Protocol protocol = new Protocol("https", socketFactory, 443);
        Protocol.registerProtocol("https", protocol);

        OMElement omElement = AXIOMUtil.stringToOM(xmlCanc);

        RecepcaoEventoStub.NfeDadosMsg dadosMsg = new RecepcaoEventoStub.NfeDadosMsg();
        dadosMsg.setExtraElement(omElement);

        RecepcaoEventoStub.NfeCabecMsg cabecMsg = new RecepcaoEventoStub.NfeCabecMsg();
        cabecMsg.setCUF(codigoUf);
        cabecMsg.setVersaoDados(versaoDados);

        RecepcaoEventoStub.NfeCabecMsgE cabecMsgE = new RecepcaoEventoStub.NfeCabecMsgE();
        cabecMsgE.setNfeCabecMsg(cabecMsg);

        RecepcaoEventoStub stub = new RecepcaoEventoStub(url);

        RecepcaoEventoStub.NfeRecepcaoEventoResult result = stub.nfeRecepcaoEvento(dadosMsg, cabecMsgE);

        ByteArrayInputStream in = new ByteArrayInputStream(result.getExtraElement().toString().getBytes());

        JAXBContext jc = JAXBContext.newInstance("br.inf.portalfiscal.nfe.retevento");
        Unmarshaller unmarshaller = jc.createUnmarshaller();

        JAXBElement<br.inf.portalfiscal.nfe.retevento.TRetEnvEvento> retEvento = (JAXBElement) unmarshaller.unmarshal(in);

        Map map = new HashMap();
        if (retEvento.getValue().getRetEvento().get(0).getInfEvento().getCStat().equals("135")) {
            map.put("nfeCancelada", true);
            xmlCanc = xmlCancelamento(retEvento.getValue(), versaoDados, codigoUf, ambiente, chaveAcesso, numeroProtocolo, justificativa, cnpj, dataHoraEvento);
            xmlCanc = xmlCanc.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");

            //xmlCanc = Biblioteca.assinaXML(xmlCanc, alias, ks, senha, "#ID110111" + chaveAcesso + "01", "evento", "infEvento", "Id");
            map.put("xmlCancelamento", xmlCanc);
        } else {
            map.put("nfeCancelada", false);
        }
        map.put("motivo1", retEvento.getValue().getXMotivo());
        map.put("motivo2", retEvento.getValue().getRetEvento().get(0).getInfEvento().getXMotivo());

        return map;
    }

    private String xmlCancelamento(br.inf.portalfiscal.nfe.retevento.TRetEnvEvento retorno, String versaoDados, String codigoUf, String ambiente, String chaveAcesso, String numeroProtocolo, String justificativa, String cnpj, String dataHoraEvento) throws Exception {
        TProcEvento xml = new TProcEvento();
        xml.setVersao(versaoDados);

        TEvento evento = new TEvento();
        xml.setEvento(evento);
        TEvento.InfEvento infEvento = new TEvento.InfEvento();
        evento.setInfEvento(infEvento);
        evento.setVersao(versaoDados);
        TEvento.InfEvento.DetEvento detEvento = new TEvento.InfEvento.DetEvento();
        infEvento.setDetEvento(detEvento);

        infEvento.setCNPJ(cnpj);
        infEvento.setCOrgao(codigoUf);
        infEvento.setChNFe(chaveAcesso);
        infEvento.setDhEvento(dataHoraEvento);
        infEvento.setId("ID" + "110111" + chaveAcesso + "01");
        infEvento.setNSeqEvento("1");
        infEvento.setTpAmb(ambiente);
        infEvento.setTpEvento("110111");
        infEvento.setVerEvento(versaoDados);

        detEvento.setDescEvento("Cancelamento");
        detEvento.setNProt(numeroProtocolo);
        detEvento.setVersao(versaoDados);
        detEvento.setXJust(justificativa);

        br.inf.portalfiscal.nfe.procevento.TRetEvento retEvento = new br.inf.portalfiscal.nfe.procevento.TRetEvento();
        xml.setRetEvento(retEvento);
        br.inf.portalfiscal.nfe.procevento.TRetEvento.InfEvento retInfEvento = new br.inf.portalfiscal.nfe.procevento.TRetEvento.InfEvento();
        retEvento.setInfEvento(retInfEvento);

        retEvento.setVersao(retorno.getVersao());
        retInfEvento.setCPFDest(retorno.getRetEvento().get(0).getInfEvento().getCPFDest());
        retInfEvento.setCNPJDest(retorno.getRetEvento().get(0).getInfEvento().getCNPJDest());
        retInfEvento.setCOrgao(retorno.getRetEvento().get(0).getInfEvento().getCOrgao());
        retInfEvento.setCStat(retorno.getRetEvento().get(0).getInfEvento().getCStat());
        retInfEvento.setChNFe(retorno.getRetEvento().get(0).getInfEvento().getChNFe());
        retInfEvento.setDhRegEvento(retorno.getRetEvento().get(0).getInfEvento().getDhRegEvento());
        retInfEvento.setEmailDest(retorno.getRetEvento().get(0).getInfEvento().getEmailDest());
        retInfEvento.setId(retorno.getRetEvento().get(0).getInfEvento().getId());
        retInfEvento.setNProt(retorno.getRetEvento().get(0).getInfEvento().getNProt());
        retInfEvento.setNSeqEvento(retorno.getRetEvento().get(0).getInfEvento().getNSeqEvento());
        retInfEvento.setTpAmb(retorno.getRetEvento().get(0).getInfEvento().getTpAmb());
        retInfEvento.setTpEvento(retorno.getRetEvento().get(0).getInfEvento().getTpEvento());
        retInfEvento.setVerAplic(retorno.getRetEvento().get(0).getInfEvento().getVerAplic());
        retInfEvento.setXEvento(retorno.getRetEvento().get(0).getInfEvento().getXEvento());
        retInfEvento.setXMotivo(retorno.getRetEvento().get(0).getInfEvento().getXMotivo());

        JAXBContext jc = JAXBContext.newInstance("br.inf.portalfiscal.nfe.procevento");
        Marshaller marshaller = jc.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
        JAXBElement<TProcEvento> element = new br.inf.portalfiscal.nfe.procevento.ObjectFactory().createProcEventoNFe(xml);

        StringWriter writer = new StringWriter();
        marshaller.marshal(element, writer);

        return writer.toString();
    }
}
