/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author Diego
 */
public class Agendamento {

    private int id_agendamento;
    private Date dataAgendamento;
    private String status;
    private String situacao;
    private Usuario usuario;
    private Imovel imovel;

    public Agendamento(int id_agendamento, Date dataAgendamento, String status, String situacao, Usuario usuario, Imovel imovel) {
        this.id_agendamento = id_agendamento;
        this.dataAgendamento = dataAgendamento;
        this.status = status;
        this.situacao = situacao;
        this.usuario = usuario;
        this.imovel = imovel;
    }
    
    public Agendamento() {
    }

    public int getId_agendamento() {
        return id_agendamento;
    }

    public void setId_agendamento(int id_agendamento) {
        this.id_agendamento = id_agendamento;
    }

    public Date getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

}
