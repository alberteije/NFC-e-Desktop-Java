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
package com.t2ti.nfce.view;

import br.inf.portalfiscal.nfe.procnfe.TNfeProc;
import com.t2ti.nfce.controller.VendaController;
import com.t2ti.nfce.infra.CancelaNfe;
import com.t2ti.nfce.infra.InutilizaNumero;
import com.t2ti.nfce.infra.NfceConstantes;
import com.t2ti.nfce.infra.SessaoUsuario;
import com.t2tierp.nfe.java.NfeCabecalhoVO;
import java.awt.Dimension;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;

public class CancelaInutiliza extends javax.swing.JDialog {

    private String caminhoArquivo;
    private String justificativa;
    private int operacao;
    private int idNfeCabecalho;

    /**
     * Creates new form CancelaInutiliza
     */
    public CancelaInutiliza(java.awt.Frame parent, boolean modal, int operacao, String justificativa, int idNfeCabecalho) {
        super(parent, modal);
        initComponents();
        
        this.operacao = operacao;
        this.justificativa = justificativa;
        this.idNfeCabecalho = idNfeCabecalho;

        setPreferredSize(new Dimension(750, 80));
        this.pack();

        try {
            cancelaInutiliza();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void cancelaInutiliza() throws Exception {
        SwingWorker swingWorker = new SwingWorker() {

            @Override
            protected Object doInBackground() throws Exception {
                try {
                    labelMensagens.setText("Buscando dados da nota...");
                    SessaoUsuario.vendaAtual = new VendaController().getBean(idNfeCabecalho, NfeCabecalhoVO.class);
                    caminhoArquivo = NfceConstantes.DIRETORIO_ARQUIVOS_XML + SessaoUsuario.vendaAtual.getChaveAcesso() + SessaoUsuario.vendaAtual.getDigitoChaveAcesso();
                    if (operacao == NfceConstantes.OP_CANCELA_NFCE) {
                        cancelaNfce(justificativa);
                    } else if (operacao == NfceConstantes.OP_INUTILIZA_NUMERO) {
                        inutilizaNumero(justificativa);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao cancelar/inutilizar a NFC-e.\n" + ex.getMessage(), "Erro do sistema", JOptionPane.ERROR_MESSAGE);
                }
                return null;
            }

            @Override
            protected void done() {
                dispose();
            }
        };
        swingWorker.execute();
    }

    private void cancelaNfce(String justificativa) throws Exception {
        labelMensagens.setText("Recuperando nr do protocolo...");
        File arquivoXml = new File(caminhoArquivo + "-nfeproc.xml");
        if (!arquivoXml.exists()) {
            JOptionPane.showMessageDialog(this, "Arquivo XML da NFC-e não localizado!", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JAXBContext jc = JAXBContext.newInstance("br.inf.portalfiscal.nfe.procnfe");
            Unmarshaller unmarshaller = jc.createUnmarshaller();

            JAXBElement<TNfeProc> element = (JAXBElement) unmarshaller.unmarshal(arquivoXml);
            String protocolo = element.getValue().getProtNFe().getInfProt().getNProt();

            labelMensagens.setText("Enviando o XML de cancelamento...");
            CancelaNfe cancelaNfe = new CancelaNfe();
            Map dadosCancelamento = cancelaNfe.cancelaNfe(SessaoUsuario.certificado.aliases().nextElement(),
                    SessaoUsuario.certificado,
                    SessaoUsuario.senhaCertificado,
                    SessaoUsuario.vendaAtual.getUfEmitente().toString(),
                    String.valueOf(SessaoUsuario.vendaAtual.getAmbiente()),
                    SessaoUsuario.vendaAtual.getChaveAcesso() + SessaoUsuario.vendaAtual.getDigitoChaveAcesso(),
                    protocolo,
                    justificativa.trim(),
                    SessaoUsuario.vendaAtual.getEmpresa().getCnpj());

            boolean cancelado = (Boolean) dadosCancelamento.get("nfeCancelada");

            String resposta = "";
            if (cancelado) {
                labelMensagens.setText("Salvando as informações...");
                OutputStream out = new FileOutputStream(new File(caminhoArquivo + "-nfeCanc.xml"));
                out.write(((String) dadosCancelamento.get("xmlCancelamento")).getBytes());
                out.close();

                SessaoUsuario.vendaAtual.setStatusNota(NfceConstantes.SN_CANCELADA);
                new VendaController().atualizar(SessaoUsuario.vendaAtual);
                resposta += "NFC-e Cancelada com sucesso";
            } else {
                resposta += "A NFC-e NÃO foi cancelada.";
            }
            resposta += "\n" + (String) dadosCancelamento.get("motivo1");
            resposta += "\n" + (String) dadosCancelamento.get("motivo2");

            labelMensagens.setText("Finalizado.");
            JOptionPane.showMessageDialog(this, resposta, "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void inutilizaNumero(String justificativa) throws Exception {
        labelMensagens.setText("Enviando o XML de inutilização...");
        SimpleDateFormat formatoAno = new SimpleDateFormat("yy");
        InutilizaNumero inutiliza = new InutilizaNumero();
        Map dadosInutilizacao = inutiliza.inutiliza(SessaoUsuario.certificado.aliases().nextElement(),
                SessaoUsuario.certificado,
                SessaoUsuario.senhaCertificado,
                SessaoUsuario.vendaAtual.getUfEmitente().toString(),
                String.valueOf(SessaoUsuario.vendaAtual.getAmbiente()),
                formatoAno.format(SessaoUsuario.vendaAtual.getDataHoraEmissao()),
                SessaoUsuario.vendaAtual.getSerie(),
                justificativa.trim(),
                SessaoUsuario.vendaAtual.getEmpresa().getCnpj(),
                SessaoUsuario.vendaAtual.getNumero(),
                SessaoUsuario.vendaAtual.getNumero());

        boolean inutilizado = (Boolean) dadosInutilizacao.get("nfeInutilizada");

        String resposta = "";
        if (inutilizado) {
            labelMensagens.setText("Salvando as informações...");
            OutputStream out = new FileOutputStream(new File(caminhoArquivo + "-nfeinut.xml"));
            out.write(((String) dadosInutilizacao.get("xmlInutilizacao")).getBytes());
            out.close();

            SessaoUsuario.vendaAtual.setStatusNota(NfceConstantes.SN_INUTILIZADA);
            new VendaController().atualizar(SessaoUsuario.vendaAtual);
            resposta += "NFC-e Inutlizada com sucesso";
        } else {
            resposta += "A NFC-e NÃO foi inutilizada.";
        }
        resposta += "\n" + (String) dadosInutilizacao.get("motivo");

        labelMensagens.setText("Finalizado.");
        JOptionPane.showMessageDialog(this, resposta, "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelMensagens = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new java.awt.CardLayout());

        labelMensagens.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelMensagens.setText("Gerando o XML...");
        getContentPane().add(labelMensagens, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelMensagens;
    // End of variables declaration//GEN-END:variables
}
