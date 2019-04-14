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

import com.t2ti.nfce.bd.NfceAcessoBanco;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;

public class NfceControllerGenerico<T> {

    private final NfceAcessoBanco acessoBanco;
    protected EntityManager em;

    public NfceControllerGenerico() {
        acessoBanco = new NfceAcessoBanco();
    }

    protected void abreConexao() {
        try {
            em = acessoBanco.conexaoRetaguarda();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao conectar ao banco de dados.\n" + ex.getMessage() + "\n" + ex.getCause().getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected void fechaConexao() {
        try {
            acessoBanco.desconectarRetaguarda();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao confirmar a transação no banco de dados.\n" + ex.getMessage() + "\n" + ex.getCause().getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void salvar(T bean) throws Exception {
        abreConexao();
        em.persist(bean);
        fechaConexao();
    }

    public void atualizar(T bean) throws Exception {
        abreConexao();
        em.merge(bean);
        fechaConexao();
    }

    public void excluir(T bean) throws Exception {
        abreConexao();
        bean = em.merge(bean);
        em.remove(bean);
        fechaConexao();
    }

    public void excluir(T bean, Integer id) throws Exception {
        abreConexao();
        em.remove(em.getReference(bean.getClass(), id));
        fechaConexao();
    }

    public T getBean(Integer id, Class<T> clazz) throws Exception {
        abreConexao();
        T bean = em.find(clazz, id);
        fechaConexao();
        return bean;
    }

    public T getBean(Class<T> clazz, String atributo, Object valor) throws Exception {
        List<T> beans = getBeans(clazz, atributo, valor);
        if (beans.isEmpty()) {
            return null;
        } else {
            return beans.get(0);
        }
    }

    public List<T> getBeans(Class<T> clazz) throws Exception {
        abreConexao();
        CriteriaQuery<T> criteria = em.getCriteriaBuilder().createQuery(clazz);
        Root<T> bean = criteria.from(clazz);
        criteria.select(bean);
        List<T> beans = em.createQuery(criteria).getResultList();
        fechaConexao();
        return beans;
    }

    public List<T> getBeans(Class<T> clazz, String atributo, Object valor) throws Exception {
        abreConexao();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(clazz);
        Root<T> root = criteria.from(clazz);
        criteria.select(root);

        if (valor.getClass() == String.class) {
            Path<String> nome = root.get(atributo);
            criteria.where(builder.equal(nome, valor));
        } else if (valor.getClass() == Integer.class) {
            Path<Integer> nome = root.get(atributo);
            criteria.where(builder.equal(nome, valor));
        } else if (valor.getClass() == Date.class) {
            Path<Date> nome = root.get(atributo);
            criteria.where(builder.equal(nome, valor));
        } else if (valor.getClass() == BigDecimal.class) {
            Path<BigDecimal> nome = root.get(atributo);
            criteria.where(builder.equal(nome, valor));
        }

        TypedQuery<T> query = em.createQuery(criteria);
        List<T> beans = query.getResultList();
        fechaConexao();
        return beans;
    }
  
}
