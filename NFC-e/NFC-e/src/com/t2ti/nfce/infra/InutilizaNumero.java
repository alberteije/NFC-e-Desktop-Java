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

import br.inf.portalfiscal.nfe.inutnfe.ObjectFactory;
import br.inf.portalfiscal.nfe.inutnfe.TInutNFe;
import br.inf.portalfiscal.nfe.retinutnfe.TRetInutNFe;
import br.inf.portalfiscal.www.nfe.wsdl.nfeinutilizacao2.NfeInutilizacao2Stub;
import com.t2ti.pafecf.infra.Biblioteca;
import com.t2tierp.nfe.servidor.SocketFactoryDinamico;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.commons.httpclient.protocol.Protocol;

public class InutilizaNumero {

    public Map inutiliza(String alias, KeyStore ks, char[] senha, String codigoUf, String ambiente, String ano, String serie, String justificativa, String cnpj, String numeroInicial, String numeroFinal) throws Exception {

        ObjectFactory factory = new ObjectFactory();
        String versaoDados = "3.10";
        String url = "";
        if (codigoUf.equals("53")) {
            if (ambiente.equals("1")) {
                url = "https://nfe.sefazvirtual.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx";
            } else if (ambiente.equals("2")) {
                url = "https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/nfeinutilizacao/nfeinutilizacao2.asmx";
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

        TInutNFe inutNFe = factory.createTInutNFe();
        inutNFe.setVersao(versaoDados);

        TInutNFe.InfInut infInut = factory.createTInutNFeInfInut();
        inutNFe.setInfInut(infInut);

        String id = "ID" + codigoUf + ano + cnpj + "65" + serie + numeroInicial + numeroFinal;

        infInut.setId(id);
        infInut.setTpAmb(ambiente);
        infInut.setXServ("INUTILIZAR");
        infInut.setCUF(codigoUf);
        infInut.setAno(ano);
        infInut.setCNPJ(cnpj);
        infInut.setMod("65");
        infInut.setSerie(Integer.valueOf(serie).toString());
        infInut.setNNFIni(Integer.valueOf(numeroInicial).toString());
        infInut.setNNFFin(Integer.valueOf(numeroFinal).toString());
        infInut.setXJust(justificativa);

        JAXBContext jc = JAXBContext.newInstance("br.inf.portalfiscal.nfe.inutnfe");
        Marshaller marshaller = jc.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
        JAXBElement<TInutNFe> element = factory.createInutNFe(inutNFe);

        StringWriter writer = new StringWriter();
        marshaller.marshal(element, writer);

        String xmlInutNfe = writer.toString();
        xmlInutNfe = xmlInutNfe.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
        xmlInutNfe = xmlInutNfe.replaceAll("xmlns:ns3=\"http://www.w3.org/2000/09/xmldsig#\"", "");
        xmlInutNfe = xmlInutNfe.replaceAll("ns2:", "");
        xmlInutNfe = xmlInutNfe.replaceAll(":ns2", "");
        xmlInutNfe = xmlInutNfe.replaceAll("ns3:", "");
        xmlInutNfe = xmlInutNfe.replaceAll(":ns3", "");

        xmlInutNfe = Biblioteca.assinaXML(xmlInutNfe, alias, ks, senha, "#" + id, "inutNFe", "infInut", "Id");

        X509Certificate certificate = (X509Certificate) ks.getCertificate(alias);
        PrivateKey privatekey = (PrivateKey) ks.getKey(alias, senha);
        SocketFactoryDinamico socketFactory = new SocketFactoryDinamico(certificate, privatekey);
        //arquivo que contém a cadeia de certificados do serviço a ser consumido
        socketFactory.setFileCacerts(this.getClass().getResourceAsStream("/br/inf/portalfiscal/nfe/jssecacerts"));

        //define o protocolo a ser utilizado na conexão
        Protocol protocol = new Protocol("https", socketFactory, 443);
        Protocol.registerProtocol("https", protocol);

        OMElement omElement = AXIOMUtil.stringToOM(xmlInutNfe);

        NfeInutilizacao2Stub.NfeDadosMsg dadosMsg = new NfeInutilizacao2Stub.NfeDadosMsg();
        dadosMsg.setExtraElement(omElement);

        NfeInutilizacao2Stub.NfeCabecMsg cabecMsg = new NfeInutilizacao2Stub.NfeCabecMsg();
        cabecMsg.setCUF(codigoUf);
        cabecMsg.setVersaoDados(versaoDados);

        NfeInutilizacao2Stub.NfeCabecMsgE cabecMsgE = new NfeInutilizacao2Stub.NfeCabecMsgE();
        cabecMsgE.setNfeCabecMsg(cabecMsg);

        NfeInutilizacao2Stub stub = new NfeInutilizacao2Stub(url);

        NfeInutilizacao2Stub.NfeInutilizacaoNF2Result result = stub.nfeInutilizacaoNF2(dadosMsg, cabecMsgE);

        ByteArrayInputStream in = new ByteArrayInputStream(result.getExtraElement().toString().getBytes());

        jc = JAXBContext.newInstance("br.inf.portalfiscal.nfe.retinutnfe");
        Unmarshaller unmarshaller = jc.createUnmarshaller();

        JAXBElement<TRetInutNFe> retInut = (JAXBElement) unmarshaller.unmarshal(in);

        Map map = new HashMap();
        if (retInut.getValue().getInfInut().getCStat().equals("102")) {
            map.put("nfeInutilizada", true);
            xmlInutNfe = xmlInutilizacao(retInut.getValue());
            xmlInutNfe = xmlInutNfe.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
            xmlInutNfe = xmlInutNfe.replaceAll("xmlns:ns3=\"http://www.w3.org/2000/09/xmldsig#\"", "");
            xmlInutNfe = xmlInutNfe.replaceAll("ns2:", "");
            xmlInutNfe = xmlInutNfe.replaceAll(":ns2", "");
            xmlInutNfe = xmlInutNfe.replaceAll("ns3:", "");
            xmlInutNfe = xmlInutNfe.replaceAll(":ns3", "");
            
            map.put("xmlInutilizacao", xmlInutNfe);
        } else {
            map.put("nfeInutilizada", false);
        }
        map.put("motivo", retInut.getValue().getInfInut().getXMotivo());

        return map;
    }

    private String xmlInutilizacao(TRetInutNFe retorno) throws Exception {
        TRetInutNFe xml = new TRetInutNFe();

        xml.setVersao(retorno.getVersao());
        xml.setSignature(retorno.getSignature());

        TRetInutNFe.InfInut infInut = new TRetInutNFe.InfInut();
        xml.setInfInut(infInut);

        infInut.setId(retorno.getInfInut().getId());
        infInut.setTpAmb(retorno.getInfInut().getTpAmb());
        infInut.setVerAplic(retorno.getInfInut().getCStat());
        infInut.setCStat(retorno.getInfInut().getCStat());
        infInut.setXMotivo(retorno.getInfInut().getXMotivo());
        infInut.setCUF(retorno.getInfInut().getCUF());

        if (retorno.getInfInut().getCStat().equals("102")) {
            infInut.setAno(retorno.getInfInut().getAno());
            infInut.setCNPJ(retorno.getInfInut().getCNPJ());
            infInut.setMod(retorno.getInfInut().getMod());
            infInut.setSerie(retorno.getInfInut().getSerie());
            infInut.setNNFIni(retorno.getInfInut().getNNFIni());
            infInut.setNNFFin(retorno.getInfInut().getNNFFin());
            infInut.setDhRecbto(retorno.getInfInut().getDhRecbto());
            infInut.setNProt(retorno.getInfInut().getNProt());
        }

        JAXBContext jc = JAXBContext.newInstance("br.inf.portalfiscal.nfe.retinutnfe");
        Marshaller marshaller = jc.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
        JAXBElement<TRetInutNFe> element = new br.inf.portalfiscal.nfe.retinutnfe.ObjectFactory().createRetInutNFe(xml);

        StringWriter writer = new StringWriter();
        marshaller.marshal(element, writer);

        return writer.toString();
    }

}
