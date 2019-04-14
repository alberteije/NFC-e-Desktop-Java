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

public class Constantes {

    public static final String ARQUIVO_CONEXAO_BD = System.getProperty("user.dir") + System.getProperty("file.separator") + "conexao.properties";
    public static final String ARQUIVO_AUXILIAR = System.getProperty("user.dir") + System.getProperty("file.separator") + "arquivoauxiliar.properties";
    public static final String DIRETORIO_APLICACAO = System.getProperty("user.dir") + System.getProperty("file.separator");
    public static final String DIRETORIO_INTEGRACAO_LOCAL = System.getProperty("user.dir") + System.getProperty("file.separator") + "integracao" + System.getProperty("file.separator");
    public static String DIRETORIO_INTEGRACAO_REMOTO = System.getProperty("user.dir") + System.getProperty("file.separator") + "integracao" + System.getProperty("file.separator");
    public static final int QUANTIDADE_POR_PAGINA = 50;
    public static int DECIMAIS_QUANTIDADE = 3;
    public static int DECIMAIS_VALOR = 2;
    public static int EMPRESA_BALCAO = 1;

    public static final String CHAVE_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----" + "\n"
            + "MIICWwIBAAKBgQCnFwt3/HloBZfRpN+HjuCkHBp7p12fRsQ4NqEKV7apObbop1Ds" + "\n"
            + "iqbP8UVMuohrgOvlHRaXLVfwgs5e+fsbff2k0u2UjeEheN+CFWhYO+VIGDQE4+OG" + "\n"
            + "GTI8+mJy5q2aybI2MEcWriaEGerEXfWNVB8jimIDQZFrYjT0EmMuNDW1DwIDAQAB" + "\n"
            + "AoGAUoi7jYUUoyFAILoD//2/UNDg8/tz710RR4MUgPtaqrRrePhMrMrAIy7WQFRT" + "\n"
            + "VmbUHtQDeKFZMuAp+BgaxTWyt472hrVv9kYw1ehWJBu9my8AdRB9iZsC8+iP2PAF" + "\n"
            + "DDC+li0U6BLOF+TLf983fNkyUL8Fi768TiWClldoYf98w4ECQQDdzpqzptf4nx5o" + "\n"
            + "77nHQ/tEbQBqCy51C6jAKwEtyo2aiKtTxiU7+X2sGYe1QX7xp6lVVQJw5enG7D8w" + "\n"
            + "m8/S03PRAkEAwNkWSkebSPcg01CyDX7MPLxzp+mpBtbHgdV/tGk75BOxbHgr/+co" + "\n"
            + "4qPe+W8Y6atvD+V4++EVmYG9oNkKPVUy3wJAfOWEmRqezCGVNxOd8cWm7B3QuOW4" + "\n"
            + "8DFzgVn13PoMiHLivlZ5yu6uxETP9NF/kMWPBTzMFhRwchG/dziVaqqksQJALN8W" + "\n"
            + "OahZsGuYHrMrCsrTGNq8inhq1OWKvGIB+ed2gQY4BUYc7slJkRqSGjXIdS5nhCpf" + "\n"
            + "sTPZQ8OVbOSzZjv5vwJACaNs2spXcmF1xS1/2KoKqd71faRwWbC+ruA+2esBmout" + "\n"
            + "pjiFmDoMmDWzuMBIl/DjvSyaQlLwcqluWv7D1OcNTg==" + "\n"
            + "-----END RSA PRIVATE KEY-----";

    public static final String CHAVE_PUBLICA = "-----BEGIN PUBLIC KEY-----" + "\n"
            + "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnFwt3/HloBZfRpN+HjuCkHBp7" + "\n"
            + "p12fRsQ4NqEKV7apObbop1DsiqbP8UVMuohrgOvlHRaXLVfwgs5e+fsbff2k0u2U" + "\n"
            + "jeEheN+CFWhYO+VIGDQE4+OGGTI8+mJy5q2aybI2MEcWriaEGerEXfWNVB8jimID" + "\n"
            + "QZFrYjT0EmMuNDW1DwIDAQAB" + "\n"
            + "-----END PUBLIC KEY-----";

}
