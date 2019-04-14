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
package com.t2ti.pafecf.infra;

public interface Tipos {

    /*
     0-aberto 
     1-venda em andamento 
     2-venda em recuperação ou importação de PV/DAV 
     3-So Consulta 
     4-Usuario cancelou a tela Movimento Aberto 
     5-Informando dados de NF
     */
    public static final int SC_ABERTO = 0;
    public static final int SC_VENDA_EM_ANDAMENTO = 1;
    public static final int SC_VENDA_RECUPERADA_DAV_PREVENDA = 2;
    public static final int SC_SOMENTE_CONSULTA = 3;
    public static final int SC_USUARIO_CANCELOU_TELA_MOVIMENTO = 4;
    public static final int SC_INFORMANDO_DADOS_NF = 5;

    public final int NAO = 0;
    public static final int SIM = 1;

    public static final int PESQUISA_CODIGO_BALANCA = 1;
    public static final int PESQUISA_GTIN = 2;
    public static final int PESQUISA_CODIGO_INTERNO = 3;
    public static final int PESQUISA_ID = 4;
    
    public static final int DESCONTO_VALOR = 0;
    public static final int DESCONTO_PERCENTUAL = 1;
    public static final int ACRESCIMO_VALOR = 2;
    public static final int ACRESCIMO_PERCENTUAL = 3;
    public static final int CANCELA_DESCONTO_ACRESCIMO = 4;
    
}
