/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Diego
 */
public class Agenda {

    private int id_agenda;
    private Usuario usuarioCorretor;
    private Agendamento agendamento;

    public Agenda() {
        this.agendamento = new Agendamento();
        this.usuarioCorretor = new Usuario();
    }

    public Agenda(int id_agenda, Usuario usuarioCorretor, Agendamento agendamento) {
        this.id_agenda = id_agenda;
        this.usuarioCorretor = usuarioCorretor;
        this.agendamento = agendamento;
    }

    public int getId_agenda() {
        return id_agenda;
    }

    public void setId_agenda(int id_agenda) {
        this.id_agenda = id_agenda;
    }

    public Usuario getUsuarioCorretor() {
        return usuarioCorretor;
    }

    public void setUsuarioCorretor(Usuario usuarioCorretor) {
        this.usuarioCorretor = usuarioCorretor;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

}
