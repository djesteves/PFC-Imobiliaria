/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Modelo.Agenda;

/**
 *
 * @author Diego
 */
public class NewClass {
    public static void main(String[] args) {
        
            Agenda agenda = new Agenda();
            //System.out.println("aq" +ag.getAgendamento().getUsuario().getNome());
            
            agenda.getAgendamento().getUsuario().setId_usuario(1);

            //ag.getAgendamento().getUsuario().setNome("d");

    }
}
