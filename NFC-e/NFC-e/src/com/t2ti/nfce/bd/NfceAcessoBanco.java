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
package com.t2ti.nfce.bd;

import com.t2ti.pafecf.infra.Constantes;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

public class NfceAcessoBanco {

    private static EntityManagerFactory factoryRetaguarda;
    private EntityManager emRetaguarda;

    static {
        try {
            Map cfg = new HashMap<>();
            Properties arquivoConexao = new Properties();
            arquivoConexao.load(new FileInputStream(new File(Constantes.ARQUIVO_CONEXAO_BD)));

            cfg.put("javax.persistence.jdbc.driver", arquivoConexao.getProperty("sgbd.retaguarda.driver"));
            cfg.put("javax.persistence.jdbc.url", arquivoConexao.getProperty("sgbd.retaguarda.url"));
            cfg.put("javax.persistence.jdbc.user", arquivoConexao.getProperty("sgbd.retaguarda.user"));
            cfg.put("javax.persistence.jdbc.password", arquivoConexao.getProperty("sgbd.retaguarda.password"));

            factoryRetaguarda = Persistence.createEntityManagerFactory("t2tierp", cfg);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível abrir o arquivo de configuração do banco de dados.", "Erro do sistema", JOptionPane.ERROR_MESSAGE);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao configurar o banco de dados.\n" + ex.getMessage(), "Erro do sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public EntityManager conexaoRetaguarda() throws Exception {
        emRetaguarda = factoryRetaguarda.createEntityManager();
        emRetaguarda.getTransaction().begin();
        return emRetaguarda;
    }

    public void desconectarRetaguarda() throws Exception {
        if (emRetaguarda != null && emRetaguarda.isOpen()) {
            try {
                emRetaguarda.getTransaction().commit();
            } catch (Exception e) {
                emRetaguarda.getTransaction().rollback();
                throw e;
            } finally {
                emRetaguarda.close();
            }
        }
    }
}
