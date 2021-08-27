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
    private Date dataSolicitacao;
    private String status;
    private String situacao;
    private Usuario usuario;
    private Usuario usuarioCorretor;
    private Imovel imovel;

    public Agendamento() {
        this.usuario = new Usuario();
        this.imovel = new Imovel();
        this.usuarioCorretor = new Usuario();
    }

    public Agendamento(int id_agendamento, Date dataAgendamento, Date dataSolicitacao, String status, String situacao, Usuario usuario, Usuario usuarioCorretor, Imovel imovel) {
        this.id_agendamento = id_agendamento;
        this.dataAgendamento = dataAgendamento;
        this.dataSolicitacao = dataSolicitacao;
        this.status = status;
        this.situacao = situacao;
        this.usuario = usuario;
        this.usuarioCorretor = usuarioCorretor;
        this.imovel = imovel;
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

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
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

    public Usuario getUsuarioCorretor() {
        return usuarioCorretor;
    }

    public void setUsuarioCorretor(Usuario usuarioCorretor) {
        this.usuarioCorretor = usuarioCorretor;
    }

}
