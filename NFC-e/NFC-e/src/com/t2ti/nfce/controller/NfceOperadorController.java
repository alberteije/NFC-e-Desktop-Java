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

import com.t2ti.pafecf.infra.Biblioteca;
import com.t2tierp.nfce.java.NfceOperadorVO;
import javax.persistence.Query;

public class NfceOperadorController extends NfceControllerGenerico<NfceOperadorVO>{
    
    public NfceOperadorVO getBean(String usuario, String senha) throws Exception {
        abreConexao();
        String senhaCript = Biblioteca.md5String(usuario + senha);
        Query query = em.createQuery("SELECT o FROM NfceOperadorVO o WHERE o.login = :login AND o.senha = :senha");
        query.setParameter("login", usuario);
        query.setParameter("senha", senha);
        NfceOperadorVO bean = (NfceOperadorVO) query.getSingleResult();
        fechaConexao();
        return bean;
    }
    
}
