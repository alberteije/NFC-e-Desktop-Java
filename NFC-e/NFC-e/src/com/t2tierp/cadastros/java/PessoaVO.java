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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.openswing.swing.message.receive.java.ValueObjectImpl;


@Entity
@Table(name = "PESSOA")
public class PessoaVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "SITE")
    private String site;
    @Column(name = "CLIENTE")
    private String cliente;
    @Column(name = "FORNECEDOR")
    private String fornecedor;
    @Column(name = "COLABORADOR")
    private String colaborador;
    @Column(name = "TRANSPORTADORA")
    private String transportadora;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="EMPRESA_PESSOA", joinColumns={@JoinColumn(name="ID_PESSOA")}, inverseJoinColumns={@JoinColumn(name="ID_EMPRESA")})
    private List<EmpresaVO> listaEmpresa;
    @OneToOne(fetch=FetchType.EAGER, mappedBy="pessoa", cascade = CascadeType.ALL)
    private PessoaFisicaVO pessoaFisica;
    @OneToOne(fetch=FetchType.EAGER, mappedBy="pessoa", cascade = CascadeType.ALL)
    private PessoaJuridicaVO pessoaJuridica;
    @OneToMany(mappedBy="pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<PessoaEnderecoVO> listaEndereco;
    @OneToMany(mappedBy="pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<PessoaContatoVO> listaContato;
    @OneToMany(mappedBy="pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<PessoaTelefoneVO> listaTelefone;

    public PessoaVO() {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getColaborador() {
        return colaborador;
    }

    public void setColaborador(String colaborador) {
        this.colaborador = colaborador;
    }

    public String getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(String transportadora) {
        this.transportadora = transportadora;
    }

   public List<EmpresaVO> getListaEmpresa() {
        return listaEmpresa;
    }

    public void setListaEmpresa(List<EmpresaVO> listaEmpresa) {
        this.listaEmpresa = listaEmpresa;
    }

    public PessoaFisicaVO getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisicaVO pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

    public PessoaJuridicaVO getPessoaJuridica() {
        return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridicaVO pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }

    public List<PessoaEnderecoVO> getListaEndereco() {
        return listaEndereco;
    }

    public void setListaEndereco(List<PessoaEnderecoVO> listaEndereco) {
        this.listaEndereco = listaEndereco;
    }

    public List<PessoaContatoVO> getListaContato() {
        return listaContato;
    }

    public void setListaContato(List<PessoaContatoVO> listaContato) {
        this.listaContato = listaContato;
    }

    public List<PessoaTelefoneVO> getListaTelefone() {
        return listaTelefone;
    }

    public void setListaTelefone(List<PessoaTelefoneVO> listaTelefone) {
        this.listaTelefone = listaTelefone;
    }
    
    @Override
    public String toString() {
        return "com.t2tierp.cadastros.java.PessoaVO[id=" + id + "]";
    }

}
