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
@Table(name = "NFCE_CONFIGURACAO_BALANCA")
public class NfceConfiguracaoBalancaVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "MODELO")
    private Integer modelo;
    @Column(name = "IDENTIFICADOR")
    private String identificador;
    @Column(name = "HAND_SHAKE")
    private Integer handShake;
    @Column(name = "PARITY")
    private Integer parity;
    @Column(name = "STOP_BITS")
    private Integer stopBits;
    @Column(name = "DATA_BITS")
    private Integer dataBits;
    @Column(name = "BAUD_RATE")
    private Integer baudRate;
    @Column(name = "PORTA")
    private String porta;
    @Column(name = "TIMEOUT")
    private Integer timeout;
    @Column(name = "TIPO_CONFIGURACAO")
    private String tipoConfiguracao;
    @JoinColumn(name = "ID_NFCE_CONFIGURACAO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private NfceConfiguracaoVO nfceConfiguracao;

    public NfceConfiguracaoBalancaVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModelo() {
        return modelo;
    }

    public void setModelo(Integer modelo) {
        this.modelo = modelo;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Integer getHandShake() {
        return handShake;
    }

    public void setHandShake(Integer handShake) {
        this.handShake = handShake;
    }

    public Integer getParity() {
        return parity;
    }

    public void setParity(Integer parity) {
        this.parity = parity;
    }

    public Integer getStopBits() {
        return stopBits;
    }

    public void setStopBits(Integer stopBits) {
        this.stopBits = stopBits;
    }

    public Integer getDataBits() {
        return dataBits;
    }

    public void setDataBits(Integer dataBits) {
        this.dataBits = dataBits;
    }

    public Integer getBaudRate() {
        return baudRate;
    }

    public void setBaudRate(Integer baudRate) {
        this.baudRate = baudRate;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public String getTipoConfiguracao() {
        return tipoConfiguracao;
    }

    public void setTipoConfiguracao(String tipoConfiguracao) {
        this.tipoConfiguracao = tipoConfiguracao;
    }

    public NfceConfiguracaoVO getNfceConfiguracao() {
        return nfceConfiguracao;
    }

    public void setNfceConfiguracao(NfceConfiguracaoVO nfceConfiguracao) {
        this.nfceConfiguracao = nfceConfiguracao;
    }


    @Override
    public String toString() {
        return "com.t2tierp.nfce.java.NfceConfiguracaoBalancaVO[id=" + id + "]";
    }

}
