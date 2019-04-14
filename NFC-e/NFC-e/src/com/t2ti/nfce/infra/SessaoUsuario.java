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

import com.t2ti.nfce.controller.NfceControllerGenerico;
import com.t2ti.nfce.controller.NfceOperadorController;
import com.t2ti.pafecf.infra.Tipos;
import com.t2tierp.nfce.java.NfceConfiguracaoVO;
import com.t2tierp.nfce.java.NfceMovimentoVO;
import com.t2tierp.nfce.java.NfceOperadorVO;
import com.t2tierp.nfce.java.NfceTipoPagamentoVO;
import com.t2tierp.nfe.java.NfeCabecalhoVO;
import com.t2tierp.nfe.java.NfeConfiguracaoVO;
import com.t2tierp.nfe.servidor.StatusServico;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.List;
import java.util.Properties;
import javax.swing.JOptionPane;

public class SessaoUsuario implements Tipos {

    private static NfceConfiguracaoVO configuracao;
    private static NfeConfiguracaoVO nfeConfiguracao;
    public static NfceMovimentoVO movimento = null;
    public static int statusCaixa = SC_ABERTO;
    public static int menuAberto = NAO;
    public static NfceOperadorVO usuario;
    public static NfeCabecalhoVO vendaAtual;
    private static List<NfceTipoPagamentoVO> listaTipoPagamento;
    public static KeyStore certificado;
    public static char[] senhaCertificado;
    public static String idToken;
    public static String valorToken;

    private SessaoUsuario() {
    }

    static {
        NfceControllerGenerico<NfceConfiguracaoVO> controller = new NfceControllerGenerico<>();
        NfceControllerGenerico<NfceTipoPagamentoVO> controllerTipoPagamento = new NfceControllerGenerico<>();
        NfceControllerGenerico<NfeConfiguracaoVO> nfeController = new NfceControllerGenerico<>();
        try {
            configuracao = controller.getBean(1, NfceConfiguracaoVO.class);
            nfeConfiguracao = nfeController.getBean(1, NfeConfiguracaoVO.class);
            listaTipoPagamento = controllerTipoPagamento.getBeans(NfceTipoPagamentoVO.class);
            try {
                Properties arquivoConfiguracao = new Properties();
                arquivoConfiguracao.load(new FileInputStream(new File(NfceConstantes.ARQUIVO_DADOS_TOKEN)));
                idToken = arquivoConfiguracao.getProperty("idToken");
                valorToken = arquivoConfiguracao.getProperty("valorToken");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao buscar as configurações do sistema.\n" + ex.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static NfceConfiguracaoVO getConfiguracao() {
        return configuracao;
    }

    public static NfeConfiguracaoVO getNfeConfiguracao() {
        return nfeConfiguracao;
    }

    public static List<NfceTipoPagamentoVO> getListaTipoPagamento() {
        return listaTipoPagamento;
    }

    public static void autenticaUsuario(String login, String senha) {
        try {
            NfceOperadorController controller = new NfceOperadorController();
            usuario = controller.getBean(login, senha);
        } catch (Exception ex) {
        }
    }

    public static NfceOperadorVO autenticaGerenteSupervisor(String login, String senha) {
        try {
            NfceOperadorController controller = new NfceOperadorController();
            NfceOperadorVO operador = controller.getBean(login, senha);
            if (operador.getNivelAutorizacao().equals("G")
                    || operador.getNivelAutorizacao().equals("S")) {
                return operador;
            }
            return null;
        } catch (Exception ex) {
            return null;
        }
    }

    public static void selecionaCertificado() throws Exception {
        SelecionaCertificado janelaSelecionaCertificado = new SelecionaCertificado(null, true);
        janelaSelecionaCertificado.setVisible(true);
        if (!janelaSelecionaCertificado.isCancelado()) {
            SelecionaCertificado.carregaDadosCertificado();
            JOptionPane.showMessageDialog(null, "Certificado carregado com sucesso.", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void statusServico() {
        try {
            StatusServico statusNfe = new StatusServico();
            String resposta = statusNfe.verificaStatusServico(certificado, certificado.aliases().nextElement(), senhaCertificado);
            JOptionPane.showMessageDialog(null, resposta, "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar o status do serviço.\n" + e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }
}
