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
package com.t2tierp.nfe.servidor;

import br.inf.portalfiscal.www.nfe.wsdl.nfeautorizacao.NfeAutorizacaoStub;
import br.inf.portalfiscal.www.nfe.wsdl.nferetautorizacao.NfeRetAutorizacaoStub;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.commons.httpclient.protocol.Protocol;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class EnviaNfe {

    public Map enviaNfe(String xml, String alias, KeyStore ks, char[] senha, String codigoUf, String ambiente) throws Exception {
        String versaoDados = "3.10";
        String url = "";
        if (codigoUf.equals("53")) {
            if (ambiente.equals("1")) {
                url = "https://nfe.sefazvirtual.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx";
            } else if (ambiente.equals("2")) {
                url = "https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeAutorizacao/NFeAutorizacao.asmx";
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

        X509Certificate certificate = (X509Certificate) ks.getCertificate(alias);
        PrivateKey privatekey = (PrivateKey) ks.getKey(alias, senha);
        SocketFactoryDinamico socketFactory = new SocketFactoryDinamico(certificate, privatekey);
        //arquivo que contém a cadeia de certificados do serviço a ser consumido
        socketFactory.setFileCacerts(this.getClass().getResourceAsStream("/br/inf/portalfiscal/nfe/jssecacerts"));

        //define o protocolo a ser utilizado na conexão
        Protocol protocol = new Protocol("https", socketFactory, 443);
        Protocol.registerProtocol("https", protocol);

        OMElement omElement = AXIOMUtil.stringToOM(xml);

        NfeAutorizacaoStub.NfeDadosMsg dadosMsg = new NfeAutorizacaoStub.NfeDadosMsg();
        dadosMsg.setExtraElement(omElement);

        NfeAutorizacaoStub.NfeCabecMsg cabecMsg = new NfeAutorizacaoStub.NfeCabecMsg();
        cabecMsg.setCUF(codigoUf);
        cabecMsg.setVersaoDados(versaoDados);

        NfeAutorizacaoStub.NfeCabecMsgE cabecMsgE = new NfeAutorizacaoStub.NfeCabecMsgE();
        cabecMsgE.setNfeCabecMsg(cabecMsg);

        NfeAutorizacaoStub stub = new NfeAutorizacaoStub(url);

        NfeAutorizacaoStub.NfeAutorizacaoLoteResult result = stub.nfeAutorizacaoLote(dadosMsg, cabecMsgE);

        ByteArrayInputStream in = new ByteArrayInputStream(result.getExtraElement().toString().getBytes());

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document doc = dbf.newDocumentBuilder().parse(in);

        String recibo = "";
        NodeList nodeList = doc.getDocumentElement().getElementsByTagName("nRec");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            recibo = element.getTextContent();
        }

        Thread.sleep(500);
        return consultaEnvioNfe(recibo, xml, codigoUf, ambiente);
    }

    public Map consultaEnvioNfe(String numeroRecibo, String xmlEnviNfe, String codigoUf, String ambiente) throws Exception {
        Map map = new HashMap();

        String url = "";
        if (codigoUf.equals("53")) {
            if (ambiente.equals("1")) {
                url = "https://nfe.sefazvirtual.rs.gov.br/ws/NfeRetAutorizacao/NFeRetAutorizacao.asmx";
            } else if (ambiente.equals("2")) {
                url = "https://homologacao.nfe.sefazvirtual.rs.gov.br/ws/NfeRetAutorizacao/NFeRetAutorizacao.asmx";
            }
        }
        /* fica a cargo de cada participante definir a url que será utiizada de acordo com o código da UF
         * URLs disponíveis em:
         * Homologação: http://hom.nfe.fazenda.gov.br/PORTAL/WebServices.aspx
         * Produção: http://www.nfe.fazenda.gov.br/portal/WebServices.aspx
         */

        if (url.equals("")) {
            throw new Exception("URL de retorno da sefaz não definida para o código de UF = " + codigoUf);
        }

        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<consReciNFe versao=\"3.10\" xmlns=\"http://www.portalfiscal.inf.br/nfe\">"
                + "<tpAmb>" + ambiente + "</tpAmb>"
                + "<nRec>" + numeroRecibo + "</nRec>"
                + "</consReciNFe>";

        OMElement omeElement = AXIOMUtil.stringToOM(xml);

        NfeRetAutorizacaoStub.NfeDadosMsg dadosMsg = new NfeRetAutorizacaoStub.NfeDadosMsg();
        dadosMsg.setExtraElement(omeElement);

        NfeRetAutorizacaoStub.NfeCabecMsg cabecMsg = new NfeRetAutorizacaoStub.NfeCabecMsg();
        cabecMsg.setCUF(codigoUf);
        cabecMsg.setVersaoDados("3.10");

        NfeRetAutorizacaoStub.NfeCabecMsgE cabecMsgE = new NfeRetAutorizacaoStub.NfeCabecMsgE();
        cabecMsgE.setNfeCabecMsg(cabecMsg);

        NfeRetAutorizacaoStub stub = new NfeRetAutorizacaoStub(url);

        NfeRetAutorizacaoStub.NfeRetAutorizacaoLoteResult result = stub.nfeRetAutorizacaoLote(dadosMsg, cabecMsgE);

        //System.out.println(result.getExtraElement().toString());
        ByteArrayInputStream in = new ByteArrayInputStream(result.getExtraElement().toString().getBytes());
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document docResposta = dbf.newDocumentBuilder().parse(in);

        NodeList nodeListMotivo = docResposta.getDocumentElement().getElementsByTagName("xMotivo");
        NodeList nodeListProt = docResposta.getDocumentElement().getElementsByTagName("protNFe");
        String respostaSefaz = "";
        String xmlProt = "";
        String xmlProc = "";
        String xmlNfe = "";
        boolean autorizado = false;

        for (int i = 0; i < nodeListMotivo.getLength(); i++) {
            Element element = (Element) nodeListMotivo.item(i);
            respostaSefaz += element.getTextContent() + "\n";
            if (element.getTextContent().startsWith("Autorizado")) {
                autorizado = true;
            }
        }

        map.put("autorizado", autorizado);
        map.put("resposta", respostaSefaz);
        map.put("numeroRecibo", numeroRecibo);
        map.put("xmlEnviNfe", xmlEnviNfe);

        if (autorizado) {
            /*inclui os  seguintes atributos no objeto map:
             tpAmb, verAplic, chNFe, dhRecbto, nProt, digVal, cStat, xMotivo
             */
            Element element = (Element) nodeListProt.item(0);
            NodeList infProt = element.getChildNodes().item(0).getChildNodes();
            for (int j = 0; j < infProt.getLength(); j++) {
                Node n = infProt.item(j);
                map.put(n.getNodeName(), n.getTextContent());
            }

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer trans = tf.newTransformer();

            ByteArrayOutputStream outProt = new ByteArrayOutputStream();
            trans.transform(new DOMSource(nodeListProt.item(0)), new StreamResult(outProt));
            xmlProt = outProt.toString().replaceAll("<\\?xml version=\"1.0\" encoding=\"UTF-8\"\\?>", "");

            ByteArrayInputStream inEnviNfe = new ByteArrayInputStream(xmlEnviNfe.getBytes());
            Document docEnviNfe = dbf.newDocumentBuilder().parse(inEnviNfe);
            ByteArrayOutputStream outNfe = new ByteArrayOutputStream();
            trans.transform(new DOMSource(docEnviNfe.getElementsByTagName("NFe").item(0)), new StreamResult(outNfe));
            xmlNfe = outNfe.toString().replaceAll("<\\?xml version=\"1.0\" encoding=\"UTF-8\"\\?>", "");

            xmlProc = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
            xmlProc += "<nfeProc versao=\"3.10\" xmlns=\"http://www.portalfiscal.inf.br/nfe\">";
            xmlProc += xmlNfe;
            xmlProc += xmlProt;
            xmlProc += "</nfeProc>";

            map.put("xmlProc", xmlProc);
        }

        return map;
    }
}
