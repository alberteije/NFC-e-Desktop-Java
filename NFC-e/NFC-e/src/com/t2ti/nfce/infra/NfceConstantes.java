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

public class NfceConstantes {

    public static final String ARQUIVO_DADOS_CERTIFICADO = System.getProperty("user.dir") + System.getProperty("file.separator") + "certificado.properties";
    public static final String ARQUIVO_DADOS_TOKEN = System.getProperty("user.dir") + System.getProperty("file.separator") + "token.properties";
    public static final String DIRETORIO_APLICACAO = System.getProperty("user.dir") + System.getProperty("file.separator");
    public static final String DIRETORIO_ARQUIVOS_XML = DIRETORIO_APLICACAO + "nfe" + System.getProperty("file.separator") + "xml" + System.getProperty("file.separator");
    public static final String DIRETORIO_ESQUEMAS_XML = DIRETORIO_APLICACAO + "nfe" + System.getProperty("file.separator") + "schemas" + System.getProperty("file.separator");

    //Status da Nota
    //0 - Em Edição | 1 - Salva | 2 - Validada |  3 - Assinada | 4 - Autorizada | 5 - Inutilizada | 6 - Cancelada
    public static final int SN_EM_EDICAO = 0;
    public static final int SN_SALVA = 1;
    public static final int SN_VALIDADA = 2;
    public static final int SN_ASSINADA = 3;
    public static final int SN_AUTORIZADA = 4;
    public static final int SN_INUTILIZADA = 5;
    public static final int SN_CANCELADA = 6;    
    
    public static final int OP_RECUPERA_VENDA = 0;
    public static final int OP_CANCELA_NFCE = 1;
    public static final int OP_INUTILIZA_NUMERO = 2;
}
