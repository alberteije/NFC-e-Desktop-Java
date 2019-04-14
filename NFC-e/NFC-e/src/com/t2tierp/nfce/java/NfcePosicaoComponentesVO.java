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
package com.t2tierp.nfce.java;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.openswing.swing.message.receive.java.ValueObjectImpl;


@Entity
@Table(name = "NFCE_POSICAO_COMPONENTES")
public class NfcePosicaoComponentesVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "ALTURA")
    private Integer altura;
    @Column(name = "LARGURA")
    private Integer largura;
    @Column(name = "TOPO")
    private Integer topo;
    @Column(name = "ESQUERDA")
    private Integer esquerda;
    @Column(name = "TAMANHO_FONTE")
    private Integer tamanhoFonte;
    @Column(name = "TEXTO")
    private String texto;
    @JoinColumn(name = "ID_NFCE_RESOLUCAO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private NfceResolucaoVO nfceResolucao;

    public NfcePosicaoComponentesVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAltura() {
        return altura;
    }

    public void setAltura(Integer altura) {
        this.altura = altura;
    }

    public Integer getLargura() {
        return largura;
    }

    public void setLargura(Integer largura) {
        this.largura = largura;
    }

    public Integer getTopo() {
        return topo;
    }

    public void setTopo(Integer topo) {
        this.topo = topo;
    }

    public Integer getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(Integer esquerda) {
        this.esquerda = esquerda;
    }

    public Integer getTamanhoFonte() {
        return tamanhoFonte;
    }

    public void setTamanhoFonte(Integer tamanhoFonte) {
        this.tamanhoFonte = tamanhoFonte;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public NfceResolucaoVO getNfceResolucao() {
        return nfceResolucao;
    }

    public void setNfceResolucao(NfceResolucaoVO nfceResolucao) {
        this.nfceResolucao = nfceResolucao;
    }


    @Override
    public String toString() {
        return "com.t2tierp.nfce.java.NfcePosicaoComponentesVO[id=" + id + "]";
    }

}
