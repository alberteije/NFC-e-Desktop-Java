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
package com.t2ti.nfce.controller;

import com.t2ti.pafecf.infra.Tipos;
import com.t2tierp.cadastros.java.ProdutoVO;
import java.util.List;
import javax.persistence.Query;

public class ProdutoController extends NfceControllerGenerico<ProdutoVO> implements Tipos {

    public ProdutoVO consultaPorTipo(String codigo, int tipo) {
        try {
            switch (tipo) {
                case PESQUISA_CODIGO_BALANCA: {
                    return getBean(ProdutoVO.class, "codigoBalanca", codigo);
                }
                case PESQUISA_GTIN: {
                    return getBean(ProdutoVO.class, "gtin", codigo);
                }
                case PESQUISA_CODIGO_INTERNO: {
                    return getBean(ProdutoVO.class, "codigoInterno", codigo);
                }
                case PESQUISA_ID: {
                    return getBean(Integer.valueOf(codigo), ProdutoVO.class);
                }
                default: {
                    return null;
                }
            }
        } catch (Exception ex) {
            return null;
        }
    }

    public List<ProdutoVO> consultaPorNome(String nome) throws Exception {
        abreConexao();
        Query query = em.createQuery("SELECT p FROM ProdutoVO p WHERE p.nome like :nome");
        query.setParameter("nome", "%" + nome + "%");
        List<ProdutoVO> produtos = query.getResultList();
        fechaConexao();
        return produtos;
    }
    
    public List<ProdutoVO> consultaPorFaixaId(Integer id1, Integer id2) throws Exception {
        abreConexao();
        Query query = em.createQuery("SELECT p FROM ProdutoVO p WHERE p.id BETWEEN :id1 AND :id2");
        query.setParameter("id1", id1);
        query.setParameter("id2", id2);
        List<ProdutoVO> produtos = query.getResultList();
        fechaConexao();
        return produtos;
    }

    public List<ProdutoVO> consultaPorNome(String nome1, String nome2) throws Exception {
        abreConexao();
        Query query = em.createQuery("SELECT p FROM ProdutoVO p WHERE p.nome LIKE :nome1 or p.nome LIKE :nome2");
        query.setParameter("nome1", "%" + nome1 + "%");
        query.setParameter("nome2", "%" + nome2 + "%");
        List<ProdutoVO> produtos = query.getResultList();
        fechaConexao();
        return produtos;
    }
    
    public List<ProdutoVO> consultaPorNomeProducaoPropria(String nome) throws Exception {
        abreConexao();
        Query query = em.createQuery("SELECT p FROM ProdutoVO p WHERE p.nome like :nome AND p.ippt = 'P'");
        query.setParameter("nome", "%" + nome + "%");
        List<ProdutoVO> produtos = query.getResultList();
        fechaConexao();
        return produtos;
    }
    
}
