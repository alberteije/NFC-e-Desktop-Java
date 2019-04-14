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
@Table(name = "NFCE_CONFIGURACAO_LEITOR_SER")
public class NfceConfiguracaoLeitorSerVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "USA")
    private String usa;
    @Column(name = "PORTA")
    private String porta;
    @Column(name = "BAUD")
    private Integer baud;
    @Column(name = "HAND_SHAKE")
    private Integer handShake;
    @Column(name = "PARITY")
    private Integer parity;
    @Column(name = "STOP_BITS")
    private Integer stopBits;
    @Column(name = "DATA_BITS")
    private Integer dataBits;
    @Column(name = "INTERVALO")
    private Integer intervalo;
    @Column(name = "USAR_FILA")
    private String usarFila;
    @Column(name = "HARD_FLOW")
    private String hardFlow;
    @Column(name = "SOFT_FLOW")
    private String softFlow;
    @Column(name = "SUFIXO")
    private String sufixo;
    @Column(name = "EXCLUIR_SUFIXO")
    private String excluirSufixo;
    @JoinColumn(name = "ID_NFCE_CONFIGURACAO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private NfceConfiguracaoVO nfceConfiguracao;

    public NfceConfiguracaoLeitorSerVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsa() {
        return usa;
    }

    public void setUsa(String usa) {
        this.usa = usa;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    public Integer getBaud() {
        return baud;
    }

    public void setBaud(Integer baud) {
        this.baud = baud;
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

    public Integer getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(Integer intervalo) {
        this.intervalo = intervalo;
    }

    public String getUsarFila() {
        return usarFila;
    }

    public void setUsarFila(String usarFila) {
        this.usarFila = usarFila;
    }

    public String getHardFlow() {
        return hardFlow;
    }

    public void setHardFlow(String hardFlow) {
        this.hardFlow = hardFlow;
    }

    public String getSoftFlow() {
        return softFlow;
    }

    public void setSoftFlow(String softFlow) {
        this.softFlow = softFlow;
    }

    public String getSufixo() {
        return sufixo;
    }

    public void setSufixo(String sufixo) {
        this.sufixo = sufixo;
    }

    public String getExcluirSufixo() {
        return excluirSufixo;
    }

    public void setExcluirSufixo(String excluirSufixo) {
        this.excluirSufixo = excluirSufixo;
    }

    public NfceConfiguracaoVO getNfceConfiguracao() {
        return nfceConfiguracao;
    }

    public void setNfceConfiguracao(NfceConfiguracaoVO nfceConfiguracao) {
        this.nfceConfiguracao = nfceConfiguracao;
    }


    @Override
    public String toString() {
        return "com.t2tierp.nfce.java.NfceConfiguracaoLeitorSerVO[id=" + id + "]";
    }

}
