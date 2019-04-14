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
package com.t2tierp.cadastros.java;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.openswing.swing.message.receive.java.ValueObjectImpl;


@Entity
@Table(name = "NIVEL_FORMACAO")
public class NivelFormacaoVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "DESCRICAO")
    private String descricao;
    @Column(name = "GRAU_INSTRUCAO_CAGED")
    private Integer grauInstrucaoCaged;
    @Column(name = "GRAU_INSTRUCAO_SEFIP")
    private Integer grauInstrucaoSefip;
    @Column(name = "GRAU_INSTRUCAO_RAIS")
    private Integer grauInstrucaoRais;

    public NivelFormacaoVO() {
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getGrauInstrucaoCaged() {
        return grauInstrucaoCaged;
    }

    public void setGrauInstrucaoCaged(Integer grauInstrucaoCaged) {
        this.grauInstrucaoCaged = grauInstrucaoCaged;
    }

    public Integer getGrauInstrucaoSefip() {
        return grauInstrucaoSefip;
    }

    public void setGrauInstrucaoSefip(Integer grauInstrucaoSefip) {
        this.grauInstrucaoSefip = grauInstrucaoSefip;
    }

    public Integer getGrauInstrucaoRais() {
        return grauInstrucaoRais;
    }

    public void setGrauInstrucaoRais(Integer grauInstrucaoRais) {
        this.grauInstrucaoRais = grauInstrucaoRais;
    }


    @Override
    public String toString() {
        return "com.t2tierp.cadastros.java.NivelFormacaoVO[id=" + id + "]";
    }

}
