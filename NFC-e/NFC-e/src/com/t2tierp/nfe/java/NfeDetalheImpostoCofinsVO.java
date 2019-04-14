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
package com.t2tierp.nfe.java;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "NFE_DETALHE_IMPOSTO_COFINS")
public class NfeDetalheImpostoCofinsVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CST_COFINS")
    private String cstCofins;
    @Column(name = "QUANTIDADE_VENDIDA")
    private BigDecimal quantidadeVendida;
    @Column(name = "BASE_CALCULO_COFINS")
    private BigDecimal baseCalculoCofins;
    @Column(name = "ALIQUOTA_COFINS_PERCENTUAL")
    private BigDecimal aliquotaCofinsPercentual;
    @Column(name = "ALIQUOTA_COFINS_REAIS")
    private BigDecimal aliquotaCofinsReais;
    @Column(name = "VALOR_COFINS")
    private BigDecimal valorCofins;
    @JoinColumn(name = "ID_NFE_DETALHE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private NfeDetalheVO nfeDetalhe;

    public NfeDetalheImpostoCofinsVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCstCofins() {
        return cstCofins;
    }

    public void setCstCofins(String cstCofins) {
        this.cstCofins = cstCofins;
    }

    public BigDecimal getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(BigDecimal quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    public BigDecimal getBaseCalculoCofins() {
        return baseCalculoCofins;
    }

    public void setBaseCalculoCofins(BigDecimal baseCalculoCofins) {
        this.baseCalculoCofins = baseCalculoCofins;
    }

    public BigDecimal getAliquotaCofinsPercentual() {
        return aliquotaCofinsPercentual;
    }

    public void setAliquotaCofinsPercentual(BigDecimal aliquotaCofinsPercentual) {
        this.aliquotaCofinsPercentual = aliquotaCofinsPercentual;
    }

    public BigDecimal getAliquotaCofinsReais() {
        return aliquotaCofinsReais;
    }

    public void setAliquotaCofinsReais(BigDecimal aliquotaCofinsReais) {
        this.aliquotaCofinsReais = aliquotaCofinsReais;
    }

    public BigDecimal getValorCofins() {
        return valorCofins;
    }

    public void setValorCofins(BigDecimal valorCofins) {
        this.valorCofins = valorCofins;
    }

    public NfeDetalheVO getNfeDetalhe() {
        return nfeDetalhe;
    }

    public void setNfeDetalhe(NfeDetalheVO nfeDetalhe) {
        this.nfeDetalhe = nfeDetalhe;
    }


    @Override
    public String toString() {
        return "com.t2tierp.nfe.java.NfeDetalheImpostoCofinsVO[id=" + id + "]";
    }

}
